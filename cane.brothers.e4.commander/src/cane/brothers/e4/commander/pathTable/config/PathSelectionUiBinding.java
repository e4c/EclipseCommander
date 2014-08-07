package cane.brothers.e4.commander.pathTable.config;

import org.eclipse.nebula.widgets.nattable.config.AbstractUiBindingConfiguration;
import org.eclipse.nebula.widgets.nattable.ui.action.IKeyAction;
import org.eclipse.nebula.widgets.nattable.ui.binding.UiBindingRegistry;
import org.eclipse.nebula.widgets.nattable.ui.matcher.KeyEventMatcher;
import org.eclipse.swt.SWT;

import cane.brothers.e4.commander.pathTable.action.OpenPathAction;

public class PathSelectionUiBinding extends AbstractUiBindingConfiguration {

	@Override
	public void configureUiBindings(UiBindingRegistry uiBindingRegistry) {
		// press Enter
		configureEnterBindings(uiBindingRegistry, new OpenPathAction());
	}
	
	protected void configureEnterBindings(UiBindingRegistry uiBindingRegistry, IKeyAction action) {
		uiBindingRegistry.registerKeyBinding(new KeyEventMatcher(SWT.NONE, SWT.CR), action);
		uiBindingRegistry.registerKeyBinding(new KeyEventMatcher(SWT.MOD1, SWT.CR), action);
	}

}
