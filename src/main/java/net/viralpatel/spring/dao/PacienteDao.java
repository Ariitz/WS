package net.viralpatel.spring.dao;


import Utils.mongo.MongoCollecction;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import constants.ConstantsTablas;
import net.viralpatel.spring.model.PacienteModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("pacienteDao")
public class PacienteDao{

    //AGREGAR PACIENTES

    public BasicDBObject insert(String nombre, String genero, String correo, String contrasena, String edo, String gradoMaximoEstudios, String lateralidad, String correoEsp, String fechaNacimiento, String observaciones) {
        BasicDBObject pDBObject = new BasicDBObject();
        pDBObject.put("nombre",nombre);
        pDBObject.put("genero",genero);
        pDBObject.put("correo",correo);
        pDBObject.put("contrasena",contrasena);
        pDBObject.put("estado",edo);
        pDBObject.put("gradoMaximoEstudios",gradoMaximoEstudios);
        pDBObject.put("lateralidad",lateralidad);
        pDBObject.put("correoEsp",correoEsp);
        pDBObject.put("fechaNacimiento",fechaNacimiento);
        pDBObject.put("observaciones", observaciones);

        MongoCollecction.getInstance(ConstantsTablas.PACIENTE).insert(pDBObject);

        System.out.println("sale dao");
        return pDBObject;

    }

    //BUSCAR TODOS LOS PACIENTES
    public List<PacienteModel> findAll() {
        List<PacienteModel> pacientes = new ArrayList<PacienteModel>();
        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find();
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            PacienteModel pacienteModel = new PacienteModel();
            pacienteModel.setId_paciente(dbObject.get("_id").toString());
            pacienteModel.setNombre(dbObject.get("nombre").toString());
            pacienteModel.setCorreo(dbObject.get("correo").toString());
            pacienteModel.setEstado(dbObject.get("estado").toString());
            pacienteModel.setGenero(dbObject.get("genero").toString());
            pacienteModel.setFechaNacimiento(dbObject.get("fechaNacimiento").toString());
            pacienteModel.setGradoMaximoEstudios(dbObject.get("gradoMaximoEstudios").toString());
            pacienteModel.setId_especialista(dbObject.get("correoEsp").toString());
            pacienteModel.setLateralidad(dbObject.get("lateralidad").toString());
            pacienteModel.setObservaciones(dbObject.get("observaciones").toString());


                pacientes.add(pacienteModel);

        }

