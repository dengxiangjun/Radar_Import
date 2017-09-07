package com.radar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * �����״����� ���̰߳�
 * 
 * @author deng
 * 
 */
public class Main {
	public static void main(String[] args) throws Exception {
		String dbPath = Config.dbPath;// ���ݿ�洢·��
		PositionDao positionDao = new PositionDao(dbPath);
		positionDao.initDB();

		final double radarLong = Config.radarLong;// �״ﾭ��
		final double radarLat = Config.radarLat;// �״�γ��
		double radarAltitude = Config.radarAltitude;// �״�߳�
		double radarToGroud = Config.radarToGroud;// �״����߾����߶�
		Num num = new Num(0);// �̰߳�ȫ�ļ�����

		positionDao.insertRadarInfo(radarLong, radarLat, radarAltitude,
				radarToGroud);// �����״���Ϣ
		List<PositionInfoCumpute> threads = new ArrayList<PositionInfoCumpute>();

		for (int filename = 1; filename < 10; filename++) {//ÿ���̼߳���һ����ͼ���ݷ�Ƭ����Ƭ����Ϊ1,2,3,...,9
			PositionInfoCumpute radarInfoCompute = new PositionInfoCumpute(num,
					filename, radarLat, radarLong);
			radarInfoCompute.start();
			threads.add(radarInfoCompute);
		}
		try {
			for (PositionInfoCumpute item : threads) {
				item.join();//�ȴ����߳�ִ�����
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out
				.println("���߳�ִ��---------------------------------------------");

		positionDao.close();
		List<Double> hList = HFactory.getHList();// �߶ȼ���
		ExecutorService radarPowerThreadPool = Executors
				.newFixedThreadPool(hList.size());
		for (int i = 0; i < hList.size(); i++) {
			double currentH = hList.get(i);// ��ǰ�߶�H
			Thread radarPowerCompute = new RadarPowerCompute(radarAltitude,
					radarToGroud, currentH, Config.LEARN_STEP);// �����״������߳�
			radarPowerThreadPool.execute(radarPowerCompute);
		}
		radarPowerThreadPool.shutdown();
		radarPowerThreadPool.awaitTermination(Integer.MAX_VALUE,
				TimeUnit.SECONDS);// ���̵߳ȴ��������߳̽���
	}
}
