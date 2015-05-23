package org.puppetory.data.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.puppetory.dummies.CollectionDummies;
import org.puppetory.model.api.Collection;
import org.puppetory.model.impl.FilterImpl;

import static org.mockito.Mockito.*;

public class MongoRepositoryTest {

    private final Document referenceDocument = Document.parse("{name: 'dummyFact', foo: 'bar', subfacts: {name: 'dummyFact', foo: 'bar'}}");
    private final Collection referenceCollection = CollectionDummies.getCollectionWithStructuredFact();
    MongoRepository repository;

    @Before
    public void setUp(){
        MongoCursor<Document> cursor = mock(MongoCursor.class);
        when(cursor.hasNext()).thenReturn(true, false);
        when(cursor.next()).thenReturn(referenceDocument);

        FindIterable<Document> documents = mock(FindIterable.class);
        when(documents.iterator()).thenReturn(cursor);

        MongoCollection<Document> server = mock(MongoCollection.class);
        when(server.find()).thenReturn(documents);
        when(server.find(any(Bson.class))).thenReturn(documents);

        MongoDatabase database = mock(MongoDatabase.class);
        when(database.getCollection(anyString())).thenReturn(server);
        repository = new MongoRepository(database, new PuppetMongoMapper());
    }

    @Test
    public void testFindByCollection(){
        Collection server = repository.find("server");

        Assert.assertEquals(referenceCollection, server);
    }

    @Test
    public void testFindByCollectionAndFilter(){
        FilterImpl filter = new FilterImpl();
        filter.setQuery("{name:'mockserver'}");

        Collection server = repository.find("server", filter);

        Assert.assertEquals(referenceCollection, server);
    }
}
