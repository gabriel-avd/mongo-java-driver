package homeworks.chapter2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import utils.Helpers;

public class Homework_2_3 {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> collection = db.getCollection("grades");

        Bson sort = Sorts.orderBy(
                Sorts.ascending("student_id"),
                Sorts.ascending("score")
        );

        List<Document> homeworkGrades = collection
                .find(Filters.eq("type","homework"))
                .sort(sort)
                .into(new ArrayList<Document>());

        int studentId = 0;
        boolean first = true;
        for(Document grade : homeworkGrades){
            if(first){
                collection.deleteOne(Filters.eq("_id",grade.getObjectId("_id")));
                System.out.println("Deleted:  "+grade.getInteger("student_id"));
                first=false;
            }

            if(grade.getInteger("student_id")!=studentId){
                collection.deleteOne(Filters.eq("_id",grade.getObjectId("_id")));
                System.out.println("Deleted:  "+grade.getInteger("student_id"));
            }

            studentId = grade.getInteger("student_id");

            //System.out.println("id "+grade.getInteger("student_id"));
            //Helpers.printJson(grade);
        }

    }
}
