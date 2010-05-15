/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.udf.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.teiid.designer.udf.UdfModelExporter;
import com.metamatrix.core.modeler.util.FileUtil;
import com.metamatrix.core.modeler.util.FileUtil.Extensions;
import com.metamatrix.core.util.I18nUtil;
import com.metamatrix.core.util.CoreStringUtil;
import com.metamatrix.ui.internal.util.WidgetFactory;
import com.metamatrix.ui.internal.util.WidgetUtil;
import com.metamatrix.ui.internal.wizard.AbstractWizardPage;

/**
 * A wizard page to allow the user to choose the file location and name of the UDF model zip file being exported.
 * 
 * @since 6.0.0
 */
public final class TargetSelectionPage extends AbstractWizardPage {

    /**
     * A prefix for I18n message keys.
     * 
     * @since 6.0.0
     */
    private static final String PREFIX = I18nUtil.getPropertyPrefix(TargetSelectionPage.class);

    private interface DialogSettingsProperties {
        String PAGE_SECTION = TargetSelectionPage.class.getSimpleName();
        String LOCATIONS = "locations"; //$NON-NLS-1$
    }

    /**
     * MRU of previously used output files.
     * 
     * @since 6.0.0
     */
    private Combo cbxTargetName;

    /**
     * When checked it authorizes the overwriting of an existing file.
     * 
     * @since 6.0.0
     */
    private Button chkOverwrite;

    /**
     * The business object.
     * 
     * @since 6.0.0
     */
    private final UdfModelExporter exporter;

    /**
     * @param exporter the wizard business object
     * @since 6.0.0
     */
    public TargetSelectionPage( UdfModelExporter exporter ) {
        super(TargetSelectionPage.class.getSimpleName(), UdfUiPlugin.UTIL.getString(PREFIX + "title")); //$NON-NLS-1$
        this.exporter = exporter;
    }

    /**
     * @return the business object
     * @since 6.0.0
     */
    UdfModelExporter accessExporter() {
        return this.exporter;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
     * @since 6.0.0
     */
    @Override
    public void createControl( Composite parent ) {
        // mainControl looks like:
        // browse panel
        // checkbox
        // jars panel
        Composite mainControl = WidgetFactory.createPanel(parent, SWT.NONE, GridData.FILL_BOTH);
        setControl(mainControl);

        // pnlBrowse looks like:
        // label textfield button
        Composite pnlBrowse = WidgetFactory.createPanel(mainControl, SWT.NONE, GridData.FILL_HORIZONTAL, 1, 3);

        // add label
        WidgetFactory.createLabel(pnlBrowse, UdfUiPlugin.UTIL.getString(PREFIX + "lblExportLocation")); //$NON-NLS-1$

        // add MRU combo box of locations
        this.cbxTargetName = WidgetFactory.createCombo(pnlBrowse,
                                                       SWT.READ_ONLY,
                                                       GridData.FILL_HORIZONTAL,
                                                       getDialogSettings().getArray(DialogSettingsProperties.LOCATIONS));
        this.cbxTargetName.addModifyListener(new ModifyListener() {
            public void modifyText( ModifyEvent theEvent ) {
                handleOutputFileModified();
            }
        });

        // add browse button
        Button btnBrowse = WidgetFactory.createButton(pnlBrowse, UdfUiPlugin.UTIL.getString(PREFIX + "btnBrowse")); //$NON-NLS-1$
        btnBrowse.setToolTipText(UdfUiPlugin.UTIL.getString(PREFIX + "btnBrowse.tip")); //$NON-NLS-1$
        btnBrowse.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected( SelectionEvent event ) {
                handleBrowse();
            }
        });
        WidgetUtil.setLayoutData(btnBrowse);