        return pacientes;
    }

    //BUSCAR PACIENTE POR NOMBRE
    public List<PacienteModel> findByName(String nombre){
        List<PacienteModel> pacientes = new ArrayList<PacienteModel>();
            DBObject fbnObject = new BasicDBObject();
            fbnObject.put("nombre", nombre);

            DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(fbnObject);

            while (cursor.hasNext()){
                DBObject dbObject = cursor.next();

                PacienteModel paciente =  new PacienteModel();
                paciente.setNombre(dbObject.get("nombre").toString());
                paciente.setCorreo(dbObject.get("correo").toString());
                paciente.setEstado(dbObject.get("estado").toString());
                paciente.setGenero(dbObject.get("genero").toString());
                paciente.setFechaNacimiento(dbObject.get("fechaNacimiento").toString());
                paciente.setGradoMaximoEstudios(dbObject.get("gradoMaximoEstudios").toString());
                paciente.setId_especialista(dbObject.get("correoEsp").toString());
                paciente.setLateralidad(dbObject.get("lateralidad").toString());
                paciente.setObservaciones(dbObject.get("observaciones").toString());


                pacientes.add(paciente);
            }

            return pacientes;

    }


    //BUSCAR POR CORREO ESPECIALISTA Y NOMBRE

    public List<PacienteModel> findByNameE(String corEsp, String nombre){
        List<PacienteModel> pacientes = new ArrayList<PacienteModel>();
        DBObject fbnObject = new BasicDBObject();
        fbnObject.put("nombre", nombre);
        fbnObject.put("correoEsp", corEsp);

        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(fbnObject);

        while (cursor.hasNext()){
            DBObject dbObject = cursor.next();

            PacienteModel paciente =  new PacienteModel();
            paciente.setNombre(dbObject.get("nombre").toString());
            paciente.setCorreo(dbObject.get("correo").toString());
            paciente.setEstado(dbObject.get("estado").toString());
            paciente.setGenero(dbObject.get("genero").toString());
            paciente.setFechaNacimiento(dbObject.get("fechaNacimiento").toString());
            paciente.setGradoMaximoEstudios(dbObject.get("gradoMaximoEstudios").toString());
            paciente.setId_especialista(dbObject.get("correoEsp").toString());
            paciente.setLateralidad(dbObject.get("lateralidad").toString());
            paciente.setObservaciones(dbObject.get("observaciones").toString());


            pacientes.add(paciente);
        }

        return pacientes;

    }


    //BUSCAR PACIENTE POR CORREO ESPECIALISTA Y CORREO PACIENTE
    public List<PacienteModel> findByNameC(String corEsp, String corPac){
        List<PacienteModel> pacientes = new ArrayList<PacienteModel>();
        DBObject fbnObject = new BasicDBObject();

        fbnObject.put("correoEsp", corEsp);
        fbnObject.put("correo", corPac);

        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(fbnObject);

        while (cursor.hasNext()){
            DBObject dbObject = cursor.next();

            PacienteModel paciente =  new PacienteModel();
            paciente.setNombre(dbObject.get("nombre").toString());
            paciente.setCorreo(dbObject.get("correo").toString());
            paciente.setEstado(dbObject.get("estado").toString());
            paciente.setGenero(dbObject.get("genero").toString());
            paciente.setFechaNacimiento(dbObject.get("fechaNacimiento").toString());
            paciente.setGradoMaximoEstudios(dbObject.get("gradoMaximoEstudios").toString());
            paciente.setId_especialista(dbObject.get("correoEsp").toString());
            paciente.setLateralidad(dbObject.get("lateralidad").toString());
            paciente.setObservaciones(dbObject.get("observaciones").toString());


            pacientes.add(paciente);
        }

        return pacientes;

    }

    //ACTUALIZAR ESTADO PACIENTE

   public void updatePaEdo(String correo, String edo){


        DBObject pacienteF = new BasicDBObject(); //Paciente Find, paciente a buscar por el correo (como documento en la coleccion)
        pacienteF.put("correo", correo);

        DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(pacienteF);

       while (cursor.hasNext()){
           DBObject dbObject = cursor.next();

           BasicDBObject pDBObject = new BasicDBObject();
           pDBObject.put("nombre",dbObject.get("nombre").toString());
           pDBObject.put("genero", dbObject.get("genero").toString());
           pDBObject.put("correo", dbObject.get("correo").toString());
           pDBObject.put("contrasena", dbObject.get("contrasena").toString());
           pDBObject.put("estado",edo);
           pDBObject.put("gradoMaximoEstudios", dbObject.get("gradoMaximoEstudios").toString());
           pDBObject.put("lateralidad",dbObject.get("lateralidad").toString());
           pDBObject.put("correoEsp", dbObject.get("correoEsp").toString());
           pDBObject.put("fechaNacimiento",dbObject.get("fechaNacimiento").toString());
           pDBObject.put("observaciones", dbObject.get("observaciones").toString());

           MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().remove(dbObject);
           MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().insert(pDBObject);



       }

   }


   //ACTUALIZAR NOMBRE PACIENTE
   public void updatePaNom(String correo, String nombre){


       DBObject pacienteF = new BasicDBObject(); //Paciente Find, paciente a buscar por el correo (como documento en la coleccion)
       pacienteF.put("correo", correo);

       DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(pacienteF);

       while (cursor.hasNext()){
           DBObject dbObject = cursor.next();

           BasicDBObject pDBObject = new BasicDBObject();
           pDBObject.put("nombre", nombre);
           pDBObject.put("genero", dbObject.get("genero").toString());
           pDBObject.put("correo", dbObject.get("correo").toString());
           pDBObject.put("contrasena", dbObject.get("contrasena").toString());
           pDBObject.put("estado", dbObject.get("estado").toString());
           pDBObject.put("gradoMaximoEstudios", dbObject.get("gradoMaximoEstudios").toString());
           pDBObject.put("lateralidad",dbObject.get("lateralidad").toString());
           pDBObject.put("correoEsp", dbObject.get("correoEsp").toString());
           pDBObject.put("fechaNacimiento",dbObject.get("fechaNacimiento").toString());
           pDBObject.put("observaciones", dbObject.get("observaciones").toString());

           MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().remove(dbObject);
           MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().insert(pDBObject);



       }

   }

    //ACTUALIZAR GRADO MÀXIMO DE ESTUDIOS PACIENTE
    public void updatePaG(String correo, String gradoMaximoEstudios){


        DBObject pacienteF = new BasicDBObject(); //Paciente Find, paciente a buscar por el correo (como documento en la coleccion)
        pacienteF.put("correo", correo);

        DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(pacienteF);

        while (cursor.hasNext()){
            DBObject dbObject = cursor.next();

            BasicDBObject pDBObject = new BasicDBObject();
            pDBObject.put("nombre", dbObject.get("nombre").toString());
            pDBObject.put("genero", dbObject.get("genero").toString());
            pDBObject.put("correo", dbObject.get("correo").toString());
            pDBObject.put("contrasena", dbObject.get("contrasena").toString());
            pDBObject.put("estado", dbObject.get("estado").toString());
            pDBObject.put("gradoMaximoEstudios", gradoMaximoEstudios);
            pDBObject.put("lateralidad",dbObject.get("lateralidad").toString());
            pDBObject.put("correoEsp", dbObject.get("correoEsp").toString());
            pDBObject.put("fechaNacimiento",dbObject.get("fechaNacimiento").toString());
            pDBObject.put("observaciones", dbObject.get("observaciones").toString());

            MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().remove(dbObject);
            MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().insert(pDBObject);



        }

    }
    //ACTUALIZAR OBSERVACIONES PACIENTE
    public void updatePaO(String correo, String observaciones){
        DBObject pacienteF = new BasicDBObject(); //Paciente Find, paciente a buscar por el correo (como documento en la coleccion)
        pacienteF.put("correo", correo);
        DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(pacienteF);
        while (cursor.hasNext()){
            DBObject dbObject = cursor.next();
            BasicDBObject pDBObject = new BasicDBObject();
            pDBObject.put("nombre", dbObject.get("nombre").toString());
            pDBObject.put("genero", dbObject.get("genero").toString());
            pDBObject.put("correo", dbObject.get("correo").toString());
            pDBObject.put("contrasena", dbObject.get("contrasena").toString());
            pDBObject.put("estado", dbObject.get("estado").toString());
            pDBObject.put("gradoMaximoEstudios", dbObject.get("gradoMaximoEstudios").toString());
            pDBObject.put("lateralidad",dbObject.get("lateralidad").toString());
            pDBObject.put("correoEsp", dbObject.get("correoEsp").toString());
            pDBObject.put("fechaNacimiento",dbObject.get("fechaNacimiento").toString());
            pDBObject.put("observaciones", dbObject.get("observaciones").toString());
            MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().remove(dbObject);
            MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().insert(pDBObject);
        }
    }

    //Actualiza
    public void actualiza(String nombre, String genero, String correo,     String edo,
                          String gradoMaximoEstudios, String lateralidad, String correoEsp, String fechaNacimiento, String observaciones){
        DBObject find = new BasicDBObject();
        find.put("correo", correo);
        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(find);
        while (cursor.hasNext()){
            DBObject dbObject = cursor.next();
            BasicDBObject pDBObject=new BasicDBObject();
            if (nombre.equals("no")){

                pDBObject.put("nombre", dbObject.get("nombre").toString());
            }
            else {
                pDBObject.put("nombre",nombre);
            }
            if (genero.equals("no")){

                pDBObject.put("genero", dbObject.get("genero").toString());
            }
            else {
                pDBObject.put("genero",genero);
            }
            if (edo.equals("no")){

                pDBObject.put("estado", dbObject.get("estado").toString());
            }
            else {
                pDBObject.put("estado", edo);
            }
            if (gradoMaximoEstudios.equals("no")){

                pDBObject.put("gradoMaximoEstudios", dbObject.get("gradoMaximoEstudios").toString());
            }
            else {
                pDBObject.put("gradoMaximoEstudios", gradoMaximoEstudios);
            }
            if (lateralidad.equals("no")){

                pDBObject.put("lateralidad", dbObject.get("lateralidad").toString());
            }
            else {
                pDBObject.put("lateralidad", lateralidad);
            }

            pDBObject.put("correo",correo);
            pDBObject.put("contrasena", dbObject.get("contrasena").toString());

            if (fechaNacimiento.equals("no")){

                pDBObject.put("fechaNacimiento", dbObject.get("fechaNacimiento").toString());
            }
            else {
                pDBObject.put("fechaNacimiento", fechaNacimiento);
            }
            if (observaciones==("sin observaciones")){

                pDBObject.put("observacionesvaciones", dbObject.get("observaciones").toString());
            }
            else {
                pDBObject.put("observaciones", observaciones);
            }
            pDBObject.put("correoEsp", dbObject.get("correoEsp").toString());
            MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().remove(dbObject);
            MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().insert(pDBObject);
        }
    }


    //INICIO DE SESIÓN

    public boolean login (String correo, String pw){
        DBObject objectLog = new BasicDBObject();
        objectLog.put("correo", correo);
        objectLog.put("contrasena", pw);
        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(objectLog);

        while (cursor.hasNext()){
            DBObject compObject = cursor.next();
            if (objectLog.get("contrasena").equals(compObject.get("contrasena").toString())){
                return true;
            }
        }
        return false;
    }

    //CAMBIAR CONTRASEÑA

    public void newPw(String correo, String pw, String npw){
        DBObject paciente = new BasicDBObject();
        paciente.put("correo", correo);
        paciente.put("contrasena", pw);

        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(paciente);
        while (cursor.hasNext()){
            DBObject find = cursor.next();
            BasicDBObject pacA = new BasicDBObject();

            pacA.put("nombre", find.get("nombre").toString());
            pacA.put("correo", find.get("correo").toString());
            pacA.put("contrasena", npw);
            pacA.put("genero", find.get("genero").toString());
            pacA.put("estado", find.get("estado").toString());
            pacA.put("gradoMaximoEstudios", find.get("gradoMaximoEstudios").toString());
            pacA.put("lateralidad", find.get("lateralidad").toString());
            pacA.put("correoEsp", find.get("correoEsp").toString());
            pacA.put("fechaNacimiento",find.get("fechaNacimiento").toString());
            pacA.put("observaciones", find.get("observaciones").toString());

            MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().remove(find);
            MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().insert(pacA);

        }
    }

    //RECUPERAR CONTRASEÑA

    public String recupera (String correo){
        DBObject paciente = new BasicDBObject();
        paciente.put("correo", correo);
        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(paciente);

        while (cursor.hasNext()){
            DBObject find = cursor.next();
            return find.get("contrasena").toString();

        }

        String recupera = "especialista no encontrado";
        return recupera;
    }

    //VERIFICA SI EXISTE
    //Correo
    public Boolean existeCorreo(String correo){
        DBCursor cursor=MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find();
        while (cursor.hasNext()){
            DBObject find = cursor.next();
            if (find.get("correo").toString().toLowerCase().equals(correo.toLowerCase())){
                return true;
            }
        }
        return false;
    }

    //BUSCAR TODOS LOS PACIENTES QUE TIENE UN ESPECIALISTA

    public List<PacienteModel> findAllByEsp(String esp) {
        List<PacienteModel> pacientes = new ArrayList<PacienteModel>();
        DBObject find = new BasicDBObject();
        find.put("correoEsp", esp);
        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.PACIENTE).getCollection().find(find);
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            PacienteModel pacienteModel = new PacienteModel();
            pacienteModel.setId_paciente(dbObject.get("_id").toString());
            pacienteModel.setNombre(dbObject.get("nombre").toString());
            pacienteModel.setCorreo(dbObject.get("correo").toString());
            pacienteModel.setEstado(dbObject.get("estado").toString());
            pacienteModel.setGenero(dbObject.get("genero").toString());
            pacienteModel.setFechaNacimiento(dbObject.get("fechaNacimiento").toString());
            pacienteModel.setGradoMaximoEstudios(dbObject.get("gradoMaximoEstudios").toString());
            pacienteModel.setId_especialista(dbObject.get("correoEsp").toString());
            pacienteModel.setLateralidad(dbObject.get("lateralidad").toString());
            pacienteModel.setObservaciones(dbObject.get("observaciones").toString());


            pacientes.add(pacienteModel);

        }

        return pacientes;
    }
}
