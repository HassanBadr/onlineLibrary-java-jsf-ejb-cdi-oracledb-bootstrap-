package com.strong.megaapp.util;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class ManageLocale implements Serializable {

	private String locale="en";
	private String dir="ltr";
	
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	public void changeToENLocale() {
		setLocale("en");
		setDir("ltr");
	}
	
	public void changeToARLocale() {
		setLocale("ar");
		setDir("rtl");
	}
}
