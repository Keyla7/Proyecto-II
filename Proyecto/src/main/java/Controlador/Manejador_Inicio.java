/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Users;
import Modelo.UsersJpaController;
import Vista.Inicio;
import Vista.Registro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Keyla
 */
public class Manejador_Inicio implements ActionListener {

    private Users user;
    private UsersJpaController registroUsuarios;
    private Inicio inicioSesion;
    private Registro registrarse;

    //---------------------------------------------------------------
    public Manejador_Inicio() {
        this.inicioSesion = new Inicio();
        this.inicioSesion.escuchar(this);
        this.inicioSesion.setVisible(true);

        this.registroUsuarios = new UsersJpaController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand().toString()) {
            case "Sign in":
                user = inicioSesion.getUserData();
                if (user != null) {
                    try {
                        if (registroUsuarios.verificacionU(user)) {
                            new Registro();
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Manejador_Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                inicioSesion.setVisible(false);
                break;

            case "Sign up":
                user = inicioSesion.getUserData();
                if (user != null) {
                    JOptionPane.showMessageDialog(null, registroUsuarios.create(user));
                }
                break;
        }
    }
}
