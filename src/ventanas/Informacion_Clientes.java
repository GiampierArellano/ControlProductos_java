/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.sql.*;
import clases.Conexion;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import static ventanas.GestionarClientes.IDcliente_update;

/**
 *
 * @author Giampier
 */
public class Informacion_Clientes extends javax.swing.JFrame {
//    ----------- DECLARAMOS VARIABLES GLOBALES -------
//    Creamos objeto para poder interactuar con nuestra tabla

    DefaultTableModel model = new DefaultTableModel();

//    Creamos una variable que nos permitira capturar un dato que viene desde otra interfaz
    int IDCliente_update = 0;
//    creamos variable que nos permita enviar informacion a otra interfaz
    public static int IDequipo = 0;
    String user = "";

    /**
     * Creates new form Informacion_Clientes
     */
//    CONSTRUCTOR
    public Informacion_Clientes() {
        initComponents();
//        Agregamos las variables que recuperaremos desde otras interfaces
        user = login.user;
        IDCliente_update = GestionarClientes.IDcliente_update;
        
//        agregamos los metodos para el diseno de interfaz
        setSize(630, 450);
        setResizable(false);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Nos permite evitar que el programa finalice cuando esta interfaz finalice
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ImageIcon fondo = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        //creamos nuevo objeto para definir las dimesiones de la imagen
        //y se ajusten a nuestro JLabel
        Icon icono = new ImageIcon(fondo.getImage().getScaledInstance(JLabel_Wallpaper.getWidth(),
                JLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        //colocar la imagen que estamos escalando dentro del jlabel
        JLabel_Wallpaper.setIcon(icono);
        this.repaint();

//        Estructura para conectarnos a la BD
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select * from clientes where idclientes = '" + IDCliente_update + "'");
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                setTitle("Informacion del cliente " + rs.getString("nombre_cliente") + " - Sesion de " + user);
                jbl_titulo.setText("Informacion del cliente " + rs.getString("nombre_cliente"));
                txt_nombre.setText(rs.getString("nombre_cliente"));
                txt_mail.setText(rs.getString("mail_cliente"));
                txt_telefono.setText(rs.getString("tel_cliente"));
                txt_direccion.setText(rs.getString("dir_cliente"));
                txt_ultimaModif.setText(rs.getString("ultima_modificacion"));
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error en cargar usuario." + e);
            JOptionPane.showMessageDialog(null, "Error al cargar!, contacte al administrador");
        }

        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select idequipos, tipo_equipo, marca, status from equipos where idcliente = '"
                    + IDCliente_update + "'");
            ResultSet rs = pst.executeQuery();

            jTable_equipos = new JTable(model);
            jScrollPane_equipos.setViewportView(jTable_equipos);

            model.addColumn("ID equipo");
            model.addColumn("Tipo de equipo");
            model.addColumn("Marca");
            model.addColumn("Status");

            while (rs.next()) {
                Object[] fila = new Object[4];
                for (int i = 0; i < 4; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                model.addRow(fila);
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error en cargar usuario." + e);
            JOptionPane.showMessageDialog(null, "Error al cargar!, contacte al administrador");
        }

        jTable_equipos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = jTable_equipos.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    IDequipo = (int) model.getValueAt(fila_point, columna_point);
//                    Creamos un objeto para que se conecte con la interfaz informacion_equipo
                    InformacionEquipo infoequipo = new InformacionEquipo();
                    infoequipo.setVisible(true);

//                    JOptionPane.showMessageDialog(null, "El id del cliente es: " + IDcliente_update);
                }
            }
        });

    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/icon.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane_equipos = new javax.swing.JScrollPane();
        jTable_equipos = new javax.swing.JTable();
        jbl_titulo = new javax.swing.JLabel();
        jbl_nombre = new javax.swing.JLabel();
        jbl_mail = new javax.swing.JLabel();
        jbl_telefono = new javax.swing.JLabel();
        jbl_direccion = new javax.swing.JLabel();
        jbl_ultimaModificacion = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_mail = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        txt_ultimaModif = new javax.swing.JTextField();
        jButton_registrar = new javax.swing.JButton();
        jButton_actualizar = new javax.swing.JButton();
        jButton_imprimirReporte = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        JLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable_equipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane_equipos.setViewportView(jTable_equipos);

        getContentPane().add(jScrollPane_equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 380, 180));

        jbl_titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jbl_titulo.setForeground(new java.awt.Color(255, 255, 255));
        jbl_titulo.setText("Informacion de Cliente");
        getContentPane().add(jbl_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jbl_nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbl_nombre.setForeground(new java.awt.Color(255, 255, 255));
        jbl_nombre.setText("Nombre:");
        getContentPane().add(jbl_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jbl_mail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbl_mail.setForeground(new java.awt.Color(255, 255, 255));
        jbl_mail.setText("Email:");
        getContentPane().add(jbl_mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jbl_telefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbl_telefono.setForeground(new java.awt.Color(255, 255, 255));
        jbl_telefono.setText("Telefono:");
        getContentPane().add(jbl_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jbl_direccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbl_direccion.setForeground(new java.awt.Color(255, 255, 255));
        jbl_direccion.setText("Direccion:");
        getContentPane().add(jbl_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jbl_ultimaModificacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbl_ultimaModificacion.setForeground(new java.awt.Color(255, 255, 255));
        jbl_ultimaModificacion.setText("Ultima modificacion:");
        getContentPane().add(jbl_ultimaModificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        txt_nombre.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombre.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_nombre.setForeground(new java.awt.Color(255, 255, 255));
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, -1));

        txt_mail.setBackground(new java.awt.Color(153, 153, 255));
        txt_mail.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_mail.setForeground(new java.awt.Color(255, 255, 255));
        txt_mail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_mail.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, -1));

        txt_telefono.setBackground(new java.awt.Color(153, 153, 255));
        txt_telefono.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_telefono.setForeground(new java.awt.Color(255, 255, 255));
        txt_telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_telefono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, -1));

        txt_direccion.setBackground(new java.awt.Color(153, 153, 255));
        txt_direccion.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_direccion.setForeground(new java.awt.Color(255, 255, 255));
        txt_direccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_direccion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, -1));

        txt_ultimaModif.setBackground(new java.awt.Color(153, 153, 255));
        txt_ultimaModif.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_ultimaModif.setForeground(new java.awt.Color(255, 255, 255));
        txt_ultimaModif.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ultimaModif.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_ultimaModif.setEnabled(false);
        getContentPane().add(txt_ultimaModif, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 210, -1));

        jButton_registrar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_registrar.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton_registrar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_registrar.setText("Registrar Equipo");
        jButton_registrar.setBorder(null);
        jButton_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_registrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 210, 35));

        jButton_actualizar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_actualizar.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        jButton_actualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_actualizar.setText("Actualizar Cliente");
        jButton_actualizar.setBorder(null);
        jButton_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_actualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 210, 35));

        jButton_imprimirReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/impresora.png"))); // NOI18N
        jButton_imprimirReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_imprimirReporteActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_imprimirReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 120, 100));

        jLabel_footer.setText("Practicando programación Java");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, -1, -1));
        getContentPane().add(JLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_registrarActionPerformed
        // TODO add your handling code here:
        RegistrarEquipo registra = new RegistrarEquipo();
        registra.setVisible(true);
    }//GEN-LAST:event_jButton_registrarActionPerformed

    private void jButton_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_actualizarActionPerformed
        // TODO add your handling code here:
        int validacion = 0;
        String nombre, mail, telefono, direccion;

        nombre = txt_nombre.getText().trim();
        mail = txt_mail.getText().trim();
        telefono = txt_telefono.getText().trim();
        direccion = txt_direccion.getText().trim();

        if (nombre.equals("")) {
            txt_nombre.setBackground(Color.red);
            validacion++;
        }
        if (direccion.equals("")) {
            txt_mail.setBackground(Color.red);
            validacion++;
        }
        if (telefono.equals("")) {
            txt_telefono.setBackground(Color.red);
            validacion++;
        }
        if (direccion.equals("")) {
            txt_direccion.setBackground(Color.red);
            validacion++;
        }
        if (validacion == 0) {
            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "update clientes set nombre_cliente=?, mail_cliente=?, tel_cliente=?, dir_cliente=?, "
                        + "ultima_modificacion=? where idclientes = '" + IDCliente_update + "'");

                pst.setString(1, nombre);
                pst.setString(2, mail);
                pst.setString(3, telefono);
                pst.setString(4, direccion);
                pst.setString(5, user);

                pst.executeUpdate();
                cn.close();

                Limpiar();

                txt_nombre.setBackground(Color.green);
                txt_mail.setBackground(Color.green);
                txt_direccion.setBackground(Color.green);
                txt_telefono.setBackground(Color.green);
                txt_ultimaModif.setText(user);

                JOptionPane.showMessageDialog(null, "Actualizacion correcta.");
                this.dispose();
            } catch (SQLException e) {
                System.out.println("Erroe en actualziar cliente. " + e);
                JOptionPane.showMessageDialog(null, "Error, contacte al administrador");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes de llenar todos los campos.");
        }

    }//GEN-LAST:event_jButton_actualizarActionPerformed

    private void jButton_imprimirReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_imprimirReporteActionPerformed
        // TODO add your handling code here:
