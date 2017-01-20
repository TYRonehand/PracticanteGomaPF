package com.carlos.practicante_goma_pf;

public class Contactos {

    private int id;
    private String nombre;
    private int pass;
    private String alias;
 
    // Constructor de un objeto Contactos
    public Contactos(int id, String nombre, int pass, String alias) {
        this.id = id;
        this.nombre = nombre;
        this.pass = pass;
        this.alias = alias;
    }
 
    // Recuperar/establecer ID
    public int getID() {
        return id;
    }
    public void setID(int id) {
        this.id = id;
    }
 
    // Recuperar/establecer NOMBRE
    public String getNOMBRE() {
        return nombre;
    }
    public void setNOMBRE(String nombre) {
        this.nombre = nombre;
    }
 
    // Recuperar/establecer pass
    public int getpass() {
        return pass;
    }
    public void setpass(int pass) {
        this.pass = pass;
    }
 
    // Recuperar/establecer alias
    public String getalias() {
        return alias;
    }
    public void setalias(String alias) {
        this.alias = alias;
    }
}