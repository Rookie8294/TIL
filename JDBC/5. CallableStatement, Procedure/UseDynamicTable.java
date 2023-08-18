package day0818;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.directory.SearchControls;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import kr.co.sist.dao.DbConn;

public class UseDynamicTable {
	
	public UseDynamicTable()throws SQLException{
		
		String inputData = JOptionPane.showInputDialog("본사나 지사를 입력해주세요.").trim();
	
		
		if( !("본사".equals(inputData)||"지사".equals(inputData)) ) {
			JOptionPane.showMessageDialog(null, "본사나 지사만 입력해주세요.");
			return;
		}//end if
		
		if(inputData.equals("본사")) {
			inputData = "emp";
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
		//1.
		//2.
			con = db.getConnection("localhost", "scott", "tiger");
		//3.
			String selectEmp = "select empno, ename,deptno,to_char(hiredate, 'yyyy-mm-dd q\"분기\"') hiredate from ?";
//			StringBuilder selectEmp = new StringBuilder();
//			selectEmp
//			.append("select empno, ename,deptno, to_char(hiredate, 'yyyy-mm-dd q\"분기\"') hiredate from ")
//			.append(inputData.equals("본사")? "emp" : "cp_emp");
			pstmt = con.prepareStatement(selectEmp.toString());
		//4.
			pstmt.setString(1, inputData);
		//5.
//			rs = pstmt.executeQuery();
			rs = pstmt.executeQuery( selectEmp.toString() );
			
			StringBuilder searchEmp = new StringBuilder();
			searchEmp.append(inputData + "로 검색한 결과 \n");
			searchEmp.append("번호\t사원번호\t사원명\t부서번호\t입사일\n");
			
			int cnt = 0;
			while( rs.next() ) {
				cnt++;
				searchEmp.append(cnt).append("\t").append(rs.getInt("empno"))
				.append("\t").append(rs.getString("ename"))
				.append("\t").append(rs.getInt("deptno"))
				.append("\t").append(rs.getString("hiredate")).append("\n");
				
			}//end while
			
			if( cnt == 0) {
				searchEmp.append("사원정보가 존재하지 않습니다");
			}
			
			JTextArea jta = new JTextArea(searchEmp.toString(), 10, 50);
			JScrollPane jsp = new JScrollPane( jta ); 
			
			JOptionPane.showMessageDialog(null, jsp);
			
		} finally {
			//6.
			db.dbClose(rs, pstmt, con);
		}
		
	}//UseDynamicTable

	public static void main(String[] args) {
		try {
			new UseDynamicTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
