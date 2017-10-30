import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import utils.Helpers;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

public class FindWithSortSkipLimitTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithSortSkipLimitTest");

        collection.drop();

        //Insert 10 documents with 2 random numbers
        for (int i=0; i<10; i++){
            for (int j=0; j<10; j++){
                collection.insertOne(new Document().append("i", i).append("j",j));
            }
        }

        Bson projection = fields(include("i","j"), excludeId());
        //Bson sort = new Document("i",1).append("j",-1);
        Bson sort = orderBy(
                            ascending("i"),
                            descending("j"));

        List<Document> all = collection.find()
                .projection(projection)
                .sort(sort)
                .skip(20)
                .limit(50)
                .into(new ArrayList<Document>());

        for(Document cur: all){
            Helpers.printJson(cur);
        }
    }
}
