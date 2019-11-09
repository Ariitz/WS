package net.viralpatel.spring.dao;

import Utils.mongo.MongoCollecction;
import Utils.mongo.MongoConection;
import com.mongodb.*;
import constants.ConstantsTablas;
import net.viralpatel.spring.model.IntentoModel;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;







import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import sun.security.pkcs11.wrapper.Constants;


@Service("intentoDao")

public class IntentoDao {
    public void insert(String tiempo, String aciertos, String corPac, String nomPru, String contPru,
                       String fechaInt, String edo) {

        BasicDBObject cBObject = new BasicDBObject();


        cBObject.put("fechaIntento", fechaInt);
        cBObject.put(("tiempo"), tiempo);
        cBObject.put("aciertos", aciertos);
        cBObject.put("correoPaciente", corPac);
        cBObject.put("nombrePrueba", nomPru);
        cBObject.put("contenidoPrueba", contPru);
        cBObject.put("estado", edo);


        MongoCollecction.getInstance(ConstantsTablas.INTENTO).insert(cBObject);

    }

    public List<IntentoModel> findAll(){
        List<IntentoModel> intentos = new ArrayList<IntentoModel>();
        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().find();
        while (cursor.hasNext()){
            DBObject dbObjectI = cursor.next();
            IntentoModel intento = new IntentoModel();
            intento.setId_intento(dbObjectI.get("_id").toString());
            intento.setFecha_i(dbObjectI.get("fechaIntento").toString());
            intento.setTiempo(dbObjectI.get("tiempo").toString());
            intento.setAciertos(dbObjectI.get("aciertos").toString());
            intento.setId_paciente(dbObjectI.get("correoPaciente").toString());
            intento.setId_prueba(dbObjectI.get("nombrePrueba").toString());
            intento.setCont_intento(dbObjectI.get("contenidoPrueba").toString());
            intento.setEdo(dbObjectI.get("estado").toString());

            intentos.add(intento);


        }
        return  intentos;
    }

    public List<IntentoModel> findByP(String corPac, String nomPru){
        List<IntentoModel> intentos = new ArrayList<IntentoModel>();
        DBObject fbpObject = new BasicDBObject();
        fbpObject.put("correoPaciente", corPac);
        fbpObject.put("nombrePrueba", nomPru);

        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().find(fbpObject);

        while (cursor.hasNext()){
            DBObject iObject = cursor.next();

            IntentoModel intento = new IntentoModel();
            intento.setId_intento(iObject.get("_id").toString());
            intento.setFecha_i(iObject.get("fechaIntento").toString());
            intento.setTiempo(iObject.get("tiempo").toString());
            intento.setAciertos(iObject.get("aciertos").toString());
            intento.setId_paciente(iObject.get("correoPaciente").toString());
            intento.setId_prueba(iObject.get("nombrePrueba").toString());
            intento.setCont_intento(iObject.get("contenidoPrueba").toString());
            intento.setEdo(iObject.get("estado").toString());


            intentos.add(intento);
        }
        return  intentos;
    }


    //ACTUALIZAR ACIERTOS
    /*public void updateI(String edo, String corPac, String nomP, String aciertos){


        DBObject intentoF = new BasicDBObject(); //Paciente Find, paciente a buscar por el correo (como documento en la coleccion)
        intentoF.put("estado", edo);
        intentoF.put("correoPaciente", corPac);
        intentoF.put("nombrePrueba", nomP);

        DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().find(intentoF);

        while (cursor.hasNext()){
            DBObject iObject = cursor.next();

            BasicDBObject iUObject = new BasicDBObject();
            iUObject.put(("tiempo"), iObject.get("tiempo").toString());
            iUObject.put("aciertos", aciertos);
            iUObject.put("correoPaciente", iObject.get("correoPaciente").toString());
            iUObject.put("nombrePrueba", iObject.get("nombrePrueba").toString());
            iUObject.put("contenidoPrueba", iObject.get("contenidoPrueba").toString());
            iUObject.put("estado", "realizada");



            MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().remove(iObject);
            MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().insert(iUObject);
        }

    }*/




