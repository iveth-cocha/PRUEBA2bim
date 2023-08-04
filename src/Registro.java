import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Registro {
    private JPanel rootPanel;
    private JTextField cod;
    private JTextField ci;
    private JTextField nom;
    private JTextField fecha;
    private JComboBox signos;
    private JButton buscarPorCodigoButton;
    private JButton buscarPorNombreButton;
    private JButton buscarPorSignoButton;
    private JButton borrarPresenteRegistroButton;
    private JButton actualizarElPresenteRegistroButton;
    private JButton ingresarElPresenteRegistroButton;
    private JButton limpiarCamposButton;

    String idx;
    String cedx;
    String namx;
    String fax;
    String sigx ;

    static final String DB_URL ="jdbc:mysql://localhost/Prueba";
    static final String USER="root";
    static final String PASS="root_bas3";
    static final String QUERY ="SELECT * FROM Informacion";


    public Registro() {
        ingresarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idx=cod.getText().trim();
                cedx=ci.getText().trim();
                namx=nom.getText().trim();
                fax=fecha.getText().trim();
                sigx =signos.getSelectedItem().toString().trim();
                registrar(idx,cedx,namx,fax,sigx);
            }
        });
        limpiarCamposButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cod.setText("");
                ci.setText("");
                nom.setText("");
                fecha.setText("");
                signos.setSelectedIndex(0);
            }
        });
        borrarPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idx=cod.getText().trim();
                cedx=ci.getText().trim();
                namx=nom.getText().trim();
                fax=fecha.getText().trim();
                sigx =signos.getSelectedItem().toString().trim();
                eliminar(idx);
            }
        });
        actualizarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idx=cod.getText().trim();
                cedx=ci.getText().trim();
                namx=nom.getText().trim();
                fax=fecha.getText().trim();
                sigx =signos.getSelectedItem().toString().trim();
                actualizar(idx,cedx,namx,fax,sigx);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registro de Personas");
        frame.setContentPane(new Registro().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }


    public static void registrar(String id,String cedu, String name,String fna,String sig){

        String query3 = " insert into Informacion values('"+id+"','"+cedu+"','"+name+"','"+fna+"','"+sig+"')";

        try(
                Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt= conn.createStatement();

        ){
            stmt.executeUpdate(query3);
            JOptionPane.showMessageDialog(null, "Informacion ingresada correctamente");

        }catch (Exception el){
            throw new RuntimeException(el);
        }
    }

    public static void eliminar(String id){
        String query2 = "DELETE FROM Informacion where codico = '"+ id +"'";

        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt= conn.createStatement();
        ){
            stmt.executeUpdate(query2);
            JOptionPane.showMessageDialog(null, "Informacion eliminada ");
        }catch (Exception el){
            throw new RuntimeException(el);
        }
    }

    public static void actualizar(String id ,String cedu, String name,String fna,String sig){
        String query2 = "UPDATE Informacion SET cedula = '" + cedu + "', nombre = '" + name + "', fecha_nacimiento = '" + fna + "', signo = '" + sig + "' WHERE codico = '" + id + "'";
        System.out.println(query2);
        try(
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS); //Esencial para la conección
                Statement stmt= conn.createStatement();
        ){
            stmt.executeUpdate(query2);
            JOptionPane.showMessageDialog(null, "Informacion actualizada ");
        }catch (Exception el){
            throw new RuntimeException(el);
        }
    }

}
