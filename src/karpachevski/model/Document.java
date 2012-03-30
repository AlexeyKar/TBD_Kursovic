package karpachevski.model;

import java.util.ArrayList;
import java.util.Calendar;

/*
 * Документы
 */

public class Document extends SuperEntity {

	private String title;  // название документа 
	private String templateOfDoc; // шаблон документа
	private Calendar dateOfSupply; // дата получения
	private ArrayList<Person> listOfSign; // список визировавших
	
	public Document() {
		super();
	}

	public Document(String title, String templateOfDoc, Calendar dateOfSupply,
			ArrayList<Person> listOfSign) {
		super();
		this.title = title;
		this.templateOfDoc = templateOfDoc;
		this.dateOfSupply = dateOfSupply;
		this.listOfSign = listOfSign;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTemplateOfDoc() {
		return templateOfDoc;
	}

	public void setTemplateOfDoc(String templateOfDoc) {
		this.templateOfDoc = templateOfDoc;
	}

	public Calendar getDateOfSupply() {
		return dateOfSupply;
	}

	public void setDateOfSupply(Calendar dateOfSupply) {
		this.dateOfSupply = dateOfSupply;
	}

	public ArrayList<Person> getListOfSign() {
		return listOfSign;
	}

	public void setListOfSign(ArrayList<Person> listOfSign) {
		this.listOfSign = listOfSign;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
}
