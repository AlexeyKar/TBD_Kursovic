package karpachevski.factory;

import karpachevski.interfaces.SuperEntityInterface;
import karpachevski.interfaces.impl.PersonInterfaceImpl;
import karpachevski.interfaces.impl.StatusInterfaceImpl;
import karpachevski.interfaces.impl.StudentInterfaceImpl;
import karpachevski.interfaces.impl.TaskInterfaceImpl;
import karpachevski.interfaces.impl.DocumentInterfaceImpl;
import karpachevski.interfaces.*;

public class Factory {

	private static SuperEntityInterface studentInterface = null;
	private static SuperEntityInterface taskInterface = null;
	private static SuperEntityInterface documentInterface = null;
	private static SuperEntityInterface personInterface = null;
	private static SuperEntityInterface statusInterface = null;
	private static Factory instance = null;
	
	public static synchronized Factory getInstance(){
		if (instance == null){
			instance = new Factory();
		}
		return instance;
	}

	public SuperEntityInterface getStudentInterface(){
		if (studentInterface == null){
			studentInterface = new StudentInterfaceImpl();
		}
		return studentInterface;
	}
	
	public SuperEntityInterface getTaskInterface(){
		if (taskInterface == null){
			taskInterface = new TaskInterfaceImpl();
		}
		return taskInterface;
	}
	
	public SuperEntityInterface getDocumentInterface(){
		if (documentInterface == null){
			documentInterface = new DocumentInterfaceImpl();
		}
		return documentInterface;
	}
	
	public SuperEntityInterface getPersonInterface(){
		if (personInterface == null){
			personInterface = new PersonInterfaceImpl();
		}
		return personInterface;
	}
	
	public SuperEntityInterface getStatusInterface(){
		if (statusInterface == null){
			statusInterface = new StatusInterfaceImpl();
		}
		return statusInterface;
	}
}
