package com.radar;
/**
 * ����
 * 
 * @author deng
 * 
 */
public class Config {
	public static String seperator = "\\";//�ļ��еķָ�����linux��Ϊ/,win��Ϊ\\
	public static String dbPath = "jdbc:sqlite:resources\\db\\radar";// ���ݿ�洢·��
	public static String mapPath = "resources\\point";
	// ��ͼ�洢·��,��Ŀ¼�´洢��9����ͼ���ݵķ�Ƭ����Ƭ����Ϊ��Ƭ����Ϊ1,2,3,...,9��	
	public static double radarLong = 116.39164925;// �״ﾭ��
	public static double radarLat = 39.78909016;// �״�γ��
	public static double radarAltitude = 35;// �״�߳�
	public static double radarToGroud = 68.4;// �״����߾����߶�
	public static double LEARN_STEP = 1;// ѧϰ����
}
