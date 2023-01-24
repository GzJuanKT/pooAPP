/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package juanramos.ventanas;

//import TioJuanito.daos.DaoBus;
import juanramos.daos.DaoEstudiante;
import juanramos.daos.*;
import juanramos.entidades.Barrio;
import juanramos.entidades.Colegio;
import juanramos.entidades.Estudiante;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import juanramos.daos.exceptions.NonexistentEntityException;
import juanramos.daos.exceptions.PreexistingEntityException;
import juanramos.entidades.Bus;
import juanramos.entidades.Horario;

/**
 *
 * @author juand
 */
public class VentanaCRUDEstudiantes extends javax.swing.JDialog {
    
    private Estudiante alguien;
    

    /**
     * Creates new form VentanaCRUDEstudiantes
     */
    public VentanaCRUDEstudiantes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarDatosOpcHorarios();
        cargarDatosOpcBarrios();
        cargarDatosOpcColegios();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelDatos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoApellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        opcionesHorarios = new javax.swing.JComboBox<>();
        campoIdEstudiante = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        opcionesBarrios = new javax.swing.JComboBox<>();
        opcionesColegios = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        botonEliminar = new javax.swing.JButton();
        botonLimpiar = new javax.swing.JButton();
        botonBuscar = new javax.swing.JButton();
        botonEditar = new javax.swing.JButton();
        botonAgregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Administrar Estudiantes");
        setModal(true);

        panelDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Datos Del Estudiante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("mononoki NF", 0, 14), new java.awt.Color(0, 153, 153))); // NOI18N

        jLabel2.setText("Nombre");

        campoNombre.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        campoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellido");

        campoApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoApellidoActionPerformed(evt);
            }
        });

        jLabel4.setText("Colegio");

        jLabel5.setText("Barrio");

        jLabel6.setText("Horario");

        opcionesHorarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionesHorariosActionPerformed(evt);
            }
        });

        campoIdEstudiante.setFont(new java.awt.Font("Dialog", 3, 12)); // NOI18N
        campoIdEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoIdEstudianteActionPerformed(evt);
            }
        });

        jLabel7.setText("Documento ID");

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoIdEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDatosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(opcionesHorarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(opcionesBarrios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(panelDatosLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(campoApellido))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelDatosLayout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(opcionesColegios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(panelDatosLayout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(400, 400, 400))
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(campoIdEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(campoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(opcionesColegios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(opcionesBarrios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6))
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(opcionesHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(46, 46, 46))
        );

        jLabel1.setFont(new java.awt.Font("mononoki NF", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORMULARIO PARA ESTUDIANTES");

        botonEliminar.setText("Eliminar");

        botonLimpiar.setText("Limpiar");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });

        botonBuscar.setText("Buscar");
        botonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBuscarActionPerformed(evt);
            }
        });

        botonEditar.setText("Editar");
        botonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEditarActionPerformed(evt);
            }
        });

        botonAgregar.setText("Agregar");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(270, 270, 270)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAgregar)
                        .addGap(108, 108, 108)
                        .addComponent(botonBuscar)
                        .addGap(148, 148, 148)
                        .addComponent(botonEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonEliminar)
                        .addGap(127, 127, 127)
                        .addComponent(botonLimpiar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEliminar)
                    .addComponent(botonLimpiar)
                    .addComponent(botonBuscar)
                    .addComponent(botonEditar)
                    .addComponent(botonAgregar))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNombreActionPerformed

    private void campoApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoApellidoActionPerformed

    int operacion = 0; // 1 si da click menu buscar, 2 si se dio click en item editar, 3 si se dio click en item eliminar. 

    private void botonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBuscarActionPerformed
        // TODO add your handling code here:

        String id = campoIdEstudiante.getText();
        if (id.isEmpty() == true) {
            JOptionPane.showMessageDialog(this, "Se requiere del ID para buscar a un estudiante.");
            return;
        } else {
            EntityManagerFactory conexion = Persistence.createEntityManagerFactory("TioJuanitoBusesPU");
            DaoEstudiante objetoDao = new DaoEstudiante(conexion);
            
            try {
                Integer numeroId = Integer.valueOf(id);
                alguien = objetoDao.buscarEstudiante(numeroId);
                
                Colegio colegios = alguien.getColegiosidColegio();
                Barrio barrios = alguien.getBarriosidBarrio();
                Horario horarios = alguien.getHorariosIdJornada();
                
                if (alguien == null){
                    JOptionPane.showMessageDialog(this, "El estudiante a buscar no existe.");
                    return;
                }
                else{
                    campoNombre.setText(alguien.getNombre());
                    campoApellido.setText(alguien.getApellido());
                    opcionesHorarios.setSelectedItem(horarios.getJornada());
                    opcionesColegios.setSelectedItem(colegios.getColegio());
                    opcionesBarrios.setSelectedItem(barrios.getBarrio());
                    
                    String titulo = getTitle();
                    if (titulo.indexOf("Editar") != -1){
                        campoNombre.setEditable(true);
                        campoApellido.setEditable(true);
                        opcionesHorarios.setEditable(true);
                        opcionesBarrios.setEditable(true);
                        opcionesColegios.setEditable(true);
                    }
                }

            } catch (Exception e) { 
                JOptionPane.showMessageDialog(this, "El ID debe ser un numero entero.");
                return;
            }
            
        }
    }//GEN-LAST:event_botonBuscarActionPerformed

    private void botonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEditarActionPerformed
        // TODO add your handling code here:
        // recupero los datos que fueron cambiados en los campos del formulario
        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        String nombreColegio = (String) opcionesColegios.getSelectedItem();
        String nombreBarrio = (String) opcionesBarrios.getSelectedItem();
        String nombreHorario = (String) opcionesHorarios.getSelectedItem();
        
        //--
        Colegio colegios = new Colegio();
        Barrio barrios = new Barrio();
        Horario horarios = new Horario();
        
        //--
        alguien.setNombre(nombre);
        alguien.setApellido(apellido);
        colegios.setColegio(nombreColegio);
        barrios.setBarrio(nombreBarrio);
        horarios.setJornada(nombreHorario);
        
        // --
        EntityManagerFactory conexion = Persistence.createEntityManagerFactory("TioJuanitoBusesPU");
        DaoEstudiante daoestu = new DaoEstudiante(conexion); 
        DaoBarrio daobarrio = new DaoBarrio(conexion);
        DaoColegio daocole = new DaoColegio(conexion);
        DaoHorario daohorario = new DaoHorario(conexion);
        
        //--
        int idBarrio = daobarrio.getIdBarrio((String) nombreBarrio);
        barrios = daobarrio.buscarBarrio(idBarrio);
        
        int idColegio = daocole.getIdColegio((String) nombreColegio);
        colegios = daocole.buscarColegio(idColegio);
        
        int idHorario = daohorario.getIdHorario((String) nombreHorario);
        horarios = daohorario.buscarHorario(idHorario);
        
        //--
        alguien.setBarriosidBarrio(barrios);
        alguien.setColegiosidColegio(colegios);
        alguien.setHorariosIdJornada(horarios);
        
        try {
            daoestu.editar(alguien);
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "Error: El estudiante a editar no existe en la base de datos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: No se puede editar el estudiante.");
            return;
        }
        
        
    }//GEN-LAST:event_botonEditarActionPerformed
        
    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        // TODO add your handling code here:
        
        String idEstudiante = campoIdEstudiante.getText();
        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        String nombreColegio = (String) opcionesColegios.getSelectedItem();
        String nombreBarrio = (String) opcionesBarrios.getSelectedItem();
        String nombreHorario = (String) opcionesHorarios.getSelectedItem();
        
        // ------
        Estudiante alguien = new Estudiante();
        Colegio colegios = new Colegio();
        Barrio barrios = new Barrio();
        Horario horarios = new Horario();
        
        try {
            Integer idStudent =  Integer.valueOf(idEstudiante);
            alguien.setIdEstudiantes(idStudent);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Hubo un problema con el numero de identificacion.");
            campoIdEstudiante.requestFocus();
            campoIdEstudiante.setSelectionColor(Color.red);
            campoIdEstudiante.selectAll();
            return;
        }
                        
        alguien.setNombre(nombre);
        alguien.setApellido(apellido);
        colegios.setColegio(nombreColegio);
        barrios.setBarrio(nombreBarrio);
        horarios.setJornada(nombreHorario);
        
        //--
        EntityManagerFactory conexion = Persistence.createEntityManagerFactory("TioJuanitoBusesPU");
        //--
        
        DaoEstudiante daoEstu = new DaoEstudiante(conexion);
        DaoBarrio daobarrio = new DaoBarrio(conexion);
        DaoColegio daocole = new DaoColegio(conexion);
        DaoHorario daohorario = new DaoHorario(conexion);
        
        //--
        int idBarrio = daobarrio.getIdBarrio((String) nombreBarrio);
        barrios = daobarrio.buscarBarrio(idBarrio);
        
        int idColegio = daocole.getIdColegio((String) nombreColegio);
        colegios = daocole.buscarColegio(idColegio);
        
        int idHorario = daohorario.getIdHorario((String) nombreHorario);
        horarios = daohorario.buscarHorario(idHorario);
        
        //--
        alguien.setBarriosidBarrio(barrios);
        alguien.setColegiosidColegio(colegios);
        alguien.setHorariosIdJornada(horarios);
        
        try {
            daoEstu.agregar(alguien);
        } catch (PreexistingEntityException ex) {
            JOptionPane.showMessageDialog(this, "Ya existe un estudiante con el mismo número de identificación.");
            return;
        }   catch (Exception ex) {
                Logger.getLogger(VentanaCRUDEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
            }       
        
        int totalEstudiantes = daoEstu.getTotalEstudiantes();
        JOptionPane.showMessageDialog(this, "Se agrego el estudiante "+nombre+" "+apellido+" al sistema.\nTotal Estudiantes: "+totalEstudiantes);
        
        botonLimpiarActionPerformed(evt);
        opcionesBarrios.setSelectedIndex(0);
        opcionesColegios.setSelectedIndex(0);
        opcionesHorarios.setSelectedIndex(0);
        
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        // TODO add your handling code here:    
        campoIdEstudiante.setText("");
        campoNombre.setText("");
        campoApellido.setText("");
       
    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void campoIdEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoIdEstudianteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoIdEstudianteActionPerformed

    private void opcionesHorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionesHorariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_opcionesHorariosActionPerformed
        
    private void cargarDatosOpcHorarios(){
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,"root","J@nda.1110");
            Statement st = conexion.createStatement();
            String sqlQuery = "SELECT * FROM tiojuanito.horarios";
            ResultSet rs = st.executeQuery(sqlQuery);

            while(rs.next()){
                opcionesHorarios.addItem(rs.getString("Jornada"));
            }
            rs.close();
            st.close();

        }catch (Exception e){
            
        }
    }
    
    private void cargarDatosOpcBarrios(){
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,"root","J@nda.1110");
            Statement st = conexion.createStatement();
            String sqlQuery = "SELECT * FROM tiojuanito.barrios";
            ResultSet rs = st.executeQuery(sqlQuery);

            while(rs.next()){
                opcionesBarrios.addItem(rs.getString("barrio"));
            }
            rs.close();
            st.close();

        }catch (Exception e){
            
        }
    }
    
       
    private void cargarDatosOpcColegios(){
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
      
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,"root","J@nda.1110");
            Statement st = conexion.createStatement();
            String sqlQuery = "SELECT * FROM tiojuanito.colegios";
            ResultSet rs = st.executeQuery(sqlQuery);

            while(rs.next()){
                opcionesColegios.addItem(rs.getString("colegio"));
            }
            rs.close();
            st.close();

        }catch (Exception e){
            
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
            java.util.logging.Logger.getLogger(VentanaCRUDEstudiantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCRUDEstudiantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCRUDEstudiantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCRUDEstudiantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaCRUDEstudiantes dialog = new VentanaCRUDEstudiantes(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonEditar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonLimpiar;
    private javax.swing.JTextField campoApellido;
    private javax.swing.JTextField campoIdEstudiante;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<String> opcionesBarrios;
    private javax.swing.JComboBox<String> opcionesColegios;
    private javax.swing.JComboBox<String> opcionesHorarios;
    private javax.swing.JPanel panelDatos;
    // End of variables declaration//GEN-END:variables

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public JButton getBotonBuscar() {
        return botonBuscar;
    }

    public JButton getBotonEditar() {
        return botonEditar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public JButton getBotonLimpiar() {
        return botonLimpiar;
    }
    
    public JTextField getCampoIdEstudiante() {
        return campoIdEstudiante;
    }
    
    public JTextField getCampoNombre() {
        return campoNombre;
    }

    public JTextField getCampoApellido() {
        return campoApellido;
    }
    
    public JComboBox<String> getOpcionesHorarios(){
        return opcionesHorarios;
    }

    public JComboBox<String> getOpcionesBarrios() {
        return opcionesBarrios;
    }

    public JComboBox<String> getOpcionesColegios() {
        return opcionesColegios;
    }
    
    
}
