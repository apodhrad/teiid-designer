/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.diagram.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.teiid.designer.metamodels.diagram.Diagram;
import org.teiid.designer.metamodels.diagram.DiagramContainer;
import org.teiid.designer.metamodels.diagram.DiagramPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.teiid.designer.metamodels.diagram.impl.DiagramContainerImpl#getDiagram <em>Diagram</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 *
 * @since 8.0
 */
public class DiagramContainerImpl extends PresentationEntityImpl implements DiagramContainer {

    /**
     * The cached value of the '{@link #getDiagram() <em>Diagram</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDiagram()
     * @generated
     * @ordered
     */
    protected EList diagram = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DiagramContainerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.eINSTANCE.getDiagramContainer();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
	public EList getDiagram() {
        if (diagram == null) {
            diagram = new EObjectContainmentWithInverseEList(Diagram.class, this, DiagramPackage.DIAGRAM_CONTAINER__DIAGRAM, DiagramPackage.DIAGRAM__DIAGRAM_CONTAINER);
        }
        return diagram;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case DiagramPackage.DIAGRAM_CONTAINER__DIAGRAM:
                    return ((InternalEList)getDiagram()).basicAdd(otherEnd, msgs);
                default:
                    return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
            }
        }
        if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case DiagramPackage.DIAGRAM_CONTAINER__DIAGRAM:
                    return ((InternalEList)getDiagram()).basicRemove(otherEnd, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case DiagramPackage.DIAGRAM_CONTAINER__NAME:
                return getName();
            case DiagramPackage.DIAGRAM_CONTAINER__DIAGRAM:
                return getDiagram();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(EStructuralFeature eFeature, Object newValue) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case DiagramPackage.DIAGRAM_CONTAINER__NAME:
                setName((String)newValue);
                return;
            case DiagramPackage.DIAGRAM_CONTAINER__DIAGRAM:
                getDiagram().clear();
                getDiagram().addAll((Collection)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case DiagramPackage.DIAGRAM_CONTAINER__NAME:
                setName(NAME_EDEFAULT);
                return;
            case DiagramPackage.DIAGRAM_CONTAINER__DIAGRAM:
                getDiagram().clear();
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case DiagramPackage.DIAGRAM_CONTAINER__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case DiagramPackage.DIAGRAM_CONTAINER__DIAGRAM:
                return diagram != null && !diagram.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //DiagramContainerImpl
