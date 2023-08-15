package day0814;

import java.sql.Connection;
import java.sql.SQLException;

import kr.co.sist.dao.DbConn;

public class UseSingle {

	public static void main(String[] args) {
		Singleton single = Singleton.getInstance();
		Singleton single2 = Singleton.getInstance();
		
		
		System.out.println(single + " / " + single2);
		System.out.println(single == single2);
		
		try {
			Connection con = DbConn.getInstance().getConnection("localhost", "scott", "tiger");
			System.out.println(con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		
	}

}
