package work0816;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	public static final String IMG_PATH = "E:\\dev\\workspace\\jdbc_prj\\src\\kr\\co\\sist\\work\\img\\";
	public static final String IMG_EXTENSION = ".png";
	
	private JLabel labelName;
	private JLabel labelImg;
	private JLabel labelAge;
	private JLabel labelNum;
	private JLabel labelView;
	
	private JTextField jtfName;
	private JComboBox<Integer> boxImg;
	private JTextField jtfAge;
	private JTextField jtfNum;
	
	private JButton insertBtn;
	private JButton searchBtn;
	
	private ImageIcon img;
	
	public View() {
		super("숙제");
		
		labelName = new JLabel("이름");
		labelImg = new JLabel("이미지");
		labelAge = new JLabel("나이");
		labelNum = new JLabel("번호");
		labelView = new JLabel();
		
		jtfName = new JTextField(20);
		boxImg = new JComboBox<Integer>();
		boxImg.addItem(1);
		boxImg.addItem(2);
		boxImg.addItem(3);
		boxImg.addItem(4);
		
		jtfAge = new JTextField(20);
		jtfNum = new JTextField(20);
		
		insertBtn = new JButton("정보추가");
		searchBtn = new JButton("정보검색");
		
		img = new ImageIcon(IMG_PATH + "defualt" + IMG_EXTENSION);
		labelView.setIcon(img);
		
		//Event추가
		Controller c = new Controller(this);
		insertBtn.addActionListener(c);
		searchBtn.addActionListener(c);
		boxImg.addActionListener(c);
		addWindowListener(c);
		
		setLayout(null);
		//Label
		labelName.setBounds(30,30,30,30);
		labelImg.setBounds(30,60,50,30);
		labelAge.setBounds(30,90,50,30);
		labelNum.setBounds(30,120,50,30);
		labelView.setBounds(330, 30, 250, 160);
		//Field, comboBox
		jtfName.setBounds(100, 30, 200, 30);
		boxImg.setBounds(100, 60, 200, 30);
		jtfAge.setBounds(100, 90, 200, 30);
		jtfNum.setBounds(100, 120, 200, 30);
		//Button
		insertBtn.setBounds(100, 150, 100, 40 );
		searchBtn.setBounds(200, 150, 100, 40 );
		
		//컴포넌트 추가
		add(labelName);
		add(labelImg);
		add(labelAge);
		add(labelNum);
		add(labelView);
		
		add(jtfName);
		add(boxImg);
		add(jtfAge);
		add(jtfNum);
		
		add(insertBtn);
		add(searchBtn);
		
		setSize(600,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JLabel getLabelName() {
		return labelName;
	}

	public void setLabelName(JLabel labelName) {
		this.labelName = labelName;
	}

	public JLabel getLabelImg() {
		return labelImg;
	}

	public void setLabelImg(JLabel labelImg) {
		this.labelImg = labelImg;
	}

	public JLabel getLabelAge() {
		return labelAge;
	}

	public void setLabelAge(JLabel labelAge) {
		this.labelAge = labelAge;
	}

	public JLabel getLabelNum() {
		return labelNum;
	}

	public void setLabelNum(JLabel labelNum) {
		this.labelNum = labelNum;
	}

	public JLabel getLabelView() {
		return labelView;
	}

	public void setLabelView(JLabel labelView) {
		this.labelView = labelView;
	}

	public JTextField getJtfName() {
		return jtfName;
	}

	public void setJtfName(JTextField jtfName) {
		this.jtfName = jtfName;
	}


	public JComboBox<Integer> getBoxImg() {
		return boxImg;
	}

	public void setBoxImg(JComboBox<Integer> boxImg) {
		this.boxImg = boxImg;
	}

	public JTextField getJtfAge() {
		return jtfAge;
	}

	public void setJtfAge(JTextField jtfAge) {
		this.jtfAge = jtfAge;
	}

	public JTextField getJtfNum() {
		return jtfNum;
	}

	public void setJtfNum(JTextField jtfNum) {
		this.jtfNum = jtfNum;
	}

	public JButton getInsertBtn() {
		return insertBtn;
	}

	public void setInsertBtn(JButton insertBtn) {
		this.insertBtn = insertBtn;
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(JButton searchBtn) {
		this.searchBtn = searchBtn;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}
	
	
}
