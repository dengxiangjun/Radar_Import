package com.radar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

/**
 * �����״���Ϣ��
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

		File file = new File(Config.mapPath + Config.seperator + filename);// ��ͼ����·����CSV��ʽ��
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
					Double longitude = Double.valueOf(lineArr[1]);// ת��ΪDouble
					Double altitude = Double.valueOf(lineArr[2]);

					double distance = Distance.getDistance(radarLat, radarLong,
							latitude, longitude);// �����״��뵱ǰ��ľ���
					double dx = longitude - radarLong, dy = latitude - radarLat;// ���Ȳγ�Ȳ�

					double direction = Direction_calculator.calculateDirection(
							latitude, longitude, radarLat, radarLong);// ���㷽λ��
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

					System.out.println("��Ϣ��" + num.inc() + ",direction��"
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
