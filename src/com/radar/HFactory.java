package com.radar;
import java.util.ArrayList;
import java.util.List;

/**
 * 雷达探测视距的高度集合
 * 
 * @author deng
 * 
 */
public class HFactory {

	/**
	 * 内部类
	 * 
	 * @author deng
	 * 
	 */
	private static class HHolder {
		private static final HFactory INSTANCE = new HFactory();
	}

	private List<Double> hList = null;

	/**
	 * 私有构造器
	 */
	private HFactory() {
		hList = new ArrayList<Double>();
		for (double i = 100.0; i <= 2000.0; i += 100.0)
			hList.add(i);
		hList.add(3000.0);
		hList.add(5000.0);
		hList.add(8000.0);
	}

	/**
	 * 单例
	 * 
	 * @return List<Double>
	 */
	public static List<Double> getHList() {
		return HHolder.INSTANCE.hList;
	}
}
