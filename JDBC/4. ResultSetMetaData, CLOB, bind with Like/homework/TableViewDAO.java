package work0817;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConn;

public class TableViewDAO {
	
	private static TableViewDAO tvDAO;
	
	private TableViewDAO(){
	}
	
	public static TableViewDAO getInstance() {
		if( tvDAO == null ) {
			tvDAO = new TableViewDAO();
		}
		return tvDAO;
	}
	
	public List<String> selectAllTableName() throws SQLException {
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			String sql = "select distinct table_name from user_tab_cols";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				list.add(rs.getString("table_name"));
			}//end while			
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
				
		return list;
	}//selectAllTableName
	
	
	public List<DataVO> selectStructureTable(String tableName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<DataVO> list = new ArrayList<DataVO>();
		DataVO dVO = null;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder sql = new StringBuilder();
			sql
			.append("	select column_name, data_type, data_length, data_precision, nullable, data_default	")
			.append("	from user_tab_cols		")
			.append("	where table_name = ?	");
	
			pstmt = con.prepareStatement(sql.toString());
			
			pstmt.setString(1, tableName);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				dVO = new DataVO();
				dVO.setColumnName(rs.getString("column_name"));
				dVO.setDataType(rs.getString("data_type"));
				dVO.setDataLength(rs.getString("data_length"));
				dVO.setDataPrecision(rs.getString("data_precision"));
				dVO.setNullable(rs.getString("nullable"));
				dVO.setDataDefault(rs.getString("data_default"));
				
				list.add(dVO);				
			}//end while			
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
	}//selectStructureTable
	
}//class
