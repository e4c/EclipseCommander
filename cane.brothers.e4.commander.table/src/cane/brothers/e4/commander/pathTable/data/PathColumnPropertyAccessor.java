/*******************************************************************************
 * File: PathColumnPropertyAccessor.java
 * 
 * Date: 2014/08/10
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

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;

import cane.brothers.e4.commander.model.PathFixture;
import cane.brothers.e4.commander.pathTable.IRootPath;

/**
 * The column property accessor class for Path table.
 * 
 * Defines column count, in which column should be which data. Defines hooks for
 * special data values like parent path representation and dir size
 * substitution.
 *
 */
public class PathColumnPropertyAccessor implements IColumnPropertyAccessor<PathFixture>, IRootPath {

    /** use list instead of set because indexes */
    private List<String> propertyNames;

    private Path rootPath;

    private Path parentPath;

    private static final String parentPathStr = "../".intern();

    private static final String pathDirSizeStr = "<DIR>".intern();

    /**
     * Constructor
     * 
     * @param propertyToLabels
     */
    public PathColumnPropertyAccessor(Map<String, String> propertyToLabels, Path rootPath) {
        this.rootPath = rootPath;
        this.propertyNames = new ArrayList<String>(propertyToLabels.keySet());
        this.parentPath = rootPath.getParent();
    }

    @Override
    public Object getDataValue(PathFixture rowObject, int columnIndex) {
        switch (columnIndex) {
        case 0:
            // there are parent path of root path
            if (parentPath != null && parentPath.equals(rowObject.getPath())) {
                return parentPathStr;
            }
            else {
                return rowObject.getName();
            }
        case 1:
            if (Files.isDirectory(rowObject.getPath())) {
                return pathDirSizeStr;
            }
            else {
                return rowObject.getSize();
            }
        case 2:
            return rowObject.getAttributes();
        }
        return null;
    }

    @Override
    public void setDataValue(PathFixture rowObject, int columnIndex, Object newValue) {
        new UnsupportedOperationException("none"); //$NON-NLS-1$
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

    /*
     * (non-Javadoc)
     * 
     * @see
     * cane.brothers.e4.commander.pathTable.IRootPath#setRootPath(java.nio.file
     * .Path)
     */
    @Override
    public void setRootPath(Path newPath) {
        if (newPath != null) {
            this.rootPath = newPath;
            this.parentPath = newPath.getParent();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cane.brothers.e4.commander.pathTable.IRootPath#getRootPath()
     */
    @Override
    public Path getRootPath() {
        return rootPath;
    }

}
