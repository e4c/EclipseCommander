package cane.brothers.e4.commander.pathTable.data;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;

import cane.brothers.e4.commander.pathTable.IRootPath;

public class PathColumnPropertyAccessor implements IColumnPropertyAccessor<PathFixture>, IRootPath {


	/** use list instead of set because indexes */
	private List<String> propertyNames;
	
	private Path parentPath;
	
	private static final String parentPathStr = "../".intern();
	

	private static final String pathDirSizeStr = "<DIR>".intern();
	
	/**
	 * Constructor
	 * 
	 * @param propertyToLabels
	 */
	public PathColumnPropertyAccessor(Map<String, String> propertyToLabels, Path rootPath) {
		this.propertyNames = new ArrayList<String>(propertyToLabels.keySet());
		this.parentPath = rootPath.getParent();
	}
	
	@Override
	public Object getDataValue(PathFixture rowObject, int columnIndex) {
		switch (columnIndex) {
		case 0:
			if(parentPath.equals(rowObject.getPath())) {
				return parentPathStr;
			} else {
				return rowObject.getName();
			}
		case 1:
			if(Files.isDirectory(rowObject.getPath(), LinkOption.NOFOLLOW_LINKS)) {
				return pathDirSizeStr;
			} else {
				return rowObject.getSize();
			}
		case 2:
			return rowObject.getAttributes();
		}
		return null;
	}

	@Override
	public void setDataValue(PathFixture rowObject, int columnIndex,
			Object newValue) {
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

	@Override
	public void setRootPath(Path newPath) {
		if(newPath != null) {
			this.parentPath = newPath.getParent();
		}
	}

}
