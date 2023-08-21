package day0821;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.co.sist.dao.DbConn;

public class InjectionTestDAO {
	
	private static InjectionTestDAO ijtDAO;
	
	private InjectionTestDAO() {
	}//InjectionTestDAO
	
	public static InjectionTestDAO getInstance() {
		if( ijtDAO == null ) {
			ijtDAO = new InjectionTestDAO();
		}//end if		
		return ijtDAO;
	}//getInstance
	
	public LoginResultVO useStatementLogin(LoginVO lVO) throws SQLException {
		LoginResultVO lrVO = null;
		Connection con  = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			stmt = con.createStatement();
			
			StringBuilder selectLogin = new StringBuilder();
			selectLogin
			.append("	select name, input_date	")
			.append("	from test_login	")
			.append("	where id = '").append(lVO.getId()).append("' and pass = '")
			.append(lVO.getPassword()).append("'");
			
			rs = stmt.executeQuery(selectLogin.toString());
			
			if( rs.next() ) {
				lrVO = new LoginResultVO(rs.getString("name"), rs.getDate("input_date"));
			}//end if
			
		} finally {
			db.dbClose(rs, stmt, con);
		}//end finally
			
		return lrVO;
	}//useStatementLogin
	
	public LoginResultVO usePreparedStatementLogin(LoginVO lVO)throws SQLException{
		LoginResultVO lrVO = null;
		Connection con  = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder selectLogin = new StringBuilder();
			selectLogin
			.append("	select name, input_date	")
			.append("	from test_login	")
			.append("	where id = ? and pass = ? " );
			
			pstmt = con.prepareStatement(selectLogin.toString());
			
			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPassword());
			
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {
				lrVO = new LoginResultVO(rs.getString("name"), rs.getDate("input_date"));
			}//end if
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
			
		return lrVO;
	}//usePreparedStatementLogin
	
	
	

	public static void main(String[] args) {
		try {
			System.out.println(
					InjectionTestDAO.getInstance().useStatementLogin(new LoginVO("hong", "1234"))
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}//class
