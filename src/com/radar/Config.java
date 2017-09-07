package com.radar;
/**
 * 配置
 * 
 * @author deng
 * 
 */
public class Config {
	public static String seperator = "\\";//文件夹的分隔符，linux下为/,win下为\\
	public static String dbPath = "jdbc:sqlite:resources\\db\\radar";// 数据库存储路径
	public static String mapPath = "resources\\point";
	// 地图存储路径,该目录下存储着9个地图数据的分片，分片名称为分片名称为1,2,3,...,9。	
	public static double radarLong = 116.39164925;// 雷达经度
	public static double radarLat = 39.78909016;// 雷达纬度
	public static double radarAltitude = 35;// 雷达高程
	public static double radarToGroud = 68.4;// 雷达天线距地面高度
	public static double LEARN_STEP = 1;// 学习步长
}
