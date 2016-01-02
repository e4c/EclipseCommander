/*******************************************************************************
 * File: IPartService.java
 * 
 * Date: 06 сент. 2014 г.
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
package cane.brothers.e4.commander.service.api;

import java.nio.file.Path;
import java.util.Set;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import cane.brothers.e4.commander.api.PartCopyType;

/**
 * Service for model of parts connected with tabs.
 * 
 * TODO use as argument <code>MPart</code> or <code>id</code>.
 */
public interface IPartService {

    /**
     * @return list of all opened parts
     */
    Set<MPart> getParts();

    /**
     * create new MPart from the scratch.
     * 
     * @return true if part was created and assigned to one of two panel.
     */
    boolean createPart(Path rootPath, String panelId);

    /**
     * copy MPart to opposite panel.
     * 
     * @return true if part was created and assigned to opposite panel.
     */
    /**
     * @param activePart
     *            Source Part
     * @param copyType
     *            copy tab to opposite panel or duplicate
     * 
     * @return true if part was created and assigned to panel.
     */
    boolean copyPart(MPart activePart, PartCopyType copyType);

    /**
     * @param id
     *            part id
     * @return existing part or null
     */
    MPart getPart(String id);

    /**
     * @param part
     *            given MPart
     * @return part id or null if there is no given part in set of opened parts
     */
    String getPartId(MPart part);

    /**
     * @param id
     * @return true if part was closed
     */
    boolean closePart(String id);

    /**
     * get visible opposite part
     * 
     * @param activePart
     *            activePart must be not null
     * @return
     */
    MPart getOppositePart(MPart activePart);
}
