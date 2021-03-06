/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Components;

import EvacSim.EvacSim;
import GUI.EvacSimMainFrame;
import Init.Settings.Settings;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;

/**
 *
 * @author hector
 */
public class SidePanel extends javax.swing.JPanel {
    
    private static Settings settings;
    private EvacSim evacSim;
    private static Timer timer;
    private static int m, s;
    

    /**
     * Creates new form SidePanel
     */
    public SidePanel(Settings set, EvacSim evs) {
        settings = set;
        evacSim = evs;
        initComponents();
        popSize.setValue(settings.getPopulationNumber());
        popRem.setText(Integer.toString(settings.getPopulationNumber()));
        m = s = 0;
        camControl.setVisible(!settings.isHideCamPanel());
        showNavmesh.setSelected(settings.isShowNavMesh());
        showDecks.setSelected(settings.isShowShip());
        showHull.setSelected(settings.isShowHullFarSide());
        camLoc.setSelectedItem(settings.currentCamLoc());
        evacButton.setEnabled(false);
        timer = new Timer(1000, new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                s++;
                if (s == 60){
                    m++;
                    s = 0;
                }
                updateStatus();
                if (evacSim.isDone()) {
                    timer.stop();
                    JOptionPane.showConfirmDialog(null, "Evacuation Complete", "Evacuation Complete", JOptionPane.PLAIN_MESSAGE);
                }
            }    
          });  
    }
    
    public void updateSettings() {
        camControl.setVisible(!settings.isHideCamPanel());
    }
    
    public static void updateStatus() {
        timeElapsed.setText(String.format("%02d:%02d", m, s));
        popRem.setText(Integer.toString(settings.getPopulationNumber()-settings.getNumEvac()));
        popEvac.setText(Integer.toString(settings.getNumEvac()));
        if (settings.getPopulationNumber()-settings.getNumEvac() == 0) {
            evacButton.setEnabled(false);
            timer.stop();
            m = s = 0;
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popSize = new javax.swing.JSpinner();
        timeElapsed = new javax.swing.JTextField();
        popRem = new javax.swing.JTextField();
        popEvac = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        evacButton = new javax.swing.JToggleButton();
        evacButton.setEnabled(false);
        routeButton = new javax.swing.JButton();
        routeButton.setEnabled(false);
        popButton = new javax.swing.JButton();
        popButton.setEnabled(false);
        camLoc = new javax.swing.JComboBox();
        camLoc.setModel(new javax.swing.DefaultComboBoxModel(settings.getCamLocations().keySet().toArray()));
        jLabel5 = new javax.swing.JLabel();
        showHull = new javax.swing.JCheckBox();
        showDecks = new javax.swing.JCheckBox();
        showNavmesh = new javax.swing.JCheckBox();
        camControl = new GUI.Components.CamControls(evacSim, settings);
        
        camLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evacSim.moveCam(settings.getCamLocations().get(camLoc.getSelectedItem()));
                settings.setCamLoc((String)camLoc.getSelectedItem());
                EvacSimMainFrame.giveCanFoc();
            }
        });


        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(254, 254, 254)));
        setMaximumSize(new java.awt.Dimension(280, 32767));
        setMinimumSize(new java.awt.Dimension(280, 0));
        setPreferredSize(new java.awt.Dimension(280, 621));

        popSize.setPreferredSize(new java.awt.Dimension(80, 28));
        popSize.setModel(new SpinnerNumberModel(20, 0, 200, 1));
        popSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                popSizeStateChanged(evt);
                EvacSimMainFrame.giveCanFoc();
            }
        });

        timeElapsed.setText("00:00");
        timeElapsed.setEditable(false);

        popRem.setText("0");
        popRem.setEditable(false);

        popEvac.setText("0");
        popEvac.setEditable(false);

        jLabel1.setText("Population Size");

        jLabel2.setText("Population Remaining");

        jLabel3.setText("Time Elapsed");

        jLabel4.setText("Safely Evacuated");

        evacButton.setText("Evacuate");
        evacButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routeButton.setEnabled(false);
                if (!evacButton.isSelected()) {
                    evacButton.setText("Resume");
                    timer.stop();
                }
                else {
                    evacButton.setText("Pause");
                    timer.start();
                }
                evacSim.evacuate();
                EvacSimMainFrame.giveCanFoc();
            }
        });

        routeButton.setText("Route");
        routeButton.setPreferredSize(new java.awt.Dimension(77, 30));
        routeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routeButton.setEnabled(false);
                evacButton.setEnabled(true);
                timer.stop();
                evacSim.route();
                EvacSimMainFrame.giveCanFoc();
            }
        });

        popButton.setText("Pop.");
        popButton.setPreferredSize(new java.awt.Dimension(77, 30));
        popButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timer.stop();
                m = s = 0;
                evacButton.setText("Evacuate");
                evacButton.setEnabled(false);
                routeButton.setEnabled(true);
                
                settings.updatePopNum();
                settings.setNumEvac(0);
                popEvac.setText("0");
                popRem.setText(Integer.toString(settings.getPopulationNumber()));
                evacSim.restartSim(0);
                EvacSimMainFrame.giveCanFoc();
            }
        });

        jLabel5.setText("Camera Location");

        showHull.setText("Show Far-Side Hull");
        showHull.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        showHull.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        showHull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showHullAction();
                EvacSimMainFrame.giveCanFoc();
            }
        });

        showDecks.setText("Show Decks");
        showDecks.setEnabled(false);
        showDecks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        showDecks.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        showDecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDecksAction();
                EvacSimMainFrame.giveCanFoc();
            }
        });

        showNavmesh.setText("Show Wireframe");
        showNavmesh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        showNavmesh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        showNavmesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showNavMeshAction();
                EvacSimMainFrame.giveCanFoc();
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(popEvac)
                            .add(popRem)
                            .add(popSize, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(timeElapsed)))
                    .add(layout.createSequentialGroup()
                        .add(popButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(routeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(evacButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(showHull, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(showDecks, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(showNavmesh, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jLabel5)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(camLoc, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(camControl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(popSize, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(timeElapsed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(popRem, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(popEvac, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 66, Short.MAX_VALUE)
                .add(showNavmesh)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(showDecks)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(showHull)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel5)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(camLoc, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(camControl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(evacButton)
                    .add(routeButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(popButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void popSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_popSizeStateChanged
        settings.setPopulationNumber((Integer) popSize.getValue());
        EvacSimMainFrame.giveCanFoc();
    }//GEN-LAST:event_popSizeStateChanged
    
    private void showNavMeshAction() {
        settings.setShowNavMesh(showNavmesh.isSelected());
        evacSim.showNavMesh();
        EvacSimMainFrame.giveCanFoc();
    }
    private void showHullAction() {
        settings.setShowHullFarSide(showHull.isSelected());
        evacSim.showNavMesh();
        EvacSimMainFrame.giveCanFoc();
    }
    private void showDecksAction() {
        settings.setShowShip(showDecks.isSelected());
        evacSim.showNavMesh();
        EvacSimMainFrame.giveCanFoc();
    }
    
    public static void enablePop() {
        popButton.setEnabled(true);
        routeButton.setEnabled(true);
        EvacSimMainFrame.giveCanFoc();
    }
    
    public static void reset() {
        evacButton.setText("Evacuate");
        timer.stop();
        updateStatus();
        m=s=0;
        routeButton.setEnabled(true);
        evacButton.setEnabled(false);
        EvacSimMainFrame.giveCanFoc();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Components.CamControls camControl;
    private javax.swing.JComboBox camLoc;
    private static javax.swing.JToggleButton evacButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private static javax.swing.JButton popButton;
    private static javax.swing.JTextField popEvac;
    private static javax.swing.JTextField popRem;
    private javax.swing.JSpinner popSize;
    private static javax.swing.JButton routeButton;
    private javax.swing.JCheckBox showDecks;
    private javax.swing.JCheckBox showHull;
    private javax.swing.JCheckBox showNavmesh;
    private static javax.swing.JTextField timeElapsed;
    // End of variables declaration//GEN-END:variables
}
