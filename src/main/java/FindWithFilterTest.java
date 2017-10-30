import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FindWithFilterTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithFilterTest");

        collection.drop();

        //Insert 10 documents with 2 random numbers
        for (int i=0; i<10; i++){
            collection.insertOne(new Document()
                                    .append("x", new Random().nextInt(2))
                                    .append("y", new Random().nextInt(100)));
        }

        //Bson filter = new Document("x",0)
        //.append("y",new Document("$gt", 10).append("$lt",70));

        Bson filter = Filters.and(Filters.eq("x",0),
                                    Filters.gt("y",10),
                                    Filters.lt("y",70));

        List<Document> all = collection.find(filter).into(new ArrayList<Document>());

        for(Document cur: all){
            Helpers.printJson(cur);
        }
        Long count = collection.count(filter);
        System.out.println();
        System.out.println("Count: "+count);
    }
}
