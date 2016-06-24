package ua.kas.clickky_Family_third;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {

	private SimpleStringProperty ip;
	private SimpleStringProperty os;
	private SimpleStringProperty browser;
	private SimpleStringProperty firstUrl;
	private SimpleStringProperty lastUrl;
	private SimpleIntegerProperty uniqueUrl;
	private SimpleStringProperty time;
	
	public Employee (String ip, String os, String browser, String firstUrl, String lastUrl, int uniqueUrl, String time){
		this.ip= new SimpleStringProperty(ip);
		this.os=new SimpleStringProperty(os);
		this.browser=new SimpleStringProperty(browser);
		this.firstUrl=new SimpleStringProperty(firstUrl);
		this.lastUrl=new SimpleStringProperty(lastUrl);
		this.uniqueUrl=new SimpleIntegerProperty(uniqueUrl);
		this.time= new SimpleStringProperty(time);
	}

	public String getTime() {
		return time.get();
	}

	public String getIp() {
		return ip.get();
	}

	public String getOs() {
		return os.get();
	}

	public String getBrowser() {
		return browser.get();
	}

	public String getFirstUrl() {
		return firstUrl.get();
	}

	public String getLastUrl() {
		return lastUrl.get();
	}

	public Integer getUniqueUrl() {
		return uniqueUrl.get();
	}

	
}