        // add overwrite checkbox
        this.chkOverwrite = WidgetFactory.createCheckBox(mainControl, UdfUiPlugin.UTIL.getString(PREFIX + "chkOverwrite")); //$NON-NLS-1$
        this.chkOverwrite.setEnabled(false);
        this.chkOverwrite.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected( SelectionEvent event ) {
                handleOverwriteSelected();
            }
        });

        // set initial state
        setPageComplete(false);
        setMessage(UdfUiPlugin.UTIL.getString(PREFIX + "initialMsg")); //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.wizard.WizardPage#getDialogSettings()
     * @since 6.0.0
     */
    @Override
    protected IDialogSettings getDialogSettings() {
        IDialogSettings temp = super.getDialogSettings();
        IDialogSettings settings = temp.getSection(DialogSettingsProperties.PAGE_SECTION);

        // add a section in the wizard's dialog setting for this page
        if (settings == null) {
            settings = temp.addNewSection(DialogSettingsProperties.PAGE_SECTION);
        }

        return settings;
    }

    /**
     * Handler for when the browse button is clicked.
     * 
     * @since 6.0.0
     */
    void handleBrowse() {
        FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
        dialog.setText(UdfUiPlugin.UTIL.getString(PREFIX + "browseDialogtitle")); //$NON-NLS-1$

        // set starting directory if necessary
        String currentPath = this.exporter.getExportZipFilePath();

        if (currentPath != null) {
            int index = currentPath.lastIndexOf(File.separator);

            if (index != -1) {
                dialog.setFilterPath(currentPath.substring(0, index));
            }
        }

        String path = dialog.open();

        // modify MRU history if new export zip file name selected
        if (path != null) {
            // make sure extension is correct
            if (!FileUtil.isZipFileName(path)) {
                path += Extensions.ZIP;
            }

            ArrayList items = new ArrayList(Arrays.asList(this.cbxTargetName.getItems()));

            if (!items.contains(path)) {
                items.add(path);
                WidgetUtil.setComboItems(this.cbxTargetName, items, null, false, path);
            } else {
                this.cbxTargetName.setText(path);
            }
        }

        updateState();
    }

    /**
     * Handler for when the output file name has been modified.
     * 
     * @since 6.0.0
     */
    void handleOutputFileModified() {
        this.exporter.setExportZipFilePath(this.cbxTargetName.getText());
        updateState();
    }

    /**
     * Handler for when overwrite is checked/unchecked.
     * 
     * @since 6.0.0
     */
    void handleOverwriteSelected() {
        this.exporter.setOverwriteMode(this.chkOverwrite.getSelection());
        updateState();
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.metamatrix.ui.internal.wizard.AbstractWizardPage#saveSettings()
     * @since 6.0.0
     */
    @Override
    public void saveSettings() {
        IDialogSettings settings = getDialogSettings();
        WidgetUtil.saveSettings(settings, DialogSettingsProperties.LOCATIONS, this.cbxTargetName);
    }

    /**
     * Refreshes the UI to reflect the current state of the business object.
     * 
     * @since 6.0.0
     */
    void updateState() {
        String errorMsg = null;
        String selectedPath = this.exporter.getExportZipFilePath();

        if (CoreStringUtil.isEmpty(selectedPath)) {
            if (this.chkOverwrite.isEnabled()) {
                this.chkOverwrite.setEnabled(false);
            }

            if (this.chkOverwrite.getSelection()) {
                this.chkOverwrite.setSelection(false);
            }

            errorMsg = UdfUiPlugin.UTIL.getString(PREFIX + "emptyDestinationMsg"); //$NON-NLS-1$
        } else if (this.exporter.isOverwriteRequired()) {
            if (this.chkOverwrite.isEnabled()) {
                if (!this.chkOverwrite.getSelection()) {
                    errorMsg = UdfUiPlugin.UTIL.getString(PREFIX + "overwriteFileMsg"); //$NON-NLS-1$
                }
            } else {
                this.chkOverwrite.setEnabled(true);
                errorMsg = UdfUiPlugin.UTIL.getString(PREFIX + "overwriteFileMsg"); //$NON-NLS-1$
            }
        } else {
            if (this.chkOverwrite.isEnabled()) {
                this.chkOverwrite.setEnabled(false);
            }

            if (this.chkOverwrite.getSelection()) {
                this.chkOverwrite.setSelection(false);
            }
        }

        setPageComplete(this.exporter.canExport().isOK());

        if (isPageComplete()) {
            setErrorMessage(null);
            setMessage(UdfUiPlugin.UTIL.getString(PREFIX + "pageCompleteMessage")); //$NON-NLS-1$
        } else {
            setErrorMessage(errorMsg);
        }
    }

}