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
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 21.05.15
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class FactDummies {

    public static List<Fact> getTwoFacts(){
        List<Fact> facts = new ArrayList<Fact>(2);

        facts.add(new TextualFact("name", "dummyFact"));
        facts.add(new TextualFact("foo", "bar"));

        return facts;
    }

    public static List<Fact> getStructuredFacts(){
        List<Fact> facts = new LinkedList<Fact>();
        facts.addAll(getTwoFacts());
        facts.add(new StructuredFact("subfacts", getTwoFacts()));

        return facts;
    }

    public static List<Fact>  getListedFacts(){
        List<Fact> facts = new LinkedList<Fact>();

        List<Fact> list = new ArrayList<Fact>(1);
        list.add(new PersistentTextualFact("name1", "value1"));
        list.add(new PersistentTextualFact("name2", "value2"));

        facts.add(new ListedFact("list", list));

        return facts;
    }

    public static List<Fact> getDeepStructuredFacts(){
        List<Fact> facts = getStructuredFacts();
        facts.addAll(getListedFacts());

        return facts;
    }

}
