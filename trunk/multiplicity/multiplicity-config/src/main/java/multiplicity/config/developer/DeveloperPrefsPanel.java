/*
 * DeveloperPrefsPanel.java
 *
 * Created on April 1, 2010, 8:31 PM
 */

package multiplicity.config.developer;

/**
 *
 * @author  dcs0ah1
 */
public class DeveloperPrefsPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = -8987975783345583927L;
	private DeveloperPreferences prefs;


	/** Creates new form DeveloperPrefsPanel */
	public DeveloperPrefsPanel(DeveloperPreferences prefs) {
		this.prefs = prefs;
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		sceneMonitor = new javax.swing.JCheckBox();

		sceneMonitor.setText("Enable scene monitor:");
		sceneMonitor.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
		sceneMonitor.setName("sceneMonitor"); // NOI18N
		sceneMonitor.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				sceneMonitorItemStateChanged(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(sceneMonitor)
						.addContainerGap(223, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(sceneMonitor)
						.addContainerGap(271, Short.MAX_VALUE))
		);
	}// </editor-fold>//GEN-END:initComponents

	private void sceneMonitorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sceneMonitorItemStateChanged
		prefs.setShowSceneMonitor(sceneMonitor.isSelected());
	}//GEN-LAST:event_sceneMonitorItemStateChanged


	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JCheckBox sceneMonitor;
	// End of variables declaration//GEN-END:variables

}