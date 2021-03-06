/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Components;

import GUI.EvacSimMainFrame;
import Init.Settings;

/**
 *
 * @author hector
 */
public class SidePanel extends javax.swing.JPanel {
    
    private Settings settings;
    private EvacSimMainFrame parent;

    /**
     * Creates new form SidePanel
     */
    public SidePanel() {
        initComponents();        
    }
    
    public void passParent(EvacSimMainFrame  p) {
        parent = p;
        camControl.passParents(this, parent);
    }
    
    public void updateSettings(Settings s) {
        settings = s;
        popSize.setValue(settings.getPopulationNumber());
        showNavmesh.setSelected(settings.isShowNavMesh());
        showDecks.setSelected(settings.isShowShip());
        showHull.setSelected(settings.isShowHullFarSide());
        camLoc.setModel(new javax.swing.DefaultComboBoxModel(settings.getCamLocations().keySet().toArray()));
        if (settings.isHideCamPanel()) {
            camControl.setVisible(false);
        }
    }
    
    public void setTime(int m, int s) {
        timeElapsed.setText(String.format("%02d:%02d", m, s));
    }
    
    public void updateStatus(int n, int e) {
        popRem.setText(Integer.toString(n-e-1));
        popEvac.setText(Integer.toString(e+1));
        if (n-e-1 == 0) {
            evacButton.setEnabled(false);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        timeElapsed = new javax.swing.JTextField();
        popRem = new javax.swing.JTextField();
        popEvac = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        popButton = new javax.swing.JButton();
        routeButton = new javax.swing.JButton();
        evacButton = new javax.swing.JToggleButton();
        camLoc = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        showHull = new javax.swing.JCheckBox();
        showDecks = new javax.swing.JCheckBox();
        showNavmesh = new javax.swing.JCheckBox();
        camControl = new GUI.Components.camControls();
        camMoveBut = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(280, 32767));
        setMinimumSize(new java.awt.Dimension(280, 0));
        setName("sidePanel"); // NOI18N
        setPreferredSize(new java.awt.Dimension(280, 500));

        popSize.setModel(new javax.swing.SpinnerNumberModel(20, 1, 200, 1));
        popSize.setToolTipText("After setting click Pop. to re-populate!");
        popSize.setMaximumSize(new java.awt.Dimension(80, 28));
        popSize.setMinimumSize(new java.awt.Dimension(80, 28));
        popSize.setPreferredSize(new java.awt.Dimension(80, 28));
        popSize.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                popSizeStateChanged(evt);
            }
        });

        jLabel1.setText("Population Size");
        jLabel1.setToolTipText("After setting click Pop. to re-populate!");

        jLabel2.setText("TIme Elapsed");

        jLabel3.setText("Population Remaining");

        timeElapsed.setEditable(false);
        timeElapsed.setBackground(new java.awt.Color(254, 254, 254));
        timeElapsed.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        timeElapsed.setText("0:00");
        timeElapsed.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        timeElapsed.setMaximumSize(new java.awt.Dimension(80, 28));
        timeElapsed.setMinimumSize(new java.awt.Dimension(80, 28));
        timeElapsed.setPreferredSize(new java.awt.Dimension(80, 28));

        popRem.setEditable(false);
        popRem.setBackground(new java.awt.Color(254, 254, 254));
        popRem.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        popRem.setText("0");
        popRem.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        popRem.setMaximumSize(new java.awt.Dimension(80, 28));
        popRem.setMinimumSize(new java.awt.Dimension(80, 28));
        popRem.setPreferredSize(new java.awt.Dimension(80, 28));

        popEvac.setEditable(false);
        popEvac.setBackground(new java.awt.Color(254, 254, 254));
        popEvac.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        popEvac.setText("0");
        popEvac.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        popEvac.setMaximumSize(new java.awt.Dimension(80, 28));
        popEvac.setMinimumSize(new java.awt.Dimension(80, 28));
        popEvac.setPreferredSize(new java.awt.Dimension(80, 28));
        popEvac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popEvacActionPerformed(evt);
            }
        });

        jLabel4.setText("Safely Evacuated");

        popButton.setText("Pop.");
        popButton.setMaximumSize(new java.awt.Dimension(77, 30));
        popButton.setMinimumSize(new java.awt.Dimension(77, 30));
        popButton.setPreferredSize(new java.awt.Dimension(77, 30));
        popButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popButtonActionPerformed(evt);
            }
        });

        routeButton.setText("Route");
        routeButton.setEnabled(false);
        routeButton.setMaximumSize(new java.awt.Dimension(77, 30));
        routeButton.setMinimumSize(new java.awt.Dimension(77, 30));
        routeButton.setPreferredSize(new java.awt.Dimension(77, 30));
        routeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routeButtonActionPerformed(evt);
            }
        });

        evacButton.setText("Evac");
        evacButton.setActionCommand("Pause");
        evacButton.setEnabled(false);
        evacButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evacButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Camera Location");

        showHull.setText("Show Far-Side Hull");
        showHull.setEnabled(false);
        showHull.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        showHull.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        showHull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showHullActionPerformed(evt);
            }
        });

        showDecks.setText("Show Decks");
        showDecks.setEnabled(false);
        showDecks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        showDecks.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        showDecks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDecksActionPerformed(evt);
            }
        });

        showNavmesh.setText("Show Nav-Mesh");
        showNavmesh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        showNavmesh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        showNavmesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showNavmeshActionPerformed(evt);
            }
        });

        camMoveBut.setText("Move");
        camMoveBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camMoveButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(popSize, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(popRem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timeElapsed, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(popEvac, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(popButton, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(routeButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(evacButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(showHull, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showDecks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(showNavmesh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(camLoc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(camMoveBut)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(camControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(popSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(timeElapsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(popRem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(popEvac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(showNavmesh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showDecks)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showHull)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(camLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(camMoveBut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(camControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(popButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(routeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(evacButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showHullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showHullActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showHullActionPerformed

    private void showDecksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDecksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_showDecksActionPerformed

    private void showNavmeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showNavmeshActionPerformed
        if (showNavmesh.isSelected()) {
            settings.setShowNavMesh(true);
            parent.drawNM(settings);
        }
        else {
            settings.setShowNavMesh(false);
            parent.remNM(settings);
        }
    }//GEN-LAST:event_showNavmeshActionPerformed

    private void popButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popButtonActionPerformed
        evacButton.setSelected(false);
        evacButton.setText("Evac");
        
        if (!routeButton.isEnabled()) {
            routeButton.setEnabled(true);
        }
        parent.updateCanvas();
    }//GEN-LAST:event_popButtonActionPerformed

    private void popEvacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popEvacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_popEvacActionPerformed

    private void routeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_routeButtonActionPerformed
        if (!evacButton.isEnabled()) {
            evacButton.setEnabled(true);
        }
        parent.route();
        routeButton.setEnabled(false);
    }//GEN-LAST:event_routeButtonActionPerformed

    private void evacButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_evacButtonActionPerformed
        if (evacButton.isSelected()) {
            parent.evac(Integer.parseInt(popRem.getText()));
            evacButton.setText("Pause");
        }
        else {
            evacButton.setText("Resume");
            parent.stop();
            evacButton.setSelected(true);
        }
    }//GEN-LAST:event_evacButtonActionPerformed

    private void camMoveButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camMoveButActionPerformed
        parent.setcam((String) camLoc.getSelectedItem());
    }//GEN-LAST:event_camMoveButActionPerformed

    private void popSizeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_popSizeStateChanged
        parent.asyncPopSetup((Integer) popSize.getValue());
    }//GEN-LAST:event_popSizeStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.Components.camControls camControl;
    private javax.swing.JComboBox camLoc;
    private javax.swing.JButton camMoveBut;
    private javax.swing.JToggleButton evacButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton popButton;
    private javax.swing.JTextField popEvac;
    private javax.swing.JTextField popRem;
    private javax.swing.JSpinner popSize;
    private javax.swing.JButton routeButton;
    private javax.swing.JCheckBox showDecks;
    private javax.swing.JCheckBox showHull;
    private javax.swing.JCheckBox showNavmesh;
    private javax.swing.JTextField timeElapsed;
    // End of variables declaration//GEN-END:variables
}
