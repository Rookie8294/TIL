package day0814;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import kr.co.sist.statement.DbConnection;

public class UseResultSet {

	public UseResultSet() throws SQLException {
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		DbConnection db = new DbConnection();
		try {
		//1.드라이버 로딩
		//2. 커넥션 얻기
			con = db.getConn();
		//3. 쿼리문생성객체 얻기
			stmt = con.createStatement();
		//4. 쿼리문 실행 후 결과 얻기
			String select = "select empno, ename, hiredate, 2023.08 temp, to_char(hiredate, 'mm-dd-yyyy') hire from emp";
			//rs = 커서의 제어권이 있다.
			rs = stmt.executeQuery(select);
			
			int empno = 0;
			String ename = "";
			Date hiredate = null;
			double temp = 0.0;
			String hire = "";
			
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

			
			//조회결과가 있다면 반복시켜 모두 가져온다
			while( rs.next() ) {
				empno = rs.getInt("empno");
				ename = rs.getString("ename");
				hiredate = rs.getDate("hiredate");
				temp = rs.getDouble("temp");
				hire = rs.getString("hire");
				
				System.out.printf("%d \t %s \t %s \t %.2f \t %s \n",empno, ename, sdf.format(hiredate), temp, hire);				
			}
		} finally {
		//5. 연결끊기
			db.closeDB(rs, stmt, con);
		}
		
	}//UseResultSet
	
	public static void main(String[] args) {
		try {
			new UseResultSet();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}//main

}//class
