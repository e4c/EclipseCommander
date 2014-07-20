package cane.brothers.nattable.test.path;

import java.nio.file.Paths;

import org.eclipse.jface.layout.GridDataFactory;


import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.util.GUIHelper;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cane.brothers.nattable.test.path.layer.PathCompositeLayer;

public class PathNatTableTest {

	public static void main(String[] args) {
		new PathNatTableTest().run();
	}
	
	/**
	 * Runs the application
	 */
	public void run() {
		// Normal SWT stuff
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Path NatTable Test");
		shell.setLayout(new GridLayout());


		createContents(shell);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
	/**
	 * Creates the shell's contents
	 * 
	 * @param shell
	 *            the shell
	 */
	private void createContents(Composite parent) {
		
		final NatTable natTable = new NatTable(parent, new PathCompositeLayer(Paths.get("C:\\Cane")));
		
		natTable.setBackground(GUIHelper.COLOR_WHITE);
		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(natTable);

	}

}
