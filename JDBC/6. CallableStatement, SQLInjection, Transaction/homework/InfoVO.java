package work0821;


public class InfoVO {
	private String empno;
	private String ename;
	private String hiredate;
	private String job;
	private String sal;
	
	public InfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InfoVO(String empno, String ename, String hiredate, String job, String sal) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.hiredate = hiredate;
		this.job = job;
		this.sal = sal;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public void setSal(String sal) {
		this.sal = sal;
	}
	public String getEmpno() {
		return empno;
	}
	public String getEname() {
		return ename;
	}
	public String getHiredate() {
		return hiredate;
	}
	public String getJob() {
		return job;
	}
	public String getSal() {
		return sal;
	}
}
