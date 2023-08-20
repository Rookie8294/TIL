package work0819;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CardAdminEvt extends WindowAdapter implements ActionListener, MouseListener {
	
	private CardAdminView caView;

	public CardAdminEvt(CardAdminView caView) {
		this.caView = caView;
		defaultSetNum();
		defaultSetTable();
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		caView.dispose();
	}//windowClosing
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == caView.getJbtnAdd() ) {
			addCardInfo();
			defaultSetNum();
			defaultSetTable();
		}//end if
		
		if( e.getSource() == caView.getJbtnRemove() ) {
			removeCardInfo();
			defaultSetNum();
			defaultSetTable();
		}//end if
		
		if( e.getSource() == caView.getJbtnModify() ) {
			modifyCardInfo();
			defaultSetTable();
		}//end if
		
		if(e.getSource() == caView.getJbtnExit() ) {
			caView.dispose();
		}//end if
		
		if(e.getSource() == caView.getJbtnReset() ) {
			reset();
		}
	}//actionPerformed

	public void addCardInfo() {
		CardAdminDAO caDAO = CardAdminDAO.getInstance();
		
		String name = caView.getJtfName().getText();
		String phone = caView.getJtfPhone().getText();
		String email = caView.getJtfEmail().getText();
		
		try {
			if( name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
				JOptionPane.showMessageDialog(caView, "이름 / 핸드폰번호 / 이메일은 필수로 입력해야 합니다.");
				return;
			}//end if
			if( isValidInfo() ) {
				caDAO.insert(name, phone, email);
				JOptionPane.showMessageDialog(caView, "정보를 추가하였습니다.");
				
				defaultSetNum();
				caView.getJtfName().setText("");
				caView.getJtfPhone().setText("");
				caView.getJtfEmail().setText("");
				
				
			} else {
				JOptionPane.showMessageDialog(caView, "이미 사용중인 명함을 선택하였습니다 리셋 후 추가해주세요.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(caView, "정보를 추가하는데 실패하였습니다.");
		}
		
	}//addCardInfo
	
	public void modifyCardInfo() {
		CardAdminDAO caDAO = CardAdminDAO.getInstance();
		
		String num = caView.getJtfNum().getText();
		String name = caView.getJtfName().getText().trim();
		String phone = caView.getJtfPhone().getText().trim();
		String email = caView.getJtfEmail().getText().trim();
		
		try {
			if( name.isEmpty() || phone.isEmpty() || email.isEmpty()) {
				JOptionPane.showMessageDialog(caView, "이름 / 핸드폰번호 / 이메일은 필수로 입력해야 합니다.");
			} else {
				caDAO.update(num, name, phone, email);
				JOptionPane.showMessageDialog(caView, "정보가 수정되었습니다.");
			}//end else
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(caView, "정보를 수정하는데 실패하였습니다.");
		}
	}//modifyCardInfo
	
	public void removeCardInfo() {
		CardAdminDAO caDAO = CardAdminDAO.getInstance();
		
		String num = caView.getJtfNum().getText();
		
		try {
			caDAO.delete(num);
			
			JOptionPane.showMessageDialog(caView, "정보가 삭제되었습니다.");
			
			caView.getJtfName().setText("");
			caView.getJtfPhone().setText("");
			caView.getJtfEmail().setText("");
			caView.getJtfInputDate().setText("");
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(caView, "정보를 삭제하는데 실패하였습니다.");
		}
	}
	
	public void defaultSetNum() {
		CardAdminDAO caDAO = CardAdminDAO.getInstance();
		
		try {
			int num = caDAO.selectNextNum();
			caView.getJtfNum().setText( String.valueOf(num + 1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//defaultSetNum
	
	public void defaultSetTable() {
		CardAdminDAO caDAO = CardAdminDAO.getInstance();
		
		try {
			List<DataVO> cardList = caDAO.selectAllInfo();
			
			//테이블 모든 행 삭제
			caView.getDtmCardInfo().setRowCount(0);
			
			//테이블 row 추가
			String[] rowData = null;
			for( DataVO dVO : cardList ) {
				rowData = new String[5];
				
				rowData[0] = dVO.getNum();
				rowData[1] = dVO.getName();
				rowData[2] = dVO.getPhone();
				rowData[3] = dVO.getEmail();
				rowData[4] = dVO.getInputDate();
				
				caView.getDtmCardInfo().addRow(rowData);
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//defaultSetTable
	
	public void selectedInfo() {
		int row = caView.getJtCardInfo().getSelectedRow();
		DefaultTableModel dtm = caView.getDtmCardInfo();
		
		caView.getJtfNum().setText((String)dtm.getValueAt(row, 0));
		caView.getJtfName().setText((String)dtm.getValueAt(row, 1));
		caView.getJtfPhone().setText((String)dtm.getValueAt(row, 2));
		caView.getJtfEmail().setText((String)dtm.getValueAt(row, 3));
		caView.getJtfInputDate().setText((String)dtm.getValueAt(row, 4));
		
	}//selectedInfo
	
	public boolean isValidInfo() {
		boolean flag = false;
		DataVO dVO = null;
		
		String num = caView.getJtfNum().getText();
		
		CardAdminDAO caDAO = CardAdminDAO.getInstance();
		
		try {
			dVO = caDAO.selectOneInfo(num);
			
			if(dVO == null) {
				flag = true;
			} else {
				flag = false;
			}//end else
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		return flag;
	}//isValidInfo
	
	public void reset() {
		defaultSetNum();
		caView.getJtfName().setText("");
		caView.getJtfPhone().setText("");
		caView.getJtfEmail().setText("");
		caView.getJtfInputDate().setText("");
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		switch( e.getButton() ) {
		case MouseEvent.BUTTON1 :
			selectedInfo();
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}//class
