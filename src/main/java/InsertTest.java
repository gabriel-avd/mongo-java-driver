import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class InsertTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document>collection = db.getCollection("insertTest");

        collection.drop();

        Document smith = new Document("name","Smith")
                .append("age",30)
                .append("profession","Programmer");

        Document jones = new Document("name","Jones")
                .append("age",25)
                .append("profession","Hacker");

        //collection.insertOne(smith);
        collection.insertMany(Arrays.asList(smith,jones));
    }
}
