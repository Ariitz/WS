package net.viralpatel.spring.logic;

import net.viralpatel.spring.dao.EspecialistaDao;
import net.viralpatel.spring.model.EspecialistaModel;
import net.viralpatel.spring.reglasNegocio.EspecialistaRN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("especialistaLogic")
public class EspecialistaLogic {


    @Autowired
    private EspecialistaDao especialistaDao;

    public static Boolean validaEmail (String email) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

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
    public EspecialistaModel  insertEspecialista(String nombre, String correo, String contrasena){
        EspecialistaModel cadenaRegresa;


        if(validaEmail(correo)){
            if (especialistaDao.existeCorreo(correo)){
                cadenaRegresa = null;
                //return ERROR_RN_01;
            }
            else {
                //if(validaNombre(nombre.toUpperCase()))
                //{
                    cadenaRegresa = especialistaDao.insert(nombre, correo, contrasena);
                //}
                //else {
                  //  cadenaRegresa=null;
                //}
            }
        }else{
            cadenaRegresa = null;
        }

        return cadenaRegresa;
    }

    public List<EspecialistaModel> findAll() {
        return especialistaDao.findAll();
    }

    public List<EspecialistaModel> findById(String id){
        return especialistaDao.findById(id);
    }

    public Boolean login(String correo, String pw) {
        return  especialistaDao.login(correo, pw);

    }


    //CAMBIAR CONTRASEÑA

    public String  newPw(String correo, String contrasena, String npw){
        String cadenaRegresa;


            cadenaRegresa =especialistaDao.newPw(correo, contrasena, npw);


        return cadenaRegresa;
    }


    //RECUPERAR CONTRASEÑA

    public String recupera (String correo){
        String recupera;
        recupera = especialistaDao.recupera(correo);
        return recupera;
    }


}
