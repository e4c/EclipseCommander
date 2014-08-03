/**
 * 
 */
package cane.brothers.e4.commander.pathTable;

import java.nio.file.Path;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.layer.ILayer;
import org.eclipse.nebula.widgets.nattable.style.theme.DefaultNatTableThemeConfiguration;
import org.eclipse.swt.widgets.Composite;

import cane.brothers.e4.commander.pathTable.layer.PathCompositeLayer;
import cane.brothers.e4.commander.pathTable.style.PathFirstThemeConfiguration;

/**
 * @author icane
 *
 */
public class PathNatTable extends NatTable {
	
	private Path rootPath = null;
	private PathCompositeLayer compositeLayer = null;
	private DefaultNatTableThemeConfiguration theme = null;

	/**
	 * @param parent
	 */
	public PathNatTable(Composite parent, Path rootPath) {
		super(parent, false);
		this.rootPath = rootPath;
		compositeLayer = new PathCompositeLayer(rootPath);
		theme = new PathFirstThemeConfiguration();
		//natTable.setBackground(GUIHelper.COLOR_WHITE);
		//natTable.addConfiguration(new PathStyleConfiguration());
		//natTable.addConfiguration(new ActiveTableStyleConfiguration());
		//add overlay painter for full borders
		//natTable.addOverlayPainter(new NatTableBorderOverlayPainter());
		
		setLayer(compositeLayer);
		configure();
		
		
		setTheme(theme);
	}
}
