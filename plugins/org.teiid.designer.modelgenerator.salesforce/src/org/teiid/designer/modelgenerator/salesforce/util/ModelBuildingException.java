/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.modelgenerator.salesforce.util;

/**
 * @since 8.0
 */
public class ModelBuildingException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ModelBuildingException() {
		super();
	}
	
	public ModelBuildingException(Throwable t) {
		super(t);
	}
}
