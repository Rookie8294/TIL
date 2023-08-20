package work0819;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConn;

public class CardAdminDAO {
	
	private static CardAdminDAO caDAO;
	
	private CardAdminDAO() {
	}//CardAdminDAO
	
	public static CardAdminDAO getInstance() {
		if( caDAO == null ) {
			caDAO = new CardAdminDAO();
		}//end if
		
		return caDAO;
	}//getInstance
	
	
	public void insert(String name, String phone, String email) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		con = db.getConnection("localhost", "scott", "tiger");
		
		StringBuilder insertSql = new StringBuilder();
		insertSql
		.append("	insert into business_card(num, name, phone, email)	")
		.append("	values( card_seq.nextval, ?, ?, ?)	");
		
		pstmt = con.prepareStatement(insertSql.toString());
		
		pstmt.setString(1, name);
		pstmt.setString(2, phone);
		pstmt.setString(3, email);
		
		pstmt.executeUpdate();
		
		} finally {
			db.dbClose(null, pstmt, con);
		}//end try
		
	}//insert
	
	public void update(String num, String name, String phone, String email) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		con = db.getConnection("localhost", "scott", "tiger");
		
		StringBuilder insertSql = new StringBuilder();
		insertSql
		.append("	update business_card	")
		.append("	set name = ?, phone = ?, email = ?,  input_date = sysdate	")
		.append("	where num = ?	");
		
		pstmt = con.prepareStatement(insertSql.toString());
		
		pstmt.setString(1, name);
		pstmt.setString(2, phone);
		pstmt.setString(3, email);
		pstmt.setString(4, num);
		
		pstmt.executeUpdate();
		
		} finally {
			db.dbClose(null, pstmt, con);
		}//end try
		
	}//update
	
	public void delete(String num) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		con = db.getConnection("localhost", "scott", "tiger");
		
		String deleteSql = "delete business_card where num = ? ";
		
		pstmt = con.prepareStatement(deleteSql);
		
		pstmt.setString(1, num);
		
		pstmt.executeUpdate();
		
		} finally {
			db.dbClose(null, pstmt, con);
		}//end try
		
	}//delete
	
	public int selectNextNum() throws SQLException {
		int cnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		con = db.getConnection("localhost", "scott", "tiger");
		
		String sql = "	select /*+ index_acs(business_card card_index)*/ num from business_card		";		
		pstmt = con.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			cnt = rs.getInt("num");
		}//end while
		
		} finally {
			db.dbClose(null, pstmt, con);
		}//end try
		
		return cnt;
		
	}//selectCurrentNum
	
	public List<DataVO> selectAllInfo() throws SQLException {
		List<DataVO> list = new ArrayList<DataVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		con = db.getConnection("localhost", "scott", "tiger");
		
		String sql = "	select * from business_card	";		
		pstmt = con.prepareStatement(sql);
		
		rs = pstmt.executeQuery();
		
		while ( rs.next() ) {
			DataVO dVO = new DataVO();
			dVO.setNum(rs.getString("num"));
			dVO.setName(rs.getString("name"));
			dVO.setEmail(rs.getString("email"));
			dVO.setPhone(rs.getString("phone"));
			dVO.setInputDate(rs.getString("input_date"));
			
			list.add(dVO);
		}//end while
		
		} finally {
			db.dbClose(null, pstmt, con);
		}//end try
		
		return list;
	}//selectAllInfo
	
	public DataVO selectOneInfo(String num) throws SQLException {
		DataVO dVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		con = db.getConnection("localhost", "scott", "tiger");
		
		String sql = "	select * from business_card where num= ?	";		
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, num);
		
		rs = pstmt.executeQuery();
		
		while ( rs.next() ) {
			dVO = new DataVO();
			dVO.setNum(rs.getString("num"));
			dVO.setName(rs.getString("name"));
			dVO.setEmail(rs.getString("email"));
			dVO.setPhone(rs.getString("phone"));
			dVO.setInputDate(rs.getString("input_date"));
		}//end while
		
		} finally {
			db.dbClose(null, pstmt, con);
		}//end try
		
		return dVO;
	}//selectOneInfo
	
	

	
}//class
