package work0814;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


@SuppressWarnings("serial")
public class View extends JFrame {
	
	public View() {
		super("테이블선택");
		
		ButtonGroup bg = new ButtonGroup();
		JPanel panel = new JPanel();
		List<String> tableList = null;
		
		try {
			tableList = new StatementDAO().selectAllTableName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Collections.sort(tableList);
		
		for(int i = 0 ; i < tableList.size(); i++) {
			JRadioButton btn = new JRadioButton(tableList.get(i));
			bg.add(btn);
			panel.add(btn);
		}
		
		add(panel);
		
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
	}

}
