package work0819;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class CardAdminView extends JFrame{
	
	//Label
	private JLabel jlNum;
	private JLabel jlName;
	private JLabel jlPhone;
	private JLabel jlEmail;
	private JLabel jlInputDate;
	
	//Field
	private JTextField jtfNum;
	private JTextField jtfName;
	private JTextField jtfPhone;
	private JTextField jtfEmail;
	private JTextField jtfInputDate;
	
	//Button
	private JButton jbtnAdd;
	private JButton jbtnModify;
	private JButton jbtnRemove;
	private JButton jbtnExit;
	private JButton jbtnReset;
	
	//Table
	private DefaultTableModel dtmCardInfo;
	private JTable jtCardInfo;
	private JScrollPane jspJtCardInfo;

	public CardAdminView() {
		super("명함관리");
		
		//Label
		jlNum = new JLabel("번호");
		jlName = new JLabel("이름");
		jlPhone = new JLabel("전화번호");
		jlEmail = new JLabel("이메일");
		jlInputDate = new JLabel("입력일");
		//Field
		jtfNum = new JTextField(10);
		jtfNum.setEditable(false); // read only
		jtfName = new JTextField(10);
		jtfPhone = new JTextField(10);
		jtfEmail = new JTextField(10);
		jtfInputDate = new JTextField(10);
		jtfInputDate.setEditable(false); // read only
		//Button
		jbtnAdd = new JButton("추가");
		jbtnModify = new JButton("변경");
		jbtnRemove = new JButton(" 삭제");
		jbtnExit = new JButton("종료");
		jbtnReset  = new JButton("리셋");
		//Table
		String[] columnNames = {"Number", "Name", "Phone", "Email", "Input Date"};
		dtmCardInfo = new DefaultTableModel(null, columnNames);
		jtCardInfo = new JTable(dtmCardInfo);
		jspJtCardInfo = new JScrollPane(jtCardInfo);
		
		//Component 크기,위치 설정
		//Label
		jlNum.setBounds(30, 30, 50, 20);
		jlName.setBounds(30, 60, 50, 20);
		jlPhone.setBounds(30, 90, 50, 20);
		jlEmail.setBounds(30, 120, 50, 20);
		jlInputDate.setBounds(30, 150, 50, 20);
		//Field
		jtfNum.setBounds(100, 30, 130, 20);
		jtfName.setBounds(100, 60, 130, 20);
		jtfPhone.setBounds(100, 90, 130, 20);
		jtfEmail.setBounds(100, 120, 130, 20);
		jtfInputDate.setBounds(100, 150, 130, 20);
		//Button
		jbtnAdd.setBounds(120,200,100,30);
		jbtnModify.setBounds(240,200,100,30);
		jbtnRemove.setBounds(360,200,100,30);
		jbtnExit.setBounds(480,200,100,30);
		jbtnReset.setBounds(600,200,100,30);
		//Table
		jspJtCardInfo.setBounds(250, 30, 500, 150);
		//Table Column크기 변경
		jtCardInfo.getColumnModel().getColumn(0).setPreferredWidth(20);
		jtCardInfo.getColumnModel().getColumn(1).setPreferredWidth(40);
		jtCardInfo.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		//Component추가
		//Label
		setLayout(null);
		add(jlNum);
		add(jlName);
		add(jlPhone);
		add(jlEmail);
		add(jlInputDate);
		//Field
		add(jtfNum);
		add(jtfName);
		add(jtfPhone);
		add(jtfEmail);
		add(jtfInputDate);
		//Button
		add(jbtnAdd);
		add(jbtnModify);
		add(jbtnRemove);
		add(jbtnExit);
		add(jbtnReset);
		//Table
		add(jspJtCardInfo);
		
		//Event
		CardAdminEvt caEvt = new CardAdminEvt(this);
		jbtnAdd.addActionListener(caEvt);
		jbtnModify.addActionListener(caEvt);
		jbtnRemove.addActionListener(caEvt);
		jbtnExit.addActionListener(caEvt);
		jbtnReset.addActionListener(caEvt);
		jtCardInfo.addMouseListener(caEvt);
		
		addWindowListener(caEvt);
		
		setBounds(100, 100, 800, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//View

	public JLabel getJlNum() {
		return jlNum;
	}

	public void setJlNum(JLabel jlNum) {
		this.jlNum = jlNum;
	}

	public JLabel getJlName() {
		return jlName;
	}

	public void setJlName(JLabel jlName) {
		this.jlName = jlName;
	}

	public JLabel getJlPhone() {
		return jlPhone;
	}

	public void setJlPhone(JLabel jlPhone) {
		this.jlPhone = jlPhone;
	}

	public JLabel getJlEmail() {
		return jlEmail;
	}

	public void setJlEmail(JLabel jlEmail) {
		this.jlEmail = jlEmail;
	}

	public JLabel getJlInputDate() {
		return jlInputDate;
	}

	public void setJlInputDate(JLabel jlInputDate) {
		this.jlInputDate = jlInputDate;
	}

	public JTextField getJtfNum() {
		return jtfNum;
	}

	public void setJtfNum(JTextField jtfNum) {
		this.jtfNum = jtfNum;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public void setJtfName(JTextField jtfName) {
		this.jtfName = jtfName;
	}

	public JTextField getJtfPhone() {
		return jtfPhone;
	}

	public void setJtfPhone(JTextField jtfPhone) {
		this.jtfPhone = jtfPhone;
	}

	public JTextField getJtfEmail() {
		return jtfEmail;
	}

	public void setJtfEmail(JTextField jtfEmail) {
		this.jtfEmail = jtfEmail;
	}

	public JTextField getJtfInputDate() {
		return jtfInputDate;
	}

	public void setJtfInputDate(JTextField jtfInputDate) {
		this.jtfInputDate = jtfInputDate;
	}

	public JButton getJbtnAdd() {
		return jbtnAdd;
	}

	public void setJbtnAdd(JButton jbtnAdd) {
		this.jbtnAdd = jbtnAdd;
	}

	public JButton getJbtnModify() {
		return jbtnModify;
	}

	public void setJbtnModify(JButton jbtnModify) {
		this.jbtnModify = jbtnModify;
	}

	public JButton getJbtnRemove() {
		return jbtnRemove;
	}

	public void setJbtnRemove(JButton jbtnRemove) {
		this.jbtnRemove = jbtnRemove;
	}

	public JButton getJbtnExit() {
		return jbtnExit;
	}

	public void setJbtnExit(JButton jbtnExit) {
		this.jbtnExit = jbtnExit;
	}

	public DefaultTableModel getDtmCardInfo() {
		return dtmCardInfo;
	}

	public void setDtmCardInfo(DefaultTableModel dtmCardInfo) {
		this.dtmCardInfo = dtmCardInfo;
	}

	public JTable getJtCardInfo() {
		return jtCardInfo;
	}

	public void setJtCardInfo(JTable jtCardInfo) {
		this.jtCardInfo = jtCardInfo;
	}

	public JScrollPane getJspJtCardInfo() {
		return jspJtCardInfo;
	}

	public void setJspJtCardInfo(JScrollPane jspJtCardInfo) {
		this.jspJtCardInfo = jspJtCardInfo;
	}

	public JButton getJbtnReset() {
		return jbtnReset;
	}

	public void setJbtnReset(JButton jbtnReset) {
		this.jbtnReset = jbtnReset;
	}
	

}//class
