/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.diagram.ui.preferences;

/**
 * NotationIDAndName
 * 
 * Data class to encompass a String ID and a String name in displayable form.
 *
 * @since 8.0
 */
public class NotationIDAndName {
	private String id;
	private String displayName;
	
	/**
	 * Constructor.
	 * 
	 * @param id     		Notation ID
	 * @param displayName   Corresponding name in displayable form
	 */
	public NotationIDAndName(String id, String displayName) {
		super();
		this.id = id;
		this.displayName = displayName;
	}
	
	/**
	 * Get the ID
	 * 
	 * @return ID
	 */
	public String getID() {
		return id;
	}
	
	/**
	 * Get the name for display
	 * 
	 * @return  name for display
	 */
	public String getDisplayName() {
		return displayName;
	}
}
