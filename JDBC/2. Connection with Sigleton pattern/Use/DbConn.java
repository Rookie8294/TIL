package work0814;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConn {
	private static DbConn dbConn;
	private DbConn() {
		
	}//DbConn
	
	public static DbConn getInstance() {
		if( dbConn == null ) {
			dbConn = new DbConn();
		}
		return dbConn;
	}//getInstance
	
	public Connection getConnection(String ip, String id, String pass) throws SQLException {
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		String url = "jdbc:oracle:thin:@" + ip + ":1521:orcl";
		con = DriverManager.getConnection(url, id, pass);
		
		
		return con;
		
	}
	
	
	/**
	 * 연결 끊기
	 * @param rs
	 * @param stmt
	 * @param con
	 * @throws SQLException
	 */
	public void dbClose(ResultSet rs, Statement stmt, Connection con) throws SQLException {
		if( rs != null ) { rs.close(); }
		if( stmt != null ) { stmt.close(); }
		if( con != null ) { con.close(); }
	}
	
}//class
