package org.teiid.designer.transformation;

import static org.teiid.designer.transformation.TransformationPlugin.PLUGIN_ID;

/**
 * Designer runtime preference names and default values.
 *
 * @since 8.0
 */
public interface PreferenceConstants {

    /**
     * The name of the preference indicating if preview is enabled.
     */
    String AUTO_EXPAND_SELECT = PLUGIN_ID + ".preferences.auto_expand_select"; //$NON-NLS-1$
    
    /**
     * The default value for the PreviewEnabled preference. Default value is {@value} .
     */
    boolean AUTO_EXPAND_SELECT_DEFAULT = false;
    
}
