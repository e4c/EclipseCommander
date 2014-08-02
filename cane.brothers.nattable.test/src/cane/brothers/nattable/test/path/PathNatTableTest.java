package cane.brothers.nattable.test.path;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.painter.NatTableBorderOverlayPainter;
import org.eclipse.nebula.widgets.nattable.style.theme.ModernNatTableThemeConfiguration;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cane.brothers.nattable.test.path.config.ActiveTableStyleConfiguration;
import cane.brothers.nattable.test.path.config.PathStyleConfiguration;
import cane.brothers.nattable.test.path.layer.PathCompositeLayer;
import cane.brothers.nattable.test.path.style.PathFirstThemeConfiguration;

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
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginWidth = 0;
		shell.setLayout(gridLayout);


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
		Path homePath = Paths.get(System.getenv().get("HOME")); 
		final NatTable natTable = new NatTable(parent, new PathCompositeLayer(homePath), false);
		
		//natTable.setBackground(GUIHelper.COLOR_WHITE);
		//natTable.addConfiguration(new PathStyleConfiguration());
		//natTable.addConfiguration(new ActiveTableStyleConfiguration());
		natTable.configure();
		
		//set the modern theme
		natTable.setTheme(new PathFirstThemeConfiguration());
		
		//add overlay painter for full borders
		//natTable.addOverlayPainter(new NatTableBorderOverlayPainter());

		
		GridDataFactory.fillDefaults().grab(true, true).applyTo(natTable);
	}

}
