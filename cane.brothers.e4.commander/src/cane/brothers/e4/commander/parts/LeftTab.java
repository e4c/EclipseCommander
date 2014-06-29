package cane.brothers.e4.commander.parts;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class LeftTab implements ITabNameId {
	
	public static final String LEFT_TAB_ID_PREF = ".lefttab.".intern();
	private static int tabsId = 1;
	private static final String tabName = "left tab ".intern();

	public int getNewId() {
		return ++tabsId;	
	}
	
	public String getElementId() {
		return ELEMENT_ID + LEFT_TAB_ID_PREF + getNewId();
	}

	public String getTabName() {
		return tabName + getNewId();
	}

	private Label label;
	private Color bgColor = Display.getCurrent()
			.getSystemColor(SWT.COLOR_WHITE);

	@Inject
	public LeftTab(Composite parent) {
		parent.setBackground(bgColor);
		parent.setLayout(new GridLayout());

		label = new Label(parent, SWT.NONE);
		label.setText("Left tab");
	}

}