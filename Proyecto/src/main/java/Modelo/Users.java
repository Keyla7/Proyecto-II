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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author quiro
 */
@Entity
@Table(name = "tbusers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByIdUser", query = "SELECT u FROM Users u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByOnline", query = "SELECT u FROM Users u WHERE u.online = :online"),
    @NamedQuery(name = "Users.findByProfile", query = "SELECT u FROM Users u WHERE u.profile = :profile")})
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
    @Column(name = "online")
    private int online;
    @Basic(optional = false)
    @Column(name = "profile")
    private String profile;
    
    public static final String [] LISTA_USUARIOS = {"id_user","name","password","email","online","profile"};
    public Users() {
    }

    public Users(Integer idUser) {
        this.idUser = idUser;
    }
    public Users(String email, String password) {
        this.email = email;
        this.password=password;
        this.online=0;
    }
    public Users( String name, String password, String email, String profile) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.online = 0;
        this.profile = profile;
    }
    public Users(int id, String name, String password, String email, String profile) {
        this.idUser= id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.profile = profile;
    }
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
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

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    
    public String getDatosU(int c){
        switch (c) {
            case 0:
                
                return this.idUser+"";
            case 1:
                
                return this.name;
                case 2:
                
                return this.password;
                case 3:
                
                return this.email;
                case 4:
                
                return this.online+"" ;
                case 5:
                
                return this.profile;
        }
        return "";
    }

    @Override
    public String toString() {
        return "Users{" + "idUser=" + idUser + ", name=" + name + ", password=" + password + ", email=" + email + ", online=" + online + ", profile=" + profile + '}';
    }

  
}
