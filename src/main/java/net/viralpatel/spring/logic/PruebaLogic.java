package net.viralpatel.spring.logic;

import net.viralpatel.spring.dao.PruebaDao;
import net.viralpatel.spring.model.PruebaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pruebaLogic")
public class PruebaLogic {

    @Autowired
    private PruebaDao pruebaDao;

    public String insertPrueba(Object object){
        String cadenaRegresa;
        System.out.println("Entro logica");
        if(object.toString().equals("Raven")||object.toString().equals("Palabras")){
            pruebaDao.insert(object);
            cadenaRegresa = "exito";
        }else{
            System.out.println("Esto es un error");
            cadenaRegresa = "error;";
        }
        System.out.println("Sale logica");
        return cadenaRegresa;
    }

    public List<PruebaModel> findAll() {
        return pruebaDao.findAll();
    }
}
