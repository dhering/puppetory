package org.puppetory.model.impl;

import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Report;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 30.04.15
 * Time: 18:25
 * To change this template use File | Settings | File Templates.
 */
public abstract class TextualReportTemplate implements Report {

    protected Collection collection;

    public TextualReportTemplate(Collection collection){

    }
}
