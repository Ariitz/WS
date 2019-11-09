package Utils.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import constants.ConstantsTablas;



public class MongoCollecction {
    private DBCollection collection;

    MongoCollecction(String collectionName) {
        try {
            MongoConection mongo =  MongoConection.getInstance();
            DB db = mongo.getMongoClient().getDB(ConstantsTablas.DB);
            collection = db.getCollection(collectionName);
        } catch (Exception e) {
            e.printStackTrace();
            collection = null;
        }
    }
    

    public static MongoCollecction getInstance(String collectionName){
        return new MongoCollecction(collectionName);
    }

    public DBCollection getCollection() {
        return collection;
    }

    public void setCollection(DBCollection collection) {
        this.collection = collection;
    }

    public boolean insert(BasicDBObject basicDBObject){
        WriteResult writeResult = collection.insert(basicDBObject);
        return writeResult.getUpsertedId() != null;
    }

    

}
