/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.ui.properties;

import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.teiid.designer.metamodels.core.CorePackage;
import org.teiid.designer.metamodels.core.ModelAnnotation;
import org.teiid.designer.ui.refactor.NamespaceUriRenameDialog;



/** 
 * @since 8.0
 */
public final class CoreModelPropertyEditorFactory implements IPropertyEditorFactory {

    /** 
     * @see org.teiid.designer.ui.properties.IPropertyEditorFactory#supportsStructuralFeature(org.eclipse.emf.ecore.EStructuralFeature)
     * @since 4.3
     */
    @Override
	public boolean supportsStructuralFeature(EStructuralFeature theFeature) {
        boolean result = false;
        
        if (ModelAnnotation.class == theFeature.getContainerClass()) {
            switch (theFeature.getFeatureID()) {
                case CorePackage.MODEL_ANNOTATION__NAMESPACE_URI: {
                    result = true;
                    break;
                } default: {
                    result = false;
                    break;
                }
            }
        }
        
        return result;
    }

    /** 
     * @see org.teiid.designer.ui.properties.IPropertyEditorFactory#createPropertyEditor(org.eclipse.swt.widgets.Composite, org.eclipse.emf.edit.provider.IItemPropertyDescriptor, org.eclipse.ui.views.properties.IPropertyDescriptor, java.lang.Object)
     * @since 4.3
     */
    @Override
	public CellEditor createPropertyEditor(Composite theComposite,
                                           IItemPropertyDescriptor theItemPropertyDescriptor,
                                           IPropertyDescriptor thePropertyDescriptor,
                                           Object theObject) {
        CellEditor result = null;
        
        if (theObject instanceof ModelAnnotation) {
            if (!theItemPropertyDescriptor.canSetProperty(theObject)) {
                return null;
            }
            EStructuralFeature feature = (EStructuralFeature)theItemPropertyDescriptor.getFeature(theObject);
            
            if (feature.getFeatureID() == CorePackage.MODEL_ANNOTATION__NAMESPACE_URI) {
                result = new ExtendedDialogCellEditor(theComposite, thePropertyDescriptor.getLabelProvider()) {
	                @Override
                    protected Object openDialogBox(Control theCellEditorWindow) {
	                    NamespaceUriRenameDialog dialog = new NamespaceUriRenameDialog(theCellEditorWindow.getShell(),
	                                                                                   (String)getValue());
                        setValidator(dialog.getValidator());

	                    // construct/show dialog
	                    dialog.create();
	                    dialog.open();

	                    // get the appropriate value
	                    return dialog.getUri();
	                }
                };
            }
        }
        
        return result;
    }

}
