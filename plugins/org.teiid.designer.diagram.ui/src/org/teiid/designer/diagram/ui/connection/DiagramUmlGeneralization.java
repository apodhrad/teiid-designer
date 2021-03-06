/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.diagram.ui.connection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.core.metamodel.aspect.uml.UmlGeneralization;
import org.teiid.designer.diagram.ui.model.DiagramModelNode;


/**
 * @author BLaFond
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 *
 * @since 8.0
 */
public class DiagramUmlGeneralization extends DiagramUmlAssociation {


	/**
	 * @param source
	 * @param target
	 * @param bAss
	 */
	public DiagramUmlGeneralization(
		DiagramModelNode source,
		DiagramModelNode target,
		BinaryAssociation bAss) {
		super(source, target, bAss);
	}

	/**
	 * @param source
	 * @param target
	 * @param bAss
	 * @param sName
	 */
	public DiagramUmlGeneralization(
		DiagramModelNode source,
		DiagramModelNode target,
		BinaryAssociation bAss,
		String sName) {
		super(source, target, bAss, sName);
	}
	
	@Override
    protected void createAdditionalLabelNodes() {
		// D
	}
	
	/* (non-Javadoc)
	 * @See org.teiid.designer.diagram.ui.connection.NodeConnectionModel#getDecoratorId()
	 */
	@Override
    public int getSourceDecoratorId() {
		return BinaryAssociation.DECORATOR_NONE;
	}
	
	/* (non-Javadoc)
	 * @See org.teiid.designer.diagram.ui.connection.NodeConnectionModel#getDecoratorId()
	 */
	@Override
    public int getTargetDecoratorId() {
		return BinaryAssociation.DECORATOR_ARROW_CLOSED;
	}

	/* (non-Javadoc)
	 * @See org.teiid.designer.diagram.ui.connection.NodeConnectionModel#getLineStyle()
	 */
	@Override
    public int getLineStyle() {
		return BinaryAssociation.LINE_SOLID;
	}
	
	/* (non-Javadoc)
	 * @See org.teiid.designer.diagram.ui.connection.NodeConnectionModel#getModelObject()
	 */
	@Override
    public EObject getModelObject() {
		if( getBAssociation().getRelationshipAspect() instanceof UmlGeneralization )
			return getBAssociation().getReference();
	
		return null;
	}
	
	/* (non-Javadoc)
	 * @See org.teiid.designer.diagram.ui.connection.NodeConnectionModel#getToolTipString()
	 */
	@Override
    public List getToolTipStrings() {
		if( getBAssociation().getReference() != null && 
			getBAssociation().getRelationshipAspect() != null &&
			getBAssociation().getRelationshipAspect() instanceof UmlGeneralization )  {
			List stringList = new ArrayList(3);
			
			String toolTip = "Generalization"; //$NON-NLS-1$
			UmlGeneralization theAspect = (UmlGeneralization)getBAssociation().getRelationshipAspect();
			String tempS = theAspect.getToolTip(getBAssociation().getReference());
			if( tempS != null )
				toolTip = tempS;
			stringList.add(toolTip);
//			stringList.add(" Source = " + getSourceNode().getName()); //$NON-NLS-1$
//			stringList.add(" Target = " + getTargetNode().getName()); //$NON-NLS-1$
			return stringList;
		}
		return Collections.EMPTY_LIST;
	}
}
