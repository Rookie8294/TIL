package kr.co.sist.statement;

import java.sql.Date;

public class StudentVO {
	private int num;
	private String name;
	private int age;
	private String email;
	private Date input_date;
	
	public StudentVO() {
	}
	public StudentVO(int num, String name, int age, String email, Date input_date) {
		super();
		this.num = num;
		this.name = name;
		this.age = age;
		this.email = email;
		this.input_date = input_date;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getInput_date() {
		return input_date;
	}
	public void setInput_date(Date input_date) {
		this.input_date = input_date;
	}
	
	@Override
	public String toString() {
		return "StudentVO [num=" + num + ", name=" + name + ", age=" + age + ", email=" + email + ", input_date="
				+ input_date + "]";
	}
	
	
}
 