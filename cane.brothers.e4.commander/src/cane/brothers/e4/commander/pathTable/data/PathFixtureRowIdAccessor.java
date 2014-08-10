/*******************************************************************************
 * File: PathFixtureRowIdAccessor.java
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
package cane.brothers.e4.commander.pathTable.data;

import java.io.Serializable;
import java.util.List;

import org.eclipse.nebula.widgets.nattable.data.IRowIdAccessor;

/**
 * TODO
 *
 */
public class PathFixtureRowIdAccessor implements IRowIdAccessor<PathFixture> {

    protected List<PathFixture> list;

    /**
     * Constructor
     *
     * @param list
     */
    public PathFixtureRowIdAccessor(List<PathFixture> list) {
	this.list = list;
    }

    @Override
    public Serializable getRowId(PathFixture rowObject) {
	return list.indexOf(rowObject);
    }
}
