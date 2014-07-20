package cane.brothers.nattable.test.path.data;

import java.io.Serializable;
import java.util.List;

import org.eclipse.nebula.widgets.nattable.data.IRowIdAccessor;

public class PathFixtureRowIdAccessor implements IRowIdAccessor<PathFixture> {

	protected List<PathFixture> list;
	
	public PathFixtureRowIdAccessor(List<PathFixture> list) {
		this.list = list;
	}
	
	@Override
	public Serializable getRowId(PathFixture rowObject) {
		return list.indexOf(rowObject);
	}
}
