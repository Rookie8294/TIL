package day0817;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ZipcodeSearchEvt extends MouseAdapter implements ActionListener {
	
	private ZipcodeSearchDesign zsd;
	
	public ZipcodeSearchEvt(ZipcodeSearchDesign zsd) {
		this.zsd = zsd;
		zsd.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				zsd.dispose();
			}
		});
	}//ZipcodeSearchEvt

	@Override
	public void actionPerformed(ActionEvent e) {
		searchZipcode();
		zsd.getJtfDong().setText("");
	}//actionPerformed
	
	
	@Override
	public void mouseClicked(MouseEvent me) {
//		if( me.getButton() == MouseEvent.BUTTON1 ) {
//			selectionZipcode();
//		}
		switch( me.getButton() ) {
		case MouseEvent.BUTTON1:
			selectionZipcode();
			break;			
		}//end
	}//mouseClie
		

	public void searchZipcode() {
		
		ZipcodeDAO zDAO = ZipcodeDAO.getInstance();
		
		String dong = zsd.getJtfDong().getText().trim();
		if( !"".equals(dong)) {
			try {
				List<ZipcodeVO> list = zDAO.selectZipcode(dong);
				if(list.isEmpty()) {
					JOptionPane.showMessageDialog(zsd,  dong + "은 존재하지 않습니다.");
					return;
				}//end if
				
				//Jtable에 설정된 모든 행을 삭제한다.
				zsd.getDtmZipcode().setRowCount(0);
				
				String[] rowData = null;
				StringBuilder addr = new StringBuilder();
				for(ZipcodeVO zVO : list) {
					// 1. 검색된 행을 채울 일차원 배열을 생성
					rowData = new String[2];
					// 2. 생성된 배열에 값을 설정
					rowData[0] = zVO.getZipcode();
					
					addr.append(zVO.getSido()).append(" ").append(zVO.getGugun())
					.append(" ").append(zVO.getDong()).append(" ")
					.append(zVO.getBunji());
					
					rowData[1] = addr.toString();
					addr.delete(0,  addr.length());
					
					// 값을 가진 일차원 배열을 JTable에 설정하기위해
					// Model 객체를 사용한다.
					zsd.getDtmZipcode().addRow(rowData);
					
				}//end for
				
			} catch (SQLException e) {
				e.printStackTrace();
			}//end catch
		}//end if
	}//searchZipcode
	
	public void selectionZipcode() {
		//테이블에서 선택한 행을 얻어와서
		JTable jtZipcode = zsd.getJtZipcode();
		DefaultTableModel dtm = zsd.getDtmZipcode();
		int row = jtZipcode.getSelectedRow();
		
		zsd.getJtfSelZipcode().setText((String)dtm.getValueAt(row, 0));
		zsd.getJtfSelAddr().setText((String)dtm.getValueAt(row, 1));
		
	}//selectionZipcode
	
	

}//class
