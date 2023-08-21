package work0821;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class InfoView extends JFrame{
	private JLabel jlEmpno;
	private JLabel jlEname;
	private JLabel jlHiredate;
	private JLabel jlJob;
	private JLabel jlSal;
	
	private JTextField jtfEmpno;
	private JTextField jtfEname;
	private JTextField jtfHiredate;
	private JTextField jtfJob;
	private JTextField jtfSal;
	
	public InfoView(InfoVO iVO) {
		
		jlEmpno = new JLabel("사원번호");
		jlEname = new JLabel("사원명");
		jlHiredate = new JLabel("입사일");
		jlJob = new JLabel("직무");
		jlSal = new JLabel("연봉");
		
		jtfEmpno = new JTextField(10);
		jtfEname = new JTextField(10);
		jtfHiredate = new JTextField(10);
		jtfJob = new JTextField(10);
		jtfSal = new JTextField(10);
		
		jtfEmpno.setText(iVO.getEmpno());
		jtfEname.setText(iVO.getEname());
		jtfHiredate.setText(iVO.getHiredate());
		jtfJob.setText(iVO.getJob());
		jtfSal.setText(iVO.getSal());
		
		
		jlEmpno.setBounds(20, 20, 50, 20);
		jlEname.setBounds(20, 50, 50, 20);
		jlHiredate.setBounds(20, 80, 50, 20);
		jlJob.setBounds(20, 110, 50, 20);
		jlSal.setBounds(20, 140, 50, 20);
		
		jtfEmpno.setBounds(70, 20, 150, 20);
		jtfEname.setBounds(70, 50, 150, 20);
		jtfHiredate.setBounds(70, 80, 150, 20);
		jtfJob.setBounds(70, 110, 150, 20);
		jtfSal.setBounds(70, 140, 150, 20);
		
		InfoEvt iEvt = new InfoEvt(this);
		addWindowListener(iEvt);
	
		setLayout(null);
		
		add(jlEmpno);
		add(jlEname);
		add(jlHiredate);
		add(jlJob);
		add(jlSal);
		
		add(jtfEmpno);
		add(jtfEname);
		add(jtfHiredate);
		add(jtfJob);
		add(jtfSal);
		
		
		setBounds(500, 300, 250, 250);
		setVisible(true);
	}//infoView

	public JLabel getJlEmpno() {
		return jlEmpno;
	}

	public JLabel getJlEname() {
		return jlEname;
	}

	public JLabel getJlHiredate() {
		return jlHiredate;
	}

	public JLabel getJlJob() {
		return jlJob;
	}

	public JLabel getJlSal() {
		return jlSal;
	}

	public JTextField getJtfEmpno() {
		return jtfEmpno;
	}

	public JTextField getJtfEname() {
		return jtfEname;
	}

	public JTextField getJtfHiredate() {
		return jtfHiredate;
	}

	public JTextField getJtfJob() {
		return jtfJob;
	}

	public JTextField getJtfSal() {
		return jtfSal;
	}
	
	
}//class
