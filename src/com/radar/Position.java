package com.radar;

/**
 * 位置�?
 * 
 * @author deng
 */
public class Position implements Comparable<Position> {

	private int id;
	private double longitude;
	private double latitude;
	private double altitude;
	private double distance;// 与雷达的距离
	private double dx;// 经度差
	private double dy;// 纬度差
	private double direction;// 方位角

	public Position(double distance) {
		super();
		this.distance = distance;
	}

	public Position(int id, double longitude, double latitude, double altitude,
			double distance, double direction) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.distance = distance;
		this.direction = direction;
	}

	public Position(int id, double longitude, double latitude, double altitude,
			double distance, double dx, double dy, double direction) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
		this.distance = distance;
		this.dx = dx;
		this.dy = dy;
		this.direction = direction;
	}

	public Position(int id, double longitude, double latitude, double altitude) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}

	public Position(double longitude, double latitude, double altitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.altitude = altitude;
	}

	public Position() {
		super();
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public int compareTo(Position other) {
		double selfDirection = this.direction;
		double otherDirection = other.getDirection();
		double delta = otherDirection - selfDirection;
		if (delta > 0)
			return 1;
		else if (delta == 0)
			return 0;
		else
			return -1;
	}
}
