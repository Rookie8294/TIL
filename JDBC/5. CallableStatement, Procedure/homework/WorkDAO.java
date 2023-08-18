package work0818;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConn;

public class WorkDAO {
	
	private static WorkDAO wDAO;
	
	private WorkDAO() {
	}//WorkDAO
	
	public static WorkDAO getInstance() {
		if( wDAO == null ) {
			wDAO = new WorkDAO();
		}//end if
		return wDAO;
	}//getInstance
	
	public List<DataVO> selectInfo(String maker) throws SQLException {
		List<DataVO> list = new ArrayList<DataVO>();
		DataVO dVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			
			con = db.getConnection("localhost", "scott", "tiger");
			
			StringBuilder select = new StringBuilder();
			select
			.append("	select country, maker, model, car_year, price, car_option	")
			.append("	from(select cc.country country, cc.maker maker, cm.model model,	")
			.append("	cm.car_year car_year, cm.price price, cm.car_option car_option	")
			.append("	from car_model cm, car_country cc, car_maker cmk	")
			.append("	where cmk.maker = cc.maker and cm.model = cmk.model)	")
			.append("	where  maker = ?	")
			;
			
			pstmt = con.prepareStatement(select.toString());
			
			pstmt.setString(1, maker);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				dVO = new DataVO();
				dVO.setCountry(rs.getString("country"));
				dVO.setMaker(rs.getString("maker"));
				dVO.setModel(rs.getString("model"));
				dVO.setCarYear(rs.getString("car_year"));
				dVO.setPrice(rs.getString("price"));
				dVO.setCarOption(rs.getString("car_option"));
				
				list.add(dVO);
			}//end while
			
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		
		return list;
		
	}//selectInfo

}//class
