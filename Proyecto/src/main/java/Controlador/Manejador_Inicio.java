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
    private Inventario tabla;

    //---------------------------------------------------------------
    public Manejador_Inicio() {
        this.inicioSesion = new Inicio();
        this.tabla= new Inventario();
        this.inicioSesion.escuchar(this);
        this.inicioSesion.setVisible(true);
        this.inicioSesion.setLocationRelativeTo(null);

        this.registroUsuarios = new UsersJpaController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand().toString()) {
            case "SIGN IN":
                user = inicioSesion.getUserData();
                if (user != null) {
                    try {
                        if (registroUsuarios.verificacionU(user)) {
                            new Manejador_Inventario();
                            inicioSesion.setVisible(false);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Manejador_Inicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

            case "SIGN UP":
                new Manejador_Registro();
                inicioSesion.setVisible(false);
                break;
        }
    }
}
