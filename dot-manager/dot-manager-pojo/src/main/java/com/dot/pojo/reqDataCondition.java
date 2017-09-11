package com.dot.pojo;

public class reqDataCondition {
	private String mac;
	private String type;
	private String dateFrom;
	private String dateEnd;
	private String timeFrom;
	private String timeEnd;
	
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
	public String getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}
	@Override
	public String toString() {
		return "reqDataCondition [mac=" + mac + ", type=" + type + ", dateFrom=" + dateFrom + ", dateEnd=" + dateEnd
				+ ", timeFrom=" + timeFrom + ", timeEnd=" + timeEnd + "]";
	}
	
	
	
}
