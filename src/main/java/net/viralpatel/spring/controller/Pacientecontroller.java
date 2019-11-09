package net.viralpatel.spring.controller;

import Utils.mongo.HexUtil;
import net.viralpatel.spring.logic.PacienteLogic;
import net.viralpatel.spring.model.PacienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
public class Pacientecontroller {

    @Autowired
    private PacienteLogic pacienteLogic;



    //NUEVO PACIENTE

    @GetMapping("/paciente/newP")
    public ResponseEntity getPacientes(@RequestParam String nombre, @RequestParam  String genero,
                                       @RequestParam String correo, @RequestParam String contrasena,
                                       @RequestParam(defaultValue = "activo", required = false) String edo,
                                       @RequestParam (required = false, defaultValue = "ninguno") String gradoMaximoEstudios,
                                       @RequestParam(required = false, defaultValue = "diestro") String lateralidad,
                                       @RequestParam String correoEsp, @RequestParam  String fechaNacimiento,
                                       @RequestParam (required = false, defaultValue = "ninguna") String observaciones){

        String cadenaRegresa;
        cadenaRegresa = pacienteLogic.insertaPaciente(nombre, genero, correo, contrasena, edo, gradoMaximoEstudios, lateralidad,
                correoEsp, fechaNacimiento, observaciones);
        System.out.print(cadenaRegresa);


        return new ResponseEntity(cadenaRegresa, HttpStatus.OK);
    }

    //ACTUALIZA PACIENTE
    @GetMapping("/paciente/update")
    public ResponseEntity upPaciente(@RequestParam(required = false, defaultValue = "no") String nombre,
                                     @RequestParam (required = false, defaultValue = "no")  String genero,
                                     @RequestParam String correo,
                                     @RequestParam(defaultValue = "no", required = false) String edo,
                                     @RequestParam (required = false, defaultValue = "no") String gradoMaximoEstudios,
                                     @RequestParam(required = false, defaultValue = "no") String lateralidad,
                                     @RequestParam (required = false) String correoEsp,
                                     @RequestParam(required = false, defaultValue = "no") String fechaNacimiento,
                                     @RequestParam (required = false, defaultValue = "ninguna") String observaciones){
        String operacion;
        operacion = pacienteLogic.actualiza(nombre, genero,
                correo, edo,
                gradoMaximoEstudios, lateralidad, correoEsp, fechaNacimiento, observaciones);
        return new ResponseEntity(operacion, HttpStatus.OK);

    }

   /* @PutMapping("/pacienteup/{correo}/{correoEsp}")
    public PacienteModel pacienteUp(@RequestBody PacienteModel pacienteModel, @PathVariable("correoEsp")String correoEsp,
                             @PathVariable("correo") String correo){
        return pacienteLogic.findByNameC(correoEsp, correo);
    }*************/

    //BÙSQUEDAS

       @GetMapping("/paciente")
    public List getPacientes(){
        return  pacienteLogic.findAll();
    }

    //PACIENTES POR ESPECIALISTA
    @GetMapping("/paciente/{esp}/")
    public List getPacientesEsp(@PathVariable("esp")String esp){
        return  pacienteLogic.findAllByEsp(esp);
    }


    @GetMapping("/paciente/fbn/{nombre}")
    public List getPacientes (@PathVariable("nombre") String nombre){
        return pacienteLogic.findByName(nombre);
    }

    @GetMapping("/paciente/fbnE/{corEsp}/{nombre}")
    public List getPacientesE (@PathVariable("corEsp") String corEsp, @PathVariable("nombre") String nombre){
        return pacienteLogic.findByNameE(corEsp, nombre);
    }

    @GetMapping("/paciente/fbnC/{corEsp}/{corPac}")
    public List getPacientesC (@PathVariable("corEsp") String corEsp, @PathVariable("corPac") String corPac){
        return pacienteLogic.findByNameC(corEsp, corPac);
    }



    //ACTUALIZACIONES
    @GetMapping("/upPaciente/{correo}/{estado}")
    public  ResponseEntity upPaciente(@PathVariable("correo") String correo, @PathVariable("estado") String edo){
        String actualiza;
        actualiza = pacienteLogic.updatePaEdo(correo, edo);
        return new ResponseEntity(actualiza, HttpStatus.OK);
    }

    @GetMapping("/upPacienteN/{correo}/{nombre}")
    public  ResponseEntity upPacienteNom(@PathVariable("correo") String correo, @PathVariable("nombre") String nombre){
        String actualiza;
        actualiza = pacienteLogic.updatePaNom(correo, nombre);
        return new ResponseEntity(actualiza, HttpStatus.OK);
    }

    @GetMapping("/upPacienteG/{correo}/{gradoMaximoEstudios}")
    public  ResponseEntity upPacienteG(@PathVariable("correo") String correo, @PathVariable("gradoMaximoEstudios") String gradoMaximoEstudios){
        String actualiza;
        actualiza = pacienteLogic.updatePaG(correo, gradoMaximoEstudios);
        return new ResponseEntity(actualiza, HttpStatus.OK);
    }

    @GetMapping("/upPacienteO/{correo}/{observaciones}")
    public  ResponseEntity upPacienteO(@PathVariable("correo") String correo, @PathVariable("observaciones") String observaciones){
        String actualiza;
        actualiza = pacienteLogic.updatePaO(correo, observaciones);
        return new ResponseEntity(actualiza, HttpStatus.OK);
    }

    //INICIO DE SESIÓN

    @GetMapping("/LogPaciente/{correo}/{pw}")
    public ResponseEntity log(@PathVariable("correo") String correo, @PathVariable("pw") String contrasena){
        String respuesta;
        respuesta = pacienteLogic.login(correo, contrasena);
        return  new ResponseEntity(respuesta, HttpStatus.ACCEPTED);
    }

    //CAMBIAR CONTRASEÑA
    @GetMapping("/Paciente/npw/{correo}/{contrasena}/{npw}")
    public ResponseEntity newPw(@PathVariable("correo") String correo, @PathVariable("contrasena") String contrasena,
                                @PathVariable("npw") String npw){
        String actualiza;
        actualiza = pacienteLogic.newPw(correo, contrasena, npw);
        return new ResponseEntity(actualiza, HttpStatus.OK);
    }

    //  RECUPERA CONTRASEÑA
    @GetMapping("Paciente/recupera/{correo}")
    public String recupera(@PathVariable("correo") String correo){
        return pacienteLogic.recupera(correo);
    }


    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("Entro ac'a");
        String fileName = "";

        System.out.println(file.getName());
        System.out.println(file.getContentType());
        try {

            System.out.println(file.getBytes().length);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return "exito";
    }

    }
