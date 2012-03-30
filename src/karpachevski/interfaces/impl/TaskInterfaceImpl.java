package karpachevski.interfaces.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import karpachevski.interfaces.SuperEntityInterface;
import karpachevski.model.SuperEntity;

public class TaskInterfaceImpl implements SuperEntityInterface {

	@Override
	public void add(SuperEntity task) throws SQLException {
		// В разработке
	}

	@Override
	public void update(SuperEntity task) throws SQLException {
		// В разработке
	}

	@Override
	public void delete(SuperEntity task) throws SQLException {
		// В разработке
	}

	@Override
	public Collection getAll() throws SQLException {
		Collection tasks = null;
		tasks = new ArrayList();
		// В разработке
		return tasks; 
	}
	
}
