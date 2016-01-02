/*******************************************************************************
 * File: PathUtils.java
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
package cane.brothers.e4.commander.utils;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * some useful path utils.
 */
public class PathUtils {

    private static final Logger log = LoggerFactory.getLogger(PathUtils.class);

    /**
     * @param path
     * @return path as string or null
     */
    public static String getPath(Path path) {
        if (path != null) {
            return path.toString();
        }
        return null;
    }

    /**
     * Root path have no file name.
     * 
     * @param path
     * @return file name
     */
    public static String getFileName(Path path) {
        String pathString = null;
        if (path != null) {
            if (path.getFileName() != null) {
                pathString = path.getFileName().toString();
            }
            else {
                pathString = path.toString();
            }
        }
        return pathString;
    }

    /**
     * Default path can be different for various OS
     * 
     * @return default path
     */
    public static Path getDefaultPath() {
        Path defaultPath = Paths.get(""); //$NON-NLS-1$

        if (SystemUtils.IS_OS_WINDOWS) {
            defaultPath = Paths.get("C:\\"); //$NON-NLS-1$
        }
        else if (SystemUtils.IS_OS_MAC_OSX) {
            defaultPath = Paths.get(System.getenv().get("HOME")); //$NON-NLS-1$
        }
        return defaultPath;
    }

    /**
     * @param path
     * @return
     */
    public static String getAttributesString(Path path) {
        Set<String> views = FileSystems.getDefault().supportedFileAttributeViews();

        if (views.contains("posix")) { //$NON-NLS-1$
            return getPosixAttributesString(path);
        }
        else {
            return getDosAttributesString(path);
        }
    }

    /**
     * @param path
     * @return
     */
    public static String getDosAttributesString(Path path) {
        DosFileAttributeView basicView = Files.getFileAttributeView(path, DosFileAttributeView.class,
                LinkOption.NOFOLLOW_LINKS);
        StringBuilder attrs = new StringBuilder();

        try {
            // + all basic attributes
            DosFileAttributes dosAttrs = basicView.readAttributes();

            attrs.append(dosAttrs.isReadOnly() ? "r" : "-"); //$NON-NLS-1$ //$NON-NLS-2$
            attrs.append(dosAttrs.isHidden() ? "h" : "-");//$NON-NLS-1$ //$NON-NLS-2$
            attrs.append(dosAttrs.isArchive() ? "a" : "-");//$NON-NLS-1$ //$NON-NLS-2$
            attrs.append(dosAttrs.isSystem() ? "s" : "-");//$NON-NLS-1$ //$NON-NLS-2$
        }
        catch (IOException e) {
            log.warn("unable to read DOS attributes.", e); //$NON-NLS-1$
        }
        return attrs.toString();
    }

    /**
     * @param path
     * @return
     */
    public static String getPosixAttributesString(Path path) {
        PosixFileAttributeView posixView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        StringBuilder attrs = new StringBuilder();

        try {
            // + all basic attributes
            PosixFileAttributes posixAttrs = posixView.readAttributes();

            if (posixAttrs != null) {
                attrs.append(PosixFilePermissions.toString(posixAttrs.permissions()));
            }
        }
        catch (IOException e) {
            log.warn("unable to read Posix file attributes.", e); //$NON-NLS-1$
        }
        return attrs.toString();
    }
}
