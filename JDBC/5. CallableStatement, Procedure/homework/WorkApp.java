package work0818;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class WorkApp extends JFrame{
	
	public WorkApp() {
		List<DataVO> list = null;
		
		String inputData = JOptionPane.showInputDialog("제조사");
		
		WorkDAO wDAO = WorkDAO.getInstance();
		try {
			list = wDAO.selectInfo(inputData);
			
			StringBuilder searchData = new StringBuilder();
			searchData
			.append("num\tcountry\tmaker\tmodel\tcar_year\tprice\tcar_option\n");
			
			int cnt = 0;
			for( DataVO dVO : list) {
				cnt++;
				searchData.append(cnt).append("\t")
				.append(dVO.getCountry()).append("\t")
				.append(dVO.getMaker()).append("\t")
				.append(dVO.getModel()).append("\t")
				.append(dVO.getCarYear()).append("\t")
				.append(dVO.getPrice()).append("\t")
				.append(dVO.getCarOption()).append("\t")
				.append("\n")
				;
			}//end for
			
			if( cnt == 0) {
				searchData.append(inputData).append(" 에 대한 데이터가 존재하지 않습니다.");
			}
			
			JTextArea jta = new JTextArea(searchData.toString(), 10, 50);
			JScrollPane jsp = new JScrollPane( jta );
			
			JOptionPane.showMessageDialog(null, jsp);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}
	
	public static void main(String[] args) {
		new WorkApp();
	}

}
