package org.puppetory.model;

import org.puppetory.model.api.Fact;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.05.15
 * Time: 23:50
 * To change this template use File | Settings | File Templates.
 */
public class FactListComparator implements Comparator<Fact>{
    private static Comparator<? super Fact> instance = new FactListComparator();

    private FactListComparator(){};

    public static Comparator<? super Fact> getInstance() {
        return instance;
    }

    @Override
    public int compare(Fact f1, Fact f2) {

        if(f1 == null && f2 != null){
            return -1;
        } else if (f1 != null && f2 == null){
            return 1;
        } else if (f1 != null && f2 != null){
            return f1.getName().compareTo(f2.getName());
        }

        return 0;
    }
}
