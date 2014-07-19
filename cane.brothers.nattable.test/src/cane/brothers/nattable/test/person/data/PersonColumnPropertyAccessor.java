package cane.brothers.nattable.test.person.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;

public class PersonColumnPropertyAccessor implements IColumnPropertyAccessor<Person> {

	/** use list instead of set because indexes */
	private List<String> propertyNames;

	/**
	 * Constructor
	 * 
	 * @param propertyToLabels
	 */
	public PersonColumnPropertyAccessor(Map<String, String> propertyToLabels) {
		this.propertyNames = new ArrayList<String>(propertyToLabels.keySet());
	}

	@Override
	public Object getDataValue(Person person, int col) {
		switch (col) {
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2:
			return person.getBirthDate();
		}
		return null;
	}

	@Override
	public void setDataValue(Person person, int col, Object val) {
		// 
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getColumnIndex(String propertyName) {
		propertyNames.indexOf(propertyName);
		return 0;
	}

	@Override
	public String getColumnProperty(int col) {
		return propertyNames.get(col);
	}

}
