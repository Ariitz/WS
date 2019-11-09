package net.viralpatel.spring.logic;

import net.viralpatel.spring.model.IntentoModel;
import net.viralpatel.spring.dao.IntentoDao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("intentoLogic")
public class IntentoLogic {
    @Autowired
    private IntentoDao intentoDao;
    public String insertaIntento( String tiempo, String aciertos, String corPac, String nomPru,
                                 String contIntento, String fechaI, String edo ){
        String regresa;
        if (nomPru!=null && corPac!=null){

                if (intentoDao.estado(corPac, nomPru)){
                    intentoDao.insert( tiempo, aciertos, corPac, nomPru, contIntento, fechaI, edo);
                    regresa = "nuevo intento agregado";
                }
                else {
                    regresa = "Este paciente tiene ya esta prueba asignada";
                }

        }
        else {
            regresa="hubo un error";
        }
        return  regresa;
    }

    public List<IntentoModel> findAll(){
        return intentoDao.findAll();
    }

    public List<IntentoModel> findByP(String corPac, String nomPru){
        return  intentoDao.findByP(corPac, nomPru);
    }

    /*public String updateI(String edo, String corPac, String nomPru, String aciertos){
        intentoDao.updateI(edo, corPac, nomPru, aciertos);
        String actializa;
        actializa ="paciente actualizado";
        return  actializa;
    }*/


    public String updateI(String edo, String corPac, String nomPru, String aciertos, String fecha) {
        intentoDao.updateI(edo, corPac, nomPru, aciertos, fecha);
        String actializa;
        actializa = "paciente actualizado";
        return actializa;
    }

    public String exist(String correo, String nomPru){
        if (intentoDao.estado(correo, nomPru)){
            return "no existe";
        }
        else {
            return "existe";
        }
    }











    public String actualiza ( String tiempo, String aciertos, String corPac, String nomPru,
                             String contIntento, String fechaI, String edo ){

        String update;
        //if (nomPru.equals("palabras") && Integer.parseInt(aciertos)>10){
          //   return update="número de aciertos no valido";
        //}

            intentoDao.actualiza(tiempo, aciertos, corPac, nomPru, contIntento, fechaI, edo);

            return  update="Cambios guardados";


    }




    public String actualizaP (String id, String tiempo, String aciertos, String corPac, String nomPru,
                              String contIntento, String fechaI, String edo ){

        String update;
        //if (nomPru.equals("palabras") && Integer.parseInt(aciertos)>10){
        //   return update="número de aciertos no valido";
        //}

        if (Integer.parseInt(aciertos)>10 || Integer.parseInt(aciertos)<0){
            return "numero de aciertos no valido";
        }
        intentoDao.actualizaPbyI(id, tiempo, aciertos, corPac, nomPru, contIntento, fechaI, edo);

        return  update="Cambios guardados";






    }

}
