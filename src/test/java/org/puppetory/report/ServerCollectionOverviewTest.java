package org.puppetory.report;

import org.junit.Test;
import org.puppetory.model.api.Collection;
import org.puppetory.model.api.Report;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 30.04.15
 * Time: 18:41
 * To change this template use File | Settings | File Templates.
 */
public class ServerCollectionOverviewTest {

    @Test
    public void findAllTest(){

        Collection collection = null;
        Report report = new ServerCollectionOverview(collection);

        System.out.println(report.toString());

    }
}
