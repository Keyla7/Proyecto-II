/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.ButtonGroup;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Keyla
 */
@Entity
@Table(name = "tbusers")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "profile")
    private String profile;

    public static final String[] LISTA_USUARIOS = {"Id", "Name", "Password", "Email", "Profile"};
    
    public Users() {
    }

    public Users(String email, String password) {
        this.email = email;
        this.password= password;
    }

    public Users(int id_User, String name, String password, String email, String profile) {
        this.idUser = idUser;
        this.name = name;
        this.password = password;
        this.email = email;
        this.profile= profile;
    }
    
    public String getDatosU(int columna) {
        switch (columna) {
            case 0:
                return String.valueOf(this.idUser);
            case 1:
                return this.name;
            case 2:
                return this.password;
            case 3:
                return this.email;
            case 4:
                return this.profile;
        }
        return "";
    }
    
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Users{" + "idUser=" + idUser + ", name=" + name + ", password=" + password + ", email=" + email + ", profile=" + profile + '}';
    }
}
