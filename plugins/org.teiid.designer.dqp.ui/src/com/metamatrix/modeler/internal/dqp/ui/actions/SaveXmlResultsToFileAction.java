/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package com.metamatrix.modeler.internal.dqp.ui.actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import com.metamatrix.core.util.I18nUtil;
import com.metamatrix.core.util.CoreStringUtil;
import com.metamatrix.modeler.dqp.ui.DqpUiConstants;
import com.metamatrix.modeler.dqp.ui.DqpUiPlugin;
import com.metamatrix.modeler.internal.dqp.ui.jdbc.IResultsProvider;
import com.metamatrix.modeler.internal.dqp.ui.jdbc.XmlDocumentResultsModel;
import com.metamatrix.modeler.internal.ui.actions.workers.ExportTextToFileWorker;

/**
 * @since 5.5.3
 */
public class SaveXmlResultsToFileAction extends Action implements
                                                      DqpUiConstants {

    // ===========================================================================================================================
    // Fields
    // ===========================================================================================================================

    private IResultsProvider resultsProvider;

    private String fileName;

    private boolean success; // indicates if the last run was successful

    // ===========================================================================================================================
    // Constructors
    // ===========================================================================================================================

    /**
     * @since 5.5.3
     */
    public SaveXmlResultsToFileAction(IResultsProvider resultsProvider) {
        super(UTIL.getString(I18nUtil.getPropertyPrefix(SaveXmlResultsToFileAction.class) + "saveToFileAction"), IAction.AS_PUSH_BUTTON); //$NON-NLS-1$
        setImageDescriptor(DqpUiPlugin.getDefault().getImageDescriptor(Images.SAVE_TO_FILE_ICON));
        setToolTipText(UTIL.getString(I18nUtil.getPropertyPrefix(SaveXmlResultsToFileAction.class) + "saveToFileAction.tip")); //$NON-NLS-1$
        setEnabled(true);

        this.resultsProvider = resultsProvider;
    }

    // ===========================================================================================================================
    // Methods
    // ===========================================================================================================================

    private String createHeader(XmlDocumentResultsModel model) {
        String prefix = I18nUtil.getPropertyPrefix(SaveXmlResultsToFileAction.class);
        DateFormat formatter = new SimpleDateFormat(UTIL.getString(prefix + "header.datePattern")); //$NON-NLS-1$
        String date = formatter.format(new Date(System.currentTimeMillis()));

        StringBuffer buf = new StringBuffer(model.getSql().length() * 2);
        buf.append(UTIL.getString(prefix + "header.line_1")); //$NON-NLS-1$
        buf.append(UTIL.getString(prefix + "header.line_2") + date + CoreStringUtil.Constants.LINE_FEED_CHAR); //$NON-NLS-1$
        buf.append(UTIL.getString(prefix + "header.line_3")); //$NON-NLS-1$
        buf.append(model.getSql()).append(CoreStringUtil.Constants.LINE_FEED_CHAR);
        buf.append(UTIL.getString(prefix + "header.line_4")); //$NON-NLS-1$
        buf.append(UTIL.getString(prefix + "header.line_5")); //$NON-NLS-1$

        return buf.toString();
    }

    /**
     * @return the name of the file where the last successful save occurred or <code>null</code>
     * @since 5.5.3
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * @see org.eclipse.jface.action.Action#run()
     * @since 5.5
     */
    @Override
    public void run() {
        this.fileName = null;
        this.success = false;
        XmlDocumentResultsModel model = (XmlDocumentResultsModel)this.resultsProvider.getResults();

        if (model == null) {
            setEnabled(false);
        } else {
            String prefix = I18nUtil.getPropertyPrefix(SaveXmlResultsToFileAction.class);
            
            // The ExportTextToFileWorker inserts the header at the beginning of the file. Since an XML file has a standard
            // first line identifying it as and XML the header must go after this first line. So we ask the model to do this
            // and pass in a null header to the exporter.
            model.setHeaderComment(createHeader(model));
            ExportTextToFileWorker expWorker = new ExportTextToFileWorker(UTIL.getString(prefix + "exportFileWorker.title"), //$NON-NLS-1$
                                                                          UTIL.getString(prefix
                                                                                         + "exportFileWorker.defaultFileName"), //$NON-NLS-1$
                                                                          UTIL.getString(prefix
                                                                                         + "exportFileWorker.defaultExtension"), //$NON-NLS-1$
                                                                          null, model.getResultsAsText());
            this.fileName = expWorker.getFileName();
            this.success = !expWorker.export();
        }
    }

    /**
     * @return <code>true</code> if save was successful
     * @since 5.5.3
     */
    public boolean wasSaveSuccessful() {
        return this.success;
    }
}