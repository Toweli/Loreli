package net.loreli.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by Towel on 5/15/2015.
 */

@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
public class FactoryTest{

    @Test
    public void test01Register()
    {
        FactoryRegister.getInstance().registerFactory("IBasic", new Factory<IBasic>());
        assertTrue(FactoryRegister.getInstance().isRegistered("IBasic"));
    }

    @Test
    public void test02RegisterCreator()
    {
        Factory<IBasic> oFactory = (Factory<IBasic>) FactoryRegister.getInstance().getFactory("IBasic");
        oFactory.registerCreator(Special.class);
        oFactory.registerCreator(Special2.class);
        assertTrue(oFactory.m_mCreator.containsKey("net.loreli.factory.Special"));
        assertTrue(oFactory.m_mCreator.containsKey("net.loreli.factory.Special2"));
    }

    @Test
    public void test03Creation()
    {
        Factory<IBasic> oFactory = (Factory<IBasic>) FactoryRegister.getInstance().getFactory("IBasic");
        IBasic oSp = oFactory.createObject("net.loreli.factory.Special");
        IBasic oSp2 = oFactory.createObject("net.loreli.factory.Special2");
        assertTrue(oSp instanceof Special);
        assertTrue(oSp2 instanceof Special2);
        assertEquals("Special", oSp.getName());
        assertEquals("Special2", oSp2.getName());
    }
}
