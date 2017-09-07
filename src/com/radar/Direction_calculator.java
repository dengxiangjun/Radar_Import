package com.radar;

/**
 * 方位角计算
 * @author deng
 *
 */
public class Direction_calculator {

	/**
	 * 计算方位角
	 * 
	 * @param lat1
	 * @param long1
	 * @param lat2
	 * @param long2
	 * @return double
	 */
	public static double calculateDirection(double lat1, double long1,
			double lat2, double long2) {
		double direction;
		direction = Math.atan((lat1 - lat2) / (long1 - long2));
		direction *= 180;
		direction /= Math.PI;
		double delataX = long1 - long2;
		if (delataX < 0)//第2，3象限
			direction = direction + 180;
		
		direction = direction + 90;//-90°-270°转化为0°-360°
		return direction;
	}
}
