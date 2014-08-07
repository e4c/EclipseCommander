/**
 * 
 */
package cane.brothers.e4.commander.pathTable;

import java.nio.file.Path;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.coordinate.PositionCoordinate;
import org.eclipse.nebula.widgets.nattable.selection.RowSelectionProvider;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.style.theme.DefaultNatTableThemeConfiguration;
import org.eclipse.swt.widgets.Composite;

import cane.brothers.e4.commander.pathTable.data.PathFixture;
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
		
//		addKeyListener(new KeyListener() {
//			
//			@Override
//			public void keyReleased(KeyEvent e) {
//				if (e.keyCode == SWT.En) {
//					closeDialog();
//				}
//				
//			}
//			
//			@Override
//			public void keyPressed(KeyEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		})
		
		
		//
		// 1. 
		//
		
//		// This adds a custom ILayerListener that will listen and handle selection events on NatTable level
//		// !!!There are no mouse selection handling here !!!
//		addLayerListener(new ILayerListener() {
//
//					// row selection behavior
//					@Override
//					public void handleLayerEvent(ILayerEvent event) {
//						if (event instanceof RowSelectionEvent) {
//							
//							RowSelectionEvent rowEvent = (RowSelectionEvent) event;
//							System.out.println("Selected Row: " + ObjectUtils.toString(rowEvent.getRowPositionRanges()));
//						
//							
//							
////							//directly ask the SelectionLayer about the selected rows and access the data via IRowDataProvider
////							SelectionLayer selectionLayer = ((RowSelectionEvent) event).getSelectionLayer();
////							Set<Range> selections = selectionLayer.getSelectedRowPositions();
////							
////							for (Range r : selections) {
////								for (int i = r.start; i < r.end; i++) {
////									
////									ListDataProvider<PathFixture> dataProvider = compositeLayer.getBodyDataProvider();
////									
////									PathFixture fixture = dataProvider.getRowObject(i);
////									System.out.println(fixture);
////									
////								}
////							}
//						} else if (event instanceof CellSelectionEvent) {
//							System.out.println(event);
//						}
//					}
//				});
		
		
		//
		// 2. 
		//
		
		// Provides rows where any cell in the row is selected
		ISelectionProvider selectionProvider = new RowSelectionProvider<PathFixture>(compositeLayer.getSelectionLayer(), compositeLayer.getBodyDataProvider(), true, false); 
		
		selectionProvider.addSelectionChangedListener(new ISelectionChangedListener() {			
			public void selectionChanged(SelectionChangedEvent event) {
				System.out.println("Selection changed:");
				
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				
				for(Object sel : selection.toArray()) {
					if(sel instanceof PathFixture) {
						System.out.println("   " + sel);
					}
				}
			}
		});
		
//		// Programmatically select a few rows
//		selectionProvider.setSelection(new StructuredSelection(new Person[] { homer, smithers, nelson }));
				

		
		setLayer(compositeLayer);
		configure();
		
		
		setTheme(theme);
	}
	
	public PositionCoordinate getSelectionAnchor() {
		if(compositeLayer != null) {
			SelectionLayer selectionLayer = compositeLayer.getSelectionLayer();
			if(selectionLayer != null) {
				return selectionLayer.getSelectionAnchor();
			}
		}
		return null;
	}
}
