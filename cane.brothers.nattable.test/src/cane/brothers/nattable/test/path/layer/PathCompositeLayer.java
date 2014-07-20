package cane.brothers.nattable.test.path.layer;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.grid.data.DefaultColumnHeaderDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.CompositeLayer;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;

import cane.brothers.nattable.test.path.data.PathColumnPropertyAccessor;
import cane.brothers.nattable.test.path.data.PathFixture;
import cane.brothers.nattable.test.path.data.SimpleColumnHeaderDataProvider;

public class PathCompositeLayer extends CompositeLayer  {

	private Path rootPath;
	private List<PathFixture> contents;
	
	private final Map<String, String> propertyToLabelMap = new HashMap<String,String>();
	

    private final ListDataProvider<PathFixture> bodyDataProvider;
    
    private IColumnPropertyAccessor<PathFixture> columnPropertyAccessor;	
	
	public PathCompositeLayer(Path rootPath) {
		super(1, 2);
		
		this.rootPath = rootPath; 
		this.contents = getContents(rootPath);
		
		propertyToLabelMap.put("name", "Name");
		propertyToLabelMap.put("size", "Size");
		propertyToLabelMap.put("attr", "Attr");
		
		columnPropertyAccessor = new PathColumnPropertyAccessor(propertyToLabelMap);
		bodyDataProvider = new ListDataProvider<PathFixture>(this.contents, columnPropertyAccessor);
		
		final DataLayer bodyDataLayer = new DataLayer(bodyDataProvider);
		
		// set columns fixed percentage sizing
		bodyDataLayer.setColumnWidthPercentageByPosition(0, 75);
		bodyDataLayer.setColumnWidthPercentageByPosition(1, 15);
		bodyDataLayer.setColumnWidthPercentageByPosition(2, 10);
		
		final SelectionLayer selectionLayer = new SelectionLayer(bodyDataLayer);
		ViewportLayer viewportLayer = new ViewportLayer(selectionLayer);

		ILayer columnHeaderLayer = new ColumnHeaderLayer(
				new DataLayer(new SimpleColumnHeaderDataProvider(propertyToLabelMap)),
				viewportLayer, 
				selectionLayer);
		
		setChildLayer(GridRegion.COLUMN_HEADER, columnHeaderLayer, 0, 0);
		setChildLayer(GridRegion.BODY, viewportLayer, 0, 1);

	}
	
	private List<PathFixture> getContents(Path dir) {
		List<PathFixture> result = new ArrayList<>();
		
		if (dir != null && Files.isDirectory(dir, LinkOption.NOFOLLOW_LINKS)) {
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
				for (Path entry : stream) {
					result.add(new PathFixture(entry));
				}
			} catch (DirectoryIteratorException | IOException ex) {
				System.out.println(ex.getMessage());
			}

		}
		return result;
	}

}
