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
                this.user = this.registrarse.getUser();
                Inicio.getMensaje(this.registroUsuarios.edit(user));
                break;
            case "Eliminar":
                Inicio.getMensaje(this.registroUsuarios.destroy(this.user.getIdUser()));
                break;
            case "Tabla":
                tabla = new Inventario();
                tabla.escuchadorMouse(this);
                tabla.setDataTable(this.registroUsuarios.getMatrizUsuarios(), Users.LISTA_USUARIOS);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Users usuarioSeleccionado = new Users();

        usuarioSeleccionado.setName(this.tabla.getRow()[0]);
        usuarioSeleccionado.setIdUser(Integer.parseInt(this.tabla.getRow()[1]));
        usuarioSeleccionado.setPassword(this.tabla.getRow()[2]);
        usuarioSeleccionado.setEmail(this.tabla.getRow()[3]);
        usuarioSeleccionado.setProfile(this.tabla.getRow()[4]);

        this.registrarse.getUser();

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
