package karpachevski.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;

/*
 * Документы
 */

public class Document extends SuperEntity {

	private String title;  // название документа 
	private Calendar dateOfSupply; // дата получения
	private Collection listOfSign; // список визировавших
	
	public Document() {
		super();
	}

	public Document(String title, Calendar dateOfSupply,
			ArrayList<Person> listOfSign) {
		super();
		this.title = title;
		this.dateOfSupply = dateOfSupply;
		this.listOfSign = listOfSign;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getDateOfSupply() {
		return dateOfSupply;
	}

	public void setDateOfSupply(Calendar dateOfSupply) {
		this.dateOfSupply = dateOfSupply;
	}

	public Collection getListOfSign() {
		return listOfSign;
	}

	public void setListOfSign(Collection listOfSign) {
		this.listOfSign = listOfSign;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
}
