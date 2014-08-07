/**
 * 
 */
package cane.brothers.e4.commander.pathTable.command;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.Set;

import org.eclipse.nebula.widgets.nattable.command.AbstractLayerCommandHandler;
import org.eclipse.nebula.widgets.nattable.coordinate.Range;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;

import cane.brothers.e4.commander.pathTable.data.PathFixture;

/**
 * @author icane
 *
 */
public class OpenPathHandler extends
		AbstractLayerCommandHandler<OpenPathCommand> {


    private SelectionLayer selectionLayer;
    
	private final ListDataProvider<PathFixture> bodyDataProvider;
	 
	public OpenPathHandler(SelectionLayer selectionLayer, ListDataProvider<PathFixture> bodyDataProvider) {
		this.selectionLayer = selectionLayer;
		this.bodyDataProvider = bodyDataProvider;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.nebula.widgets.nattable.command.ILayerCommandHandler#getCommandClass()
	 */
	@Override
	public Class<OpenPathCommand> getCommandClass() {
		return OpenPathCommand.class;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.nebula.widgets.nattable.command.AbstractLayerCommandHandler#doCommand(org.eclipse.nebula.widgets.nattable.command.ILayerCommand)
	 */
	@Override
	protected boolean doCommand(OpenPathCommand command) {
		// open new Path if possible
		
		// 1. get path
		Set<Range> selections = selectionLayer.getSelectedRowPositions();
		PathFixture fixture = null;
		
		for (Range r : selections) {
			for (int i = r.start; i < r.end; i++) {
				fixture = bodyDataProvider.getRowObject(i);
				break;
			}
		}
		
		// 2. check if directory
		if(Files.isDirectory(fixture.getPath(), LinkOption.NOFOLLOW_LINKS)) {
			// 2.1 open new path
			System.out.println("dir");
		} else {
			// 2.2 do nothing at this moment
		}
		
		return false;
	}

}
