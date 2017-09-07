package com.radar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PositionDao {
	/**
	 * 用户表
	 */
	private static final String USER_TABLE_NAME = "user";
	/**
	 * Database creation sql statement
	 */
	private static final String CREATE_USER_TABLE = "create table "
			+ USER_TABLE_NAME
			+ " (account varchar not null primary key, "
			+ "id_number varchar not null unique,password varchar not null,name varchar not null, "
			+ "role varchar not null,job_type varchar not null);";

	/**
	 * 表名称，主界面第一元素（LDJJ）个下的第一个表（LDXN）
	 */
	private static final String LDXN_TABLE_NAME = "LDXN";
	private static final String CREATE_LDXN_TABLE = "create table "
			+ LDXN_TABLE_NAME
			+ "(id integer not null primary key autoincrement,kind varchar not null, description text not null);";

	/**
	 * 表名称，主界面第一元素（LDJJ）个下的第二个表（ZYCS）
	 */
	private static final String ZYCS_TABLE_NAME = "ZYCS";
	private static final String CREATE_ZYCS_TABLE = "create table "
			+ ZYCS_TABLE_NAME
			+ "(id integer not null primary key  autoincrement,kind varchar not null, description text not null,img varchar not null);";
	/**
	 * 表名称，主界面第二元素（ZCHJ）个下的第一个子项（ZRHJ）
	 */
	public static final String ZRHJ_TABLE_NAME = "ZRHJ";
	private static final String CREATE_ZRHJ_TABLE = "create table "
			+ ZRHJ_TABLE_NAME
			+ "(id integer not null primary key  autoincrement,kind varchar not null, description  text not null,img varchar not null);";

	/**
	 * 表名称，主界面第二元素（ZCHJ）个下的第二个子项（DCHJ）
	 */
	public static final String DCHJ_TABLE_NAME = "DCHJ";
	private static final String CREATE_DCHJ_TABLE = "create table "
			+ DCHJ_TABLE_NAME
			+ "(id integer not null primary key  autoincrement, description  text not null);";

	/**
	 * 表名称，主界面第三元素（KQTJ）
	 */
	public static final String KQTJ_TABLE_NAME = "KQTJ";
	private static final String CREATE_KQTJ_TABLE = "create table "
			+ KQTJ_TABLE_NAME
			+ "(id integer not null primary key  autoincrement,"
			+ "kind varchar not null, description  text not null,img varchar not null);";

	/**
	 * 表名称，主界面第四元素下的第一个元素（LDZZBDJ）下的第一个元素（KQZWQK）
	 */
	public static final String KQZWQK_TABLE_NAME = "KQZWQK";
	private static final String CREATE_KQZWQK_TABLE = "create table "
			+ KQZWQK_TABLE_NAME
			+ "(id integer not null primary key  autoincrement,lx varchar not null,"
			+ "rw varchar not null,jx varchar not null,ps integer not null,"
			+ "js integer not null,sj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第一个元素（LDZZBDJ）下的第二个元素（XTGZKQ）
	 */
	public static final String XTGZKQ_TABLE_NAME = "XTGZQK";
	private static final String CREATE_XTGZQK_TABLE = "create table "
			+ XTGZKQ_TABLE_NAME
			+ "(id integer not null primary key  autoincrement,ld varchar not null,"
			+ "tx varchar not null,zdh varchar not null,dz varchar not null,"
			+ "ld_desc text not null,tx_desc text not null,zdh_desc text not null,"
			+ "dz_desc text not null,sj datetime not null,jlr varchar not null,"
			+ "soft_delete integer default 0,foreign key(jlr) references "
			+ USER_TABLE_NAME
			+ "(account) on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第一个元素（LDZZBDJ）下的第三个元素（SJMLZS）
	 */
	public static final String SJMLZS_TABLE_NAME = "SJMLZS";
	private static final String CREATE_SJMLZS_TABLE = "create table "
			+ SJMLZS_TABLE_NAME
			+ "(id integer not null primary key  autoincrement,zssj datetime not null,"
			+ "wcqx datetime not null,zsnr text not null,jlsj datetime not null,"
			+ "jlr varchar not null," + "foreign key(jlr) references "
			+ USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第一个元素（LDZZBDJ）下的第四个元素（ZBJCQK）
	 */
	public static final String ZBJCQK_TABLE_NAME = "ZBJCQK";
	private static final String CREATE_ZBJCQK_TABLE = "create table "
			+ ZBJCQK_TABLE_NAME
			+ "(id integer not null primary key autoincrement,jcsj datetime not null,"
			+ "sjr varchar not null,zw1 varchar not null,jcr varchar not null,"
			+ "dw varchar not null,zw2 varchar not null,sjml text not null,"
			+ "zbzb text not null,zzzc text not null,zbry text not null,"
			+ "wqzb text not null,jcjl varchar not null,jlsj datetime not null,"
			+ "jlr varchar not null,foreign key(jlr) references "
			+ USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第一个元素（LDZZBDJ）下的第五个元素（DXSL）
	 */
	public static final String DXSL_TABLE_NAME = "DXSL";
	private static final String CREATE_DXSL_TABLE = "create table "
			+ DXSL_TABLE_NAME
			+ "(id integer not null primary key autoincrement,almc varchar not null,"
			+ "zdjb text not null,img varchar not null,jyzj varchar not null,"
			+ "sj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第一个元素（LDZZBDJ）下的第七个元素（CZWT）
	 */
	public static final String CZWT_TABLE_NAME = "CZWT";
	private static final String CREATE_CZWT_TABLE = "create table "
			+ CZWT_TABLE_NAME
			+ "(id integer not null primary key autoincrement,gzlx text not null,"
			+ "czwt text not null,zgcs text not null,zgcx text not null,"
			+ "sj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第4个元素（ZBGWDJ）下的指挥员
	 */
	public static final String DIRECTOR_TABLE_NAME = "Director";
	private static final String CREATE_DIRECTOR_TABLE = "create table "
			+ DIRECTOR_TABLE_NAME
			+ "(id integer not null primary key autoincrement,instruction varchar not null,"
			+ "task varchar not null,attention varchar not null,equipment varchar not null,"
			+ "duty varchar not null,to_do varchar not null,sj datetime not null,"
			+ "jlr varchar not null,foreign key(jlr) references "
			+ USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第4个元素（ZBGWDJ）下的操作员
	 */
	public static final String Operator_TABLE_NAME = "Operator";
	private static final String CREATE_Operator_TABLE = "create table "
			+ Operator_TABLE_NAME
			+ "(id integer not null primary key autoincrement,circs varchar not null,"
			+ "work varchar not null,instruction varchar not null,item varchar not null,"
			+ "equipment varchar not null,tool varchar not null,sj datetime not null,"
			+ "jlr varchar not null,foreign key(jlr) references "
			+ USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第4个元素（ZBGWDJ）下的油机员
	 */
	public static final String OilCrew_TABLE_NAME = "OilCrew";
	private static final String CREATE_OilCrew_TABLE = "create table "
			+ OilCrew_TABLE_NAME
			+ "(id integer not null primary key autoincrement,power_station varchar not null,"
			+ "machine varchar not null,duty varchar not null,instruction varchar not null,"
			+ "other varchar not null,sj datetime not null,"
			+ "jlr varchar not null,foreign key(jlr) references "
			+ USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第4个元素（ZBGWDJ）下的标记员或技师
	 */
	public static final String MarkerAndTech_TABLE_NAME = "MarkerAndTech";
	private static final String CREATE_MarkerAndTech_TABLE = "create table "
			+ MarkerAndTech_TABLE_NAME
			+ "(id integer not null primary key autoincrement,description text not null,"
			+ "kind varchar not null,sj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第5元素LDJS
	 */
	public static final String LDJS_TABLE_NAME = "LDJS";
	private static final String CREATE_LDJS_TABLE = "create table "
			+ LDJS_TABLE_NAME
			+ "(id integer not null primary key autoincrement,"
			+ "kind varchar not null,img varchar not null);";

	/**
	 * 表名称，主界面第四元素下的第五个元素（FXYBDJ）下的第1个元素（RWFXJH）
	 */
	public static final String RWFXJH_TABLE_NAME = "RWFXJH";
	private static final String CREATE_RWFXJH_TABLE = "create table "
			+ RWFXJH_TABLE_NAME
			+ "(id integer not null primary key autoincrement,ph varchar not null,"
			+ "dw varchar not null,jx varchar not null,jh varchar not null,"
			+ "js varchar not null,hx varchar not null,rw varchar not null,"
			+ "yjsj datetime not null,jlr varchar not null,bz varchar not null,jlsj datetime not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第五个元素（FXYBDJ）下的第2个元素（THXLJH）
	 */
	public static final String THXLJH_TABLE_NAME = "THXLJH";
	private static final String CREATE_THXLJH_TABLE = "create table "
			+ THXLJH_TABLE_NAME
			+ "(id integer not null primary key autoincrement,ph varchar not null,"
			+ "qjc varchar not null,dw varchar not null,jx varchar not null,"
			+ "js varchar not null,ky varchar not null,kfsj datetime not null,"
			+ "jssj datetime not null,bz varchar not null,jlsj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，ZBDJ->LDZZBDJ->DBSX
	 */
	public static final String DBSX_TABLE_NAME = "DBSX";
	private static final String CREATE_DBSX_TABLE = "create table "
			+ DBSX_TABLE_NAME
			+ "(id integer not null primary key autoincrement,deadline varchar not null,"
			+ "progress varchar not null,description varchar not null,"
			+ "sj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第五个元素（FXYBDJ）下的第3个元素（THRWFX）
	 */
	public static final String THRWFX_TABLE_NAME = "THRWFX";
	private static final String CREATE_THRWFX_TABLE = "create table "
			+ THRWFX_TABLE_NAME
			+ "(id integer not null primary key autoincrement,ph varchar not null,"
			+ "dw varchar not null,jx varchar not null,"
			+ "jh varchar not null,js varchar not null,"
			+ "hx varchar not null,rw varchar not null,yjsj datetime not null,"
			+ "sssj datetime not null,bz varchar not null,jlsj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第五个元素（FXYBDJ）下的第4个元素（BDXFJH）
	 */
	public static final String BDXFJH_TABLE_NAME = "BDXFJH";
	private static final String CREATE_BDXFJH_TABLE = "create table "
			+ BDXFJH_TABLE_NAME
			+ "(id integer not null primary key autoincrement,jc varchar not null,"
			+ "bb varchar not null,zykm varchar not null,kfsj datetime not null,"
			+ "jssj datetime not null,bz varchar not null,jlsj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第五个元素（FXYBDJ）下的第6个元素（DBCX）和主界面第四元素下的第3个元素（ZBJSB）
	 */
	public static final String NoteBook_TABLE_NAME = "NoteBook";
	private static final String CREATE_NoteBook_TABLE = "create table "
			+ NoteBook_TABLE_NAME
			+ "(id integer not null primary key autoincrement,"
			+ "description text not null,sj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第6个元素（FXYBDJ）下的第1个元素（MLZS）
	 */
	public static final String MLZS_TABLE_NAME = "MLZS";
	private static final String CREATE_MLZS_TABLE = "create table "
			+ MLZS_TABLE_NAME
			+ "(id integer not null primary key autoincrement,lhdw varchar not null,"
			+ "dh varchar not null,lhr varchar not null,cbr varchar not null,"
			+ "rqsj datetime not null,nrzy varchar not null,ldps varchar not null,"
			+ "zxqk varchar not null,jlsj datetime not null,jlr varchar not null,"
			+ "status varchar not null,soft_delete integer default 0,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第6个元素（FXYBDJ）下的第2个元素（QSBG）
	 */
	public static final String QSBG_TABLE_NAME = "QSBG";
	private static final String CREATE_QSBG_TABLE = "create table "
			+ QSBG_TABLE_NAME
			+ "(id integer not null primary key autoincrement,qhdw varchar not null,"
			+ "dh varchar not null,shr varchar not null,rqsj datetime not null,"
			+ "nrzy varchar not null,pfqk varchar not null,jlsj datetime not null,"
			+ "soft_delete integer default 0,jlr varchar not null,foreign key(jlr) references "
			+ USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第6个元素（FXYBDJ）下的第3个元素（ZBCC）
	 */
	public static final String ZBCC_TABLE_NAME = "ZBCC";
	private static final String CREATE_ZBCC_TABLE = "create table "
			+ ZBCC_TABLE_NAME
			+ "(id integer not null primary key autoincrement,jcsj datetime not null,"
			+ "ccdw varchar not null,zbcc varchar not null,djr varchar not null,"
			+ "zbccjg varchar not null,yyjx varchar not null,cljg varchar not null,"
			+ "bz varchar not null,jlsj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第6个元素（FXYBDJ）下的第4个元素（BQGZ）
	 */
	public static final String BQGZ_TABLE_NAME = "BQGZ";
	private static final String CREATE_BQGZ_TABLE = "create table "
			+ BQGZ_TABLE_NAME
			+ "(id integer not null primary key autoincrement,dw varchar not null,"
			+ "sbmc varchar not null,gzlx varchar not null,gzsj datetime not null,"
			+ "xfsj datetime not null,bz varchar not null,"
			+ "jlsj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第6个元素（FXYBDJ）下的第5个元素（BQWH）
	 */
	public static final String BQWH_TABLE_NAME = "BQWH";
	private static final String CREATE_BQWH_TABLE = "create table "
			+ BQWH_TABLE_NAME
			+ "(id integer not null primary key autoincrement,dw varchar not null,"
			+ "sbmc varchar not null,whlx varchar not null,whsj datetime not null,"
			+ "whjd varchar not null,wcsj datetime not null,bz varchar not null,"
			+ "jlsj datetime not null,jlr varchar not null,soft_delete integer default 0,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第6个元素（FXYBDJ）下的第6个元素（SGRYFGR）
	 */
	public static final String SGRYFGR_TABLE_NAME = "SGRYFGR";
	private static final String CREATE_SGRYFGR_TABLE = "create table "
			+ SGRYFGR_TABLE_NAME
			+ "(id integer not null primary key autoincrement,zsgrqk text not null,"
			+ "img1 varchar,fgrqk text not null,img2 varchar,fgrxg text not null,"
			+ "img3 varchar,bz varchar not null,jlsj datetime not null,"
			+ "jlr varchar not null," + "foreign key(jlr) references "
			+ USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第6个元素（FXYBDJ）下的第7，8，9，10个元素（ZBJYZD,ZBXSFX,JQYJ,ZBYLDJ）
	 */
	public static final String MEETING_TABLE_NAME = "Meeting";
	private static final String CREATE_MEETING_TABLE = "create table "
			+ MEETING_TABLE_NAME
			+ "(id integer not null primary key autoincrement,kind text not null,"
			+ "rqsj datetime not null,dd text not null,zcr varchar not null,jlr varchar not null,"
			+ "cjry varchar not null,zt varchar not null,jtqk varchar not null,"
			+ "bz varchar not null,djsj datetime not null,djr varchar not null,"
			+ "foreign key(djr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第6个元素（FXYBDJ）下的第11个元素（ZBKH）
	 */
	public static final String ZBKH_TABLE_NAME = "ZBKH";
	private static final String CREATE_ZBKH_TABLE = "create table "
			+ ZBKH_TABLE_NAME
			+ "(id integer not null primary key autoincrement,rq varchar not null,"
			+ "zzdw varchar not null,sjdw varchar not null,gzz varchar not null,"
			+ "jtqk varchar not null,jlsj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	/**
	 * 表名称，主界面第四元素下的第6个元素（FXYBDJ）下的第12个元素（JRZB）
	 */
	public static final String JRZB_TABLE_NAME = "JRZB";
	private static final String CREATE_JRZB_TABLE = "create table "
			+ JRZB_TABLE_NAME
			+ "(id integer not null primary key autoincrement,jrmc varchar not null,"
			+ "zbsj1 datetime not null,zbsj2 datetime not null,zbqk varchar not null,wcqk varchar not null,"
			+ "jlsj datetime not null,jlr varchar not null,"
			+ "foreign key(jlr) references " + USER_TABLE_NAME + "(account)"
			+ "on delete cascade on update cascade);";

	public static final String POSITION_TABLE_NAME = "Position";
	private static final String CREATE_POSITION_TABLE = "create table "
			+ POSITION_TABLE_NAME
			+ "(id integer not null primary key autoincrement,longitude double not null,"
			+ "latitude double not null,altitude double not null,distance double not null,"
			+ "dx double not null,dy double not null,direction double not null);";

	public static final String RADARINFO_TABLE_NAME = "RadarInfo";
	private static final String CREATE_RADARINFO_TABLE = "create table "
			+ RADARINFO_TABLE_NAME
			+ "(longitude double not null,latitude double not null,altitude double not null,height_over_background);";

	public static final String DrawablePosition_TABLE_NAME = "DrawablePosition";
	private static final String CREATE_DrawablePosition_TABLE = "create table "
			+ DrawablePosition_TABLE_NAME
			+ "(id integer not null primary key autoincrement,longitude double not null,"
			+ "latitude "
			+ " not null,H double not null,direction double not null,altitude double not null,angleofShade double not null);";

	public static final String StatisticPower_TABLE_NAME = "StatisticPower";
	private static final String CREATE_StatisticPower_TABLE = "create table "
			+ StatisticPower_TABLE_NAME
			+ "(id integer not null primary key autoincrement,longitude double not null,"
			+ "latitude double not null,distance double not null,altitude double not null,"
			+ "aircraftType varchar double not null,direction double not null,drawType varchar,source varchar);";

	// public static final String DrawableStatisticPower_TABLE_NAME =
	// "DrawableStatisticPower";
	// private static final String CREATE_DrawableStatisticPower_TABLE =
	// "create table "
	// + DrawableStatisticPower_TABLE_NAME
	// +
	// "(id integer not null primary key autoincrement,longitude double not null,"
	// + "latitude not null,H double not null,direction double not null,"
	// + "drawType varchar not null,source varchar not null);";

	public static final String MARK_TABLE_NAME = "Mark";
	private static final String CREATE_MARK_TABLE = "create table "
			+ MARK_TABLE_NAME
			+ "(id integer not null primary key autoincrement,kind varchar not null,"
			+ "longitude double not null,latitude double not null,description varchar);";

	public static final String PointSet_TABLE_NAME = "PointSet";// 航线和训练空域
	private static final String CREATE_PointSet_TABLE = "create table "
			+ PointSet_TABLE_NAME
			+ "(id integer not null primary key autoincrement,"
			+ "kind varchar not null,points text,description varchar not null);";

	public Connection connection;
	public Statement statement;

	public PositionDao(String dbPath) throws SQLException {
		connection = DriverManager.getConnection(dbPath);
		statement = connection.createStatement();
		statement.setQueryTimeout(30);
	}

	public void close() throws SQLException {
		// statement.close();
		connection.close();
	}

	public void initDB() throws SQLException {
		// PreparedStatement prep = connection.prepareStatement();
		// prep.addBatch(sql)

		 statement.executeUpdate(CREATE_USER_TABLE);
		 statement.executeUpdate(CREATE_LDXN_TABLE);
		 statement.executeUpdate(CREATE_ZYCS_TABLE);
		 statement.executeUpdate(CREATE_DCHJ_TABLE);
		 statement.executeUpdate(CREATE_ZRHJ_TABLE);
		 statement.executeUpdate(CREATE_KQTJ_TABLE);
		 statement.executeUpdate(CREATE_KQZWQK_TABLE);
		 statement.executeUpdate(CREATE_XTGZQK_TABLE);
		 statement.executeUpdate(CREATE_SJMLZS_TABLE);
		 statement.executeUpdate(CREATE_ZBJCQK_TABLE);
		 statement.executeUpdate(CREATE_DXSL_TABLE);
		 statement.executeUpdate(CREATE_CZWT_TABLE);
		 statement.executeUpdate(CREATE_RWFXJH_TABLE);
		 statement.executeUpdate(CREATE_THXLJH_TABLE);
		 statement.executeUpdate(CREATE_DBSX_TABLE);
		 statement.executeUpdate(CREATE_THRWFX_TABLE);
		 statement.executeUpdate(CREATE_BDXFJH_TABLE);
		 statement.executeUpdate(CREATE_NoteBook_TABLE);
		 statement.executeUpdate(CREATE_MLZS_TABLE);
		 statement.executeUpdate(CREATE_QSBG_TABLE);
		 statement.executeUpdate(CREATE_ZBCC_TABLE);
		 statement.executeUpdate(CREATE_BQGZ_TABLE);
		 statement.executeUpdate(CREATE_BQWH_TABLE);
		 statement.executeUpdate(CREATE_SGRYFGR_TABLE);
		 statement.executeUpdate(CREATE_MEETING_TABLE);
		 statement.executeUpdate(CREATE_ZBKH_TABLE);
		 statement.executeUpdate(CREATE_JRZB_TABLE);
		 statement.executeUpdate(CREATE_DIRECTOR_TABLE);
		 statement.executeUpdate(CREATE_Operator_TABLE);
		 statement.executeUpdate(CREATE_OilCrew_TABLE);
		 statement.executeUpdate(CREATE_MarkerAndTech_TABLE);
		 statement.executeUpdate(CREATE_LDJS_TABLE);
		 statement.executeUpdate(CREATE_POSITION_TABLE);
		 statement.executeUpdate(CREATE_MARK_TABLE);
		 statement.executeUpdate(CREATE_RADARINFO_TABLE);
		 statement.executeUpdate(CREATE_DrawablePosition_TABLE);
		 statement.executeUpdate(CREATE_StatisticPower_TABLE);
		 statement.executeUpdate(CREATE_PointSet_TABLE);
	}

	public void insertRadarInfo(double radarLong, double radarLat,
			double radarAltitude, double radarToGroud) throws SQLException {
		PreparedStatement prep = connection
				.prepareStatement("insert into RadarInfo values(" + radarLong
						+ "," + radarLat + "," + radarAltitude + ","
						+ radarToGroud + ")");
		prep.executeUpdate();
	}

	public void insertPosition(Double longitude, Double latitude,
			Double altitude, double distance, double dx, double dy,
			double direction, int pointCnt) throws SQLException {
		// System.out.println("当前线程是" + Thread.currentThread().getId());
		// statement
		// .executeUpdate("insert into Position(longitude,latitude,altitude,distance,dx,dy,direction) values("
		// + longitude
		// + ","
		// + latitude
		// + ","
		// + altitude
		// + ","
		// + distance
		// + ","
		// + dx
		// + ","
		// + dy
		// + ","
		// + direction
		// + ")");
		String sql = "insert into Position(longitude,latitude,altitude,distance,dx,dy,direction) values("
				+ longitude
				+ ","
				+ latitude
				+ ","
				+ altitude
				+ ","
				+ distance
				+ "," + dx + "," + dy + "," + direction + ")";
		PreparedStatement prep = connection.prepareStatement(sql);
		prep.addBatch();
		// if(statement.)
		if (pointCnt % 500 == 0)
			prep.executeBatch();
	}

	private Position fetchDirectionRadarPower(double radarRadius, double H,
			double direction, double step) throws SQLException {
		Position position = null;
		String sql = "select id,longitude,latitude,altitude,direction,max(distance) as maxDistance from Position where distance <= "
				+ radarRadius
				+ " and altitude <= "
				+ H
				+ " and direction >= "
				+ direction + " and direction <" + (direction + step);
		PreparedStatement prep = connection.prepareStatement(sql);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			int id = result.getInt("id");
			if (id > 0) {
				double longitude = result.getDouble("longitude");
				double latitude = result.getDouble("latitude");
				double altitude = result.getDouble("altitude");
				double distance = result.getDouble("maxDistance");
				double realDirection = result.getDouble("direction");
				position = new Position(id, longitude, latitude, altitude,
						distance, realDirection);
				break;
			}
		}
		result.close();
		return position;
	}

	/**
	 *
	 * 计算雷达威力
	 * @param radarAltitude 雷达高程
	 * @param detect_radius 探测半径
	 * @param H 高度
	 * @param step 步长
	 * @throws SQLException
	 */
	public void computeRadarPower(double radarAltitude, double detect_radius,
			double H, double step) throws SQLException {
		List<Position> positions = new ArrayList<Position>();
		double scaleCnt = 360 / step;
		for (double j = 0; j < scaleCnt;) {
			Position position = fetchDirectionRadarPower(detect_radius, H, j,
					step);
			System.out.println("正在筛选" + position + "当前刻度:" + j + ",步长:" + step
					+ ",当前高度：" + H);
			if (position != null)
				positions.add(position);
			j += step;
		}
		//int cnt = 0;
		StringBuilder batchSql = new StringBuilder();
		for (Position item : positions) {
			double itemAltitude = item.getAltitude();
			double distance = item.getDistance();
			double angleofShade = Math.atan((itemAltitude - radarAltitude)
					/ distance);// 遮蔽角
			System.out.println("可绘制点" + item.getDirection() + ","
					+ item.getLatitude() + "," + item.getLongitude() + ","
					+ itemAltitude + ",遮蔽角:" + angleofShade);

			String sql = "insert into DrawablePosition(longitude,latitude,H,direction,altitude,angleofShade) values("
					+ item.getLongitude()
					+ ","
					+ item.getLatitude()
					+ ","
					+ H
					+ ","
					+ item.getDirection()
					+ ","
					+ itemAltitude
					+ ","
					+ angleofShade + ");";
			batchSql.append(sql);
		}
		batchinsertDrawablePosition(batchSql.toString());
	}

	/**
	 * 插入用来绘制不规则多边形的点
	 * @param batchSql
	 */
	private synchronized void batchinsertDrawablePosition(String batchSql) {
		Statement ste = null;
		try {
			connection.setAutoCommit(false);
			ste = connection.createStatement();
			String[] sqls = batchSql.split(";");
			for (String sql : sqls) {
				ste.addBatch(sql);
			}
			ste.executeBatch();
			ste.close();
			connection.commit();// 提交
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	private void insertDrawablePosition(double longitude, double latitude,
//			double altitude, double H, double direction, double angleofShade,
//			int cnt) throws SQLException {
//		String sql = "insert into DrawablePosition(longitude,latitude,H,direction,altitude,angleofShade) values("
//				+ longitude
//				+ ","
//				+ latitude
//				+ ","
//				+ H
//				+ ","
//				+ direction
//				+ ","
//				+ altitude + "," + angleofShade + ")";
//		PreparedStatement prep = connection.prepareStatement(sql);
//		prep.execute();
//		// prep.addBatch();
//		// if (cnt % 100 == 0)
//		// prep.executeBatch();
//	}

	/**
	 * 批量插入地图点数据
	 * @param batchSql
	 */
	public synchronized void batchInsertPosition(String batchSql) {
		System.out.println(batchSql.length() + ";批量插入position");
		Statement ste = null;
		try {
			connection.setAutoCommit(false);
			ste = connection.createStatement();
			String[] sqls = batchSql.split(";");
			for (String sql : sqls) {
				ste.addBatch(sql);
			}
			ste.executeBatch();
			ste.close();
			connection.commit();// 提交
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}