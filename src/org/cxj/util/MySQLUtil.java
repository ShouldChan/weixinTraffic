package org.cxj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.cxj.pojo.UserLocation;

/**
 * mysql数据库操作类
 * @author cxj
 * @date 2016-04-14
 * */
public class MySQLUtil {

	/**
	 * 获取mysql数据库连接
	 * @return Connection
	 * */
	private Connection getConn(HttpServletRequest request) {
		String jdbcDrive = null; // JDBC驱动类型
		String DBhost = "w.rdc.sae.sina.com.cn"; // 数据库主机地址
		String DBname = "app_ezanmydream"; // 数据库名
		String DBprot = "3307"; // 数据库端口
		String DBuser = "2m01zz2xwn"; // 数据库用户名      即应用的accessKey
		String DBpasswd = "4ill3250j3hlj041h2jjh4lxmm3z0ym20izzz13z"; // 数据库密码  即应用的sercretKey
		String strcon = null; // 连接字符串

		Connection conn = null; // 连接对象
		
		// 设置mysql数据库的驱动程序和连接字符
		jdbcDrive = "com.mysql.jdbc.Driver";
		strcon = "jdbc:mysql://" + DBhost + ":" + DBprot + "/" + DBname;
		try {
			// 加载MySQL驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取数据库连接
			conn = DriverManager.getConnection(strcon, DBuser, DBpasswd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 保存用户地理位置
	 * 
	 * @param request 请求对象
	 * @param openId 用户的OpenID
	 * @param lng 用户发送的经度
	 * @param lat 用户发送的纬度
	 * @param bd09_lng 经过百度坐标转换后的经度
	 * @param bd09_lat 经过百度坐标转换后的纬度
	 */
	public static void saveUserLocation(HttpServletRequest request, String openId, String lng, String lat, String bd09_lng, String bd09_lat) {
		String sql = "insert into user_location(open_id, lng, lat, bd09_lng, bd09_lat) values (?, ?, ?, ?, ?)";
		try {
			Connection conn = new MySQLUtil().getConn(request);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ps.setString(2, lng);
			ps.setString(3, lat);
			ps.setString(4, bd09_lng);
			ps.setString(5, bd09_lat);
			ps.executeUpdate();
			// 释放资源
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户最后一次发送的地理位置
	 * 
	 * @param request 请求对象
	 * @param openId 用户的OpenID
	 * @return UserLocation
	 */
	public static UserLocation getLastLocation(HttpServletRequest request, String openId) {
		UserLocation userLocation = null;
		String sql = "select open_id, lng, lat, bd09_lng, bd09_lat from user_location where open_id=? order by id desc limit 0,1";
		try {
			Connection conn = new MySQLUtil().getConn(request);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, openId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userLocation = new UserLocation();
				userLocation.setOpenId(rs.getString("open_id"));
				userLocation.setLng(rs.getString("lng"));
				userLocation.setLat(rs.getString("lat"));
				userLocation.setBd09Lng(rs.getString("bd09_lng"));
				userLocation.setBd09Lat(rs.getString("bd09_lat"));
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userLocation;
	}
}
