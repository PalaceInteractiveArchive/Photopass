package network.palace.photopass.utils;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import lombok.Getter;
import network.palace.core.Core;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

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

    public boolean checkToggle(Player p) {
        Document doc = Core.getMongoHandler().getPlayer(p.getUniqueId());
        if (!doc.containsKey("photoPassToggle")) {
            Core.getMongoHandler().getDatabase().getCollection("players")
                    .updateOne(Filters.eq("uuid", p.getUniqueId().toString()),
                            Updates.set("photoPassToggle", true));
            return true;
        } else {
            return doc.getBoolean("photoPassToggle");
        }
    }

    public boolean switchToggle(Player p) {
        Document doc = Core.getMongoHandler().getPlayer(p.getUniqueId());
        if (!doc.containsKey("photoPassToggle")) {
            Core.getMongoHandler().getDatabase().getCollection("players")
                    .updateOne(Filters.eq("uuid", p.getUniqueId().toString()),
                            Updates.set("photoPassToggle", false));
            return false;
        } else {
            if (doc.getBoolean("photoPassToggle")) {
                Core.getMongoHandler().getDatabase().getCollection("players")
                        .updateOne(Filters.eq("uuid", p.getUniqueId().toString()),
                                Updates.set("photoPassToggle", false));
                return false;
            } else {
                Core.getMongoHandler().getDatabase().getCollection("players")
                        .updateOne(Filters.eq("uuid", p.getUniqueId().toString()),
                                Updates.set("photoPassToggle", true));
                return true;
            }
        }
    }

}
