package net.viralpatel.spring.controller;

import net.viralpatel.spring.logic.PacienteLogic;
import net.viralpatel.spring.logic.PruebaLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PruebaController {

    @Autowired
    private PruebaLogic pruebaLogic;

    @GetMapping("/pruebas")
    public List getPruebas(){
        return pruebaLogic.findAll();
    }

    @GetMapping("/pruebas/{id}")
    private ResponseEntity  getPruebas(@PathVariable("id") String id){
        System.out.println("entra ws");
        String cadenaRegresa;
        cadenaRegresa = pruebaLogic.insertPrueba(id);
        System.out.println("sale ws");

        return new ResponseEntity(cadenaRegresa, HttpStatus.OK);
    }

}
