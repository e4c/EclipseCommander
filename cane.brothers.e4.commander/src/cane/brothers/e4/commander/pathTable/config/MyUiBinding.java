package cane.brothers.e4.commander.pathTable.config;

import org.eclipse.nebula.widgets.nattable.config.AbstractUiBindingConfiguration;
import org.eclipse.nebula.widgets.nattable.selection.action.SelectAllAction;
import org.eclipse.nebula.widgets.nattable.ui.binding.UiBindingRegistry;
import org.eclipse.nebula.widgets.nattable.ui.matcher.KeyEventMatcher;
import org.eclipse.swt.SWT;

public class MyUiBinding extends AbstractUiBindingConfiguration {

	public MyUiBinding() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void configureUiBindings(UiBindingRegistry uiBindingRegistry) {
		// press Enter
		uiBindingRegistry.registerKeyBinding(new KeyEventMatcher(SWT.MOD1, 'a'), new SelectAllAction());

	}

}
