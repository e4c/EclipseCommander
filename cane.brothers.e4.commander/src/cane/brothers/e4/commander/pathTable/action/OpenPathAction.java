/**
 * 
 */
package cane.brothers.e4.commander.pathTable.action;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.selection.command.SelectAllCommand;
import org.eclipse.nebula.widgets.nattable.ui.action.IKeyAction;
import org.eclipse.swt.events.KeyEvent;

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
		natTable.doCommand(new SelectAllCommand());
		//natTable.doCommand(new OpenPathCommand());
	}

}
