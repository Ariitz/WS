package net.viralpatel.spring.model;

import org.bson.types.ObjectId;

public class IntentoModel {
    private String id_intento;
    private String tiempo;
    private String aciertos;
    private String id_paciente;
    private String id_prueba;
    private String cont_intento;
    private String fecha_i;
    private String edo;

    public String getEdo() {
        return edo;
    }

    public void setEdo(String edo) {
        this.edo = edo;
    }

    public IntentoModel() {

    }

    public String getId_intento() {
        return id_intento;
    }

    public void setId_intento(String id_intento) {
        this.id_intento = id_intento;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public String getAciertos() {
        return aciertos;
    }

    public void setAciertos(String aciertos) {
        this.aciertos = aciertos;
    }

    public String getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(String id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getId_prueba() {
        return id_prueba;
    }

    public void setId_prueba(String id_prueba) {
        this.id_prueba = id_prueba;
    }

    public String getCont_intento() {
        return cont_intento;
    }

    public void setCont_intento(String cont_intento) {
        this.cont_intento = cont_intento;
    }


    public String getFecha_i() {
        return fecha_i;
    }

    public void setFecha_i(String fecha_i) {
        this.fecha_i = fecha_i;
    }

    public IntentoModel(String id_intento, String tiempo, String aciertos, String id_paciente, String id_prueba, String cont_intento, String fecha_i){
        this.id_intento = id_intento;
        this.tiempo = tiempo;
        this.aciertos = aciertos;
        this.id_paciente = id_paciente;
        this.id_prueba = id_prueba;
        this.cont_intento = cont_intento;
        this.fecha_i = fecha_i;
    }
}
