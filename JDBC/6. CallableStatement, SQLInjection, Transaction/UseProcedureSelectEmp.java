package day0821;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import kr.co.sist.dao.DbConn;

/**
 *Procedure를 사용한 부서사원정보 조회
 * @author user
 */
public class UseProcedureSelectEmp  {

	public UseProcedureSelectEmp(int deptno)throws SQLException {
		
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("localhost", "scott", "tiger");
			
			cstmt = con.prepareCall("{ call select_detp_emp( ?, ? ) }");
			
			//in parameter
			cstmt.setInt(1, deptno);
			//out parameter
			cstmt.registerOutParameter(2, Types.REF_CURSOR);
			
			cstmt.execute();
			
			rs = (ResultSet)cstmt.getObject(2);
			
			System.out.println( deptno + "번 부서 정보 조회");
			
			while(rs.next()) {
				System.out.println(rs.getInt("deptno") + " / " + rs.getString("dname") + " / " +
						rs.getString("loc") + " / " + rs.getString("empno") + " / " + rs.getString("ename")
						+ " / " + rs.getInt("sal")
						);
			}//end while
			
			
			
			
		} finally {
			db.dbClose(rs, cstmt, con);
		}//end finally
		
	}//UseProcedureSelectEmp
	
	public static void main(String[] args) {
		
		try {
			String inputDeptno = JOptionPane.showInputDialog("부서번호를 입력해주세요");
			
			new UseProcedureSelectEmp(Integer.parseInt(inputDeptno));
		} catch( NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "숫자만 입력가능합니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//main

}//class