//        creamos objeto de la clase document
        Document documento = new Document();
        try {
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/" + txt_nombre.getText().trim() + ".pdf"));

//            Definir la imagen como banner de nuestro documento a imprimir
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/images/BannerPDF.jpg");
//            colocar las dimensiones de la imagen
            header.scaleToFit(650, 1000);
//            colocar al centro la imagen
            header.setAlignment(Chunk.ALIGN_CENTER);

//            Configuramos el titulo del documento
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Informacion del cliente. \n \n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

//            Empezamos a trabajar el documento
            documento.open();
            documento.add(header);
            documento.add(parrafo);

//            creamos la tabla que contendran las datos que vendran desde la BD
//            creamos objeto tabla cliente - definimos la cantidad de columnas
            PdfPTable tablaCliente = new PdfPTable(5);
            tablaCliente.addCell("ID");
            tablaCliente.addCell("Hombre");
            tablaCliente.addCell("email");
            tablaCliente.addCell("Telefono");
            tablaCliente.addCell("Direccion");

            try {
                Connection cn = Conexion.conectar();
                PreparedStatement pst = cn.prepareStatement(
                        "select * from clientes where idclientes = '" + IDCliente_update + "'");

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    do {
                        tablaCliente.addCell(rs.getString(1));
                        tablaCliente.addCell(rs.getString(2));
                        tablaCliente.addCell(rs.getString(3));
                        tablaCliente.addCell(rs.getString(4));
                        tablaCliente.addCell(rs.getString(5));

                    } while (rs.next());

                    documento.add(tablaCliente);
                }

//                Configuramos un nuevo parrafo
                Paragraph parrafo2 = new Paragraph();
                parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo2.add("\n \n Equipos registrados \n \n");
                parrafo2.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

                documento.add(parrafo2);

                PdfPTable tablaEquipos = new PdfPTable(4);
                tablaEquipos.addCell("ID Equipos");
                tablaEquipos.addCell("ID Equipos");
                tablaEquipos.addCell("Marca");
                tablaEquipos.addCell("Status");

                try {
                    Connection cn2 = Conexion.conectar();
                    PreparedStatement pst2 = cn2.prepareStatement(
                            "select idequipos, tipo_equipo, marca, status from equipos where idcliente = '" + IDCliente_update + "'");
                    
                    ResultSet rs2 = pst2.executeQuery();
                    
                    if (rs2.next()) {
                    do {
                        tablaEquipos.addCell(rs2.getString(1));
                        tablaEquipos.addCell(rs2.getString(2));
                        tablaEquipos.addCell(rs2.getString(3));
                        tablaEquipos.addCell(rs2.getString(4));

                    } while (rs2.next());

                    documento.add(tablaEquipos);
                }
                    
                } catch (SQLException e) {
                    System.err.println("Error al cargar equipos " +e);
                }

            } catch (SQLException e) {
                System.err.println("Error al obtener datos del cliente" + e);
            }
            
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado de forma correcta");

        } catch (DocumentException | IOException e) {
            System.err.println("Error en PDF o ruta de imagen" + e);
            JOptionPane.showMessageDialog(null, "Error al generar PDF, contacte al administrador");
        }
    }//GEN-LAST:event_jButton_imprimirReporteActionPerformed

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
            java.util.logging.Logger.getLogger(Informacion_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informacion_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informacion_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacion_Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informacion_Clientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel_Wallpaper;
    private javax.swing.JButton jButton_actualizar;
    private javax.swing.JButton jButton_imprimirReporte;
    private javax.swing.JButton jButton_registrar;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane_equipos;
    private javax.swing.JTable jTable_equipos;
    private javax.swing.JLabel jbl_direccion;
    private javax.swing.JLabel jbl_mail;
    private javax.swing.JLabel jbl_nombre;
    private javax.swing.JLabel jbl_telefono;
    private javax.swing.JLabel jbl_titulo;
    private javax.swing.JLabel jbl_ultimaModificacion;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_mail;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_ultimaModif;
    // End of variables declaration//GEN-END:variables

    public void Limpiar() {
        txt_nombre.setText("");
        txt_mail.setText("");
        txt_telefono.setText("");
        txt_direccion.setText("");
    }
}
