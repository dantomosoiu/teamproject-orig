/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Components;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hector
 */
public class Help extends javax.swing.JDialog {

    /**
     * Creates new form Help
     */
    public Help(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.setTitle("EvacSim Help");
        initComponents();
        GUIHelperMethods.centralise(this.getWidth(), this.getHeight(), this);
        helpSelection.setSelectedIndex(0);
        this.setVisible(true);
        this.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        helpSelection = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        helpContent = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        helpSelection.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "About", "Main View", "Evacuation Progress", "Camera Control", "Evacuation Buttons", "Advanced Settings - General", "Advanced Settings - Population Categories", "Advanced Settings - Population Distribution", "Importing Models" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        helpSelection.setMaximumSize(new java.awt.Dimension(285, 4000));
        helpSelection.setMinimumSize(new java.awt.Dimension(285, 80));
        helpSelection.setPreferredSize(new java.awt.Dimension(285, 140));
        helpSelection.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                helpSelectionValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(helpSelection);

        helpContent.setContentType("text/html"); // NOI18N
        jScrollPane2.setViewportView(helpContent);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void helpSelectionValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_helpSelectionValueChanged
        helpContent.setText(newContent(helpSelection.getSelectedIndex()));
    }//GEN-LAST:event_helpSelectionValueChanged

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
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Help dialog = new Help(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    private String newContent(int  index) {
        String loc;
        switch (index) {
            case 0: loc = "About.html";
                    break;
            case 1:  loc = "MainView.html";
                     break;
            case 2:  loc = "EvacProgress.html";
                     break;
            case 3:  loc = "CamControl.html";
                     break;
            case 4:  loc = "EvacButtons.html";
                     break;
            case 5:  loc = "Advanced-Gen.html";
                     break;
            case 6:  loc = "Advanced-PopCat.html";
                     break;
            case 7:  loc = "AdSet-PopDist.html";
                     break;
            case 8:  loc = "Import.html";
                     break;
            default: loc = "About.html";
                     break;
        }
        
        Scanner scan;
        try {
            scan = new Scanner((new BufferedReader(new FileReader("assets/Help/" + loc))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Help.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
        scan.useDelimiter("\\Z");
        if (scan.hasNext()) return scan.next();
        else return "";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane helpContent;
    private javax.swing.JList helpSelection;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
