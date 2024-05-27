package by.vsu.twoa.dao;

import by.vsu.twoa.domain.Task;
import by.vsu.twoa.domain.User;
import by.vsu.twoa.geometry.Point;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao extends BaseDao<Task> {
	public List<Task> readByOwner(Integer ownerId) throws DaoException {
		String sql = "SELECT \"id\", \"owner_id\", \"name\", \"created\", \"x_1\", \"y_1\", \"x_2\", \"y_2\", \"r\" FROM \"task\" WHERE \"owner_id\" = ?";
		List<Task> tasks = new ArrayList<>();
		readWithCriteria(sql, statement -> statement.setInt(1, ownerId), tasks::add);
		return tasks;
	}

	@Override
	protected String select() {
		return "SELECT \"id\", \"owner_id\", \"name\", \"created\", \"x_1\", \"y_1\", \"x_2\", \"y_2\", \"r\" FROM \"task\" WHERE \"id\" = ?";
	}

	@Override
	protected String insert() {
		return "INSERT INTO \"task\" (\"owner_id\", \"name\",\"created\", \"x_1\", \"y_1\", \"x_2\", \"y_2\", \"r\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected Task extractEntity(ResultSet resultSet) throws SQLException {
		Task task = new Task();
		task.setId(resultSet.getInt("id"));
		task.setOwner(new User());
		task.getOwner().setId(resultSet.getInt("owner_id"));
		task.setName(resultSet.getString("name"));
		task.setCreated(new java.util.Date(resultSet.getDate("created").getTime()));
		double x1 = resultSet.getDouble("x_1");
		double y1 = resultSet.getDouble("y_1");
		task.setPoint1(new Point(x1, y1));
		double x2 = resultSet.getDouble("x_2");
		double y2 = resultSet.getDouble("y_2");
		task.setPoint2(new Point(x2, y2));
		task.setRadius(resultSet.getDouble("r"));
		return task;
	}

	@Override
	protected void fillInsertedEntity(PreparedStatement statement, Task task) throws SQLException {
		statement.setInt(1, task.getOwner().getId());
		statement.setString(2, task.getName());
		statement.setDate(3, new java.sql.Date(task.getCreated().getTime()));
		statement.setDouble(4, task.getPoint1().getX());
		statement.setDouble(5, task.getPoint1().getY());
		statement.setDouble(6, task.getPoint2().getX());
		statement.setDouble(7, task.getPoint2().getY());
		statement.setDouble(8, task.getRadius());
	}
}
