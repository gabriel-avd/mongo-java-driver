package homeworks.chapter2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import utils.Helpers;

import java.util.ArrayList;
import java.util.List;

public class Homework_2_5 {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("video");
        MongoCollection<Document> collection = db.getCollection("movieDetails");

        Bson filters = Filters.and(
                Filters.eq("year",2013),
                Filters.eq("rated","PG-13")
        );

        List<Document> movies = collection
                .find(filters)
                .into(new ArrayList<Document>());

        for(Document movie: movies){
            Helpers.printJson(movie);
        }


        /*
        
        db.movieDetails.find({
        "year":2013,
        "rated":"PG-13",
        "awards.wins":0},{title:1});

         */
    }
}
