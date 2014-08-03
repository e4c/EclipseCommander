package cane.brothers.e4.commander.pathTable.config;

import org.eclipse.nebula.widgets.nattable.config.AbstractRegistryConfiguration;
import org.eclipse.nebula.widgets.nattable.config.CellConfigAttributes;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.eclipse.nebula.widgets.nattable.style.CellStyleAttributes;
import org.eclipse.nebula.widgets.nattable.style.DisplayMode;
import org.eclipse.nebula.widgets.nattable.style.IStyle;
import org.eclipse.nebula.widgets.nattable.style.Style;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;

public class ActiveTableStyleConfiguration extends
		AbstractRegistryConfiguration {

	public static final String ACTIVE_LABEL = "ACTIVE";

	@Override
	public void configureRegistry(IConfigRegistry configRegistry) {
		IStyle style = new Style();
		style.setAttributeValue(CellStyleAttributes.BACKGROUND_COLOR, GUIHelper.COLOR_BLUE);
		style.setAttributeValue(CellStyleAttributes.FOREGROUND_COLOR, GUIHelper.COLOR_WHITE);
		
		configRegistry.registerConfigAttribute(
				CellConfigAttributes.CELL_STYLE, 
				style,
				DisplayMode.NORMAL,
				ACTIVE_LABEL);
		
		configRegistry.registerConfigAttribute(
				CellConfigAttributes.CELL_STYLE, 
				style,
				DisplayMode.SELECT,
				ACTIVE_LABEL);
	}

}
