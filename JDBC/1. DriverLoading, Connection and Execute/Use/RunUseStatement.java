package kr.co.sist.statement;

import java.sql.SQLException;

public class RunUseStatement {
	
	public RunUseStatement() {
		
	}//RunUseStatement

	public void useCreateStudentTable() {
		UseStatementDAO usDAO = new UseStatementDAO();
		try {
			usDAO.createStudentTable();		//테이블생성
			usDAO.createStudentTableIndex();//인덱스생성
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//useCreateStudentTable
	
	public void useInsertStudent() {
		StudentVO stu = new StudentVO(3, "강다연", 25, "kang@test.com", null);
		UseStatementDAO usDAO = new UseStatementDAO();
		

		try {
			usDAO.insertStudent(stu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}// useInsertStudent
	
	public void useUpdateStudent() {
		StudentVO stu = new StudentVO(2, "", 0, "test@test.com", null);
		UseStatementDAO usDAO = new UseStatementDAO();
		
		
		
		try {
			usDAO.updateStudent(stu);
//			if(cnt ==0) {
//				System.out.println("dd");
//			} else {
//				System.out.println("df");
//			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void useDeleteStudent() {
		StudentVO stu = new StudentVO(3, "", 0, "", null);
		UseStatementDAO usDAO = new UseStatementDAO();
		
		try {
			usDAO.deleteStudent(stu);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public static void main(String[] args) {
		RunUseStatement rus = new RunUseStatement();
//		rus.useCreateStudentTable();// 테이블/인덱스 생성
//		rus.useInsertStudent();
//		rus.useUpdateStudent();
		rus.useDeleteStudent();
	}//main

}//class
