package net.viralpatel.spring.model;

public class PruebaModel {
    private String id_prueba;
    private String nombre;
    private String ruta_datos;
    private int tiempo_l;
    private String cont_extra;
    private int fases;

    

    public String getId_prueba() {
        return id_prueba;
    }

    public void setId_prueba(String id_prueba) {
        this.id_prueba = id_prueba;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta_datos() {
        return ruta_datos;
    }

    public void setRuta_datos(String ruta_datos) {
        this.ruta_datos = ruta_datos;
    }

    public int getTiempo_l() {
        return tiempo_l;
    }

    public void setTiempo_l(int tiempo_l) {
        this.tiempo_l = tiempo_l;
    }

    public String getCont_extra() {
        return cont_extra;
    }

    public void setCont_extra(String cont_extra) {
        this.cont_extra = cont_extra;
    }

    public int getFases() {
        return fases;
    }

    public void setFases(int fases) {
        this.fases = fases;
    }

    public PruebaModel() {
    }

    public PruebaModel (String id_prueba, String nombre, String ruta_datos, int tiempo_l, String cont_extra, int fases){
        this.id_prueba = id_prueba;
        this.nombre = nombre;
        this.ruta_datos = ruta_datos;
        this.tiempo_l = tiempo_l;
        this.cont_extra = cont_extra;
        this.fases = fases;
    }
}
