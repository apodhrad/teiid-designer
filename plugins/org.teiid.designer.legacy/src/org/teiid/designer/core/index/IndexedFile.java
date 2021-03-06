/*******************************************************************************
 * Copyright (c) 2000, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     MetaMatrix, Inc - repackaging and updates for use as a metadata store
 *******************************************************************************/
package org.teiid.designer.core.index;


/**
 * An indexedFile associates a number to a document path, and document properties. 
 * It is what we add into an index, and the result of a query.
 *
 * @since 8.0
 */

public class IndexedFile implements IQueryResult, Comparable<IndexedFile> {
	protected String path;
	protected int fileNumber;

	public IndexedFile(String path, int fileNum) {
		if (fileNum < 1)
			throw new IllegalArgumentException();
		this.fileNumber= fileNum;
		this.path= path;
	}
	public IndexedFile(IDocument document, int fileNum) {
		if (fileNum < 1)
			throw new IllegalArgumentException();
		this.path= document.getName();
		this.fileNumber= fileNum;
	}

    /**
	 * Returns the size of the indexedFile.
	 */
	public int footprint() {
		//object+ 2 slots + size of the string (header + 4 slots + char[])
		return 8 + (2 * 4) + (8 + (4 * 4) + 8 + path.length() * 2);
	}
	/**
	 * Returns the file number.
	 */
	public int getFileNumber() {
		return fileNumber;
	}
	/**
	 * Returns the path.
	 */
	@Override
	public String getPath() {
		return path;
	}
	/**
	 * Sets the file number.
	 */
	public void setFileNumber(int fileNumber) {
		this.fileNumber= fileNumber;
	}
	@Override
    public String toString() {
		return "IndexedFile(" + fileNumber + ": " + path + ")"; //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-3$
	}
	@Override
	public int compareTo(IndexedFile other) {
		return this.path.compareTo(other.path);
	}
}
