package net.viralpatel.spring.dao;

import Utils.mongo.MongoCollecction;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import constants.ConstantsTablas;
import net.viralpatel.spring.model.PruebaModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("pruebaDao")

public class PruebaDao implements GenericDao {

    @Override
    public void insert(Object object) {
        System.out.println("Entra dao");

        BasicDBObject cBObject = new BasicDBObject();

        cBObject.put("nombre", object);

        MongoCollecction.getInstance(ConstantsTablas.PRUEBA).insert(cBObject);
        System.out.println("sale dao");
    }

    public List<PruebaModel> findAll() {
        List<PruebaModel> pruebaList = new ArrayList<PruebaModel>();
        DBCursor cursor = MongoCollecction.getInstance(ConstantsTablas.PRUEBA).getCollection().find();
        while (cursor.hasNext()) {
            DBObject dbObject = cursor.next();
            PruebaModel pruebaModel = new PruebaModel();
            pruebaModel.setNombre(dbObject.get("nombre").toString());
            pruebaList.add(pruebaModel);
        }
        return pruebaList;
    }
}