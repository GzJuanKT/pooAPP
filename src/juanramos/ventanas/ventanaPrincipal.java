/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package juanramos.ventanas;

import javax.swing.JFrame;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author juand
 */
public class ventanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public ventanaPrincipal() { 
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        jPanel2 = new javax.swing.JPanel();
        botonBuses = new javax.swing.JButton();
        botonEstudiantes = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuAdmin = new javax.swing.JMenu();
        itemMenuAgregarEstudiante = new javax.swing.JMenuItem();
        itemMenuBuscarEstudiante = new javax.swing.JMenuItem();
        itemMenuEliminarEstudiante = new javax.swing.JMenuItem();
        itemMenuEditarEstudiante = new javax.swing.JMenuItem();
        menuAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tio Juanito's Schoolar Bus");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationByPlatform(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        botonBuses.setBackground(new java.awt.Color(0, 204, 204));
        botonBuses.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        botonBuses.setText("Buses");
        botonBuses.setBorderPainted(false);
        botonBuses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonBusesMousePressed(evt);
            }
        });
        botonBuses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBusesActionPerformed(evt);
            }
        });

        botonEstudiantes.setBackground(new java.awt.Color(0, 204, 204));
        botonEstudiantes.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        botonEstudiantes.setText("Estudiantes");
        botonEstudiantes.setBorderPainted(false);
        botonEstudiantes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonEstudiantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                botonEstudiantesMousePressed(evt);
            }
        });
        botonEstudiantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEstudiantesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(botonEstudiantes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
            .addComponent(botonBuses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(botonEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(botonBuses, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido", "Colegio", "Direccion", "Horario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaDatos.setGridColor(new java.awt.Color(255, 255, 255));
        tablaDatos.setIntercellSpacing(new java.awt.Dimension(10, 10));
        tablaDatos.setRowHeight(25);
        tablaDatos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaDatos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(69, 69, 69))
        );

        menuAdmin.setText("Admin");

        itemMenuAgregarEstudiante.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemMenuAgregarEstudiante.setText("Agregar...");
        itemMenuAgregarEstudiante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                itemMenuAgregarEstudianteMousePressed(evt);
            }
        });
        itemMenuAgregarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuAgregarEstudianteActionPerformed(evt);
            }
        });
        menuAdmin.add(itemMenuAgregarEstudiante);

        itemMenuBuscarEstudiante.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemMenuBuscarEstudiante.setText("Buscar...");
        itemMenuBuscarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuBuscarEstudianteActionPerformed(evt);
            }
        });
        menuAdmin.add(itemMenuBuscarEstudiante);

        itemMenuEliminarEstudiante.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        itemMenuEliminarEstudiante.setText("Eliminar...");
        itemMenuEliminarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuEliminarEstudianteActionPerformed(evt);
            }
        });
        menuAdmin.add(itemMenuEliminarEstudiante);

        itemMenuEditarEstudiante.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_DOWN_MASK));
        itemMenuEditarEstudiante.setText("Editar...");
        itemMenuEditarEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMenuEditarEstudianteActionPerformed(evt);
            }
        });
        menuAdmin.add(itemMenuEditarEstudiante);

        jMenuBar1.add(menuAdmin);

        menuAyuda.setText("Ayuda");
        jMenuBar1.add(menuAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonBusesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBusesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBusesActionPerformed

    private void botonEstudiantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEstudiantesActionPerformed
        // TODO add your handling code here:
        cargarDatosEstudiantes();
    }//GEN-LAST:event_botonEstudiantesActionPerformed

    private void botonEstudiantesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEstudiantesMousePressed
        // TODO add your handling code here:
        cargarDatosEstudiantes();
    }//GEN-LAST:event_botonEstudiantesMousePressed

    private void botonBusesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonBusesMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonBusesMousePressed

    private void itemMenuAgregarEstudianteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemMenuAgregarEstudianteMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemMenuAgregarEstudianteMousePressed

    private void itemMenuAgregarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuAgregarEstudianteActionPerformed
        // TODO add your handling code here:
        VentanaCRUDEstudiantes miVentana = new VentanaCRUDEstudiantes(this, true);
        miVentana.setLocationRelativeTo(this);
        
        JButton botones[] = new JButton[] {miVentana.getBotonEditar(),
                                            miVentana.getBotonBuscar(),
                                            miVentana.getBotonEliminar()};      
                
        desactivarBotones(botones);
        miVentana.setVisible(true);

    }//GEN-LAST:event_itemMenuAgregarEstudianteActionPerformed

    private void itemMenuBuscarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuBuscarEstudianteActionPerformed
        // TODO add your handling code here:
        VentanaCRUDEstudiantes miVentana = new VentanaCRUDEstudiantes(this, true);
        JButton botones[] = new JButton[] {miVentana.getBotonAgregar(), 
                                            miVentana.getBotonEditar(),
                                            miVentana.getBotonEliminar()};
        
        JTextComponent camposDeTexto[] = new JTextComponent[] {miVentana.getCampoNombre(),
                                                                miVentana.getCampoApellido()
        };
        
        JComboBox comboOpciones[] = new JComboBox[] {miVentana.getOpcionesHorarios(),
                                                     miVentana.getOpcionesBarrios(),
                                                     miVentana.getOpcionesColegios()};
        
        desactivarBotones(botones);
        desactivarComboBox(comboOpciones);
        camposSoloLectura(camposDeTexto);
        
        miVentana.setLocationRelativeTo(this);
        miVentana.setVisible(true);
        
    }//GEN-LAST:event_itemMenuBuscarEstudianteActionPerformed

    private void itemMenuEliminarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuEliminarEstudianteActionPerformed
        // TODO add your handling code here:
        VentanaCRUDEstudiantes miVentana = new VentanaCRUDEstudiantes(this, true);
        JButton botones[] = new JButton[] {miVentana.getBotonAgregar(),
                                            miVentana.getBotonEditar()};
        
        JTextComponent camposDeTexto[] = new JTextComponent[] {miVentana.getCampoNombre(),
                                                                miVentana.getCampoApellido()
        };
        
        JComboBox comboOpciones[] = new JComboBox[] {miVentana.getOpcionesHorarios(),
                                                     miVentana.getOpcionesBarrios(),
                                                     miVentana.getOpcionesColegios()};
        
        desactivarBotones(botones);
        desactivarComboBox(comboOpciones);
        camposSoloLectura(camposDeTexto);
        
        miVentana.setLocationRelativeTo(this);
        miVentana.setVisible(true);
    }//GEN-LAST:event_itemMenuEliminarEstudianteActionPerformed

    private void itemMenuEditarEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMenuEditarEstudianteActionPerformed
        // TODO add your handling code here:
        VentanaCRUDEstudiantes miVentana = new VentanaCRUDEstudiantes(this, true);
        JButton botones[] = new JButton[] {miVentana.getBotonAgregar(),
                                            miVentana.getBotonEliminar()};
        
        JTextComponent camposDeTexto[] = new JTextComponent[] {miVentana.getCampoNombre(),
                                                                miVentana.getCampoApellido()
        };
        
        JComboBox comboOpciones[] = new JComboBox[] {miVentana.getOpcionesHorarios(),
                                                     miVentana.getOpcionesBarrios(),
                                                     miVentana.getOpcionesColegios()};
        
        desactivarBotones(botones);
        desactivarComboBox(comboOpciones);
        camposSoloLectura(camposDeTexto);
        
        miVentana.setLocationRelativeTo(this);
        miVentana.setVisible(true);
    }//GEN-LAST:event_itemMenuEditarEstudianteActionPerformed
    
    int operacion = 0; // 1 si da click menu buscar, 2 si se dio click en item editar, 3 si se dio click en item eliminar. 
    
    public void desactivarBotones(JButton botones[]){
        for (JButton boton : botones) {
            boton.setEnabled(false);
        }
    }
    
    public void desactivarComboBox(JComboBox combobox[]){
        for(JComboBox combo : combobox){
            combo.setEnabled(false);
        }
    }
    
    public void camposSoloLectura(JTextComponent camposDeTexto[] ){
        for (JTextComponent campo : camposDeTexto){
            campo.setEditable(false);
        }
    }
    
    void cargarDatosEstudiantes(){
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,"root","J@nda.1110");
            Statement st = conexion.createStatement();
            String sqlQuery = "SELECT * FROM tiojuanito.colegios;";
            ResultSet rs = st.executeQuery(sqlQuery);
            
            while(tablaDatos.getRowCount() > 0){
                ((DefaultTableModel) tablaDatos.getModel()).removeRow(0);
            }
            
            int col = rs.getMetaData().getColumnCount();
            while(rs.next()){
                
                Object [] rows = new Object[col];
                for(int i = 1; i <= col; i++){
                    rows[i-1] = rs.getObject(i);
                }
                ((DefaultTableModel) tablaDatos.getModel()).insertRow(rs.getRow() -1, rows);
            }
            rs.close();
            st.close();
            
        } catch (Exception e) {
            
        }
    }
    
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
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaPrincipal().setVisible(true);
            }
        });
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuses;
    private javax.swing.JButton botonEstudiantes;
    private javax.swing.JMenuItem itemMenuAgregarEstudiante;
    private javax.swing.JMenuItem itemMenuBuscarEstudiante;
    private javax.swing.JMenuItem itemMenuEditarEstudiante;
    private javax.swing.JMenuItem itemMenuEliminarEstudiante;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuAdmin;
    private javax.swing.JMenu menuAyuda;
    private javax.swing.JTable tablaDatos;
    // End of variables declaration//GEN-END:variables
}
