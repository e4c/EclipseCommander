package cane.brothers.e4.commander.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class DynamicTab implements ITabNameId {

	public static final String DYNAMIC_TAB_ID_PREF = ".dynamictab.".intern();
	private static int tabsId = 1;
	private static final String tabName = "Dynamic tab ".intern();
	
	private int currentTabId = -1;
	
/**
 * GUI stuff
 */
	private Label label;
	private Color bgColor = Display.getCurrent()
			.getSystemColor(SWT.COLOR_WHITE);

	@Inject
	public DynamicTab() {
		currentTabId = ++tabsId;
	}
	
	@PostConstruct
	public void createPartControl(Composite parent) {
		
		parent.setBackground(bgColor);
		parent.setLayout(new GridLayout());

		label = new Label(parent, SWT.NONE);
		label.setText("Dynamic tab");
	}

	@Override
	public String getElementId() {
		return ELEMENT_ID + DYNAMIC_TAB_ID_PREF + currentTabId;
	}

	@Override
	public String getTabName() {
		return tabName + currentTabId;
	}

}
