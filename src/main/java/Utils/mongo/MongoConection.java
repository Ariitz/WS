package Utils.mongo;

import com.mongodb.MongoClient;
import constants.ConstantsDB;

import java.net.UnknownHostException;

public class MongoConection {
    private static MongoConection mongoConection;
    private static MongoClient mongoClient;


    MongoConection() {
        try {
            mongoClient = new MongoClient(ConstantsDB.URL, ConstantsDB.PORT); // should use this always
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static MongoConection getInstance() {
        mongoConection = new MongoConection();
        return mongoConection;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static void setMongoClient(MongoClient mongoClient) {
        MongoConection.mongoClient = mongoClient;
    }
}
