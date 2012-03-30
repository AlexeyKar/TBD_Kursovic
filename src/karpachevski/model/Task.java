package karpachevski.model;

import java.util.ArrayList;
import java.util.Calendar;

/*
 * Задачи
 */

public class Task extends SuperEntity {

	private String title; // название задания 
	private Calendar dateOfStartPlan; // планируемое время окончания задачи
	private Calendar dateOfStartFact; // фактическое время начала задачи
	private Calendar dateOfFinishPlan; // планируемое время окончания задачи
	private Calendar dateOfFinishFact; // фактическое время окончания задачи
	private Integer durration; // планируемая продолжительность
	private Integer complishion; // процент выполения задачи
	private Document document; // документ подтверждающий выполнение
	private ArrayList<Task> tasksToDo; // список предварительных задач
	
	public Task() {
		super();
	}
	
	public Task(String title, Calendar dateOfStartPlan,
			Calendar dateOfStartFact, Calendar dateOfFinishPlan,
			Calendar dateOfFinishFact, Integer durration, Integer complishion,
			Document document, ArrayList<Task> tasksToDo) {
		super();
		this.title = title;
		this.dateOfStartPlan = dateOfStartPlan;
		this.dateOfStartFact = dateOfStartFact;
		this.dateOfFinishPlan = dateOfFinishPlan;
		this.dateOfFinishFact = dateOfFinishFact;
		this.durration = durration;
		this.complishion = complishion;
		this.document = document;
		this.tasksToDo = tasksToDo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getDateOfStartPlan() {
		return dateOfStartPlan;
	}

	public void setDateOfStartPlan(Calendar dateOfStartPlan) {
		this.dateOfStartPlan = dateOfStartPlan;
	}

	public Calendar getDateOfStartFact() {
		return dateOfStartFact;
	}

	public void setDateOfStartFact(Calendar dateOfStartFact) {
		this.dateOfStartFact = dateOfStartFact;
	}

	public Calendar getDateOfFinishPlan() {
		return dateOfFinishPlan;
	}

	public void setDateOfFinishPlan(Calendar dateOfFinishPlan) {
		this.dateOfFinishPlan = dateOfFinishPlan;
	}

	public Calendar getDateOfFinishFact() {
		return dateOfFinishFact;
	}

	public void setDateOfFinishFact(Calendar dateOfFinishFact) {
		this.dateOfFinishFact = dateOfFinishFact;
	}

	public Integer getDurration() {
		return durration;
	}

	public void setDurration(Integer durration) {
		this.durration = durration;
	}

	public Integer getComplishion() {
		return complishion;
	}

	public void setComplishion(Integer complishion) {
		this.complishion = complishion;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public ArrayList<Task> getTasksToDo() {
		return tasksToDo;
	}

	public void setTasksToDo(ArrayList<Task> tasksToDo) {
		this.tasksToDo = tasksToDo;
	}

	@Override
	public String toString() {
		return title;
	}
	
}