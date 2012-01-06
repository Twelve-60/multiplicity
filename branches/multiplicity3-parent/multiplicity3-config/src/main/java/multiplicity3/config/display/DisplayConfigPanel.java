/*
 * DisplayPreferences2.java
 *
 * Created on March 31, 2010, 9:54 PM
 */

package multiplicity3.config.display;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.jme3.system.AppSettings;
import com.jme3.system.JmeSystem;

import multiplicity3.config.display.DisplayPrefsItem.InputType;

import multiplicity3.config.PreferencesItem;

/**
 *
 * @author  dcs0ah1
 */
public class DisplayConfigPanel extends JPanel implements PreferencesItem {
	
	private static final long serialVersionUID = 8757133417077939163L;
	private DisplayPrefsItem prefs;
	
    private JTextField alphaBits = new JTextField();
    private JTextField antiAlias = new JTextField();
    private JTextField depthBits = new JTextField();
    private JComboBox displaySelector = new JComboBox();
    private JCheckBox fullScreen = new JCheckBox();
    private JLabel jLabel1 = new JLabel();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JLabel jLabel4 = new JLabel();
    private JLabel jLabel5 = new JLabel();
    private JTextField stencilBits = new JTextField();
    private JComboBox jcb = new JComboBox(InputType.values());
	private JLabel jLabelInputType = new JLabel();

