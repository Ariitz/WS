package net.viralpatel.spring.controller;

import constants.CodeResponse;
import net.viralpatel.spring.logic.EspecialistaLogic;
import net.viralpatel.spring.model.EspecialistaModel;
import net.viralpatel.spring.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EspecialistaWS {


    @Autowired
    private EspecialistaLogic especialistaLogic;


    @GetMapping("/especialista")
    public List getCustomers() {

    	return especialistaLogic.findAll();
    }


    @GetMapping("/especialista/{nombre}/{correo}/{contrasena}")
    public ResponseEntity getCustomer( @PathVariable("nombre") String nombre, @PathVariable("correo") String correo, @PathVariable("contrasena") String contrasena) {

        EspecialistaModel reponse;


        reponse = especialistaLogic.insertEspecialista(nombre, correo, contrasena);
        System.out.print(reponse);



        return new ResponseEntity(reponse, HttpStatus.OK);
    }

    @GetMapping("/especialista/{correo}/")
    public List getEspe(@PathVariable("correo") String id){
        return especialistaLogic.findById(id);

    }

    //INICIO DE SESIÓN

    @GetMapping("/LogEspecialista/{correo}/{pw}")
    public ResponseEntity getEspecialista(@PathVariable("correo") String correo, @PathVariable("pw") String contrasena){
        String respuesta;
        if (especialistaLogic.login(correo, contrasena))
            return new ResponseEntity(new ResponseModel(Boolean.TRUE, CodeResponse.OK), HttpStatus.OK);
        else
            return new ResponseEntity(new ResponseModel(Boolean.FALSE, CodeResponse.GENERIC_ERROR), HttpStatus.NOT_FOUND);
    }

    //CAMBIAR CONTRASEÑA
    @GetMapping("/Especialista/npw/{correo}/{contrasena}/{npw}")
    public ResponseEntity newPw(@PathVariable("correo") String correo, @PathVariable("contrasena") String contrasena,
    @PathVariable("npw") String npw){
        String actualiza;
        actualiza = especialistaLogic.newPw(correo, contrasena, npw);
        return new ResponseEntity(actualiza, HttpStatus.OK);
    }

    //  RECUPERA CONTRASEÑA
    @GetMapping("Especialista/recupera/{correo}/")
    public String recupera(@PathVariable("correo") String correo){
        return especialistaLogic.recupera(correo);
    }



}