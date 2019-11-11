package net.viralpatel.spring.dao;

import Utils.mongo.MongoCollecction;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import constants.ConstantsTablas;
import net.viralpatel.spring.model.EspecialistaModel;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;

import static Utils.mongo.MongoCollecction.*;


@Service("especialistaDao")
public class EspecialistaDao {

    public EspecialistaModel insert( String nombre, String correo, String contrasena) {

        BasicDBObject objetoBaseDatos = new BasicDBObject();

        objetoBaseDatos.put("nombre", nombre);
        objetoBaseDatos.put("correo", correo);
        objetoBaseDatos.put("contrasena", contrasena);

        getInstance(ConstantsTablas.ESPECIALISTA).insert(objetoBaseDatos);

        return findById(correo).get(0);

    }


    public List<EspecialistaModel> findAll() {
        List<EspecialistaModel> especialistaList = new ArrayList<EspecialistaModel>();

        DBCursor cursor = getInstance(ConstantsTablas.ESPECIALISTA).getCollection().find();

        while (cursor.hasNext()) {

            DBObject dbObject = cursor.next();


            EspecialistaModel especialistaModel = new EspecialistaModel();

            especialistaModel.setNombre(dbObject.get("nombre").toString());
            especialistaModel.setCorreo(dbObject.get("correo").toString());
            especialistaModel.setContrasena(dbObject.get("contrasena").toString());

            especialistaList.add(especialistaModel);
        }


        return especialistaList;
    }


    public List<EspecialistaModel> findById(String id) {
        List<EspecialistaModel> especialistaModelids = new ArrayList<EspecialistaModel>();

        DBObject dbObjectQuery = new BasicDBObject();
        dbObjectQuery.put("correo", id);


        DBCursor cursor = getInstance(ConstantsTablas.ESPECIALISTA).getCollection().find(dbObjectQuery);

        while (cursor.hasNext()) {

            DBObject dbObject = cursor.next();


            EspecialistaModel esp = new EspecialistaModel();
            esp.setNombre(dbObject.get("nombre").toString());
            esp.setCorreo(dbObject.get("correo").toString());
            esp.setContrasena(dbObject.get("contrasena").toString());
            especialistaModelids.add(esp);
        }


        return especialistaModelids;
    }


    //INICIO DE SESIÒN ESPECIALISTA

    public boolean login (String correo, String pw){
        DBObject objectLog = new BasicDBObject();
        objectLog.put("correo", correo);
        objectLog.put("contrasena", pw);
        DBCursor cursor = getInstance(ConstantsTablas.ESPECIALISTA).getCollection().find(objectLog);

        while (cursor.hasNext()){
            DBObject compObject = cursor.next();
            if (objectLog.get("contrasena").equals(compObject.get("contrasena").toString())){
                return true;
            }
        }
        return false;
    }

    //CAMBIAR CONTRASEÑA

    public String newPw(String correo, String pw, String npw){
        DBObject espFind = new BasicDBObject();
        espFind.put("correo", correo);
        espFind.put("contrasena", pw);

        DBCursor cursor = getInstance(ConstantsTablas.ESPECIALISTA).getCollection().find(espFind);
        while (cursor.hasNext()){
            DBObject find = cursor.next();
            BasicDBObject espA = new BasicDBObject();
            espA.put("nombre", find.get("nombre").toString());
            espA.put("correo", find.get("correo").toString());
            espA.put("contrasena", npw);

            getInstance(ConstantsTablas.ESPECIALISTA).getCollection().remove(find);
            getInstance(ConstantsTablas.ESPECIALISTA).getCollection().insert(espA);
            return "contraseña cambiaba";

        }
        return "no";
    }

    //RECUPERAR CONTRASEÑA

    public String recupera (String correo){
        DBObject esp = new BasicDBObject();
        esp.put("correo", correo);
        DBCursor cursor = getInstance(ConstantsTablas.ESPECIALISTA).getCollection().find(esp);

        while (cursor.hasNext()){
            DBObject find = cursor.next();
            return find.get("contrasena").toString();

        }

        String recupera = "especialista no encontrado";
        return recupera;
    }

    //EXISTE ATRIBUTO
    //Correo
    public Boolean existeCorreo(String correo){
        DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.ESPECIALISTA).getCollection().find();
        while (cursor.hasNext()){
            DBObject find = cursor.next();
            if (find.get("correo").toString().toLowerCase().equals(correo.toLowerCase())){
                return true;
            }
        }
        return false;
    }


}
