package cane.brothers.e4.commander.pathTable.layer;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.nebula.widgets.nattable.data.IColumnPropertyAccessor;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.grid.GridRegion;
import org.eclipse.nebula.widgets.nattable.grid.layer.ColumnHeaderLayer;
import org.eclipse.nebula.widgets.nattable.layer.CompositeLayer;
import org.eclipse.nebula.widgets.nattable.layer.DataLayer;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.selection.RowSelectionModel;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.selection.config.RowOnlySelectionBindings;
import org.eclipse.nebula.widgets.nattable.selection.config.RowOnlySelectionConfiguration;
import org.eclipse.nebula.widgets.nattable.viewport.ViewportLayer;

import cane.brothers.e4.commander.pathTable.command.OpenPathHandler;
import cane.brothers.e4.commander.pathTable.config.PathSelectionLayerConfiguration;
import cane.brothers.e4.commander.pathTable.data.PathColumnPropertyAccessor;
import cane.brothers.e4.commander.pathTable.data.PathFixture;
import cane.brothers.e4.commander.pathTable.data.PathFixtureRowIdAccessor;
import cane.brothers.e4.commander.pathTable.data.SimpleColumnHeaderDataProvider;



public class PathCompositeLayer extends CompositeLayer  {

	private Path rootPath;
	private List<PathFixture> contents;
	
	private final Map<String, String> propertyToLabelMap = new LinkedHashMap<String,String>();
	

    private final ListDataProvider<PathFixture> bodyDataProvider;
    
    private IColumnPropertyAccessor<PathFixture> columnPropertyAccessor;	

    private SelectionLayer selectionLayer;

	public PathCompositeLayer(Path rootPath) {
		super(1, 2);
		
		this.rootPath = rootPath; 
		this.contents = getContents(rootPath);
		
		propertyToLabelMap.put("name", "Name");
		propertyToLabelMap.put("size", "Size");
		propertyToLabelMap.put("attr", "Attr");
		
		columnPropertyAccessor = new PathColumnPropertyAccessor(propertyToLabelMap, rootPath);
		bodyDataProvider = new ListDataProvider<PathFixture>(this.contents, columnPropertyAccessor);
		
		final DataLayer bodyDataLayer = new DataLayer(bodyDataProvider);
		
		// set columns fixed percentage sizing
		bodyDataLayer.setColumnWidthPercentageByPosition(0, 80);
		bodyDataLayer.setColumnWidthPercentageByPosition(1, 10);
		bodyDataLayer.setColumnWidthPercentageByPosition(2, 10);
		
		selectionLayer = new SelectionLayer(bodyDataLayer, false);
		ViewportLayer viewportLayer = new ViewportLayer(selectionLayer);

		//use a RowSelectionModel that will perform row selections and is able to identify a row via unique ID
		selectionLayer.setSelectionModel(new RowSelectionModel<PathFixture>(selectionLayer, bodyDataProvider, new PathFixtureRowIdAccessor(this.contents)));
		
		//register different selection move command handler that always moves by row
		selectionLayer.addConfiguration(new RowOnlySelectionConfiguration<PathFixture>());
		selectionLayer.addConfiguration(new PathSelectionLayerConfiguration());
		
		// register path handler
		OpenPathHandler pathHandler = new OpenPathHandler(selectionLayer, bodyDataProvider);
		selectionLayer.registerCommandHandler(pathHandler);
		
		
		//register selection bindings that will perform row selections instead of cell selections
		//registering the bindings on a layer that is above the SelectionLayer will consume the
		//commands before they are handled by the SelectionLayer
		viewportLayer.addConfiguration(new RowOnlySelectionBindings());
		

		ILayer columnHeaderLayer = new ColumnHeaderLayer(
				new DataLayer(new SimpleColumnHeaderDataProvider(propertyToLabelMap)),
				viewportLayer, 
				selectionLayer);
		
		setChildLayer(GridRegion.COLUMN_HEADER, columnHeaderLayer, 0, 0);
		setChildLayer(GridRegion.BODY, viewportLayer, 0, 1);

	}
	
	private List<PathFixture> getContents(Path dir) {
		List<PathFixture> result = new ArrayList<>();
		
		// add parent path
		Path parentPath = dir.getParent();
		result.add(new PathFixture(parentPath));
		
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

    
	public SelectionLayer getSelectionLayer() {
		return selectionLayer;
	}
	
	public ListDataProvider<PathFixture> getBodyDataProvider() {
		return bodyDataProvider;
	}

}