	/** Creates new form DisplayPreferences2 */
	public DisplayConfigPanel(DisplayPrefsItem prefs) {
		this.prefs = prefs;
		initComponents();
		loadCurrentSettings();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1.setText("Display Size:");
        jLabel1.setName("jLabel1"); // NOI18N

        displaySelector.setModel(new DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        displaySelector.setName("displaySelector"); // NOI18N
        displaySelector.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                displaySelectorItemStateChanged(evt);
            }
        });

        fullScreen.setText("Full Screen");
        fullScreen.setHorizontalTextPosition(SwingConstants.LEADING);
        fullScreen.setName("fullScreen"); // NOI18N
        fullScreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullScreenActionPerformed(evt);
            }
        });

        jLabel2.setText("Anti-alias min samples:");
        jLabel2.setName("jLabel2"); // NOI18N

        antiAlias.setText("jTextField1");
        antiAlias.setName("antiAlias"); // NOI18N
        antiAlias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                antiAliasKeyReleased(evt);
            }
        });

        jLabel3.setText("Stencil bits:");
        jLabel3.setName("jLabel3"); // NOI18N

        stencilBits.setText("jTextField1");
        stencilBits.setName("stencilBits"); // NOI18N
        stencilBits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                stencilBitsKeyReleased(evt);
            }
        });

        jLabel4.setText("Alpha bits:");
        jLabel4.setName("jLabel4"); // NOI18N

        alphaBits.setText("jTextField1");
        alphaBits.setName("alphaBits"); // NOI18N
        alphaBits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                alphaBitsKeyReleased(evt);
            }
        });

        jLabel5.setText("Depth bits:");
        jLabel5.setName("jLabel5"); // NOI18N

        depthBits.setText("jTextField1");
        depthBits.setName("depthBits"); // NOI18N
        depthBits.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                depthBitsKeyReleased(evt);
            }
        });
            
        jLabelInputType.setText("Input Type:");

		jcb.setSelectedItem(prefs.getInputType());
		jcb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prefs.setInputType(InputType.valueOf(jcb.getSelectedItem().toString()));
			}			
		});
		
	    setLayout(null);
	    
		jLabelInputType.setBounds(new Rectangle(30, 30, 100, 24));
		jcb.setBounds(new Rectangle(130, 30, 200, 24));
	    
	    jLabel1.setBounds(new Rectangle(30, 90, 100, 24));
	    displaySelector.setBounds(new Rectangle(130, 90, 200, 24));	    
	    fullScreen.setBounds(new Rectangle(350, 90, 125, 24));
	    
	    jLabel2.setBounds(new Rectangle(30, 165, 150, 24));
	    antiAlias.setBounds(new Rectangle(180, 165, 35, 24));
	    
	    jLabel3.setBounds(new Rectangle(275, 165, 150, 24));
	    stencilBits.setBounds(new Rectangle(360, 165, 35, 24));
	    
	    jLabel4.setBounds(new Rectangle(30, 195, 150, 24));
	    alphaBits.setBounds(new Rectangle(180, 195, 35, 24));
	    
	    jLabel5.setBounds(new Rectangle(275, 195, 150, 24));
	    depthBits.setBounds(new Rectangle(360, 195, 35, 24));
	    
	    add(jLabelInputType);
	    add(jcb);
	    add(jLabel1);
	    add(displaySelector);	
	    add(fullScreen);
	    add(jLabel2);
		add(antiAlias);
	    add(jLabel3);
		add(stencilBits);
	    add(jLabel4);
		add(alphaBits);
	    add(jLabel5);
	    add(depthBits);

    }

	private void displaySelectorItemStateChanged(java.awt.event.ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED && displaySelector != null) {
			DisplayMode m = (DisplayMode) displaySelector.getSelectedItem();
			setSelectedDisplayMode(m);
		}
	}

	private void fullScreenActionPerformed(java.awt.event.ActionEvent evt) {
		prefs.setFullScreen(fullScreen.isSelected());
	}

	private void antiAliasKeyReleased(java.awt.event.KeyEvent evt) {
		prefs.setMinimumAntiAliasSamples(getNumberFromTextField(antiAlias));
	}


	private void stencilBitsKeyReleased(java.awt.event.KeyEvent evt) {
		prefs.setStencilBits(getNumberFromTextField(stencilBits));
	}

	private void alphaBitsKeyReleased(java.awt.event.KeyEvent evt) {
		prefs.setAlphaBits(getNumberFromTextField(alphaBits));
	}

	private void depthBitsKeyReleased(java.awt.event.KeyEvent evt) {
		prefs.setDepthBits(getNumberFromTextField(depthBits));
	}
	
	private int getNumberFromTextField(JTextField tf) {
		try {
			int num = Integer.parseInt(tf.getText());
			tf.setForeground(Color.black);
			return num;
		}catch(NumberFormatException ex) {
			tf.setForeground(Color.red);
		}    
		return 0;
	}

	private DisplayMode[] getDisplayModes() {
		DisplayMode[] modes = null;
		try {
			AppSettings settings = new AppSettings(true);
			JmeSystem.initialize(settings);
			modes = Display.getAvailableDisplayModes();
			Arrays.sort(modes, new DisplayModeComparator());
			Display.destroy();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		return modes;
	}

	private int getCurrentDisplayModeIndex(DisplayMode[] modes) {
		for(int i = 0; i < modes.length; i++) {
			DisplayMode m = modes[i];

			if(m.getHeight() == prefs.getHeight() &&
					m.getWidth() == prefs.getWidth() &&
					m.getBitsPerPixel() == prefs.getBitsPerPixel() &&
					m.getFrequency() == prefs.getFrequency())
			{
				return i;
			}
		}

		return -1;
	}

	public DisplayMode getCurrentDisplayMode(DisplayMode[] modes) {
		for(DisplayMode m : modes) {
			if(m.getHeight() == prefs.getHeight() &&
					m.getWidth() == prefs.getWidth() &&
					m.getBitsPerPixel() == prefs.getBitsPerPixel() &&
					m.getFrequency() == prefs.getFrequency())
			{
				return m;
			}
		}

		return null;
	}

	private void setSelectedDisplayMode(DisplayMode m) {
		prefs.setWidth(m.getWidth());
		prefs.setHeight(m.getHeight());
		prefs.setBitsPerPixel(m.getBitsPerPixel());
		prefs.setFrequency(m.getFrequency());
	}

	private void loadCurrentSettings() {
		initDisplaySelector();
		initFullScreen();
		antiAlias.setText("" + prefs.getMinimumAntiAliasSamples());
		alphaBits.setText("" + prefs.getAlphaBits());
		stencilBits.setText("" + prefs.getStencilBits());
		depthBits.setText("" + prefs.getDepthBits());
//		displayShapeFile.setText(prefs.getDisplayShape());
//		stereo3DSelector.setSelectedItem(prefs.getStereo3DMode().toString());
//		defaultDisplayShape.setSelected(prefs.getUseDefaultShapeFlag());
	}

	private void initFullScreen() {
		fullScreen.setSelected(prefs.getFullScreen());
	}

	private void initDisplaySelector() {
		// get the saved display mode as loading the list causes a change
		DisplayMode currentMode = getCurrentDisplayMode(getDisplayModes());

		displaySelector.removeAllItems();
		for (DisplayMode dm : getDisplayModes()) {
			displaySelector.addItem(dm);
		}

		if(currentMode == null) {
			// we didn't already have a display, pick the first
			currentMode = getDisplayModes()[0];
		}

		// restore saved display mode
		setSelectedDisplayMode(currentMode);
		displaySelector.setSelectedIndex(getCurrentDisplayModeIndex(getDisplayModes()));		
	}
	
	@Override
	public JPanel getConfigurationPanel() {
		return this;
	}

	@Override
	public String getConfigurationPanelName() {
		return "Display";
	}

}
