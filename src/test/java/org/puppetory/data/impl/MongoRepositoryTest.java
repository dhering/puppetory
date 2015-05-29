package org.puppetory.data.impl;

import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.puppetory.data.api.Repository;
import org.puppetory.dummies.CollectionDummies;
import org.puppetory.dummies.RepositoryDummies;
import org.puppetory.model.api.Collection;
import org.puppetory.model.impl.FilterImpl;

public class MongoRepositoryTest {

    private final Document referenceDocument = Document.parse("{name: 'dummyFact', foo: 'bar', subfacts: {name: 'dummyFact', foo: 'bar'}}");
    private final Collection referenceCollection = CollectionDummies.getCollectionWithStructuredFact();
    Repository repository;

    @Before
    public void setUp(){
        repository = RepositoryDummies.mockupMongoRepository(referenceDocument);
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
