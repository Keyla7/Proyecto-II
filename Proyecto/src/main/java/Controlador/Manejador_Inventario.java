/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Users;
import Modelo.UsersJpaController;
import Vista.Inicio;
import Vista.Inventario;
import Vista.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Keyla
 */
public class Manejador_Inventario implements ActionListener, MouseListener {

    private Users user;
    private Inventario tabla;
    private UsersJpaController registroUsuarios;
    private Registro registrarse;

    public Manejador_Inventario() {
        this.tabla = new Inventario();
        this.registrarse = new Registro();
        this.registroUsuarios = new UsersJpaController();
        this.tabla.escuchadorIn(this);
        this.tabla.setVisible(true);
        this.registroUsuarios = new UsersJpaController();
        this.tabla.setDataTable(this.registroUsuarios.getMatrizUsuarios(), Users.LISTA_USUARIOS);
        this.tabla.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand().toString()) {
            case "Editar":

                int selectedRow = tabla.getTable().getSelectedRow();
                if (selectedRow != -1) {
                    int id = Integer.parseInt(tabla.getTable().getValueAt(selectedRow, 0).toString());
                    String name = tabla.getTable().getValueAt(selectedRow, 1).toString();
                    String password = tabla.getTable().getValueAt(selectedRow, 2).toString();
                    String email = tabla.getTable().getValueAt(selectedRow, 3).toString();
                    String profile = tabla.getTable().getValueAt(selectedRow, 5).toString();
                    this.user = new Users(id, name, password, email, profile);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para editar.");
                }

                Inicio.getMensaje(this.registroUsuarios.edit(user));
                tabla.setDataTable(this.registroUsuarios.getMatrizUsuarios(), Users.LISTA_USUARIOS);
                break;
            case "Eliminar":
                selectedRow = tabla.getTable().getSelectedRow();
                if (selectedRow != -1) {
                    int id = Integer.parseInt(tabla.getTable().getValueAt(selectedRow, 0).toString());
                    String name = tabla.getTable().getValueAt(selectedRow, 1).toString();
                    String password = tabla.getTable().getValueAt(selectedRow, 2).toString();
                    String email = tabla.getTable().getValueAt(selectedRow, 3).toString();
                    String profile = tabla.getTable().getValueAt(selectedRow, 5).toString();
                    this.user = new Users(id, name, password, email, profile);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila para editar.");
                }

                Inicio.getMensaje(this.registroUsuarios.destroy(this.user.getIdUser()));
                tabla.setDataTable(this.registroUsuarios.getMatrizUsuarios(), Users.LISTA_USUARIOS);
                break;
            case "Tabla":
                tabla = new Inventario();
                tabla.escuchadorMouse(this);
                break;
            case "X":
                new Manejador_Inicio();
                tabla.setVisible(false);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Users usuarioSeleccionado = new Users();

        usuarioSeleccionado.setIdUser(Integer.parseInt(this.tabla.getRow()[0]));
        usuarioSeleccionado.setName(this.tabla.getRow()[1]);
        usuarioSeleccionado.setPassword(this.tabla.getRow()[2]);
        usuarioSeleccionado.setEmail(this.tabla.getRow()[3]);
        usuarioSeleccionado.setProfile(this.tabla.getRow()[4]);

        this.registroUsuarios.edit(usuarioSeleccionado);
        this.tabla.dispose();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
