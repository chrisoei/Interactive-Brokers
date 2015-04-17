/*
 * Created by Ben Towner
 * Tools used for the collection of input market data for portfolio modeling. 
 */
package com.bti3global.hour11;

import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Hour11 {

	public static void main(String[] args) {
        DataCollector analytics_data = new DataCollector();
        analytics_data.setVisible(true);
    }

    static public void inform( final Component parent, final String str) {
    if( SwingUtilities.isEventDispatchThread() ) {
    	showMsg( parent, str, JOptionPane.INFORMATION_MESSAGE);
    }
    else {
        SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				showMsg( parent, str, JOptionPane.INFORMATION_MESSAGE);
			}
		});
    }
}

static private void showMsg( Component parent, String str, int type) {    	
    // this function pops up a dlg box displaying a message
    JOptionPane.showMessageDialog( parent, str, "Hour 11 - IB", type);
}

}

