package karpachevski.model;

/*
 * Статус характеризуется названием
 */

public class Status extends SuperEntity {

	private String name;  // название статуса

	
	public Status() {
		super();
	}
	
	public Status (String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}