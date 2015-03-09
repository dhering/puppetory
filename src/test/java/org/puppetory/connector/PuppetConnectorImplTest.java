package org.puppetory.connector;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNotNull;

/**
 * Created with IntelliJ IDEA.
 * User: dennish
 * Date: 03.03.15
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */
public class PuppetConnectorImplTest {

    @Test
    public void test() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("puppet/query.node.linux.json");
        assertNotNull(inputStream);

        StringBuffer out = new StringBuffer();
        byte[] in = new byte[100];
        while(inputStream.read(in) > 0){
            out.append(new String(in));
        }

        System.out.println(out.toString());
    }
}
