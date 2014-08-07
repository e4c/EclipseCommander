/**
 * 
 */
package cane.brothers.e4.commander.pathTable.action;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.coordinate.PositionCoordinate;
import org.eclipse.nebula.widgets.nattable.ui.action.IKeyAction;
import org.eclipse.swt.events.KeyEvent;

import cane.brothers.e4.commander.pathTable.PathNatTable;
import cane.brothers.e4.commander.pathTable.command.OpenPathCommand;

/**
 * @author icane
 *
 */
public class OpenPathAction implements IKeyAction {

	/* (non-Javadoc)
	 * @see org.eclipse.nebula.widgets.nattable.ui.action.IKeyAction#run(org.eclipse.nebula.widgets.nattable.NatTable, org.eclipse.swt.events.KeyEvent)
	 */
	@Override
	public void run(NatTable natTable, KeyEvent event) {
		//natTable.doCommand(new SelectAllCommand());
		
		if(natTable instanceof PathNatTable) {
			PathNatTable pathTable = (PathNatTable) natTable;
			PositionCoordinate selectionAnchor = pathTable.getSelectionAnchor();
			if(selectionAnchor != null) {
				int rowPosition = selectionAnchor.getRowPosition();
				if(rowPosition != -1) {
					natTable.doCommand(new OpenPathCommand(pathTable, rowPosition));
				}
			}
		}
	}

}
