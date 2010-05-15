/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package com.metamatrix.metamodels.history;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Label</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link com.metamatrix.metamodels.history.Label#getRevision <em>Revision</em>}</li>
 * <li>{@link com.metamatrix.metamodels.history.Label#getHistoryLog <em>History Log</em>}</li>
 * <li>{@link com.metamatrix.metamodels.history.Label#getTag <em>Tag</em>}</li>
 * </ul>
 * </p>
 * 
 * @see com.metamatrix.metamodels.history.HistoryPackage#getLabel()
 * @model
 * @generated
 */
public interface Label extends HistoryLogEntry {

    /**
     * Returns the value of the '<em><b>Tag</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tag</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Tag</em>' attribute.
     * @see #setTag(String)
     * @see com.metamatrix.metamodels.history.HistoryPackage#getLabel_Tag()
     * @model
     * @generated
     */
    String getTag();

    /**
     * Sets the value of the '{@link com.metamatrix.metamodels.history.Label#getTag <em>Tag</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Tag</em>' attribute.
     * @see #getTag()
     * @generated
     */
    void setTag( String value );

    /**
     * Returns the value of the '<em><b>Revision</b></em>' container reference. It is bidirectional and its opposite is '
     * {@link com.metamatrix.metamodels.history.Revision#getLabel <em>Label</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Revision</em>' container reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Revision</em>' container reference.
     * @see #setRevision(Revision)
     * @see com.metamatrix.metamodels.history.HistoryPackage#getLabel_Revision()
     * @see com.metamatrix.metamodels.history.Revision#getLabel
     * @model opposite="label" required="true"
     * @generated
     */
    Revision getRevision();

    /**
     * Sets the value of the '{@link com.metamatrix.metamodels.history.Label#getRevision <em>Revision</em>}' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Revision</em>' container reference.
     * @see #getRevision()
     * @generated
     */
    void setRevision( Revision value );

    /**
     * Returns the value of the '<em><b>History Log</b></em>' container reference. It is bidirectional and its opposite is '
     * {@link com.metamatrix.metamodels.history.LabelLog#getLabels <em>Labels</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>History Log</em>' container reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>History Log</em>' container reference.
     * @see #setHistoryLog(LabelLog)
     * @see com.metamatrix.metamodels.history.HistoryPackage#getLabel_HistoryLog()
     * @see com.metamatrix.metamodels.history.LabelLog#getLabels
     * @model opposite="labels" required="true"
     * @generated
     */
    LabelLog getHistoryLog();

    /**
     * Sets the value of the '{@link com.metamatrix.metamodels.history.Label#getHistoryLog <em>History Log</em>}' container
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>History Log</em>' container reference.
     * @see #getHistoryLog()
     * @generated
     */
    void setHistoryLog( LabelLog value );

} // Label