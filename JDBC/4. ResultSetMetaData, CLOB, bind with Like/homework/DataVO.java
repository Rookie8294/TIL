package work0817;

public class DataVO {
	
	private String columnName;
	private String dataType;
	private String dataLength;
	private String dataPrecision;
	private String nullable;
	private String dataDefault;
	
	public DataVO() {
		super();
	}

	public DataVO(String columnName, String dataType, String dataLength, String dataPrecision, String nullable, String dataDefault) {
		super();
		this.columnName = columnName;
		this.dataType = dataType;
		this.dataLength = dataLength;
		this.dataPrecision = dataPrecision;
		this.nullable = nullable;
		this.dataDefault = dataDefault;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDataLength() {
		return dataLength;
	}

	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}

	public String getDataPrecision() {
		return dataPrecision;
	}

	public void setDataPrecision(String dataPrecision) {
		this.dataPrecision = dataPrecision;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getDataDefault() {
		return dataDefault;
	}
	
	public void setDataDefault(String dataDefault) {
		this.dataDefault = dataDefault;
	}
	
	
}
