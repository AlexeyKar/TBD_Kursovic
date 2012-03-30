package karpachevski.model;

import java.util.ArrayList;
import java.util.Calendar;

/*
 * Студенты - аспиратны - докторианты 
 */

public class Student extends Person {

	private String status;  // статус
	private Calendar dateOfAdmission; // дата поступления
	private Calendar dateOfPlanDiss; // планируемая дата защиты 
	private Calendar dateOfFactDiss; // дата фактической защиты
	private ArrayList<Task> tasks; // список задач
	private String nameOfDiss; // название диссертации
	private String codeOfDiss; // шифр диссертационного совета
	private Person supervisor; // научный руководитель
	private ArrayList<Person> opponents; // оппоненты
	private String organization; // ведущая организация

	public Student() {
		super();
	}
	
	public Student(String name, String surname, String middleName, 
			String status, Calendar dateOfAdmission, Calendar dateOfPlanDiss, 
			Calendar dateOfFactDiss, ArrayList<Task> tasks, String nameOfDiss, 
			String codeOfDiss, Person supervisor, ArrayList<Person> opponents, String organization) {
		
		super(name, surname, middleName);
		this.status = status;
		this.dateOfAdmission = dateOfAdmission;
		this.dateOfPlanDiss = dateOfPlanDiss;
		this.dateOfFactDiss = dateOfFactDiss;
		this.tasks = tasks; 
		this.nameOfDiss = nameOfDiss;
		this.codeOfDiss = codeOfDiss; 
		this.supervisor = supervisor;
		this.opponents = opponents; 
		this.organization = organization; 
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Calendar getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(Calendar dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public Calendar getDateOfPlanDiss() {
		return dateOfPlanDiss;
	}

	public void setDateOfPlanDiss(Calendar dateOfPlanDiss) {
		this.dateOfPlanDiss = dateOfPlanDiss;
	}

	public Calendar getDateOfFactDiss() {
		return dateOfFactDiss;
	}

	public void setDateOfFactDiss(Calendar dateOfFactDiss) {
		this.dateOfFactDiss = dateOfFactDiss;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	public String getNameOfDiss() {
		return nameOfDiss;
	}

	public void setNameOfDiss(String nameOfDiss) {
		this.nameOfDiss = nameOfDiss;
	}

	public String getCodeOfDiss() {
		return codeOfDiss;
	}

	public void setCodeOfDiss(String codeOfDiss) {
		this.codeOfDiss = codeOfDiss;
	}

	public Person getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Person supervisor) {
		this.supervisor = supervisor;
	}

	public ArrayList<Person> getOpponents() {
		return opponents;
	}

	public void setOpponents(ArrayList<Person> opponents) {
		this.opponents = opponents;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
