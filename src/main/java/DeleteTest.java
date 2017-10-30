import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import utils.Helpers;

import java.util.ArrayList;

public class DeleteTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("DeleteTest");

        collection.drop();

        for(int i=0; i<8; i++){
            collection.insertOne(new Document()
                    .append("_id", i));
        }

        collection.deleteMany(Filters.gt("_id",4));

        for(Document cur: collection.find().into(new ArrayList<Document>())){
            Helpers.printJson(cur);
        }
    }
}
