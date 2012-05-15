package karpachevski.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;

/*
 * Студенты - аспиратны - докторианты 
 */

public class Student extends Person {

	private Status status;  // статус
	private int degree; // Докторант или Аспирант
	private Calendar dateOfAdmission; // дата поступления
	private Calendar dateOfPlanDiss; // планируемая дата защиты 
	private Calendar dateOfFactDiss; // дата фактической защиты
	private Collection tasks; // список задач
	private String nameOfDiss; // название диссертации
	private String codeOfDiss; // шифр диссертационного совета
	private Person supervisor; // научный руководитель
	private Collection opponents; // оппоненты
	private String organization; // ведущая организация

	public Student() {
		super();
		degree = 0;
	}
	
	public Student(String name, String surname, String middleName, 
			Status status, int degree, Calendar dateOfAdmission, Calendar dateOfPlanDiss, 
			Calendar dateOfFactDiss, Collection tasks, String nameOfDiss, 
			String codeOfDiss, Person supervisor,Collection opponents, String organization) {
		
		super(name, surname, middleName);
		this.status = status;
		this.degree = degree;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
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

	public Collection getTasks() {
		return tasks;
	}

	public void setTasks(Collection tasks) {
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

	public Collection getOpponents() {
		return opponents;
	}

	public void setOpponents(Collection opponents) {
		this.opponents = opponents;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

}
