/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.sql.*;
import clases.Conexion;
import com.sun.java.swing.plaf.windows.resources.windows;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author N3mesis
 */
public class GestionarUsuarios extends javax.swing.JFrame {

    //En esta variable vamos a alojar el nombre de usuario que viene 
    //desde la interfaz login
    String user;

    //creamos la variable user_update que almacenará cual es el usuario que 
    //se va a consultar desde la interfaz GestionarUsuarios
    public static String user_update = "";
    //creamos el objeto model que nos permitirá acceder a todos metodos 
    //necesarios de la clase DefaultTableModel para modificar los datos en su 
    //interior (añadir filas o columnas, darle nombre a las columnas, etc)
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form GestionarUsuarios
     */
    //Constructor de interfaz
    public GestionarUsuarios() {
        initComponents();
        //Recuperamos el nombre de usuario que viene desde la interfaz login
        user = login.user;
        //personalizamos nuestra interfaz
        setSize(630, 330);
        setTitle("Usuarios registrados - Sesion de " + user);
        setResizable(false);
        setLocationRelativeTo(null);
        //El metodo inferior evita que finalice el programa cuando cerremos la interfaz
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        ImageIcon fondo = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        //creamos nuevo objeto para definir las dimesiones de la imagen
        //y se ajusten a nuestro JLabel
        Icon icono = new ImageIcon(fondo.getImage().getScaledInstance(lbl_wallpapper.getWidth(),
                lbl_wallpapper.getHeight(), Image.SCALE_DEFAULT));
        //colocar la imagen que estamos escalando dentro del jlabel
        lbl_wallpapper.setIcon(icono);
        this.repaint();

        //iniciamos las consultas a la BD
        try {
            Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(
                    "select  idusuarios, nombre_usuario, username, tipo_nivel, status from usuarios");
            
            ResultSet rs = pst.executeQuery();
            jTable_usuarios = new JTable(model);
            //Colocamos el jtable dentro del jscrollpanel porque si los datos son mas 
            //de los que podemos mostrar en nuestra interfaz entonces creará 
            //de forma automatica un scroll
            jScrollPane1.setViewportView(jTable_usuarios);

            //Asignamos nombre a cada columna
            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Username");
            model.addColumn("Status");

            //crear estructura repetitiva para llenar los datos en la tabla
            while (rs.next()) {
                //creamos un vector de tipo object para ir descargando las filas
                //el numero 5 corresponde a las 5 columnas de la tabla
                Object[] fila = new Object[5];
                //indice inicia en 0 y mientras sea menor de 5 entonces
                //mientras se cumpla la condicion se guardará lo que encontremos en la BD
                //en nuestro vector fila
                for (int i = 0; i < 5; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                //añadimos al modelo la fila completa
                model.addRow(fila);
            }
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error al llenar la tabla." + e);
            JOptionPane.showMessageDialog(null, "Error al mostrar informacion, ¡contacte al administrador!");
        }
        
        jTable_usuarios.addMouseListener(new MouseAdapter() {
            //Usamos Override para sobre escribir metodos
            @Override
            public void mouseClicked(MouseEvent e){
                //Debemos indicarle a nuestro programa una fila o una columna
                //Siempre se va a seleccionar la columna2 porque es donde esta
                //registrado el usuario
                int fila_point = jTable_usuarios.rowAtPoint(e.getPoint());
                //colocamos 2 porque nostros queremos recuperar la columna donde esta
                //el username
                int columna_point = 2;
                if(fila_point > -1){
                    user_update = (String)model.getValueAt(fila_point, columna_point);
                    InformacionUsuarios info_usuario = new InformacionUsuarios();
                    info_usuario.setVisible(true);
                }
            }
        });
    }

    //Este metodo nos permite cambiar el icono de nuestra interfaz
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_usuarios = new javax.swing.JTable();
        lbl_footer = new javax.swing.JLabel();
        lbl_wallpapper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuarios Registrados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jTable_usuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable_usuarios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 630, 180));

        lbl_footer.setText("Practicando programacion Java");
        getContentPane().add(lbl_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, -1, -1));
        getContentPane().add(lbl_wallpapper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 330));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GestionarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionarUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_usuarios;
    private javax.swing.JLabel lbl_footer;
    private javax.swing.JLabel lbl_wallpapper;
    // End of variables declaration//GEN-END:variables
}