    public void updateI(String edo, String corPac, String nomP, String aciertos, String fecha){


        DBObject intentoF = new BasicDBObject(); //Paciente Find, paciente a buscar por el correo (como documento en la coleccion)
        intentoF.put("estado", edo);
        intentoF.put("correoPaciente", corPac);
        intentoF.put("nombrePrueba", nomP);

        DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().find(intentoF);

        while (cursor.hasNext()){
            DBObject iObject = cursor.next();

            BasicDBObject iUObject = new BasicDBObject();
            iUObject.put(("tiempo"), iObject.get("tiempo").toString());
            iUObject.put("aciertos", aciertos);
            iUObject.put("correoPaciente", iObject.get("correoPaciente").toString());
            iUObject.put("nombrePrueba", iObject.get("nombrePrueba").toString());
            iUObject.put("contenidoPrueba", iObject.get("contenidoPrueba").toString());
            iUObject.put("estado", "realizada");
            iUObject.put("fechaIntento", fecha);



            MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().remove(iObject);
            MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().insert(iUObject);
        }

    }





















    //INTENTO NO REALIZADO
     public Boolean estado(String correoP, String nomPru){
        DBObject find = new BasicDBObject();
        find.put("correoPaciente", correoP);
        find.put("nombrePrueba", nomPru);
        DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().find(find);

        while (cursor.hasNext()){
            DBObject intento = cursor.next();
            if (intento.get("estado").toString().equals("no realizada")){
                return false;
            }
        }
        return true;
     }

     //ACTUALIZA INTENTO
    public void actualiza(String tiempo, String aciertos, String corPac, String nomPru,
                          String contPru,
                          String fechaInt, String edo){
        DBObject find = new BasicDBObject();
        find.put("estado", edo);
        find.put("correoPaciente", corPac);
        find.put("nombrePrueba", nomPru);
        DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().find(find);
        while (cursor.hasNext()){
            DBObject intentoV = cursor.next();
            BasicDBObject intentoN = new BasicDBObject();
            intentoN.put("correoPaciente", corPac);
            intentoN.put("nombrePrueba", nomPru);
            if (tiempo.equals("0")){
                intentoN.put("tiempo", intentoV.get("tiempo").toString());
            }else {
                intentoN.put("tiempo", tiempo);
            }
            if (aciertos.equals("sin calificar")){
                intentoN.put("aciertos", intentoV.get("aciertos").toString());
            }else {
                intentoN.put("aciertos", aciertos);
            }
            if (contPru.equals("sin contenido")){
                intentoN.put("contenidoPrueba", intentoV.get("contenidoPrueba").toString());
            }else {
                intentoN.put("contenidoPrueba", contPru);
            }
            if (fechaInt.equals("sin fecha")){
                intentoN.put("fechaIntento", intentoV.get("fechaIntento").toString());
            }else {
                intentoN.put("fechaIntento", fechaInt);
            }
            intentoN.put("estado", "realizada");

            MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().remove(intentoV);
            MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().insert(intentoN);

        }







    }






    //ACTUALIZA INTENTO
    public void actualizaPbyI(String id, String tiempo, String aciertos, String corPac, String nomPru,
                          String contPru,
                          String fechaInt, String edo){
        DBObject find = new BasicDBObject();

        find.put("_id", new ObjectId(id));
        System.out.print(find.get("_id"));
        DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().find(find);
        while (cursor.hasNext()){
            DBObject intentoV = cursor.next();
            BasicDBObject intentoN = new BasicDBObject();
            intentoN.put("correoPaciente", intentoV.get("correoPaciente").toString());
            intentoN.put("nombrePrueba", intentoV.get("nombrePrueba").toString());
            if (tiempo.equals("0")){
                intentoN.put("tiempo", intentoV.get("tiempo").toString());
            }else {
                intentoN.put("tiempo", tiempo);
            }
            if (aciertos.equals("sin calificar")){
                intentoN.put("aciertos", intentoV.get("aciertos").toString());
            }else {
                intentoN.put("aciertos", aciertos);
            }
            if (contPru.equals("sin contenido")){
                intentoN.put("contenidoPrueba", intentoV.get("contenidoPrueba").toString());
            }else {
                intentoN.put("contenidoPrueba", contPru);
            }
            if (fechaInt.equals("sin fecha")){
                intentoN.put("fechaIntento", intentoV.get("fechaIntento").toString());
            }else {
                intentoN.put("fechaIntento", fechaInt);
            }
            intentoN.put("estado", "realizada");

            MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().remove(intentoV);
            MongoCollecction.getInstance(ConstantsTablas.INTENTO).getCollection().insert(intentoN);

        }







    }















    //GUARDAR AUDIO
    public void saveAudio(String path, String name){
        GridFS gAudio = new GridFS(MongoConection.getMongoClient().getDB(ConstantsTablas.DB), "audio");
        gAudio.createFile();
    }


}
