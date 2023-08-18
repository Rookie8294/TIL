package day0818;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Random;

import kr.co.sist.dao.DbConn;

public class UseCallableStatement {
	
	public UseCallableStatement(int num1, int num2) throws SQLException {
		Connection con = null;
		CallableStatement cstmt = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		//1. 
		//2.
			con =  db.getConnection("127.0.0.1", "scott", "tiger");
		//3. 쿼리문 생성객체 얻기
			cstmt = con.prepareCall("{call plus_proc(?,?, ?,?) }");
		//4. 바인드변수에 값 설정
			// in parameter : setXxx method를 사용하여 값을 넣는다.
			cstmt.setInt(1, num1);
			cstmt.setInt(2, num2);
			// out parameter : registerOutParameter method를 사용하여 값을 저장할 변수를 만든다
			// Oracle의 bind변수 등록
			cstmt.registerOutParameter(3, Types.NUMERIC); // NUMBER 대신 NUMERIC
			cstmt.registerOutParameter(4,  Types.VARCHAR); // VARCHAR2 대신 VARCHAR
		//5. 쿼리 수행 후 결과 얻기
			cstmt.execute();
		//6 out parameter 설정된 값 얻기
			int result = 0;
			String msg = "";
			
			result = cstmt.getInt(3);
			msg = cstmt.getString(4);
			
			System.out.println( msg );
			System.out.println( num1 + " + " + num2 + " = " + result);
			
		} finally {
		//7 연결끊기
			db.dbClose(null, cstmt, con);
		}
		
	}

	public static void main(String[] args) {
		
		try {
			Random r = new Random();
			int num1 = r.nextInt(10);
			int num2 = r.nextInt(10);
			new UseCallableStatement(num1, num2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
