package net.viralpatel.spring.model;

import java.util.Date;

public class PacienteModel {
    private String id_paciente;
    private String nombre;
    private String genero;
    private String correo;
    private String contrasena;
    private String estado;
    private String gradoMaximoEstudios;
    private String lateralidad;
    private String id_especialista;



    private String fechaNacimiento;
    private String observaciones;

    public PacienteModel(String id_paciente, String nombre, String genero, String correo, String contrasena, String estado, String gradoMaximoEstudios, String lateralidad,
                         String id_especialista, String fechaNacimiento, String observaciones){
        this.id_paciente = id_paciente;
        this.nombre = nombre;
        this.genero = genero;
        this.correo = correo;
        this.contrasena = contrasena;
        this.estado = estado;
        this.gradoMaximoEstudios = gradoMaximoEstudios;
        this.lateralidad = lateralidad;
        this.id_especialista = id_especialista;
        this.fechaNacimiento = fechaNacimiento;
        this.observaciones = observaciones;
    }

    public PacienteModel() {

    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getId_paciente(){
        return id_paciente;
    }

    public void  setId_paciente(String id_paciente){
        this.id_paciente = id_paciente;
    }

    public String getNombre(){
        return  nombre;
    }

    public void setNombre (String nombre){
        this.nombre = nombre;
    }

    public String getGenero (){
        return genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public String getCorreo (){
        return  correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public String getContrasena(){
        return contrasena;
    }

    public void setContrasena(String contrasena){
        this.contrasena = contrasena;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public String getGradoMaximoEstudios(){
        return  gradoMaximoEstudios;
    }

    public void setGradoMaximoEstudios(String gradoMaximoEstudios){
        this.gradoMaximoEstudios = gradoMaximoEstudios;
    }

    public String getLateralidad(){
        return lateralidad;
    }

    public void setLateralidad(String lateralidad){
        this.lateralidad = lateralidad;
    }

    public String getId_especialista(){
        return  id_especialista= id_especialista;
    }

    public void setId_especialista(String id_especialista){
        this.id_especialista = id_especialista;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}

