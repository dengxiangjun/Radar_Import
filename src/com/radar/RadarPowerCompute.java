package com.radar;
import java.sql.SQLException;


public class RadarPowerCompute extends Thread {

	private double radarAltitude;
	private double radarToGroud;
	private double currentH;
	private double LEARN_STEP;

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

	public RadarPowerCompute(double radarAltitude, double radarToGroud,
			double currentH, double lEARN_STEP) {
		super();
		this.radarAltitude = radarAltitude;
		this.radarToGroud = radarToGroud;
		this.currentH = currentH;
		this.LEARN_STEP = lEARN_STEP;
		// try {
		// positionDao.set(new PositionDao(dbPath));
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// System.out.println(radarToGroud + "," +
		// Thread.currentThread().getId()
		// + "," + positionDao.get());
	}

	@Override
	public void run() {
		double detect_radius = RadarUtil.CalculateDetectRadius(radarToGroud,
				currentH);// ��ȡ�״������̽��뾶
		// System.out.println(radarToGroud + "," +
		// Thread.currentThread().getId()
		// + "," + positionDao.get());
		try {
			positionDao.get().computeRadarPower(radarAltitude, detect_radius,
					currentH, LEARN_STEP);
			positionDao.get().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}// �����״������������뵽DrawablePosition����
	}
}
