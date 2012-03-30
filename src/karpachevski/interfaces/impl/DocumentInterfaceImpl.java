package karpachevski.interfaces.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import karpachevski.interfaces.SuperEntityInterface;
import karpachevski.model.SuperEntity;

public class DocumentInterfaceImpl implements SuperEntityInterface {

	@Override
	public void add(SuperEntity document) throws SQLException {
		// В разработке
	}

	@Override
	public void update(SuperEntity document) throws SQLException {
		// В разработке
	}

	@Override
	public void delete(SuperEntity document) throws SQLException {
		// В разработке
	}

	@Override
	public Collection getAll() throws SQLException {
		Collection documents = null;
		documents = new ArrayList();
		// В разработке
		return documents; 
	}

}
