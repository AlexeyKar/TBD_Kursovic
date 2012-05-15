package karpachevski.interfaces;

import java.sql.SQLException;
import java.util.Collection;

import karpachevski.model.SuperEntity;
import java.util.*;
import karpachevski.model.*;

public interface SuperEntityInterface {

	public void add(SuperEntity obj) throws SQLException, ClassNotFoundException;
	public void update(SuperEntity obj) throws SQLException, ClassNotFoundException;
	public void delete(SuperEntity obj) throws SQLException, ClassNotFoundException;
	public Collection getAll() throws SQLException, ClassNotFoundException;
	public Collection getListForEntity(SuperEntity obj) throws SQLException, ClassNotFoundException;

}
