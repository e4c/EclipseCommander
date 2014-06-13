 
package cane.brothers.e4.commander.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class SimplePart {
	private Label label;

	public SimplePart() {
		System.out.println("SimplePart");
	}
	
	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setBackground( getDisplay().getSystemColor(SWT.COLOR_WHITE) );
		parent.setLayout(new GridLayout());

		label = new Label(parent, SWT.NONE);
		label.setText("Sample table");
	}
	
	public static Display getDisplay() {
	      Display display = Display.getCurrent();
	      //may be null if outside the UI thread
	      if (display == null)
	         display = Display.getDefault();
	      return display;		
	   }	
	
	
	
	
}