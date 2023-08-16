package work0816;

public class WorkVO {
	
	private String name;
	private int img;
	private int age;
	private int num;
	
public WorkVO() {
		
	}
	
	public WorkVO(String name, int image, int age, int num) {
		super();
		this.name = name;
		this.img = image;
		this.age = age;
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getImg() {
		return img;
	}

	public void setImg(int image) {
		this.img = image;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}



}
