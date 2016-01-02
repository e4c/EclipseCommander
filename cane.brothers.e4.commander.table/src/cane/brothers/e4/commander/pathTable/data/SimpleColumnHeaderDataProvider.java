/*******************************************************************************
 * File: SimpleColumnHeaderDataProvider.java
 * 
 * Date: 2014/08/11
 * Author: Mikhail Niedre
 * 
 * Copyright (c) 2014 Original authors and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * <a href="http://www.eclipse.org/legal/epl-v10.html">epl-v1.0</a>
 *
 * Contributors:
 * Mikhail Niedre - initial API and implementation
 *******************************************************************************/
package cane.brothers.e4.commander.pathTable.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.data.IDataProvider;

/**
 * Simple column header data provider which take on input only the property to
 * label map and creates list of property names by keys from map.
 */
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
