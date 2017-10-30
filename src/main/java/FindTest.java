import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import utils.Helpers;

public class FindTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("findTest");

        collection.drop();

        //insert 10 Documents
        for (int i=0;i<10;i++){
            collection.insertOne(new Document("x",i));
        }

        /*System.out.println("Find One");
        Document first = collection.find().first();
        utils.Helpers.printJson(first);*/

        /*System.out.println("Find all with into:");
        List<Document> all = collection.find().into(new ArrayList<Document>());
        for(Document cur:all){
            utils.Helpers.printJson(cur);
        }*/

        System.out.println("Find all with iteration");
        MongoCursor<Document> cursor = collection.find().iterator();
        try{
            while (cursor.hasNext()){
                Document cur = cursor.next();
                Helpers.printJson(cur);
            }
        }finally {
            cursor.close();
        }

        System.out.println("Count: ");
        long count = collection.count();
        System.out.println(count);
    }
}
