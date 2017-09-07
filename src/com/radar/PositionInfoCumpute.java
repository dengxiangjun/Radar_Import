package com.radar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 计算雷达信息，
 * @author deng
 *
 */
public class PositionInfoCumpute extends Thread {

	private Num num;
	private int filename;
	private double radarLat;
	private double radarLong;

	private static ThreadLocal<PositionDao> positionDao = new ThreadLocal<PositionDao>() {
		public PositionDao initialValue() {
			String dbPath = Config.dbPath;
		 	try {
				return new PositionDao(dbPath);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	};

	public PositionInfoCumpute(Num num, int filename, double radarLat,
			double radarLong) {
		super();
		this.num = num;
		this.filename = filename;
		this.radarLat = radarLat;
		this.radarLong = radarLong;
	}

	@Override
	public void run() {

		File file = new File(Config.mapPath + Config.seperator + filename);// 地图数据路径（CSV格式）
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
			// reader.readLine();
			String line = null;
			int pointCnt = 0;
			StringBuilder sqlBatch = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				String[] lineArr = line.split(",");
				// int batchCnt = 0;
				if (lineArr.length == 3) {
					Double latitude = Double.valueOf(lineArr[0]);
					Double longitude = Double.valueOf(lineArr[1]);// 转化为Double
					Double altitude = Double.valueOf(lineArr[2]);

					double distance = Distance.getDistance(radarLat, radarLong,
							latitude, longitude);// 计算雷达与当前点的距离
					double dx = longitude - radarLong, dy = latitude - radarLat;// 经度差，纬度差

					double direction = Direction_calculator.calculateDirection(
							latitude, longitude, radarLat, radarLong);// 计算方位角
					String sql = "insert into Position(longitude,latitude,altitude,distance,dx,dy,direction) values("
							+ longitude
							+ ","
							+ latitude
							+ ","
							+ altitude
							+ ","
							+ distance
							+ ","
							+ dx
							+ ","
							+ dy
							+ ","
							+ direction
							+ ");";
					sqlBatch.append(sql);
					pointCnt++;

					System.out.println("信息：" + num.inc() + ",direction："
							+ direction + ",distance:" + distance);
				} else
					continue;
				if (pointCnt > 0 && pointCnt % 500 == 0) {
					positionDao.get().batchInsertPosition(sqlBatch.toString());
					sqlBatch = new StringBuilder();
					pointCnt = 0;
				}
			}
			if (sqlBatch.length() > 10)
				positionDao.get().batchInsertPosition(sqlBatch.toString());
			positionDao.get().close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
