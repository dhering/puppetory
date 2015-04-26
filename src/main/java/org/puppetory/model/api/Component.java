package org.puppetory.model.api;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 26.04.15
 * Time: 14:34
 * To change this template use File | Settings | File Templates.
 */
public interface Component {
    Set<Fact> getFacts();
}
