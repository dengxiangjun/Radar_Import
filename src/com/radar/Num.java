package com.radar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全的计数器
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
	 * 累加器
	 * @return int
	 */
	public int inc() {
		return count.addAndGet(1);
	}

}
