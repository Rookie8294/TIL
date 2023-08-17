package work0817;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TableView extends JFrame {
	
	private JComboBox<String> jcbTableName;
	private JTable jtTableView;
	private DefaultTableModel dtmTable;
	private JScrollPane jspJtTableView;
	
	public TableView(){
		super("테이블 구조");
		jcbTableName = new JComboBox<String>();
		try {
			List<String> tableList =  TableViewDAO.getInstance().selectAllTableName();
			for( String name : tableList ) {
				jcbTableName.addItem(name);
			}	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "테이블 목록을 읽어오는데 실패하였습니다.");
		}//end catch
		
		String[] columnNames = {"컬럼명", "데이터형", "크기", "nullable", "default여부"};
		dtmTable = new DefaultTableModel(null, columnNames);
		jtTableView = new JTable( dtmTable );
		jspJtTableView = new JScrollPane(jtTableView);
		
		TableViewEvt evt = new TableViewEvt(this);
		jcbTableName.addActionListener(evt);
		addWindowListener(evt);
		
		JPanel jpNorth = new JPanel();
		jpNorth.add(jcbTableName);
		
		add("North", jpNorth);
		add("Center", jspJtTableView);
		
		setBounds(100, 100, 500, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//TableView

	public JComboBox<String> getJcbTableName() {
		return jcbTableName;
	}

	public JTable getJtTableView() {
		return jtTableView;
	}

	public DefaultTableModel getDtmTable() {
		return dtmTable;
	}

	public JScrollPane getJspJtTableView() {
		return jspJtTableView;
	}
	
	
	
}//class
