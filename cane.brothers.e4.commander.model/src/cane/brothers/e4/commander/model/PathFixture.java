/*******************************************************************************
 * File: PathFixture.java
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
package cane.brothers.e4.commander.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cane.brothers.e4.commander.utils.PathUtils;

/**
 * Class wrapper for given path representation in the table row. One field per
 * column except path.
 *
 */
public class PathFixture {

    private static final Logger log = LoggerFactory
	    .getLogger(PathFixture.class);

    private Path path;

    // maybe use it later
    public static final String FIELD_ID = "id"; //$NON-NLS-1$

    public static final String FIELD_NAME = "name"; //$NON-NLS-1$
    public static final String FIELD_SIZE = "size"; //$NON-NLS-1$
    public static final String FIELD_ATTRS = "attr"; //$NON-NLS-1$

    private String name;
    private long size;
    private String attributes;

    /**
     * Constructor
     * 
     * @param path
     */
    public PathFixture(Path path) {
	this.path = path;

	if (path != null) {

	    // root path have no file name
	    this.name = PathUtils.getFileName(path);

	    try {
		this.size = Files.size(path);
	    }
	    catch (IOException ex) {
		this.size = 0;
		// TODO ex
		log.warn("there is problems on size retrieve", ex); //$NON-NLS-1$ 
	    }
	}
	else {
	    log.warn("path is null"); //$NON-NLS-1$
	}

	this.attributes = PathUtils.getAttributesString(path);
    }

    public Path getPath() {
	return path;
    }

    public String getName() {
	return name;
    }

    public long getSize() {
	return size;
    }

    public String getAttributes() {
	return attributes;
    }

    @Override
    public String toString() {
	// TODO size to Kb/Mb
	return "[" + name + ", " + size + ", " + attributes + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
    }

}
