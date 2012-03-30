package karpachevski.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import karpachevski.model.SuperEntity;

public interface SuperEntityInterface {

	public void add(SuperEntity obj) throws SQLException;
	public void update(SuperEntity obj) throws SQLException;
	public void delete(SuperEntity obj) throws SQLException;
	public Collection getAll() throws SQLException;

}
