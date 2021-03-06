/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.transformation.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;
import org.teiid.designer.metamodels.transformation.DataFlowMappingRoot;
import org.teiid.designer.metamodels.transformation.TargetNode;
import org.teiid.designer.metamodels.transformation.TransformationPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Target Node</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.teiid.designer.metamodels.transformation.impl.TargetNodeImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 *
 * @since 8.0
 */
public class TargetNodeImpl extends DataFlowNodeImpl implements TargetNode {

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected EObject target = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TargetNodeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TransformationPackage.eINSTANCE.getTargetNode();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public EObject getTarget() {
        if (target != null && target.eIsProxy()) {
            EObject oldTarget = target;
            target = eResolveProxy((InternalEObject)target);
            if (target != oldTarget) {
                if (eNotificationRequired()) eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                                                                           TransformationPackage.TARGET_NODE__TARGET, oldTarget,
                                                                           target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
	public void setTarget( EObject newTarget ) {
        EObject oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) eNotify(new ENotificationImpl(this, Notification.SET,
                                                                   TransformationPackage.TARGET_NODE__TARGET, oldTarget, target));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd( InternalEObject otherEnd,
                                          int featureID,
                                          Class baseClass,
                                          NotificationChain msgs ) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TransformationPackage.TARGET_NODE__OWNER:
                    if (eContainer != null) msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, TransformationPackage.TARGET_NODE__OWNER, msgs);
                case TransformationPackage.TARGET_NODE__INPUT_LINKS:
                    return ((InternalEList)getInputLinks()).basicAdd(otherEnd, msgs);
                case TransformationPackage.TARGET_NODE__OUTPUT_LINKS:
                    return ((InternalEList)getOutputLinks()).basicAdd(otherEnd, msgs);
                default:
                    return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
            }
        }
        if (eContainer != null) msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove( InternalEObject otherEnd,
                                             int featureID,
                                             Class baseClass,
                                             NotificationChain msgs ) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case TransformationPackage.TARGET_NODE__OWNER:
                    return eBasicSetContainer(null, TransformationPackage.TARGET_NODE__OWNER, msgs);
                case TransformationPackage.TARGET_NODE__INPUT_LINKS:
                    return ((InternalEList)getInputLinks()).basicRemove(otherEnd, msgs);
                case TransformationPackage.TARGET_NODE__OUTPUT_LINKS:
                    return ((InternalEList)getOutputLinks()).basicRemove(otherEnd, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainer( NotificationChain msgs ) {
        if (eContainerFeatureID >= 0) {
            switch (eContainerFeatureID) {
                case TransformationPackage.TARGET_NODE__OWNER:
                    return eContainer.eInverseRemove(this,
                                                     TransformationPackage.DATA_FLOW_MAPPING_ROOT__NODES,
                                                     DataFlowMappingRoot.class,
                                                     msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( EStructuralFeature eFeature,
                        boolean resolve ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TransformationPackage.TARGET_NODE__NAME:
                return getName();
            case TransformationPackage.TARGET_NODE__OWNER:
                return getOwner();
            case TransformationPackage.TARGET_NODE__INPUT_LINKS:
                return getInputLinks();
            case TransformationPackage.TARGET_NODE__OUTPUT_LINKS:
                return getOutputLinks();
            case TransformationPackage.TARGET_NODE__TARGET:
                if (resolve) return getTarget();
                return basicGetTarget();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet( EStructuralFeature eFeature,
                      Object newValue ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TransformationPackage.TARGET_NODE__NAME:
                setName((String)newValue);
                return;
            case TransformationPackage.TARGET_NODE__OWNER:
                setOwner((DataFlowMappingRoot)newValue);
                return;
            case TransformationPackage.TARGET_NODE__INPUT_LINKS:
                getInputLinks().clear();
                getInputLinks().addAll((Collection)newValue);
                return;
            case TransformationPackage.TARGET_NODE__OUTPUT_LINKS:
                getOutputLinks().clear();
                getOutputLinks().addAll((Collection)newValue);
                return;
            case TransformationPackage.TARGET_NODE__TARGET:
                setTarget((EObject)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset( EStructuralFeature eFeature ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TransformationPackage.TARGET_NODE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TransformationPackage.TARGET_NODE__OWNER:
                setOwner((DataFlowMappingRoot)null);
                return;
            case TransformationPackage.TARGET_NODE__INPUT_LINKS:
                getInputLinks().clear();
                return;
            case TransformationPackage.TARGET_NODE__OUTPUT_LINKS:
                getOutputLinks().clear();
                return;
            case TransformationPackage.TARGET_NODE__TARGET:
                setTarget((EObject)null);
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet( EStructuralFeature eFeature ) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case TransformationPackage.TARGET_NODE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TransformationPackage.TARGET_NODE__OWNER:
                return getOwner() != null;
            case TransformationPackage.TARGET_NODE__INPUT_LINKS:
                return inputLinks != null && !inputLinks.isEmpty();
            case TransformationPackage.TARGET_NODE__OUTPUT_LINKS:
                return outputLinks != null && !outputLinks.isEmpty();
            case TransformationPackage.TARGET_NODE__TARGET:
                return target != null;
        }
        return eDynamicIsSet(eFeature);
    }

} // TargetNodeImpl
