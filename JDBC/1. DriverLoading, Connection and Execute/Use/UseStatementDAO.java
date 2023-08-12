package kr.co.sist.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO
 * DBMS작업 중 쿼리문에 관한 업무를 정의하는 클래스
 * 
 * @author user
 */
public class UseStatementDAO {
	
	public void createStudentTable() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		//1. 드라이버 연결
		//2. 커넥션얻기
		DbConnection dc = new DbConnection();
		try {
		con=dc.getConn();
		//3. 쿼리문 생성객체 얻기
		stmt = con.createStatement();
		//4. 쿼리문 수행/ 결과 얻기
		StringBuilder createStudent = new StringBuilder();
		createStudent
		.append("	create table student(										")
		.append("	num number(5),											")
		.append("	name varchar2(30) not null,								")
		.append("	age number(3) check (age between 20 and 39 ),	")
		.append("	email varchar2(50),										")
		.append("	input_date date,											")
		.append("	constraint pk_student primary key(num)				")
		.append("	)																");
		
//		테이블이 생성되더다로 false가 나온다
//		boolean flag = stmt.execute( createStudent.toString() )
		stmt.execute( createStudent.toString() );
		System.out.println("테이블생성완료 ");
		
		} finally {
		//5. 연결끊기
			dc.closeDB(null, stmt, con);
		}//end finally
		
	}//createStudentTable
	
	public void createStudentTableIndex() throws SQLException {
		Connection con = null;
		Statement stmt = null;
		//1. 드라이버 연결
		//2. 커넥션얻기
		DbConnection dc = new DbConnection();
		try {
		con=dc.getConn();
		//3. 쿼리문 생성객체 얻기
		stmt = con.createStatement();
		//4. 쿼리문 수행/ 결과 얻기
		String createStudent = "	create index ind_date_student on student(input_date)";
		
//		boolean flag = stmt.execute( createStudent.toString() );
		stmt.execute( createStudent );
		System.out.println("인덱스가 생성되었다.");
		
		} finally {
		//5. 연결끊기
			dc.closeDB(null, stmt, con);
		}//end finally
		
	}//createStudentTableIndex
	
	public void insertStudent( StudentVO sVO )throws SQLException {
		//1. 드라이브 로딩
		Connection con = null;
		Statement stmt = null;
		
		DbConnection dc = new DbConnection();
		try {
		//2. 커넥션얻기
			con = dc.getConn();
		//3. 쿼리문 생성 객체 얻기
			stmt = con.createStatement();
		//4. 쿼리수행 후 결과 얻기
			StringBuilder insertStudent = new StringBuilder();
			insertStudent
			.append("insert into student(num, name, age, email, input_date)")
			.append("values(" ).append(sVO.getNum()).append(",'")
			.append(sVO.getName())	.append("',")
			.append(sVO.getAge()).append(",'")
			.append(sVO.getEmail()).append("', sysdate)")	;
			
			int rowCnt = stmt.executeUpdate(insertStudent.toString());
			System.out.println(rowCnt + "건 추가");
			
		} finally {
		//5. 연결끊기
			dc.closeDB(null, stmt, con);
		}
	}//insertStudent
	
	public void updateStudent( StudentVO sVO )throws SQLException {
		//1. 드라이브 로딩
		Connection con = null;
		Statement stmt = null;
		
		DbConnection dc = new DbConnection();
		try {
		//2. 커넥션얻기
			con = dc.getConn();
		//3. 쿼리문 생성 객체 얻기
			stmt = con.createStatement();
		//4. 쿼리수행 후 결과 얻기
			StringBuilder updateStudent = new StringBuilder();
			
			updateStudent
			.append("update student	")
			.append("set email='").append(sVO.getEmail()).append("'")
			.append("	where num=").append(sVO.getNum());
			
			int rowCnt = stmt.executeUpdate(updateStudent.toString());
			System.out.println( rowCnt );
			
		} finally {
		//5. 연결끊기
			dc.closeDB(null, stmt, con);
		}// end finally
	}//updateStudent
	
	public void deleteStudent( StudentVO sVO ) throws SQLException {
	//1. 드라이브 로딩
			Connection con = null;
			Statement stmt = null;
			
			DbConnection dc = new DbConnection();
			try {
			//2. 커넥션얻기
				con = dc.getConn();
			//3. 쿼리문 생성 객체 얻기
				stmt = con.createStatement();
			//4. 쿼리수행 후 결과 얻기
				StringBuilder deleteStudent = new StringBuilder();
				
				deleteStudent
				.append("Delete student		")
				.append("where num=").append(sVO.getNum());
				
				System.out.println(deleteStudent);
				
				int rowCnt = stmt.executeUpdate(deleteStudent.toString());
				System.out.println( rowCnt );
				
			} finally {
			//5. 연결끊기
				dc.closeDB(null, stmt, con);
			}// end finally
	}
	
}//class
