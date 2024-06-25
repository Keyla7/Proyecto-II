/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Users;
import Modelo.UsersJpaController;
import Vista.Inventario;
import Vista.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Keyla
 */
public class Manejador_Login implements ActionListener {

    Users user;
    UsersJpaController registroUsuarios;
    Login inicioSesion;

    //---------------------------------------------------------------
    public Manejador_Login() {
        this.inicioSesion = new Login();
        this.inicioSesion.escuchar(this);
        this.inicioSesion.setVisible(true);

        this.registroUsuarios = new UsersJpaController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand().toString()) {
            case "Sign in":
            Users user = inicioSesion.getUser();
            Users password = inicioSesion.getUser();
                
//                if (user.equals(user)&& password.equals(password)) {
//                    new Inventario();
//                }
                break;

            case "Sign up":

                break;
        }
    }
}
