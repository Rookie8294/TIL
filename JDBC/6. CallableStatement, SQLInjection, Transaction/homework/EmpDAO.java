package work0821;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConn;

public class EmpDAO {
	
	private static EmpDAO eDAO;
	
	private EmpDAO() {
	}//EmpDAO
	
	public static EmpDAO getInstance() {
		if( eDAO == null ) {
			eDAO = new EmpDAO();
		}//end if
		return eDAO;
	}//getInstance
	
	public List<String> selectEmpno() throws SQLException {
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			String sql = "select empno from emp";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				list.add(rs.getString("empno"));
			}//end while
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		
		return list;
		
	}//selectEmpno;
	
	public InfoVO selectOneInfo(String empno) throws SQLException{
		InfoVO iVO = null;
		Connection con = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			String sql = "select * from emp where empno = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, empno);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				iVO = new InfoVO();
				iVO.setEmpno(rs.getString("empno"));
				iVO.setEname(rs.getString("ename"));
				iVO.setHiredate(rs.getString("hiredate"));
				iVO.setJob(rs.getString("job"));
				iVO.setSal(rs.getString("sal"));
			}//end while
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		return iVO;
	}//selectOneInfo

}//class
