package com.radar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * �̰߳�ȫ�ļ�����
 * @author deng
 *
 */
public class Num {
	public AtomicInteger count;

	public Num(int count) {
		super();
		this.count = new AtomicInteger(count);
	}

	public AtomicInteger getCount() {
		return count;
	}

	public void setCount(AtomicInteger count) {
		this.count = count;
	}

	/**
	 * �ۼ���
	 * @return int
	 */
	public int inc() {
		return count.addAndGet(1);
	}

}
