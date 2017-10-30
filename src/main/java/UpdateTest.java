import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collection;

import static com.mongodb.client.model.Updates.inc;

public class UpdateTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findWithSortSkipLimitTest");

        collection.drop();

        for(int i=0; i<8; i++){
            collection.insertOne(new Document()
                                    .append("_id", i)
                                    .append("x",i)
                                    .append("y",true));
        }

        //collection.replaceOne(Filters.eq("x",5), new Document("x",20).append("updated",true));

        //collection.updateOne(Filters.eq("x",5),new Document("$set",
         //                                                   new Document("x",20).append("updated",true)));

        /*collection.updateOne(Filters.eq("x",5), Updates.combine(
                                                                    Updates.set("x",20),
                                                                    Updates.set("y",false)));*/

        /*collection.updateOne(Filters.eq("_id",9), Updates.combine(
                Updates.set("x",20),
                Updates.set("y",false)),
                new UpdateOptions().upsert(true));*/
        collection.updateMany(Filters.gte("x",5), inc("x",1));

        for(Document cur: collection.find().into(new ArrayList<Document>())){
            Helpers.printJson(cur);
        }
    }
}
