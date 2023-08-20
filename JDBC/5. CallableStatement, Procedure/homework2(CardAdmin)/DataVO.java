package work0819;

public class DataVO {
	private String num;
	private String name;
	private String phone;
	private String email;
	private String inputDate;
	
	public DataVO() {
	}//DataVO
	
	public DataVO(String num, String name, String phone, String email, String inputDate) {
		super();
		this.num = num;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.inputDate = inputDate;
	}//DataVO


	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	
	
}//class
