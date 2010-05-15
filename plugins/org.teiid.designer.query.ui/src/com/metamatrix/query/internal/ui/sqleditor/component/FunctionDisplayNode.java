/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package com.metamatrix.query.internal.ui.sqleditor.component;

import java.util.ArrayList;
import com.metamatrix.query.function.FunctionLibrary;
import com.metamatrix.query.sql.ReservedWords;
import com.metamatrix.query.sql.symbol.Constant;
import com.metamatrix.query.sql.symbol.Expression;
import com.metamatrix.query.sql.symbol.Function;

/**
 * The <code>FunctionDisplayNode</code> class is used to represent a Function.
 */
public class FunctionDisplayNode extends ExpressionDisplayNode {

    ///////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ///////////////////////////////////////////////////////////////////////////

    /**
     *   FunctionDisplayNode constructor
     *  @param parentNode the parent DisplayNode of this.
     *  @param function the query language object used to construct this display node.
     */
    public FunctionDisplayNode(DisplayNode parentNode, Function function) {
        this.parentNode = parentNode;
        this.languageObject = function;
        createChildNodes();
    }

    ///////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS
    ///////////////////////////////////////////////////////////////////////////

    /**
     * This supports expressions within it.
     */
    @Override
    public boolean supportsExpression() {
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////
    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////

    /**
     *   Create the child nodes for this type of DisplayNode.
     */
    private void createChildNodes() {
        childNodeList = new ArrayList();

        Function function = (Function)this.getLanguageObject();
        String name = function.getName();
        Expression[] args = function.getArgs();
        if(args!=null) {
	        for(int i=0; i<args.length; i++) {
	            // Special case for TIMESTAMPADD, TIMESTAMPDIFF. 
	            if(i==0 && 
	               (name.equalsIgnoreCase(ReservedWords.TIMESTAMPADD)||name.equalsIgnoreCase(ReservedWords.TIMESTAMPDIFF)) ) {
	                childNodeList.add(DisplayNodeFactory.createDisplayNode(this,((Constant)args[i]).getValue()));
	            } else {
	                childNodeList.add(DisplayNodeFactory.createDisplayNode(this,args[i]));
	            }
	        }
        }
        // Build the Display Node List
        createDisplayNodeList();
    }

    /**
     *   Create the DisplayNode list for this type of DisplayNode.  This is a list of
     *  all the lowest level nodes for this DisplayNode.
     */
    private void createDisplayNodeList() {
        displayNodeList = new ArrayList();
//        int indent = this.getIndentLevel();

        DisplayNode child = null;
        Function function = (Function)this.getLanguageObject();

        String name = function.getName();

		if(function.isImplicit()) {
			// Hide this function, which is implicit
            child = (DisplayNode)childNodeList.get(0);
            if( child.hasDisplayNodes() ) {
                    displayNodeList.addAll(child.getDisplayNodeList());
            } else {
                    displayNodeList.add(child);
            }
            
		} else if(name.equalsIgnoreCase(FunctionLibrary.CONVERT) || name.equalsIgnoreCase(FunctionLibrary.CAST)) {
            displayNodeList.add(DisplayNodeFactory.createFunctionNameDisplayNode(this,name));
            displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,LTPAREN));
            if(childNodeList.size()!=0) {
	            child = (DisplayNode)childNodeList.get(0);
	            if( child.hasDisplayNodes() ) {
	                    displayNodeList.addAll(child.getDisplayNodeList());
	            } else {
	                    displayNodeList.add(child);
	            }
				if(name.equalsIgnoreCase(FunctionLibrary.CONVERT)) {
	                displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,COMMA+SPACE));
				} else {
	                displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,SPACE));
	                displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,ReservedWords.AS));
	                displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,SPACE));
				}
            }
            if(childNodeList.size()>0) {
                Expression[] args = function.getArgs();
                if(args!=null) {
        			if(args.length < 2 || args[1] == null || !(args[1] instanceof Constant)) {
    	                displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,UNDEFINED));
        			} else {
	                    displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,((Constant)args[1]).getValue()));
        			}
                }
            }
            displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,RTPAREN));
		} else if(name.equals("+") || name.equals("-") ||    //$NON-NLS-1$ //$NON-NLS-2$
                  name.equals("*") || name.equals("/") ||    //$NON-NLS-1$ //$NON-NLS-2$
                  name.equals("||")) {                       //$NON-NLS-1$
            displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,LTPAREN));

			for(int i=0; i<childNodeList.size(); i++) {
                child = (DisplayNode)childNodeList.get(i);
                if( child.hasDisplayNodes() ) {
                        displayNodeList.addAll(child.getDisplayNodeList());
                } else {
                        displayNodeList.add(child);
                }
				if(i < (childNodeList.size()-1)) {
                    displayNodeList.add(DisplayNodeFactory.createFunctionNameDisplayNode(this,SPACE+name+SPACE));
				}
			}
            displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,RTPAREN));		
		} else {
            displayNodeList.add(DisplayNodeFactory.createFunctionNameDisplayNode(this,name));
            displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,LTPAREN));

			if(childNodeList.size() > 0) {
				for(int i=0; i<childNodeList.size(); i++) {
                    child = (DisplayNode)childNodeList.get(i);
                    if( child.hasDisplayNodes() ) {
                            displayNodeList.addAll(child.getDisplayNodeList());
                    } else {
                            displayNodeList.add(child);
                    }
					if(i < (childNodeList.size()-1)) {
                        displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,COMMA+SPACE));
					}
				}
			}

            displayNodeList.add(DisplayNodeFactory.createDisplayNode(this,RTPAREN));
		}

	}

}