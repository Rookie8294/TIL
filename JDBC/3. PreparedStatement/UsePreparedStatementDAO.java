package kr.co.sist.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.dao.DbConn;
import kr.co.sist.statement.StudentVO;

public class UsePreparedStatementDAO {
	
	public void useInsertStudent() {
		
		String inputData = JOptionPane.showInputDialog("추가할 학생의, 이름, 나이, 이메일을 입력해주세요.");
		if( inputData != null ) {
			String[] arrData =  inputData.split(",");			
			String msg = "";
			try {
				StudentVO stuVO = new StudentVO(Integer.parseInt(arrData[0]), arrData[1], Integer.parseInt(arrData[2]), arrData[3], null);
				
				PreparedStatementDAO psDao = PreparedStatementDAO.getInstance();
				psDao.insertStudent(stuVO);
				msg=arrData[1] + "학생 정보가 성공적으 추가되었습니다.";
				
			} catch(NumberFormatException nfe ) {
				msg = "번호나 나이는 숫자이어야 합니다.";
			} catch(SQLException se) {
				se.printStackTrace();
				switch ( se.getErrorCode() ) {
				case 1 : msg =arrData[0] + "학번은 이미 존재합니다."; break;
				case 1438: msg = "학번은 5자리, 나이는 3자리를 초과할 수 없습니다"; break;
				case 12899: msg = "이름은 한글 10자, 이메일은 50글자를 초과할 수 없습니다."; break;
				default : msg = "ㅈㅅ";
				}//end switch
			}//end catch
			System.out.println(msg);
		}//end if
	}//useInsertStudent
	
	/**
	 * 학생번호, 나이, 이메일을 입력받아 번호에 해당하는 레코드를 변경
	 * @param stuVO 값을 가진 객체
	 * @return 변경된 레코드의 수
	 * @throws SQLException
	 */
	public void updateStudent( ){
		
		String inputData = JOptionPane.showInputDialog("변경할 학생의 나이, 이메일을 입력해주세요.");
		if( inputData != null ) {
			String[] arrData =  inputData.split(",");
			if( arrData.length != 3) {
				JOptionPane.showMessageDialog(null, "입력값의 수가 올바르지 않습니다. \n번호, 나이, 이메일을 입력해주세요.");
				return;
			}//end if
			System.out.println(arrData);
			String msg = "";
			try {
				StudentVO stuVO = new StudentVO(Integer.parseInt(arrData[0]), "", Integer.parseInt(arrData[1]), arrData[2], null);
				
				//DAO 객체 얻기
				PreparedStatementDAO psDao = PreparedStatementDAO.getInstance();
				//쿼리문을 실행
				int cnt = psDao.updateStudent(stuVO);
				if( cnt == 0 ) {
					msg =  arrData[1] + "학생번호를 확인해주세요.";
				} else {
					msg=arrData[1] + "학생정보가 변경되었습니다.";					
				}//end else
				
			} catch(NumberFormatException nfe ) {
				msg = "번호는 숫자이어야 합니다.";
			} catch(SQLException se) {
				se.printStackTrace();
				switch ( se.getErrorCode() ) {
				case 1438: msg = "나이는 3자리를 초과할 수 없습니다"; break;
				case 12899: msg = "이메일은 50글자를 초과할 수 없습니다."; break;
				default : msg = "ㅈㅅ";
				}//end switch
			}//end catch
			System.out.println(msg);
		}//end if
	}//updateStudent
	
	public void useDeleteStudent() {
		
		String inputData = JOptionPane.showInputDialog("삭제할 학생의 번호를 입력해주세요.");

			String msg = "";
			try {
				
				//DAO 객체 얻기
				PreparedStatementDAO psDao = PreparedStatementDAO.getInstance();
				//쿼리문을 실행
				int cnt = psDao.deleteStudent(Integer.parseInt(inputData));
				if( cnt == 0 ) {
					msg =  inputData + "학생번호를 확인해주세요.";
				} else {
					msg= inputData + "학생정보가 삭제되었습니다.";					
				}//end else
				
			} catch(NumberFormatException nfe ) {
				msg = "번호는 숫자이어야 합니다.";
			} catch(SQLException se) {
				se.printStackTrace();
				msg = "ㅈㅅ";
			}//end catch
			System.out.println(msg);
		
	}//useDeleteStudent
	
	public void useSelectOneStudent() {
		
		String inputData = JOptionPane.showInputDialog("조회할 학생의 번호를 입력해주세요.");

		String msg = "";
		try {
			
			//DAO 객체 얻기
			PreparedStatementDAO psDao = PreparedStatementDAO.getInstance();
			//쿼리문을 실행
			StudentVO stuVO = psDao.selectOneStudent(Integer.parseInt(inputData));
			if( stuVO == null ) {
				msg =  inputData + "학생번호를 확인해주세요.";
			} else {
				StringBuilder result = new StringBuilder();
				result.append("검색 결과\n이름 : ").append( stuVO.getName() )
				.append("(학번 ").append( stuVO.getNum()).append(")\n")
				.append("나이 : ").append(stuVO.getAge()).append("살\n")
				.append("이메일 : " ).append(stuVO.getEmail()).append("\n")
				.append("입력일 : ").append(stuVO.getInput_date());
				
				msg = result.toString();
			}//end else
			
		} catch(NumberFormatException nfe ) {
			msg = "번호는 숫자이어야 합니다.";
		} catch(SQLException se) {
			se.printStackTrace();
			msg = "ㅈㅅ";
		}//end catch
		JOptionPane.showMessageDialog(null, msg);
		
	}//useSelectOneStudent
	
	public void useSelectAllStudent() {
		PreparedStatementDAO psDAO = PreparedStatementDAO.getInstance();
		
		try {
			List<StudentVO> list = psDAO.selectAllStudent();
			
			StringBuilder result = new StringBuilder();
			JTextArea jta = new JTextArea();
			JScrollPane jsp = new JScrollPane(jta);
			if(list.isEmpty()) {
				result.append("학생정보가 없습니다.\n 학생정보를 추가해 주세요.");
			} else {
				jta.append("번호\t이름\t이메일\t입력일\n");
				for(StudentVO stuVO : list) {
					jta.append(stuVO.getNum() + "\t" + stuVO.getName() + "\t" + stuVO.getEmail() + "\t" + stuVO.getInput_date() + "\n");
				}//end for
			}//end else
			
			JOptionPane.showMessageDialog(null,
					result.length() == 0? jsp : result.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
		
		UsePreparedStatementDAO upsDAO = new UsePreparedStatementDAO();
		
//		upsDAO.updateStudent();
//		upsDAO.useDeleteStudent();
//		upsDAO.useSelectOneStudent();
		upsDAO.useSelectAllStudent();
		


		
//		StudentVO stu = new StudentVO(6, null, 26, "kim@tet.com", null);
//		try {
//			int cnt = upsDAO.updateStudent(stu);
//			if(cnt == 0) {
//				System.out.println("학생 번호를 확인해주세요");
//			} else {
//				System.out.println("변경성공");
//			}
//		} catch (SQLException e) {
//			System.out.println("변경실패");
//		}
		
		
//		upsDAO.useInsertStudent();
//		StudentVO stu = new StudentVO(6, "김주민2", 26, "kim@tet.com", null);
//		
//		PreparedStatementDAO psDAO = PreparedStatementDAO.getInstance();
//		try {
//			psDAO.insertStudent(stu);
//			System.out.println("추가성공");
//		} catch (SQLException e) {
//			e.printStackTrace();
//			System.out.println("추가실패");
//		}
		

		
	}

}
