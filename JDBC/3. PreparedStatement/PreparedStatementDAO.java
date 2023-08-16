package kr.co.sist.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConn;
import kr.co.sist.statement.StudentVO;

public class PreparedStatementDAO {
	
	private static PreparedStatementDAO psDAO;
	
	private PreparedStatementDAO(){		
	}//PreparedStatementDAO
	
	public static PreparedStatementDAO getInstance() {
		if( psDAO == null ) {
			psDAO = new PreparedStatementDAO();			
		}		
		return psDAO;
	}//getInstance
	
	public void insertStudent(StudentVO stuVO) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		//1. 드라이버로딩
		//2. 커넥션어딕
			con = db.getConnection("localhost", "scott", "tiger");
		//3. 쿼리문생성객체 얻기
			String insertStudent = "insert into student(num, name, age, email, input_date) values(?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(insertStudent);
		//4. 바인드 변수값 설정
			pstmt.setInt(1, stuVO.getNum());
			pstmt.setString(2, stuVO.getName());
			pstmt.setInt(3, stuVO.getAge());
			pstmt.setString(4, stuVO.getEmail());
		//5. 쿼리문 수행 후 결과 얻기
			pstmt.executeUpdate();
		} finally {
			//6. 연결 끊기
			db.dbClose(null, pstmt, con);
		}
	}//insertStudent
	
	/**
	 * 학생번호, 나이, 이메일을 입력받아 번호에 해당하는 레코드를 변경
	 * @param stuVO 값을 가진 객체
	 * @return 변경된 레코드의 수
	 * @throws SQLException
	 */
	public int updateStudent( StudentVO stuVO) throws SQLException {
		int rowCnt = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		DbConn db = DbConn.getInstance();
		
		try {
		//1. 드라이버로딩
		//2. 커넥션얻기
			con = db.getConnection("localhost", "scott", "tiger");
		//3. 쿼리생성객체얻기
			StringBuilder updateStudent = new StringBuilder();
			updateStudent
			.append("		update student	")
			.append("		set age=?, email=? 	")
			.append("		where num=?	");
		//4. 바인드 값 설정 
			pstmt = con.prepareStatement(updateStudent.toString());
			pstmt.setInt(1, stuVO.getAge());
			pstmt.setString(2, stuVO.getEmail());
			pstmt.setInt(3,  stuVO.getNum());			
		//5. 쿼리문 실행후 결과얻기
			rowCnt = pstmt.executeUpdate();
		} finally {
		//6. 연결 끊기
			db.dbClose(null, pstmt, con);
		}//end finally
		return rowCnt;
	}//updateStudent
	
	/**
	 * 학생번호를 입력받아 학생레코드를 삭제
	 * @param stuNum
	 * @throws SQLException
	 */
	public int deleteStudent(int stuNum) throws SQLException{
		int rowCnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConn db = DbConn.getInstance();
		try {
			//1.
			//2.
				con = db.getConnection("localhost", "scott", "tiger");
			//3.
				String deleteStudent = "delete from student where num = ?";
				pstmt = con.prepareStatement(deleteStudent);
			//4.
				pstmt.setInt(1,  stuNum);
			//5.
				rowCnt = pstmt.executeUpdate();
		} finally {
			//6.
			db.dbClose(null, pstmt, con);
		}
		
		return rowCnt;
	}//deleteStudent
	
	public StudentVO selectOneStudent(int stuNum) throws SQLException {
		StudentVO stuVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();		
		
		try {
		//1.
		//2.
			con = db.getConnection("localhost", "scott", "tiger");
		//3.
			StringBuilder selectStudent = new StringBuilder();
			selectStudent
			.append("	select	name, age, email, input_date	")
			.append("	from	student	")
			.append("	where		num=?	");
			
			pstmt = con.prepareStatement(selectStudent.toString());
		//4.
			pstmt.setInt(1, stuNum);
		//5.
			rs = pstmt.executeQuery();
			
			if( rs.next() ) {//학생의 번호로 검색된 레코드가 존재?
				stuVO = new StudentVO();
				stuVO.setName(rs.getString("name"));
				stuVO.setAge(rs.getInt("age"));
				stuVO.setEmail(rs.getString("email"));
				stuVO.setInput_date(rs.getDate("input_date"));
			}//end if
		} finally {
		//6.
			db.dbClose(rs, pstmt, con);			
		}//end finally
		
		return stuVO;
	}//selectOneStudent
	
	public List<StudentVO> selectAllStudent() throws SQLException{
		List<StudentVO> list = new ArrayList<StudentVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		//1.
		//2.
			con = db.getConnection("localhost", "scott", "tiger");
		//3.
			String selectAllStudent = "select num, name, age, email, input_date from student";
			pstmt = con.prepareStatement(selectAllStudent);
		//4. 바인드 변수 없음 
		//5.
			rs = pstmt.executeQuery();
			
			StudentVO stuVO = null;
			
			while( rs.next() ) {
				stuVO = new StudentVO();
				stuVO.setNum( rs.getInt("num"));
				stuVO.setName(rs.getString("name"));
				stuVO.setAge(rs.getInt("age"));
				stuVO.setEmail(rs.getString("email"));
				stuVO.setInput_date(rs.getDate("input_date"));
				
				list.add(stuVO);
			}//end while
			
		} finally {
		//6.
			db.dbClose(rs, pstmt, con);			
		}
		
		return list;
	}
	
//	public static void main(String[] args) {
//		
//		try {
//			getInstance().selectAllStudent();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
}//class
