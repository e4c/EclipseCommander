/*******************************************************************************
 * File: PathNatTable.java
 * 
 * Date: 2014/08/11
 * Author: Mikhail Niedre
 * 
 * Copyright (c) 2014 Original authors and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * <a href="http://www.eclipse.org/legal/epl-v10.html">epl-v1.0</a>
 *
 * Contributors:
 * Mikhail Niedre - initial API and implementation
 *******************************************************************************/
package cane.brothers.e4.commander.pathTable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.coordinate.Range;
import org.eclipse.nebula.widgets.nattable.selection.RowSelectionProvider;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.style.theme.DefaultNatTableThemeConfiguration;
import org.eclipse.swt.widgets.Composite;

import cane.brothers.e4.commander.pathTable.data.PathFixture;
import cane.brothers.e4.commander.pathTable.layer.PathCompositeLayer;
import cane.brothers.e4.commander.pathTable.style.PathFirstThemeConfiguration;

/**
 * The table with path's as row entries.
 *
 */
public class PathNatTable extends NatTable implements IRootPath {

    private Path rootPath = null;

    private ISelectionProvider selectionProvider;

    /**
     * @return the selectionProvider
     */
    public ISelectionProvider getSelectionProvider() {
	return selectionProvider;
    }

    private PathCompositeLayer compositeLayer = null;
    private DefaultNatTableThemeConfiguration theme = null;

    /**
     * @param parent
     */
    public PathNatTable(Composite parent, Path rootPath,
	    IEventBroker eventBroker) {
	super(parent, false);
	// this.rootPath = rootPath;

	compositeLayer = new PathCompositeLayer(rootPath, eventBroker);
	theme = new PathFirstThemeConfiguration();

	// natTable.setBackground(GUIHelper.COLOR_WHITE);
	// natTable.addConfiguration(new PathStyleConfiguration());
	// natTable.addConfiguration(new ActiveTableStyleConfiguration());
	// add overlay painter for full borders
	// natTable.addOverlayPainter(new NatTableBorderOverlayPainter());

	// addKeyListener(new KeyListener() {
	//
	// @Override
	// public void keyReleased(KeyEvent e) {
	// if (e.keyCode == SWT.En) {
	// closeDialog();
	// }
	//
	// }
	//
	// @Override
	// public void keyPressed(KeyEvent e) {
	// // TODO Auto-generated method stub
	//
	// }
	// })

	//
	// 1.
	//

	// // This adds a custom ILayerListener that will listen and handle
	// selection events on NatTable level
	// // !!!There are no mouse selection handling here !!!
	// addLayerListener(new ILayerListener() {
	//
	// // row selection behavior
	// @Override
	// public void handleLayerEvent(ILayerEvent event) {
	// if (event instanceof RowSelectionEvent) {
	//
	// RowSelectionEvent rowEvent = (RowSelectionEvent) event;
	// System.out.println("Selected Row: " +
	// ObjectUtils.toString(rowEvent.getRowPositionRanges()));
	//
	//
	//
	// // //directly ask the SelectionLayer about the selected rows and
	// access the data via IRowDataProvider
	// // SelectionLayer selectionLayer = ((RowSelectionEvent)
	// event).getSelectionLayer();
	// // Set<Range> selections = selectionLayer.getSelectedRowPositions();
	// //
	// // for (Range r : selections) {
	// // for (int i = r.start; i < r.end; i++) {
	// //
	// // ListDataProvider<PathFixture> dataProvider =
	// compositeLayer.getBodyDataProvider();
	// //
	// // PathFixture fixture = dataProvider.getRowObject(i);
	// // System.out.println(fixture);
	// //
	// // }
	// // }
	// } else if (event instanceof CellSelectionEvent) {
	// System.out.println(event);
	// }
	// }
	// });

	//
	// 2.
	//

	// Provides rows where any cell in the row is selected
	selectionProvider = new RowSelectionProvider<PathFixture>(
		compositeLayer.getSelectionLayer(),
		compositeLayer.getBodyDataProvider(), true, false);

	// // Programmatically select a few rows
	// selectionProvider.setSelection(new StructuredSelection(new Person[] {
	// homer, smithers, nelson }));

	setLayer(compositeLayer);
	configure();

	// add overlay painter for full borders
	// addOverlayPainter(new NatTableBorderOverlayPainter());

	setTheme(theme);
    }

    public int getSelectedRowPosition() {
	if (compositeLayer != null) {
	    SelectionLayer selectionLayer = compositeLayer.getSelectionLayer();

	    final Set<Range> selectedRows = selectionLayer
		    .getSelectedRowPositions();
	    List<Integer> selectedRowPositions = new ArrayList<Integer>();
	    for (Range range : selectedRows) {
		for (int rowPosition = range.start; rowPosition < range.end; rowPosition++) {
		    // + 1
		    selectedRowPositions.add(rowPosition + 1);
		}
	    }
	    Collections.sort(selectedRowPositions);

	    // at this moment return only first
	    if (selectedRowPositions.size() > 0) {
		return selectedRowPositions.get(0).intValue();
	    }
	}
	return -1;
    }

    /**
     * clear selection
     */
    public void clearSelection() {
	SelectionLayer selLayer = getSelectionLayer();
	if (selLayer != null) {
	    selLayer.clear();
	}
    }

    private SelectionLayer getSelectionLayer() {
	if (compositeLayer != null) {
	    return compositeLayer.getSelectionLayer();
	}
	return null;
    }

    /**
     * select first row in the table
     */
    public void setDefaultSelection() {
	SelectionLayer selLayer = getSelectionLayer();
	if (selLayer != null) {
	    selLayer.selectRow(0, 0, false, false);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see cane.brothers.e4.commander.pathTable.IRootPath#getRootPath()
     */
    @Override
    public Path getRootPath() {
	return rootPath;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cane.brothers.e4.commander.pathTable.IRootPath#setRootPath(java.nio.file
     * .Path)
     */
    @Override
    public void setRootPath(Path newPath) {
	this.rootPath = newPath;
	if (compositeLayer != null) {
	    compositeLayer.setRootPath(newPath);
	}
    }

    // /* (non-Javadoc)
    // * @see org.eclipse.swt.widgets.Composite#setFocus()
    // */
    // @Override
    // public boolean setFocus() {
    // return super.setFocus();
    //
    // SelectionLayer selLayer = getSelectionLayer();
    // if (selLayer != null) {
    // selLayer.clear();
    // }
    //
    // }
}
