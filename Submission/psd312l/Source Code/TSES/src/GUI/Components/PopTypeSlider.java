/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Components;

import Init.Settings.PersonCategory;
import Init.Settings.Settings;

/**
 *
 * @author hector
 */
public class PopTypeSlider extends javax.swing.JPanel {

    Settings settings;
    String typ;
    AdvancedSettings advancedSettings;
    
    /**
     * Creates new form PopTypeSlider
     */
    public PopTypeSlider(Settings set, String t, AdvancedSettings as) {
        settings = set;
        typ = t;
        advancedSettings = as;
        initComponents();
        type.setText(typ);
        percent.setValue(settings.getPersonCategories().get(typ).getNumberOfPeople());
    }
    
    public void updateDist() {
        percent.setValue(settings.getPersonCategories().get(typ).getNumberOfPeople());
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        percent = new javax.swing.JSlider();
        type = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(530, 40));
        setMinimumSize(new java.awt.Dimension(530, 40));
        setPreferredSize(new java.awt.Dimension(530, 40));

        percent.setPreferredSize(new java.awt.Dimension(100, 54));
        percent.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                percentStateChanged(evt);
            }
        });

        type.setText("Type");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(percent, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(type)
                .addGap(0, 10, Short.MAX_VALUE))
            .addComponent(percent, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void percentStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_percentStateChanged
        /*This is Fucked. Fix It!!!*/
        
        int deltaA = percent.getValue() - settings.getPersonCategories().get(typ).getNumberOfPeople();
        int oldA = settings.getPersonCategories().get(typ).getNumberOfPeople();
        
        PersonCategory pc =settings.getPersonCategories().get(typ);
        pc.setNumberOfPeople(percent.getValue());
        
        int total = percent.getValue();
        
        for (PersonCategory p : settings.getPersonCategories().values()) {
            if (!p.getName().equals(typ)) {
                int newB = (int) (p.getNumberOfPeople() * ((double)(100-oldA) - deltaA) / (100-oldA));
                total += newB;
                p.setNumberOfPeople(newB);
                if (p.getNumberOfPeople() > pc.getNumberOfPeople()) {
                    pc = p;
                }
            }
        }
        if (total != 100) {
            pc.setNumberOfPeople(pc.getNumberOfPeople() - total + 100);
        }
        advancedSettings.updateDist();
    }//GEN-LAST:event_percentStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider percent;
    private javax.swing.JLabel type;
    // End of variables declaration//GEN-END:variables
}