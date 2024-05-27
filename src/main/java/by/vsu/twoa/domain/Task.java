package by.vsu.twoa.domain;

import by.vsu.twoa.geometry.Circle;
import by.vsu.twoa.geometry.Point;

import java.util.Date;

public class Task extends Entity {
	private User owner;
	private String name;
	private Date created;
	private Point point1;
	private Point point2;
	private Double radius;
	private Circle circleVariant1;
	private Circle circleVariant2;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	public Double getRadius() {
		return radius;
	}

	public void setRadius(Double radius) {
		this.radius = radius;
	}

	public Circle getCircleVariant1() {
		return circleVariant1;
	}

	public void setCircleVariant1(Circle circleVariant1) {
		this.circleVariant1 = circleVariant1;
	}

	public Circle getCircleVariant2() {
		return circleVariant2;
	}

	public void setCircleVariant2(Circle circleVariant2) {
		this.circleVariant2 = circleVariant2;
	}
}
