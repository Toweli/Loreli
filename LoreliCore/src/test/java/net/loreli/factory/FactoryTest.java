package net.loreli.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Created by Towel on 5/15/2015.
 */

public class FactoryTest{

    @Test
    public void TestInit()
    {
        FactoryRegister.getInstance().registerFactory("IBasic", new Factory<IBasic>());
        Factory<IBasic> oFactory = (Factory<IBasic>) FactoryRegister.getInstance().getFactory("IBasic");
        oFactory.registerCreator(Special.class);
        oFactory.registerCreator(Special2.class);

        IBasic oSp = oFactory.createObject("net.loreli.factory.Special");
        IBasic oSp2 = oFactory.createObject("net.loreli.factory.Special2");

        assertTrue(oSp instanceof Special);
        assertTrue(oSp2 instanceof Special2);
        assertEquals("Special", oSp.getName());
        assertEquals("Special2", oSp2.getName());
    }
}
