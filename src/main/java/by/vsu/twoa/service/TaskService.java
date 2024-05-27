package by.vsu.twoa.service;

import by.vsu.twoa.dao.DaoException;
import by.vsu.twoa.dao.TaskDao;
import by.vsu.twoa.dao.UserDao;
import by.vsu.twoa.domain.Task;
import by.vsu.twoa.domain.User;
import by.vsu.twoa.geometry.Circle;
import by.vsu.twoa.geometry.Point;
import by.vsu.twoa.geometry.Segment;
import by.vsu.twoa.geometry.Vector;
import by.vsu.twoa.service.exception.RadiusTooShortException;
import by.vsu.twoa.service.exception.ServiceException;
import by.vsu.twoa.service.exception.TaskNotExistsException;
import by.vsu.twoa.service.exception.UserNotExistsException;

import java.util.Date;
import java.util.List;

public class TaskService {
	private TaskDao taskDao;
	private UserDao userDao;

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<Task> findByOwner(Integer id) throws ServiceException {
		try {
			User owner = userDao.read(id).orElseThrow(() -> new UserNotExistsException(id));
			List<Task> tasks = taskDao.readByOwner(id);
			tasks.forEach(task -> task.setOwner(owner));
			return tasks;
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public Task findById(Integer id) throws ServiceException {
		try {
			Task task = taskDao.read(id).orElseThrow(() -> new TaskNotExistsException(id));
			task.setOwner(userDao.read(task.getOwner().getId()).orElseThrow(() -> new UserNotExistsException(id)));
			Point a = task.getPoint1();
			Point b = task.getPoint2();
			double radius = task.getRadius();
			Segment chord = new Segment(a, b);
			double chordLength = chord.length();
			if(chordLength <= 2 * radius) {
				double h = Math.sqrt(radius * radius - chordLength * chordLength / 4);
				Point middle = chord.middle();
				Vector v = new Vector(middle, a);
				v.multiply(h / v.length());
				v = v.rotate(90);
				task.setCircleVariant1(new Circle(v.put(middle), radius));
				v = v.multiply(-1);
				task.setCircleVariant2(new Circle(v.put(middle), radius));
			} else {
				throw new RadiusTooShortException();
			}
			return task;
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	public Integer save(Task task) throws ServiceException {
		try {
			if(task.getId() == null) {
				task.setCreated(new Date(0));
				return taskDao.create(task);
			} else {
				throw new RuntimeException("Update operation not implemented yet");
			}
		} catch(DaoException e) {
			throw new ServiceException(e);
		}
	}
}
