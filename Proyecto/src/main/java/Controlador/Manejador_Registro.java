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

/**
 *
 * @author Keyla
 */
public class Manejador_Registro implements ActionListener {

    private Registro registrarse;
    private Users user;
    private UsersJpaController registro;
    private Inicio inicioSesion;
    private Inventario tabla;

    public Manejador_Registro() {
        this.registrarse = new Registro();
        this.registrarse.setVisible(true);
        this.registrarse.escuchar(this);
        this.registrarse.setLocationRelativeTo(null);
        registro = new UsersJpaController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand().toString()) {
            case "SIGN UP":
                this.user = this.registrarse.getUser();
                if (this.validarCampos(user)) {
                    Inicio.getMensaje(this.registro.create(user));
                    this.registrarse.limpiar();
                }
                break;
            case "SIGN IN":
                new Manejador_Inicio();
                registrarse.setVisible(false);
                break;
        }
    }

    public boolean validarCampos(Users user) {
        if (user.getName().isEmpty()|| user.getPassword().isEmpty() || user.getEmail().isEmpty() || user.getProfile().isEmpty()) {
            Inicio.getMensaje("Por favor ingrese todos los valores");
            return false;
        }
        return true;
    }
}
