package com.radar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 导入雷达数据 多线程版
 * 
 * @author deng
 * 
 */
public class Main {
	public static void main(String[] args) throws Exception {
		String dbPath = Config.dbPath;// 数据库存储路径
		PositionDao positionDao = new PositionDao(dbPath);
		positionDao.initDB();

		final double radarLong = Config.radarLong;// 雷达经度
		final double radarLat = Config.radarLat;// 雷达纬度
		double radarAltitude = Config.radarAltitude;// 雷达高程
		double radarToGroud = Config.radarToGroud;// 雷达天线距地面高度
		Num num = new Num(0);// 线程安全的计数器

		positionDao.insertRadarInfo(radarLong, radarLat, radarAltitude,
				radarToGroud);// 插入雷达信息
		List<PositionInfoCumpute> threads = new ArrayList<PositionInfoCumpute>();

		for (int filename = 1; filename < 10; filename++) {//每个线程计算一个地图数据分片，分片名称为1,2,3,...,9
			PositionInfoCumpute radarInfoCompute = new PositionInfoCumpute(num,
					filename, radarLat, radarLong);
			radarInfoCompute.start();
			threads.add(radarInfoCompute);
		}
		try {
			for (PositionInfoCumpute item : threads) {
				item.join();//等待子线程执行完毕
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out
				.println("主线程执行---------------------------------------------");

		positionDao.close();
		List<Double> hList = HFactory.getHList();// 高度集合
		ExecutorService radarPowerThreadPool = Executors
				.newFixedThreadPool(hList.size());
		for (int i = 0; i < hList.size(); i++) {
			double currentH = hList.get(i);// 当前高度H
			Thread radarPowerCompute = new RadarPowerCompute(radarAltitude,
					radarToGroud, currentH, Config.LEARN_STEP);// 计算雷达威力线程
			radarPowerThreadPool.execute(radarPowerCompute);
		}
		radarPowerThreadPool.shutdown();
		radarPowerThreadPool.awaitTermination(Integer.MAX_VALUE,
				TimeUnit.SECONDS);// 主线程等待所有子线程结束
	}
}
