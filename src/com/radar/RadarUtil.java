package com.radar;

public class RadarUtil {

	/**
	 * 获取雷达视距
	 * @param h1
	 * @param h2
	 * @return double
	 */

	public static double CalculateDetectRadius(double h1 , double h2){
		double result;
		result = (4.12*(Math.sqrt(h1) + Math.sqrt(h2)));			
		return result;	
	}
}
