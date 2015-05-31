package org.puppetory.model;

import org.puppetory.model.api.Fact;

import java.util.Comparator;

/**
 * comparator to compare {@link Fact}s
 */
public class FactListComparator implements Comparator<Fact>{
    private static Comparator<? super Fact> instance = new FactListComparator();

    private FactListComparator(){};

    /**
     * this comparator is singleton, please call this method to get an instance
     * @return
     */
    public static Comparator<? super Fact> getInstance() {
        return instance;
    }

    /**
     * compare two {@link Fact}s by there names
     *
     * @param f1
     * @param f2
     * @return
     */
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
