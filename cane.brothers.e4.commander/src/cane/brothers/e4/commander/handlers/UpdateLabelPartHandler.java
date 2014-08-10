/**
 * 
 */
package cane.brothers.e4.commander.handlers;

import java.nio.file.Path;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;

import cane.brothers.e4.commander.MyEventConstants;

/**
 * @author icane
 *
 */
public class UpdateLabelPartHandler {

	@Inject
	@Optional
	@Execute
	public void execute(
			@UIEventTopic(MyEventConstants.TAB_PATH_OPEN) Path newPath,
			@Named(IServiceConstants.ACTIVE_PART) MPart activePart) {

		// update label of current tab
		if (activePart != null && newPath != null) {
			String rootPathString = newPath.getFileName().toString();
			activePart.setLabel(rootPathString);
		}
	}
}
