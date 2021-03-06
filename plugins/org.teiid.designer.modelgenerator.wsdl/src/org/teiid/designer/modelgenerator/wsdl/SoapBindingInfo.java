/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.modelgenerator.wsdl;

/**
 * @since 8.0
 */
public class SoapBindingInfo {

    public static final int STYLE_RPC_ENCODED = 0;
    public static final int STYLE_RPC_LITERAL = 1;
    public static final int STYLE_DOCUMENT_ENCODED = 2;
    public static final int STYLE_DOCUMENT_LITERAL = 3;

    //    private static final String CONNECTOR_NAME = "XML-Relational SOAP Connector"; //$NON-NLS-1$
    private static final String RPC_ENCODED = "RPC - Encoded"; //$NON-NLS-1$
    private static final String RPC_LITERAL = "RPC - Literal"; //$NON-NLS-1$
    private static final String DOCUMENT_ENCODED = "Document - Encoded"; //$NON-NLS-1$
    private static final String DOCUMENT_LITERAL = "Document - Literal"; //$NON-NLS-1$

    //    private static final String STYLE = "EncodingStyle"; //$NON-NLS-1$
    //    private static final String DESTINATION = "Uri"; //$NON-NLS-1$

    private String m_destinationURL;
    private String m_operName;
    private int m_style;

    public SoapBindingInfo() {

    }

    public void setDestinationURL( String destinationURL ) {
        m_destinationURL = destinationURL;
    }

    public String getDestinationURL() {
        return m_destinationURL;
    }

    public void setStyle( int style ) {
        m_style = style;
    }

    public int getStyle() {
        return m_style;
    }

    public void setOperationName( String name ) {
        m_operName = name;
    }

    public String getStyleString() {
        String strStyle;
        switch (m_style) {
            case STYLE_RPC_ENCODED:
                strStyle = RPC_ENCODED;
                break;
            case STYLE_RPC_LITERAL:
                strStyle = RPC_LITERAL;
                break;
            case STYLE_DOCUMENT_ENCODED:
                strStyle = DOCUMENT_ENCODED;
                break;
            case STYLE_DOCUMENT_LITERAL:
                strStyle = DOCUMENT_LITERAL;
                break;
            default:
                strStyle = null;
        }
        return strStyle;
    }

    public void createConnectorBinding( String modelName,
                                        String name ) throws Exception {
        /*
        final String suffix = ".xmi"; //$NON-NLS-1$
        if (modelName.endsWith(suffix)) {
            modelName = modelName.substring(0, modelName.length() - suffix.length());
        }
        ConfigurationManager impl = DqpPlugin.getInstance().getConfigurationManager();
        Collection types = DqpPlugin.getInstance().getConfigurationManager().getConnectorTypes();
        ComponentType type = null;
        for (Iterator iter = types.iterator(); iter.hasNext();) {
            ComponentType tp = (ComponentType)iter.next();
            if (tp.getName().equals(CONNECTOR_NAME)) {
                type = tp;
                break;
            }
        }
        if (type == null) return;
        ConnectorBinding bind = impl.createConnectorBinding(type, name, false);
        
        try {
            ModelerDqpUtils.setPropertyValue(bind, DESTINATION, getDestinationURL());
        } catch (Exception e) {
            // property was not set
            ModelGeneratorWsdlPlugin.Util.log(e);
        }

        try {
            ModelerDqpUtils.setPropertyValue(bind, STYLE, getStyleString());
        } catch (Exception e) {
            // property was not set
            ModelGeneratorWsdlPlugin.Util.log(e);
        }
        
        impl.addBinding(bind);
        */
    }

    @Override
    public boolean equals( Object other ) {
        if (!(other instanceof SoapBindingInfo)) return false;
        SoapBindingInfo test = (SoapBindingInfo)other;
        return (getDestinationURL().equalsIgnoreCase(test.getDestinationURL()) && getStyle() == test.getStyle());
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + (m_destinationURL == null ? 0 : m_destinationURL.hashCode());
        hash = hash * 31 + m_style;
        return hash;
    }

    public String generateUniqueName() {
        String candidate = m_operName;
        /*
        int num = 1;
        while (!ModelerDqpUtils.isUniqueBindingName(candidate)) {
            candidate = m_operName + "_" + num++; //$NON-NLS-1$
        }
        */
        return candidate;
    }

}
