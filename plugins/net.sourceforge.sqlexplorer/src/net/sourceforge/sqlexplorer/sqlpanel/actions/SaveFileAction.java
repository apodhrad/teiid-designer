package net.sourceforge.sqlexplorer.sqlpanel.actions;

/*
 * Copyright (C) 2002-2004 Andrea Mazzolini
 * andreamazzolini@users.sourceforge.net
 *
 * This program is free software; you can redistribute it and/or
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
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

import net.sourceforge.sqlexplorer.Messages;
import net.sourceforge.sqlexplorer.SqlexplorerImages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public class SaveFileAction extends Action {

    private ImageDescriptor img = ImageDescriptor.createFromURL(SqlexplorerImages.getSaveFileIcon());

    public SaveFileAction() {

    }

    @Override
    public String getText() {
        return Messages.getString("Save_1"); //$NON-NLS-1$
    }

    @Override
    public void run() {
        /*FileDialog dlg=new FileDialog(txtComposite.getShell(),SWT.SAVE);
        dlg.setFilterExtensions(new String[]{"*.sql","*.txt"});  //$NON-NLS-1$ //$NON-NLS-2$
        String str=dlg.open();
        if(str!=null){
        	txtComposite.save(str);
        }
        */
    }

    @Override
    public String getToolTipText() {
        return Messages.getString("Save_2"); //$NON-NLS-1$
    }

    @Override
    public ImageDescriptor getHoverImageDescriptor() {
        return img;
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        return img;
    }
}