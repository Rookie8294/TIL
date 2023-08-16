package work0816;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Controller extends WindowAdapter implements ActionListener{
	
	private View view;
	
	public Controller(View view) {
		this.view = view;
	}//Controller
	
	@Override
	public void windowClosing(WindowEvent e) {
		view.dispose();
	}//windowClosing

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == view.getInsertBtn() ) {
			insertInfo();
		}
		if( e.getSource() == view.getSearchBtn()) {
			searchInfo();
		}
	}//actionPerformed
	
	public void insertInfo() {
		
		DAO dao = DAO.getInstance();
	
		try {
			String name = view.getJtfName().getText();
			int img = Integer.parseInt(view.getBoxImg().getSelectedItem().toString());
			int num =  Integer.parseInt( view.getJtfNum().getText());
			int age =  Integer.parseInt(view.getJtfAge().getText());
			
			WorkVO wVO = new WorkVO(name, img, age, num);
			
			dao.updateInfo(wVO);
			JOptionPane.showMessageDialog(view, "정보가 추가되었습니다.");
			
			view.getJtfName().setText("");
			view.getJtfAge().setText("");
			view.getJtfNum().setText("");
			
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(view, "입력값을 확인해주세요.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(view, "정보 추가 실패.");
		}//end catch
		
	}//InsertInfo
	
	public void searchInfo() {
		
		try {
			int num = Integer.parseInt(view.getJtfNum().getText());
			
			WorkVO wVO = DAO.getInstance().selectInfo(num);
			
			if( wVO != null) {
				
				view.getJtfName().setText(wVO.getName());
				view.getJtfAge().setText( String.valueOf(wVO.getAge()));
				view.getJtfNum().setText(String.valueOf(wVO.getNum()));
				
				ImageIcon img = new ImageIcon( View.IMG_PATH + wVO.getImg() + View.IMG_EXTENSION  );
				view.getLabelView().setIcon(img);
				
			} else {
				JOptionPane.showMessageDialog(view, "존재하지 않는 번호입니다 다시 확인해주세요.");
			}//end else
			
		} catch (NumberFormatException nfe ) {
			JOptionPane.showMessageDialog(view, "입력값을 확인해주세요.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(view, "정보검색 실패.");
		}//end catch
		
	}//searchInfo

}//Controller
