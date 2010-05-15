/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

package com.metamatrix.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import com.metamatrix.core.util.CoreStringUtil;

/**
 * This class allows for the original URL of a URL object that was used to create a File object to be saved. The File object is
 * created from the URL by saving the InputStream from the URL off to a local file.
 */
public class FileUrl extends File {

    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see java.io.File#createTempFile(java.lang.String, java.lang.String, java.io.File)
     */
    public static File createTempFile( final String prefix,
                                       final String suffix ) throws IOException {
        FileUrl fileUrl = null;
        File file = File.createTempFile(prefix, suffix);

        fileUrl = new FileUrl(file.toURI());

        file = null;

        return fileUrl;
    }

    /*
     * This is the original URL of the InputStream that was
     * used to create this File object. 
     */
    private String originalUrlString = CoreStringUtil.Constants.EMPTY_STRING;

    public FileUrl( final String pathname ) {
        super(pathname);
    }

    public FileUrl( final String parent,
                    final String child ) {
        super(parent, child);
    }

    public FileUrl( final URI uri ) {
        super(uri);
    }

    /**
     * @return originalUrlString The original URL used to create this File object
     */
    public String getOriginalUrlString() {
        return originalUrlString;
    }

    /**
     * @param originalUrlString
     */
    public void setOriginalUrlString( final String originalUrlString ) {
        this.originalUrlString = originalUrlString;
    }

}