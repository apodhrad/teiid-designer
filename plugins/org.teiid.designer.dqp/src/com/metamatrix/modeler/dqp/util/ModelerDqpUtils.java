/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package com.metamatrix.modeler.dqp.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.teiid.designer.vdb.VdbModelEntry;
import com.metamatrix.common.vdb.ModelInfo;
import com.metamatrix.core.util.I18nUtil;
import com.metamatrix.metamodels.core.ModelType;
import com.metamatrix.modeler.dqp.DqpPlugin;
import com.metamatrix.modeler.internal.core.workspace.WorkspaceResourceFinderUtil;

/**
 * @since 4.3
 */
public final class ModelerDqpUtils {

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTANTS
    // /////////////////////////////////////////////////////////////////////////////////////////////

    private static final String PREFIX = I18nUtil.getPropertyPrefix(ModelerDqpUtils.class);

    /**
     * Max length of a binding name.
     * 
     * @since 4.3
     */
    public static final int BINDING_NAME_MAX_LENGTH = 255;

    /**
     * {@link IStatus} code for an empty or <code>null</code> binding name.
     * 
     * @since 4.3
     */
    public static final int BINDING_NAME_EMPTY_ERROR = 100;

    /**
     * {@link IStatus} code for binding name with an invalid character.
     * 
     * @since 4.3
     */
    public static final int BINDING_NAME_INVALID_CHAR_ERROR = 200;

    /**
     * {@link IStatus} code for a binding name that is too long.
     * 
     * @since 4.3
     */
    public static final int BINDING_NAME_MAX_LENGTH_ERROR = 300;

    /**
     * {@link IStatus} code for a binding name that begins or ends with a space or has consecutive spaces.
     * 
     * @since 4.3
     */
    public static final int BINDING_NAME_WHITESPACE_ERROR = 400;

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTORS
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Don't allow construction.
     */
    private ModelerDqpUtils() {
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // METHODS
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * @param msgKey the properties file key
     * @param params the optional message data parameters
     * @return the error status object with the localized message
     * @since 6.0.0
     */
    public static IStatus createErrorStatus( String msgKey,
                                             String... params ) {
        String msg = (params == null) ? DqpPlugin.Util.getString(msgKey) : DqpPlugin.Util.getString(msgKey, (Object[])params);
        return new Status(IStatus.ERROR, DqpPlugin.PLUGIN_ID, msg);
    }

    /**
     * Obtains a connector binding name based on a <code>ModelInfo</code> object.
     * 
     * @param theModelInfo the model info object which will be used to construct a name
     * @return the name
     * @since 4.3
     */
    public static String createNewConnectorName( ModelInfo theModelInfo ) {
        return createNewConnectorName(theModelInfo.getName());
    }

    /**
     * Obtains a connector binding name based on a model name.
     * 
     * @param model the model name
     * @return the binding name
     * @since 5.5.3
     */
    public static String createNewConnectorName( String name ) {
        return DqpPlugin.Util.getString(PREFIX + "newConnectorName", name); //$NON-NLS-1$
    }

    /**
     * Get a <code>Map</code> of property name to values for JDBC connection properties stored on a model reference on the vdb
     * manifest model.
     * 
     * @param theModelRef the model reference whose connection properties are being requested
     * @return a map of JDBC connection properties (never <code>null</code> but maybe empty)
     * @since 5.0
     */
    public static Map getModelJdbcProperties( VdbModelEntry modelEntry ) {
        Map result = null;
        IPath modelPath = modelEntry.getName();

        IResource resource = WorkspaceResourceFinderUtil.findIResourceByPath(modelPath);

        if ((modelEntry.getType() != ModelType.PHYSICAL_LITERAL) || (resource == null)) {
            result = Collections.EMPTY_MAP;
        } else {
            result = new HashMap();

            // TODO: Find Model's JDBC PRoperties here!!!!

            // Collection properties = mdlSource.getProperties();
            //
            // for (Iterator itr = properties.iterator(); itr.hasNext();) {
            // ModelSourceProperty sourceProperty = (ModelSourceProperty)itr.next();
            // String propertyName = sourceProperty.getName();
            // String propertyValue = sourceProperty.getValue();
            //
            // if (!StringUtil.isEmpty(propertyName) && !StringUtil.isEmpty(propertyValue)) {
            // if (propertyName.equalsIgnoreCase(JDBCConnectionPropertyNames.JDBC_IMPORT_DRIVER_CLASS)) {
            // result.put(JDBCConnectionPropertyNames.JDBC_IMPORT_DRIVER_CLASS, propertyValue);
            // } else if (propertyName.equalsIgnoreCase(JDBCConnectionPropertyNames.JDBC_IMPORT_URL)) {
            // result.put(JDBCConnectionPropertyNames.JDBC_IMPORT_URL, propertyValue);
            // } else if (propertyName.equalsIgnoreCase(JDBCConnectionPropertyNames.JDBC_IMPORT_USERNAME)) {
            // result.put(JDBCConnectionPropertyNames.JDBC_IMPORT_USERNAME, propertyValue);
            // }
            // }
            // }
        }

        return result;
    }

    /**
     * Indicates if the specified name is of proper length and is composed of valid characters for a connector binding name.
     * 
     * @param theProposedName the name being validated
     * @return the validation status
     * @since 4.3
     */
    public static IStatus isValidBindingName( String theProposedName ) {
        int severity = IStatus.OK;
        int code = IStatus.OK;
        String msg = ""; //$NON-NLS-1$

        if ((theProposedName == null) || (theProposedName.length() == 0)) {
            // binding name can't be empty or null
            severity = IStatus.ERROR;
            code = BINDING_NAME_EMPTY_ERROR;
            msg = DqpPlugin.Util.getStringOrKey(PREFIX + "bindingNameEmptyError"); //$NON-NLS-1$
        } else if (theProposedName.startsWith(" ") || theProposedName.endsWith(" ") || (theProposedName.indexOf("  ") != -1)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            severity = IStatus.ERROR;
            code = BINDING_NAME_WHITESPACE_ERROR;
            msg = DqpPlugin.Util.getStringOrKey(PREFIX + "bindingNameWhitespaceError"); //$NON-NLS-1$
        } else {
            int length = theProposedName.length();

            if (length <= BINDING_NAME_MAX_LENGTH) {
                // make sure all characters are valid
                for (int i = 0; i < length; ++i) {
                    char c = theProposedName.charAt(i);

                    if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c) && (c != '_')) {
                        severity = IStatus.ERROR;
                        code = BINDING_NAME_INVALID_CHAR_ERROR;
                        msg = DqpPlugin.Util.getString(PREFIX + "bindingNameInvalidCharError", //$NON-NLS-1$
                                                       new Object[] {Character.toString(c), Integer.toString(i + 1)});
                        break;
                    }
                }
            } else {
                // binding name too long
                severity = IStatus.ERROR;
                code = BINDING_NAME_MAX_LENGTH_ERROR;
                msg = DqpPlugin.Util.getString(PREFIX + "bindingNameMaxLengthError", BINDING_NAME_MAX_LENGTH); //$NON-NLS-1$
            }
        }

        return new Status(severity, DqpPlugin.PLUGIN_ID, code, msg, null);
    }

}