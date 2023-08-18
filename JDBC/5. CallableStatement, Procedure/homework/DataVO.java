package work0818;

public class DataVO {
	
	private String country;
	private String maker;
	private String model;
	private String carYear;
	private String price;
	private String carOption;
	
	public DataVO() {
		super();
	}

	public DataVO(String country, String maker, String model, String carYear, String price, String carOption) {
		super();
		this.country = country;
		this.maker = maker;
		this.model = model;
		this.carYear = carYear;
		this.price = price;
		this.carOption = carOption;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCarYear() {
		return carYear;
	}

	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCarOption() {
		return carOption;
	}

	public void setCarOption(String carOption) {
		this.carOption = carOption;
	}
	
	
}//class
