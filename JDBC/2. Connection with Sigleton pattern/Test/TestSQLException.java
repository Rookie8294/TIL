package day0814;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSQLException {
	
	public TestSQLException() throws SQLException {
		//1.드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection con = null;
		Statement stmt = null;
		try {
			//2.커넥션얻기
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pass = "tiger";
			
			con = DriverManager.getConnection(url, id, pass);
					
			//3.쿼리문 생성객체 얻기
			stmt = con.createStatement();
		
			//4.쿼리문 수행 후 결과 얻기
			int empno=4333;
			String ename="장용석";
			int sal = 999999;
//			String sql = "insert into cp_emp3(empno, ename, hiredate) values(1234, '홍찬영', sysdate)";
			String sql = String.format("insert into cp_emp3(empno, ename, sal, hiredate) values(  %d, '%s', %d, sysdate)", empno, ename, sal);
			
			int rowCnt = stmt.executeUpdate(sql);
			System.out.println(rowCnt + "건 추가되었습니다.");
			
			
			
		} finally {
			//5.연결끊기
			if( stmt !=null ) { stmt.close(); }
			if( con != null) { con.close(); }
		}
		
		
	}
	
	public static void main(String[] args) {
		
		try {
			new TestSQLException();
		} catch (SQLException e) {
			e.printStackTrace();
			switch(e.getErrorCode()) {
			case 1 :  System.out.println("사원번호가 중복되었습니다."); break;
			case 12899 : System.out.println("문자열에 값이 너무 큽니다."); break;
			case 1438 : System.out.println("연봉에 입력된 값이 너무 큽니다."); break;
			}
		}
	}

}
