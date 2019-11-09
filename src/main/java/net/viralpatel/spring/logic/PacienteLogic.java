package net.viralpatel.spring.logic;

import com.mongodb.DBObject;
import net.viralpatel.spring.dao.PacienteDao;
import net.viralpatel.spring.model.PacienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("pacienteLogic")

public class PacienteLogic {

    @Autowired

    private PacienteDao pacienteDao;
    public Boolean validaNombre(String nombre){
        for (int i = 0; i < nombre.length(); i++)
        {
            char caracter = nombre.toUpperCase().charAt(i);
            int valorASCII = (int)caracter;
            if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90 || valorASCII == 32))
                return false; //Se ha encontrado un caracter que no es letra
        }
        return true;
    }

    public static Boolean validaEmail (String email) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public String insertaPaciente (String nombre, String genero, String correo, String contrasena, String edo, String gradoMaximoEstudios, String lateralidad, String correoEsp, String fechaNacimiento, String observaciones){
        String cadenaRegresa;
        if(validaEmail(correo)){
            if (pacienteDao.existeCorreo(correo)){
                return null;
            }
            else {
                if (genero.toLowerCase().equals("hombre")||genero.toLowerCase().equals("mujer")){

                    DBObject pDBObject =pacienteDao.insert(nombre, genero, correo, contrasena, edo, gradoMaximoEstudios, lateralidad, correoEsp, fechaNacimiento, observaciones);
                    cadenaRegresa = pDBObject.toString();
                }
                else {
                    cadenaRegresa="{}";
                }

            }


        }else{
            cadenaRegresa = "error;";
        }
        return cadenaRegresa;
    }

    // BÙSQUEDAS

    public List<PacienteModel> findAll() {
        return pacienteDao.findAll();
    }

    public  List<PacienteModel> findByName(String nombre) {return pacienteDao.findByName(nombre);}

    public List<PacienteModel> findByNameE (String corEsp, String nombre) {
        return pacienteDao.findByNameE(corEsp, nombre);
    }
    public List<PacienteModel> findByNameC (String corEsp, String corPac) {
        return pacienteDao.findByNameC(corEsp, corPac);
    }




    //ACTUALIZACIONES

    public String updatePaEdo(String correo, String edo)
    {
        pacienteDao.updatePaEdo(correo, edo);
        String actializa;
        actializa ="paciente actualizado";
        return  actializa;
    }

    public String updatePaNom(String correo, String nombre)
    {
        pacienteDao.updatePaNom(correo, nombre);
        String actializa;
        actializa ="paciente actualizado";
        return  actializa;
    }

    public String updatePaG(String correo, String gradoMaximoEstudios)
    {
        pacienteDao.updatePaG(correo, gradoMaximoEstudios);
        String actializa;
        actializa ="paciente actualizado";
        return  actializa;
    }

    public String updatePaO(String correo, String observaciones)
    {
        pacienteDao.updatePaO(correo, observaciones);
        String actializa;
        actializa ="paciente actualizado";
        return  actializa;
    }

    public String actualiza(String nombre, String genero, String correo, String edo,
                            String gradoMaximoEstudios, String lateralidad, String correoEsp, String fechaNacimiento, String observaciones){
        pacienteDao.actualiza(nombre, genero, correo,  edo, gradoMaximoEstudios, lateralidad, correoEsp, fechaNacimiento, observaciones);
        String update;
        return  update="paciente actualizado";
    }


    //INICIO DE SESIÒN
    public String login(String correo, String pw) {
        String log;
        if (pacienteDao.login(correo, pw)){
            log="entra";
        }else {
            log="algún dato está mal";
        }
        return  log;

    }


    //CAMBIAR CONTRASEÑA

    public String  newPw(String correo, String contrasena, String npw){
        String cadenaRegresa;

        pacienteDao.newPw(correo, contrasena, npw);
        cadenaRegresa ="contraseña cambiada";


        return cadenaRegresa;
    }


    //RECUPERAR CONTRASEÑA

    public String recupera (String correo){
        String recupera;
        recupera = pacienteDao.recupera(correo);
        return recupera;
    }


    //PACIENTES ESPECIALISTA
    public  List<PacienteModel> findAllByEsp(String esp) {return pacienteDao.findAllByEsp(esp);}
}

