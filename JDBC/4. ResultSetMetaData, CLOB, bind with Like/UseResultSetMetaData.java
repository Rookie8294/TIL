package day0817;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import kr.co.sist.dao.DbConn;

public class UseResultSetMetaData {
	
	public UseResultSetMetaData() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			//1.
			//2.
			con = db.getConnection("localhost", "scott", "tiger");
			
			pstmt = con.prepareStatement("select * from emp");
			//4.
			//5.
			rs = pstmt.executeQuery();
			//6.ResultSetMetaData(RSMD) 얻기
			rsmd = rs.getMetaData();
			System.out.println("컬럼의 개수 : " + rsmd.getColumnCount());
			System.out.println("처음 컬럼명 : " + rsmd.getColumnName(1));
			System.out.println("처음 데이터형명 : " + rsmd.getColumnLabel(1));
			System.out.println("처음 데이터형명 : " + rsmd.getColumnType(1));
			System.out.println("처음 데이터형 크기 : " + rsmd.getPrecision(1));
			System.out.println("널 허용 : " + rsmd.isNullable(1));
	
		} finally {
			//7.연결끊기
			db.dbClose(rs, pstmt, con);
		}//end finally
				
	}//UseResultSetMetaData

	public static void main(String[] args) {
		try {
			new UseResultSetMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
