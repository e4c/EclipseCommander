package cane.brothers.nattable.test.path.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;

public class PathColumnPropertyAccessor implements IColumnPropertyAccessor<PathFixture> {


	/** use list instead of set because indexes */
	private List<String> propertyNames;
	
	/**
	 * Constructor
	 * 
	 * @param propertyToLabels
	 */
	public PathColumnPropertyAccessor(Map<String, String> propertyToLabels) {
		this.propertyNames = new ArrayList<String>(propertyToLabels.keySet());
	}
	
	@Override
	public Object getDataValue(PathFixture rowObject, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowObject.getName();
		case 1:
			return rowObject.getSize();
		case 2:
			return rowObject.getAttributes();
		}
		return null;
	}

	@Override
	public void setDataValue(PathFixture rowObject, int columnIndex,
			Object newValue) {
		new UnsupportedOperationException("none");
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnProperty(int columnIndex) {
		return propertyNames.get(columnIndex);
	}

	@Override
	public int getColumnIndex(String propertyName) {
		return propertyNames.indexOf(propertyName);
	}

}
