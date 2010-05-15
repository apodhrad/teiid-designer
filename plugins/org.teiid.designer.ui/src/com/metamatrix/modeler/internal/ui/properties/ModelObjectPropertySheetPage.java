/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package com.metamatrix.modeler.internal.ui.properties;

import java.util.Collections;
import java.util.EventObject;
import java.util.Iterator;
import java.util.Set;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.properties.PropertySheetPage;
import com.metamatrix.core.event.EventObjectListener;
import com.metamatrix.core.event.EventSourceException;
import com.metamatrix.metamodels.core.ModelAnnotation;
import com.metamatrix.modeler.core.notification.util.NotificationUtilities;
import com.metamatrix.modeler.core.notification.util.SourcedNotificationUtilities;
import com.metamatrix.modeler.core.transaction.SourcedNotification;
import com.metamatrix.modeler.internal.ui.viewsupport.ModelUtilities;
import com.metamatrix.modeler.ui.UiConstants;
import com.metamatrix.modeler.ui.UiPlugin;
import com.metamatrix.modeler.ui.event.ModelResourceEvent;
import com.metamatrix.ui.internal.eventsupport.SelectionUtilities;

//import com.metamatrix.ui.table.TableSizeAdapter;

/**
 * ModelObjectPropertySheetPage is a specialization of PropertySheetPage that refreshes the content
 * of the PropertySheetPage when a notification occurs on the displayed object.  It also adds an
 * Action to the toolbar that can display the metamodel extension properties of a selected
 * model object.
 */
public class ModelObjectPropertySheetPage 
        extends PropertySheetPage 
        implements INotifyChangedListener, EventObjectListener {

    /** the current ISelection state in the PropertySheetPage */
    private ISelection currentSelection;
    
    private Control control;
    
    private boolean handlingNotification = false;

    
    
    /** 
     * 
     * @since 4.2
     */
    public ModelObjectPropertySheetPage() {
        super();
        // Wire up for event object listener
        try {
            UiPlugin.getDefault().getEventBroker().addListener(ModelResourceEvent.class, this);
        } catch (EventSourceException e) {
            UiConstants.Util.log(IStatus.ERROR, e, e.getMessage());
        }
    }
    /**
     * Overridden from the base class to keep track of the current selection and enable/disable
     * the extension properties toggle action. 
     * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
     */
    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {

        if ( control != null && ! control.isDisposed() ) {
            if ( selection != null && ! selection.isEmpty() ) {
                try {
                    currentSelection = selection;
                    super.selectionChanged(part, selection);
                } catch (Exception e) {
                    UiConstants.Util.log(IStatus.ERROR, e, e.getClass().getName());
                }
            } else if ( selection != null ) {
                try {
                    super.selectionChanged(part, selection);
                } catch (Exception e) {
                    UiConstants.Util.log(IStatus.ERROR, e, e.getClass().getName());
                }
            }
        }

    }

    /* (non-Javadoc)
     * Listens to notifications that change the currently displayed object so that the panel can be refreshed.
     * @see org.eclipse.emf.edit.provider.INotifyChangedListener#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        // to prevent looping, do not process any notifications that occur during refresh.
        if ( handlingNotification ) {
            return;
        }
        
        if ( notification instanceof SourcedNotification ) {
            Object source = ((SourcedNotification) notification).getSource(); 
            if( source == this || source instanceof ModelObjectPropertySource) {
                //If this is the source of the notification, don't process - just return
                return;
            }
        }
        
        if ( currentSelection != null ) {
            EObject selectedObject = SelectionUtilities.getSelectedEObject(currentSelection);
            Set affectedObjects = SourcedNotificationUtilities.gatherNotifiers(notification, true);

            if ( selectedObject != null ) {                
                if (affectedObjects.contains(selectedObject)) {
                    handlingNotification = true;
                    try {
                        this.refresh();
                    } catch (SWTException e) {
                        // swallow - a Widget is disposed exception may occur due to deactivateCellEditor call
                        //   but this is not even important enough to log.
                    } finally {
                        handlingNotification = false;
                    }
                }
            } else if (!SelectionUtilities.getSelectedIResourceObjects(this.currentSelection).isEmpty()) {
                // if an IResource is selected and the EObject of the notification(s) is a ModelAnnotation
                // go ahead and refresh the property page. to determine that the ModelAnnotation is from
                // the model in the current selection is not worth the effort. just refresh.

                boolean isModelAnnotation = false;
                if (notification instanceof SourcedNotification) {
                    for (final Iterator it = ((SourcedNotification) notification).getNotifications().iterator(); it.hasNext();) {
                      if (((Notification) it.next()).getNotifier() instanceof ModelAnnotation) {
                          isModelAnnotation = true;
                          break;
                      }
                    }
                } else {
                    final EObject target = NotificationUtilities.getEObject(notification);
                    isModelAnnotation = target instanceof ModelAnnotation; 
                }
                
                // editable model resource properties are found in the ModelAnnotation
                if (isModelAnnotation) {
                    this.handlingNotification = true;

                    try {
                        refresh();
                    } catch (SWTException e) {
                        // swallow - a Widget is disposed exception may occur due to deactivateCellEditor call
                        //   but this is not even important enough to log.
                    } finally {
                        this.handlingNotification = false;
                    }
                }
            }
        }
    }


    /* (non-Javadoc)
     * Overridden to hook this page up as a notification listener.
     * @see org.eclipse.ui.part.IPageBookViewPage#init(org.eclipse.ui.part.IPageSite)
     */
    @Override
    public void init(IPageSite pageSite) {
        super.init(pageSite);
        ModelUtilities.addNotifyChangedListener(this);
    }

    /* (non-Javadoc)
     * Overridden to create the extensions action and add it to the toolbar.
     * @see org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    @Override
    public void createControl(Composite parent) {
        super.createControl(parent);
        
        control = super.getControl();
//        if ( control instanceof TableTree ) {
//            new TableSizeAdapter(((TableTree) control).getTable());
//        }
    }
    
    /* (non-Javadoc)
     * Overridden to unhook this page as a notification listener.
     * @see org.eclipse.ui.part.IPage#dispose()
     */
    @Override
    public void dispose() {
        ModelUtilities.removeNotifyChangedListener(this);
        // remove event object listener
        try {
            UiPlugin.getDefault().getEventBroker().removeListener(ModelResourceEvent.class, this);
        } catch (EventSourceException e) {
            UiConstants.Util.log(IStatus.ERROR, e, e.getMessage());
        }
        super.dispose();
    }
    /** 
     * @see com.metamatrix.core.event.EventObjectListener#processEvent(java.util.EventObject)
     * @since 4.2
     */
    public void processEvent(EventObject obj) {
        ModelResourceEvent event = (ModelResourceEvent) obj;
        if ( event.getType() == ModelResourceEvent.RELOADED ) {
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    selectionChanged(null, new StructuredSelection(Collections.EMPTY_LIST));
                }
            });
            
        }
    }
}