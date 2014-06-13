 
package cane.brothers.e4.commander.parts;

import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class RightTab implements ITabNameId {
	
	public static final String RIGHT_TAB_ID_PREF = ".lefttab.".intern();
	private static int tabsId = 1;
	private static final String tabName = "right tab ".intern();

	public int getNewId() {
		return ++tabsId;
	}
	
	public String getElementId() {
		return ELEMENT_ID + RIGHT_TAB_ID_PREF + getNewId();
	}

	public String getTabName() {
		return tabName + getNewId();
	}
	
	private Label label;
	private Color bgColor = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);
	
	@Inject
	public RightTab(Composite parent) {
		parent.setBackground(bgColor);
		parent.setLayout(new GridLayout());
		
		label = new Label(parent, SWT.NONE);
		label.setText("Right tab");
	}
	
	
	
	
}