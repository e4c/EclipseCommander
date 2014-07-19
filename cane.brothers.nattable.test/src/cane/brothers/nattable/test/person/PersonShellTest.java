package cane.brothers.nattable.test.person;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cane.brothers.nattable.test.person.grid.PersonGridLayer;

public class PersonShellTest {
	
	public static void main(String[] args) {
		new PersonShellTest().run();
	}

	/**
	 * Runs the application
	 */
	public void run() {
		// Normal SWT stuff
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Person NatTable Test");
		shell.setLayout(new FillLayout(SWT.NONE));

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
		
		new NatTable(parent, new PersonGridLayer());
	} // createDialogArea(Composite)
}
