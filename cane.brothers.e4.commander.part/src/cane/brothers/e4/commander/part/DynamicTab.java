package cane.brothers.e4.commander.part;

/*******************************************************************************
 * File: DynamicTab.java
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

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.osgi.service.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.IdStorage;
import cane.brothers.e4.commander.api.IDynamicTab;
import cane.brothers.e4.commander.event.PartEvents;
import cane.brothers.e4.commander.model.PathFixture;
import cane.brothers.e4.commander.pathTable.PathNatTable;
import cane.brothers.e4.commander.preferences.PreferenceConstants;
import cane.brothers.e4.commander.service.api.IPartService;
import cane.brothers.e4.commander.service.api.ITabService;
import cane.brothers.e4.commander.utils.PathUtils;

/**
 * Dynamic Tab. GUI class of part descriptor implementation.
 * 
 */
public class DynamicTab implements IDynamicTab, ISelectionChangedListener {

    private static final Logger log = LoggerFactory.getLogger(DynamicTab.class);

    @Inject
    private IEventBroker eventBroker;

    // @Inject
    // private EModelService modelService;

    // @Inject
    // private MApplication application;

    @Inject
    private IEclipseContext context;

    @Inject
    @Named(IServiceConstants.ACTIVE_PART)
    private MPart activePart;

    @Inject
    ITabService tabService;

    @Inject
    private ESelectionService selectionService;

    @Inject
    private IPartService partService;

    @Inject
    @Preference(value = PreferenceConstants.PB_STAY_ACTIVE_TAB, nodePath = IdStorage.PREF_PLUGIN_ID)
    private boolean stayActiveTab;

    /**
     * GUI stuff
     */
    private PathNatTable table;

    /**
     * @return the table
     */
    public PathNatTable getTable() {
        return table;
    }

    private Path rootPath;

    private final Color bgColor = Display.getCurrent().getSystemColor(SWT.COLOR_WHITE);

    @Inject
    public DynamicTab() {
    }

    @PostConstruct
    public void createPartControl(Composite parent) {

        Composite panel = new Composite(parent, SWT.NONE);
        // panel.setLayout(new GridLayout(2, true));

        panel.setBackground(bgColor);

        GridLayout gridLayout = new GridLayout();
        gridLayout.marginWidth = 0;
        panel.setLayout(gridLayout);

        // TODO switch context

        Object rootPathObject = context.get(IdStorage.STATE_ROOT_PATH);
        if (rootPathObject != null) {
            rootPath = Paths.get(rootPathObject.toString());
            // rootPath =
            // Paths.get(activePart.getPersistedState().get("rootPath"));
            // log.debug("pref: {}", stayActiveTab);

            // create path table
            table = new PathNatTable(panel, rootPath, eventBroker);
            table.setBackground(bgColor);

            // attach a selection listener to our table
            table.getSelectionProvider().addSelectionChangedListener(this);

            // layout
            GridDataFactory.fillDefaults().grab(true, true).applyTo(table);
        }

    }

    @Inject
    @Optional
    private void setRootPath(@UIEventTopic(PartEvents.TOPIC_PART_PATH_OPEN) final Path newPath) {

        // update root path of current tab
        if (activePart != null && activePart.getObject() == this) {

            rootPath = newPath;
            if (table != null) {
                table.setRootPath(rootPath);

                resolveSelections();
            }
        }
    }

    // resolve visible tabs selections
    private void resolveSelections() {
        // hook: active part always should have selection - dont't care of
        // PB_STAY_ACTIVE_TAB
        // MPart selPart = (stayActiveTab ? activePart :
        // partService.getOppositePart(activePart));

        // eventBroker.post(TabEvents.TOPIC_TAB_REMOVE_SELECTION, selPart);

        // resolve selection
        // tabService.clearSelection(partService.getOppositePart(activePart));
        // tabService.setSelection(activePart);
        eventBroker.post(PartEvents.TOPIC_PART_PATH_REFRESH, null);
    }

    /**
     * TODO clean-up
     * 
     * remove selection on other tabs if any tab was activated
     * 
     * @param event
     */
    @Inject
    @Optional
    public void partActivation(@UIEventTopic(UIEvents.UILifeCycle.ACTIVATE) Event event) {

        if (event != null && event.getPropertyNames() != null) {

            // get active part
            Object obj = event.getProperty(event.getPropertyNames()[0]);
            if (obj instanceof MPart) {
                MPart part = (MPart) obj;

                // clear selection for non-active tabs
                if (part.getObject() != this) {
                    // clearSelection();

                    try {
                        if (log.isDebugEnabled()) {
                            log.debug("part {} was activated", //$NON-NLS-1$
                                    String.valueOf(tabService.getTabId(part)));
                        }
                    }
                    catch (Exception ex) {
                        log.warn("unable to retreview tab id");
                    }
                }
                // restore selection
                else {

                }
            }
        }

    }

    /**
     * clear table selection
     */
    @Override
    public void clearSelection() {
        if (table != null && !table.isDisposed() && table.hasSelection()) {
            table.clearSelection();
            if (log.isDebugEnabled()) {
                log.debug("clear table selection"); //$NON-NLS-1$
            }
        }
    }

    /**
     * set default table selection
     */
    @Override
    public void setSelection() {
        if (table != null && !table.isDisposed()) {
            table.setDefaultSelection();
            if (log.isDebugEnabled()) {
                log.debug("set default table selection"); //$NON-NLS-1$
            }
        }
    }

    @Override
    @Focus
    public void setFocus() {
        if (table != null && !table.isDisposed()) {
            if (table.setFocus()) {
                if (log.isDebugEnabled()) {
                    log.debug("set focus for table"); //$NON-NLS-1$
                }
            }
        }
    }

    /**
     * @return the rootPath
     */
    @Override
    public Path getRootPath() {
        return rootPath;
    }

    @Override
    public String toString() {
        return // DynamicTab.class.getSimpleName()
        super.toString() + " [roopPath: " //$NON-NLS-1$
                + PathUtils.getPath(rootPath) + "]"; //$NON-NLS-1$
    }

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        Object prevSelection = selectionService.getSelection();
        if (log.isDebugEnabled()) {
            log.debug("Prev selection is {}", prevSelection); //$NON-NLS-1$
        }

        IStructuredSelection selection = (IStructuredSelection) event.getSelection();
        boolean hasSelection = selection != null && selection.isEmpty() == false;

        if (hasSelection) {
            Object firstElement = selection.getFirstElement();
            if (log.isDebugEnabled()) {
                log.debug("Current selection is {}", firstElement); //$NON-NLS-1$
            }

            // set the selection to the service
            selectionService.setSelection(firstElement);

            if (log.isDebugEnabled()) {
                log.debug("Selection changed:"); //$NON-NLS-1$
            }

            // trace selected objects
            for (Object sel : selection.toArray()) {
                if (sel instanceof PathFixture) {
                    PathFixture fixture = (PathFixture) sel;
                    if (log.isDebugEnabled()) {
                        log.debug("   " + fixture); //$NON-NLS-1$
                    }
                }
            }
        }
    }

}
