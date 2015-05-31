package org.puppetory.dummies;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.puppetory.data.impl.MongoRepository;
import org.puppetory.data.impl.PuppetMongoMapper;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * create some MongoRepository Mockups
 */
public class RepositoryDummies {

    public static MongoRepository mockupMongoRepository(Document document){
        return new MongoRepository(mockupMongoDatabaser(document), new PuppetMongoMapper());
    }

    public static MongoDatabase mockupMongoDatabaser(Document document){
        MongoCursor<Document> cursor = mock(MongoCursor.class);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(document);

        FindIterable<Document> documents = mock(FindIterable.class);
        when(documents.iterator()).thenReturn(cursor);

        MongoCollection<Document> server = mock(MongoCollection.class);
        when(server.find()).thenReturn(documents);
        when(server.find(any(Bson.class))).thenReturn(documents);

        MongoDatabase database = mock(MongoDatabase.class);
        when(database.getCollection(anyString())).thenReturn(server);

        return database;
    }
}
