package net.viralpatel.spring.controller;


import  net.viralpatel.spring.logic.IntentoLogic;

import net.viralpatel.spring.logic.PacienteLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IntentoController {
    @Autowired
    private IntentoLogic intentoLogic;

    @GetMapping("/intento/newI")
    public ResponseEntity newIntento( @RequestParam (required = false, defaultValue = "0") String tiempo,
                                     @RequestParam (required = false, defaultValue = "no calificado") String aciertos,
                                     @RequestParam String corPac, @RequestParam String nomPru,
                                     @RequestParam(required = false, defaultValue = "sin contenido") String contI,
                                     @RequestParam (required = false, defaultValue = "sin fecha") String fechaI,
                                     @RequestParam( required = false, defaultValue = "no realizada") String edo){
        String regresa;
        regresa = intentoLogic.insertaIntento( tiempo, aciertos, corPac, nomPru, contI, fechaI, edo);
        return new ResponseEntity(regresa, HttpStatus.OK);
    }

    @GetMapping("/intento/up")
    public ResponseEntity upIntento(
                                    @RequestParam (required = false, defaultValue = "0") String tiempo,
                                     @RequestParam (required = false, defaultValue = "sin calificar") String aciertos,
                                     @RequestParam String corPac,
                                    @RequestParam String nomPru,
                                     @RequestParam(required = false, defaultValue = "sin contenido") String contI,
                                     @RequestParam (required = false, defaultValue = "sin fecha") String fechaI,
                                     @RequestParam String edo){
        String regresa;
        regresa = intentoLogic.actualiza( tiempo, aciertos, corPac, nomPru, contI, fechaI, edo);
        return new ResponseEntity(regresa, HttpStatus.OK);
    }





    @GetMapping("/intento/upP")
    public ResponseEntity upIntentoPbyI(
            @RequestParam String id,
            @RequestParam (required = false, defaultValue = "0") String tiempo,
            @RequestParam (required = false, defaultValue = "sin calificar") String aciertos,
            @RequestParam (required = false, defaultValue = "sin calificar") String corPac,
            @RequestParam (required = false, defaultValue = "sin calificar") String nomPru,
            @RequestParam(required = false, defaultValue = "sin contenido") String contI,
            @RequestParam (required = false, defaultValue = "sin fecha") String fechaI,
            @RequestParam (required = false, defaultValue = "realizada")String edo){
        String regresa;
        regresa = intentoLogic.actualizaP(id, tiempo, aciertos, corPac, nomPru, contI, fechaI, edo);
        return new ResponseEntity(regresa, HttpStatus.OK);
    }








    @GetMapping("/intentos")
    public List getIntentos(){
        return  intentoLogic.findAll();
    }

    @GetMapping("/intentos/fbp/{corPac}/{nomPru}")
    public List getIntentos (@PathVariable("corPac") String corPac, @PathVariable("nomPru") String nomPru){
        return intentoLogic.findByP(corPac, nomPru);
    }

    /*//ACTUALIZA  ACIERTOS

    @GetMapping("/upIntento/{estado}/{corPac}/{nomPru}/{aciertos}")
    public  ResponseEntity upIntento(@PathVariable("estado") String estado, @PathVariable("corPac") String corPac,
                                      @PathVariable("nomPru") String nomPru, @PathVariable("aciertos") String aciertos){
        String actualiza;
        actualiza = intentoLogic.updateI(estado, corPac, nomPru, aciertos);
        return new ResponseEntity(actualiza, HttpStatus.OK);
    }*/

    //ACTUALIZA  ACIERTOS

    @GetMapping("/upIntento/{estado}/{corPac}/{nomPru}/{aciertos}/{fecha}")
    public  ResponseEntity upIntento(@PathVariable("estado") String estado, @PathVariable("corPac") String corPac,
                                     @PathVariable("nomPru") String nomPru, @PathVariable("aciertos") String aciertos,
                                     @PathVariable("fecha") String fecha
    ){
        String actualiza;
        actualiza = intentoLogic.updateI(estado, corPac, nomPru, aciertos, fecha);
        return new ResponseEntity(actualiza, HttpStatus.OK);
    }

    //Existe intento
    @GetMapping("intento/{correo}/{prueba}")
    public String exist (@PathVariable("correo") String correo,
                         @PathVariable("prueba") String nomPru){
        String existe=intentoLogic.exist(correo, nomPru);
        return existe;
    }

}
