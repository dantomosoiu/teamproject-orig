/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package initializer;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.BorderFactory; 
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
/**
 *
 * @author hector
 */
public class mainWindow extends javax.swing.JFrame {
    
    static Settings set;
//    private static JTextField numPeople, peopleEvacuated, timeElapsed;

    /**
     * Creates new form mainWindow
     */
    
    MouseListener l = new MouseAdapter() {

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel label = (JLabel)e.getComponent();
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel)e.getComponent();
            label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            label.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        }


    };
    
    public mainWindow() {
        initComponents();
        
        
        this.setTitle("TeamL Evacuation Simulator: ");
        
        //Finds the size of the screen and item. Uses this to calculate how to position the frame in the center of the screen.
        Toolkit kit = this.getToolkit();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        Insets in = kit.getScreenInsets(gs[0].getDefaultConfiguration());
        Dimension d = kit.getScreenSize();
        int max_width = (d.width - in.left - in.right);
        int max_height = (d.height - in.top - in.bottom);
        this.setSize(Math.min(max_width, 1070), Math.min(max_height, 630));//whatever size you want but smaller the insets
        this.setLocation((int) (max_width - this.getWidth()) / 2, (int) (max_height - this.getHeight()) / 2);
        TitledBorder title = BorderFactory.createTitledBorder("System Status");
        statusPanel.setBorder(title);
        populationControlPanel.setBorder(BorderFactory.createTitledBorder("Population Panel"));
        controlPanel.setBorder(BorderFactory.createTitledBorder("Control Panel"));
        popSize.setForeground(Color.BLUE);
        timeElapsed.setForeground(Color.BLUE);
        timeElapsed.setHorizontalAlignment(JTextField.RIGHT);
//
        timeElapsed.setBorder(BorderFactory.createEtchedBorder());
        timeElapsed.setText("00:00");
        timeElapsed.setEditable(false);
//
        numPeople.setForeground(Color.BLUE);
        numPeople.setHorizontalAlignment(JTextField.RIGHT);
        numPeople.setBorder(BorderFactory.createEtchedBorder());
        numPeople.setEditable(false);
//        peopleEvacuated = new JTextField(4);
//
        cameraPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Overview", "Exits", "Deck" }));
        
        numEvacuated.setText("0");
        numEvacuated.setForeground(Color.BLUE);
        numEvacuated.setHorizontalAlignment(JTextField.RIGHT);
        numEvacuated.setEditable(false);
        numEvacuated.setBorder(BorderFactory.createEtchedBorder());

//        ImageIcon icon = new ImageIcon("images/up.jpg", "up");
        up.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        down.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        left.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        right.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        gUp.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        gDown.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        dLeft.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        dRight.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        up.setIcon(new ImageIcon("images/up.jpg", "up"));
        up.addMouseListener(l);
        up.setText("");
        down.setIcon(new ImageIcon("images/down.jpg", "down"));
        down.addMouseListener(l);
        down.setText("");
        left.setIcon(new ImageIcon("images/left.jpg", "left"));
        left.addMouseListener(l);
        left.setText("");
        right.setIcon(new ImageIcon("images/right.jpg", "right"));
        right.addMouseListener(l);
        right.setText("");
        gUp.setIcon(new ImageIcon("images/G_up.jpg"));
        gUp.addMouseListener(l);
        gDown.setIcon(new ImageIcon("images/G_down.jpg"));
        gDown.addMouseListener(l);
        gUp.setText("");
        gDown.setText("");
        
        dLeft.setText("");
        dRight.setText("");
        dLeft.setIcon(new ImageIcon("images/d_left.jpg"));
        dRight.setIcon(new ImageIcon("images/d_right.jpg"));
        dLeft.addMouseListener(l);
        dRight.addMouseListener(l);
        container.setMinimumSize(new Dimension(800,640));
        
    }
    
    public void setTimeElapsed(int m, int s){
        timeElapsed.setText(String.format("%02d", m) + ":" + String.format("%02d", s));
    }
    
    public void updateStatus(int total, int n){
        numPeople.setText("" + (total-n-1));
        numEvacuated.setText("" + (n+1));
    }
    
    public void setNumOfPeople(int n){
        numPeople.setText("" + n);
    }
    
    
    
    public void update(Settings s) {
        set = s;
        popSize.setValue(set.getPopulationNumber());
        showNavmesh.setSelected(set.isShowNavMesh());
        showDecks.setSelected(set.isShowShip());
        showHull.setSelected(set.isShowHullFarSide());
        camSpeed.setValue((int)Math.round(set.getCamSpeed()));
        this.setTitle("TeamL Evacuation Simulator: " + set.getModelName());
    }
    
    public void setContainer(Canvas c) {
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.weightx = g.weighty = 1.0;
        g.gridwidth = 2;
        g.insets = new Insets(10, 0, 0, 8);
        g.gridx = g.gridy = 0;

        container.add(c, g);
    }

    public void loadDone() {
        container.remove(loadingText);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        container = new javax.swing.JPanel();
        loadingText = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        statusPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        timeElapsed = new javax.swing.JTextField();
        numPeople = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        numEvacuated = new javax.swing.JTextField();
        populationControlPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        popSize = new javax.swing.JSpinner();
        controlPanel = new javax.swing.JPanel();
        showNavmesh = new javax.swing.JCheckBox();
        showDecks = new javax.swing.JCheckBox();
        showHull = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        camSpeed = new javax.swing.JSlider();
        cameraPosition = new javax.swing.JComboBox();
        up = new javax.swing.JLabel();
        down = new javax.swing.JLabel();
        right = new javax.swing.JLabel();
        left = new javax.swing.JLabel();
        gUp = new javax.swing.JLabel();
        gDown = new javax.swing.JLabel();
        dLeft = new javax.swing.JLabel();
        dRight = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1050, 610));
        setResizable(false);

        container.setBackground(new java.awt.Color(1, 1, 1));
        container.setPreferredSize(new java.awt.Dimension(800, 580));
        container.setLayout(new java.awt.GridBagLayout());

        loadingText.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        loadingText.setForeground(new java.awt.Color(175, 175, 175));
        loadingText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadingText.setText("Loading, Please Wait...");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(223, 295, 339, 364);
        container.add(loadingText, gridBagConstraints);

        jButton1.setText("Evac");

        jButton2.setText("Pop");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        statusPanel.setPreferredSize(new java.awt.Dimension(321, 128));

        jLabel6.setText("number of people:");

        jLabel5.setText("time elapsed:");

        jLabel7.setText("people evacuated:");

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(4, 4, 4)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numEvacuated, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(timeElapsed, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                        .addComponent(numPeople)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(timeElapsed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(numPeople, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(numEvacuated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Population Size: ");

        javax.swing.GroupLayout populationControlPanelLayout = new javax.swing.GroupLayout(populationControlPanel);
        populationControlPanel.setLayout(populationControlPanelLayout);
        populationControlPanelLayout.setHorizontalGroup(
            populationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(populationControlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(popSize, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        populationControlPanelLayout.setVerticalGroup(
            populationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(populationControlPanelLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(populationControlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(popSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        showNavmesh.setText("NavMesh");
        showNavmesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showNavmeshActionPerformed(evt);
            }
        });

        showDecks.setText("Decks");

        showHull.setText("Hull");

        jLabel1.setText("Cam Speed");

        cameraPosition.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        up.setText("u");

        down.setText("d");

        right.setText("right");

        left.setText("left");

        gUp.setText("gUp");

        gDown.setText("gDown");

        dLeft.setText("dLeft");

        dRight.setText("dRight");

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(showNavmesh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showDecks)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showHull))
                    .addComponent(cameraPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(controlPanelLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gDown)
                                    .addComponent(gUp))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(controlPanelLayout.createSequentialGroup()
                                .addComponent(left)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(up)
                                    .addComponent(down))
                                .addGap(3, 3, 3)
                                .addComponent(right))
                            .addComponent(camSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(dLeft)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dRight))
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addComponent(cameraPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showNavmesh)
                    .addComponent(showDecks)
                    .addComponent(showHull))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(camSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(gUp)
                        .addGap(30, 30, 30)
                        .addComponent(gDown))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(up)
                        .addGap(7, 7, 7)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(right)
                            .addComponent(left))
                        .addGap(3, 3, 3)
                        .addComponent(down)))
                .addGap(18, 18, 18)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dLeft)
                    .addComponent(dRight))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton3.setText("Rte");

        jMenu1.setText("File");

        jMenuItem4.setText("Import Model");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Settings/Help");

        jMenuItem1.setText("Advanced Settings");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem3.setText("Restore Default Settings");
        jMenu2.add(jMenuItem3);

        jMenuItem2.setText("Help");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(populationControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                .addGap(0, 368, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(container, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(populationControlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new SettingDlg();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void showNavmeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showNavmeshActionPerformed
        set.setShowNavMesh(showNavmesh.isSelected());
        if (set.isShowNavMesh()) {
            
        }
        else {
            
        }
        
    }//GEN-LAST:event_showNavmeshActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider camSpeed;
    private javax.swing.JComboBox cameraPosition;
    private javax.swing.JPanel container;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JLabel dLeft;
    private javax.swing.JLabel dRight;
    private javax.swing.JLabel down;
    private javax.swing.JLabel gDown;
    private javax.swing.JLabel gUp;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JLabel left;
    private javax.swing.JLabel loadingText;
    private javax.swing.JTextField numEvacuated;
    private javax.swing.JTextField numPeople;
    private javax.swing.JSpinner popSize;
    private javax.swing.JPanel populationControlPanel;
    private javax.swing.JLabel right;
    private javax.swing.JCheckBox showDecks;
    private javax.swing.JCheckBox showHull;
    private javax.swing.JCheckBox showNavmesh;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextField timeElapsed;
    private javax.swing.JLabel up;
    // End of variables declaration//GEN-END:variables
}
