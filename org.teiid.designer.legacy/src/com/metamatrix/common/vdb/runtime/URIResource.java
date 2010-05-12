/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

package com.metamatrix.common.vdb.runtime;

import java.util.Map;
import com.metamatrix.core.MetaMatrixRuntimeException;

/**
 */
public final class URIResource {

    private static Map resources = null;

    static {
        try {
            URIResourceReader reader = new URIResourceReader();
            resources = reader.getURIResources();

        } catch (Exception e) {
            // TODO: I18n stuff
            String msg = "URIResource.Resource_not_found"; //, new Object[] {URIResourceReader.RESOURCE_NAME}); //$NON-NLS-1$
            throw new MetaMatrixRuntimeException(e, msg);
        }
    }

    public boolean isXMLDocument( String uri ) {
        URIModelResource r = getURIModelResource(uri);
        if (r != null) {
            return r.isXMLDocType();
        }
        return false;
    }

    public String getAuthorizationLevel( String uri ) {
        URIModelResource r = getURIModelResource(uri);
        if (r != null) {
            return r.getAuthLevel();
        }
        return URIModelResource.AUTH_LEVEL.ALL;
    }

    public boolean isPhysicalBindingAllowed( String uri ) {
        URIModelResource r = getURIModelResource(uri);
        if (r != null) {
            return r.isPhysicalBindingAllowed();
        }
        return false;
    }

    private URIModelResource getURIModelResource( String uri ) {
        if (resources.containsKey(uri)) {
            return (URIModelResource)resources.get(uri);
        }
        return null;

    }
}