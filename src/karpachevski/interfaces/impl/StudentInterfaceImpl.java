package karpachevski.interfaces.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import karpachevski.interfaces.SuperEntityInterface;
import karpachevski.model.SuperEntity;

public class StudentInterfaceImpl implements SuperEntityInterface {

	@Override
	public void add(SuperEntity student) throws SQLException {
		// В разработке
	}

	@Override
	public void update(SuperEntity student) throws SQLException {
		// В разработке
	}

	@Override
	public void delete(SuperEntity student) throws SQLException {
		// В разработке
	}

	@Override
	public Collection getAll() throws SQLException {
		Collection students = null;
		students = new ArrayList();
		// В разработке
		return students; 
	}

}
