/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomlist;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.table.DefaultTableModel;
import java.util.Random;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Cena
 */
public class RandL extends javax.swing.JFrame {

    ArrayList<Etudiant> ListEtudiant = new ArrayList<>();
    ArrayList<Etudiant> Tab_grp = new ArrayList<>();

    /**
     * Creates new form Rand
     */
    public RandL() {
        initComponents();
        TrirTab();
    }

    private File imporList() {
        File f = null;
        try {

            JFileChooser fichier = new JFileChooser();
            FileNameExtensionFilter txt = new FileNameExtensionFilter("*.texte", "txt");
            fichier.setAcceptAllFileFilterUsed(false);
            fichier.setFileFilter(txt);
            int r = fichier.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {

                f = fichier.getSelectedFile();
                lbl_path.setText(f.getPath());
               
            }
        } catch (HeadlessException e) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
        return f;
    }

    public void chargeTab() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        for (Etudiant et : Tab_grp) {
            String[] dataRow = {String.valueOf(et.getNumero()), et.getMatricule(), et.getNom(),String.valueOf(et.getN_groupe())};
            model.addRow(dataRow);
        }
    }

    private void TrirTab() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> Tri = new TableRowSorter<>(model);
        jTable1.setRowSorter(Tri);
    }
    
    public void chargeList(File f) throws FileNotFoundException, IOException, NullPointerException {
        String str;
        Etudiant e;
        BufferedReader br = new BufferedReader(new FileReader(f));
        while ((str = br.readLine()) != null) {
            e = new Etudiant(Integer.valueOf(str.substring(0, str.indexOf("\t"))),
                    str.substring(str.indexOf("\t") + 1, str.lastIndexOf("\t")),
                    str.substring(str.lastIndexOf("\t") + 1, str.length()));
            ListEtudiant.add(e);

        }
    }
    
    public void SaveList() throws IOException{
        DefaultTableModel Model = (DefaultTableModel)jTable1.getModel();
        String str="";
        BufferedWriter bw = null;
        for (int i = 0; i < Model.getRowCount(); i++){
            for (int j = 0; j < Model.getColumnCount(); j++){
                bw = new BufferedWriter(new FileWriter(new File("Liste des groupe.txt")));
                str += (String) Model.getValueAt(i, j)+"\t";
            }
            str += "\r\n" ;
            System.out.println(str);
            bw.write(str);
        }
        bw.close();
    }
    
    public void RandNum(){
        
        int choix;
        Random RANDOM = new Random();
        
       do{
        for (int i = 1; i < 6; i++) {
//            System.out.println("OK");
            if(ListEtudiant.size() > 0){
             int max = ListEtudiant.size();
             choix = RANDOM.nextInt(max);
//                System.out.println(choix);
                ListEtudiant.get(choix).setN_groupe(i);
                Tab_grp.add(ListEtudiant.remove(choix));
            }
        }
       }while(ListEtudiant.size() > 0);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btn_imp = new javax.swing.JButton();
        lbl_path = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_gen = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Random List");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btn_imp.setBackground(new java.awt.Color(204, 204, 204));
        btn_imp.setText("Importer une liste");
        btn_imp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_impActionPerformed(evt);
            }
        });

        lbl_path.setEditable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "numero", "matricule", "nom et prenom", "numero de groupe"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(25);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        btn_gen.setText("Génerer");
        btn_gen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_genActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btn_imp, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_path, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_gen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_imp, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_path, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_gen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE))
        );

        jButton1.setText("Enregistrer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_impActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_impActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            javax.swing.UIManager.setLookAndFeel(new WindowsLookAndFeel());
            chargeList(imporList());
            javax.swing.UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException | IOException | NullPointerException ex) {
            java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Erreur survenue pendant l'import du fichier\n"+ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_impActionPerformed

    private void btn_genActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_genActionPerformed
        // TODO add your handling code here:
        RandNum();
        chargeTab();
    }//GEN-LAST:event_btn_genActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{
        SaveList();
        }catch(Exception e){
          java.awt.Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Erreur survenue pendant la sauvegarde\n"+e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);      
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RandL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RandL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RandL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RandL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RandL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_gen;
    private javax.swing.JButton btn_imp;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField lbl_path;
    // End of variables declaration//GEN-END:variables
}