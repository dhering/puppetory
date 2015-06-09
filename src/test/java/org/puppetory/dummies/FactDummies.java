package org.puppetory.dummies;

import org.puppetory.model.api.Fact;
import org.puppetory.model.impl.ListedFact;
import org.puppetory.model.impl.PersistentTextualFact;
import org.puppetory.model.impl.StructuredFact;
import org.puppetory.model.impl.TextualFact;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * create some fact mockups
 */
public class FactDummies {

    /**
     * get list of two {@link TextualFact}
     * @return list of facts
     */
    public static List<Fact> getTwoFacts(){
        List<Fact> facts = new ArrayList<Fact>(2);

        facts.add(new TextualFact("name", "dummyFact"));
        facts.add(new TextualFact("foo", "bar"));

        return facts;
    }

    /**
     * get a list with two {@link TextualFact} and one {@link StructuredFact} within two
     * {@link TextualFact}s.
     *
     * @return list of facts
     */
    public static List<Fact> getStructuredFacts(){
        List<Fact> facts = new LinkedList<Fact>();
        facts.addAll(getTwoFacts());
        facts.add(new StructuredFact("subfacts", getTwoFacts()));

        return facts;
    }

    /**
     * get a list with one {@link ListedFact} within two
     * {@link PersistentTextualFact}s.
     *
     * @return list of facts
     */
    public static List<Fact>  getListedFacts(){
        List<Fact> facts = new LinkedList<Fact>();

        List<Fact> list = new ArrayList<Fact>(1);
        list.add(new PersistentTextualFact("name1", "value1"));
        list.add(new PersistentTextualFact("name2", "value2"));

        facts.add(new ListedFact("list", list));

        return facts;
    }

    /**
     * combine the result of {@link #getStructuredFacts()} and {@link #getListedFacts()}
     * into a list.
     *
     * @return list of facts
     */
    public static List<Fact> getDeepStructuredFacts(){
        List<Fact> facts = getStructuredFacts();
        facts.addAll(getListedFacts());

        return facts;
    }

}
