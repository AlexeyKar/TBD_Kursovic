package karpachevski.model;

/*
 * Люди, характеризуются именем, фамилией и отчеством
 */

public class Person extends SuperEntity {

	private String name;  // имя аспиратна/докторанта
	private String surname; // фамилия 
	private String middleName; // отчество
	
	public Person() {
		super();
	}
	
	public Person(String name, String surname, String middleName) {
		super();
		this.name = name;
		this.surname = surname;
		this.middleName = middleName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	@Override
	public String toString() {
		return surname + " " + name + " " + middleName;
	}
	
}
