/**
 * 
 */
package cane.brothers.e4.commander.pathTable.command;

import org.eclipse.nebula.widgets.nattable.command.AbstractRowCommand;
import org.eclipse.nebula.widgets.nattable.command.ILayerCommand;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;

/**
 * @author icane
 *
 */
public class OpenPathCommand extends AbstractRowCommand {

	/**
	 * Creates a OpenPathCommand for the given row position related to the given layer.
	 * 
	 * @param layer The layer to which the row position matches.
	 * @param rowPosition The selected row position.
	 */
	public OpenPathCommand(ILayer layer, int rowPosition) {
		super(layer, rowPosition);
	}
	
	/**
	 * Constructor used for cloning the command.
	 * @param command The command which is the base for the new cloned instance.
	 */
	protected OpenPathCommand(OpenPathCommand command) {
		super(command);
	}

	@Override
	public ILayerCommand cloneCommand() {
		return new OpenPathCommand(this);
	}

}
