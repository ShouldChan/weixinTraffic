package org.cxj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.cxj.pojo.UserLocation;

/**
 * mysql���ݿ������
 * @author cxj
 * @date 2016-04-14
 * */
public class MySQLUtil {

	/**
	 * ��ȡmysql���ݿ�����
	 * @return Connection
	 * */
	private Connection getConn(HttpServletRequest request) {
		String jdbcDrive = null; // JDBC��������
		String DBhost = "w.rdc.sae.sina.com.cn"; // ���ݿ�������ַ
		String DBname = "app_ezanmydream"; // ���ݿ���
		String DBprot = "3307"; // ���ݿ�˿�
		String DBuser = "2m01zz2xwn"; // ���ݿ��û���      ��Ӧ�õ�accessKey
		String DBpasswd = "4ill3250j3hlj041h2jjh4lxmm3z0ym20izzz13z"; // ���ݿ�����  ��Ӧ�õ�sercretKey
		String strcon = null; // �����ַ���

		Connection conn = null; // ���Ӷ���
		
		// ����mysql���ݿ����������������ַ�
		jdbcDrive = "com.mysql.jdbc.Driver";
		strcon = "jdbc:mysql://" + DBhost + ":" + DBprot + "/" + DBname;
		try {
			// ����MySQL����
			Class.forName("com.mysql.jdbc.Driver");
			// ��ȡ���ݿ�����
			conn = DriverManager.getConnection(strcon, DBuser, DBpasswd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * �����û�����λ��
	 * 
	 * @param request �������
	 * @param openId �û���OpenID
	 * @param lng �û����͵ľ���
	 * @param lat �û����͵�γ��
	 * @param bd09_lng �����ٶ�����ת����ľ���
	 * @param bd09_lat �����ٶ�����ת�����γ��
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
			// �ͷ���Դ
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ�û����һ�η��͵ĵ���λ��
	 * 
	 * @param request �������
	 * @param openId �û���OpenID
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userLocation;
	}
}
