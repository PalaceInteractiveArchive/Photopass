package network.palace.photopass.utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;
import network.palace.core.Core;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @author Tom
 * @since 28/12/2020
 * @version 1.0.0
 */

public class MongoManager {
    @Getter public final MongoCollection<Document> photosCollection;

    public MongoManager() {
        MongoDatabase db = Core.getMongoHandler().getDatabase();
        photosCollection = db.getCollection("photopass");
    }

    public void createPhoto(String url, List<String> players, String info) {
        Document photo = new Document("_id", new ObjectId());
        photo.append("url", new BsonString(url))
                .append("players", players)
                .append("info", new BsonString(info));
        photosCollection.insertOne(photo);
    }

}
