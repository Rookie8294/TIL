package work0814;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConn;

public class StatementDAO {
	public List<String> selectAllTableName() throws SQLException {
		List<String> tableNameList = new ArrayList<String>();
		ResultSet rs = null;
		Connection con = null;
		Statement stmt = null;
		
		DbConn dc = null;
		
		try {
			dc = DbConn.getInstance();
			con = dc.getConnection("localhost", "scott", "tiger");
			String sql = "select table_name from user_tables";
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while( rs.next()) {
				tableNameList.add(rs.getString("table_name"));
			}
		
		} finally {
			dc.dbClose(rs, stmt, con);
		}//end finally
		
		return tableNameList;
		
	}//selectAllTableName
	
}//class