/*******************************************************************************
 * File: OpenPathCommand.java
 * 
 * Date: 2014/08/10
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
package cane.brothers.e4.commander.pathTable.command;

import java.nio.file.Files;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.nebula.widgets.nattable.command.AbstractLayerCommandHandler;
import org.eclipse.nebula.widgets.nattable.coordinate.Range;
import org.eclipse.nebula.widgets.nattable.data.ListDataProvider;
import org.eclipse.nebula.widgets.nattable.selection.SelectionLayer;
import org.eclipse.nebula.widgets.nattable.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.event.PartEvents;
import cane.brothers.e4.commander.model.PathFixture;

/**
 * Open new path. Selection in the table should be not null.
 *
 */
public class OpenPathHandler extends
	AbstractLayerCommandHandler<OpenPathCommand> {

    Logger log = LoggerFactory.getLogger(OpenPathHandler.class);

    // @Inject
    private IEventBroker eventBroker;

    @Inject
    IEclipseContext context;

    private SelectionLayer selectionLayer;

    private ListDataProvider<PathFixture> bodyDataProvider;

    /**
     * Constructor
     *
     * @param selectionLayer
     * @param bodyDataProvider
     * @param eventBroker
     */
    public OpenPathHandler(SelectionLayer selectionLayer,
	    ListDataProvider<PathFixture> bodyDataProvider,
	    IEventBroker eventBroker) {
	this.selectionLayer = selectionLayer;
	this.bodyDataProvider = bodyDataProvider;
	this.eventBroker = eventBroker;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.nebula.widgets.nattable.command.ILayerCommandHandler#
     * getCommandClass()
     */
    @Override
    public Class<OpenPathCommand> getCommandClass() {
	return OpenPathCommand.class;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.nebula.widgets.nattable.command.AbstractLayerCommandHandler
     * #doCommand(org.eclipse.nebula.widgets.nattable.command.ILayerCommand)
     */
    @Override
    protected boolean doCommand(OpenPathCommand command) {
	// open new Path if possible

	// 1. get path
	Set<Range> selections = selectionLayer.getSelectedRowPositions();
	PathFixture fixture = null;

	if (log.isDebugEnabled()) {
	    log.debug("Selected Row: " + ObjectUtils.toString(selections)); //$NON-NLS-1$ 
	}

	for (Range r : selections) {
	    for (int i = r.start; i < r.end; i++) {
		if (i > -1) {
		    // handle only first row object in range
		    fixture = bodyDataProvider.getRowObject(i);
		    break;
		}
	    }
	}

	// clear selection
	// selectionLayer.doCommand(new ClearAllSelectionsCommand());

	if (fixture != null) {
	    // 2. check if directory
	    if (Files.isDirectory(fixture.getPath())) {
		// 2.1 open new path
		System.out.println("dir");

		System.out.println("open new path: " + fixture.getPath());
		// synchronously sending a path
		if (eventBroker != null) {
		    eventBroker.send(PartEvents.TOPIC_PART_PATH_OPEN,
			    fixture.getPath());
		}

	    }
	    else {
		// 2.2 do nothing at this moment
	    }
	}

	return false;
    }

}
