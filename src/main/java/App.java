import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;

public class App {
    public static void main(String[] args) {
        //Mongo Java Driver
//        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(500).build();
//        MongoClient mongoClient = new MongoClient(new ServerAddress(), options);
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("test").withReadPreference(ReadPreference.secondary());
//        MongoCollection<Document> collection= mongoDatabase.getCollection("test",Document.class);
        //End Mongo Java Driver

        Document document = new Document()
                .append("str","MongoDB, Hello")
                .append("int", 42)
                .append("double",1.1)
                .append("long", 2L)
                .append("boolean", true)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1,2,3));

        Helpers.printJson(document);
    }
}
