package cane.brothers.e4.commander.test.handler;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class MessageDialogHandlerTest {
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.open();
		MessageDialogHandler.execute(shell);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
