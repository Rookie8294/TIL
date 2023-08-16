package work0816;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConn;

public class DAO {
	
	private static DAO dao;
	
	private  DAO() {
	}//DAO
	
	public static DAO getInstance() {
		if( dao == null ) {
			dao = new DAO();
		}
		return dao;
	}//getInstance
	
	public void createTable() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("localhost", "scott", "tiger");
			
			String sql = "create table work_user(name varchar2(12), img number(3), age number(3), num number(10)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.execute();
			
			System.out.println("work_user 테이블이 생성되었습니다.");
			
		} finally {
			db.dbClose(null, pstmt, con);
		}//end finally
				
	}//createTable
	
	public void updateInfo(WorkVO wVO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			String sql = "insert into work_user(name, img, age, num) values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,wVO.getName());
			pstmt.setInt(2, wVO.getImg());
			pstmt.setInt(3,  wVO.getAge());
			pstmt.setInt(4, wVO.getNum());
			
			pstmt.executeUpdate();
			
		}catch (NumberFormatException nfe) {
			System.out.println("여긴가");
		} finally {
			db.dbClose(null, pstmt, con);
		}//end finally
		
	}//updateInfo
	
	public WorkVO selectInfo(int num) throws SQLException {
		WorkVO wVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			String sql = "select name, img, age, num from work_user where num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();			
			
			while( rs.next() ) {
				wVO = new WorkVO();
				wVO.setName(rs.getString("name"));
				wVO.setImg(rs.getInt("img"));
				wVO.setAge(rs.getInt("age"));
				wVO.setNum(rs.getInt("num"));
			}//end while
		} finally {
			db.dbClose(null, pstmt, con);
		}//end finally
		
		return wVO;
		
	}//selectInfo
	
	public List<WorkVO> selectAllInfo() throws SQLException {
		List<WorkVO> list = new ArrayList<WorkVO>();
		WorkVO wVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			String sql = "select name, img, age, num from work_user";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();			
			
			while( rs.next() ) {
				wVO = new WorkVO();
				wVO.setName(rs.getString("name"));
				wVO.setImg(rs.getInt("img"));
				wVO.setAge(rs.getInt("age"));
				wVO.setNum(rs.getInt("num"));
				
				list.add(wVO);
			}//end while
		} finally {
			db.dbClose(null, pstmt, con);
		}//end finally
		
		return list;
		
	}//selectAllInfo
}
