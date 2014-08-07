/**
 * 
 */
package cane.brothers.e4.commander.pathTable.config;

import org.eclipse.nebula.widgets.nattable.selection.config.DefaultSelectionLayerConfiguration;

/**
 * @author icane
 *
 */
public class PathSelectionLayerConfiguration extends
		DefaultSelectionLayerConfiguration {

	protected void addSelectionUIBindings() {
		addConfiguration(new PathSelectionUiBinding());
	}

}
