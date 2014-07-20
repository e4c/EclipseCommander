package cane.brothers.nattable.test.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.data.IDataProvider;

public class SimpleColumnHeaderDataProvider implements IDataProvider {

	private List<String> propertyNames;

	private Map<String, String> propertyToLabelMap;
	
	
	/**
	 * Constructor
	 * 
	 * @param propertyToLabelMap
	 */
	public SimpleColumnHeaderDataProvider(Map<String, String> propertyToLabelMap) {
		this.propertyToLabelMap = propertyToLabelMap;
		this.propertyNames = new ArrayList<String>(propertyToLabelMap.keySet());
	}
	
	public String getColumnHeaderLabel(int columnIndex) {
		String propertyName = propertyNames.get(columnIndex);
		if (propertyToLabelMap != null) {
			String label = propertyToLabelMap.get(propertyName);
			if (label != null) {
				return label;
			}
		}
		return propertyName;
	}
	
	/**
	 * This class does not support multiple rows in the column header layer.
	 */
	@Override
	public Object getDataValue(int columnIndex, int rowIndex) {
		return getColumnHeaderLabel(columnIndex);
	}

	@Override
	public void setDataValue(int columnIndex, int rowIndex, Object newValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getColumnCount() {
		return propertyNames.size();
	}

	@Override
	public int getRowCount() {
		return 1;
	}
	
	

}
