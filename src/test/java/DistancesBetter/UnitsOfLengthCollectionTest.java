package DistancesBetter;


import org.junit.Test;

import java.util.ServiceLoader;

import static org.junit.Assert.*;

public class UnitsOfLengthCollectionTest {

    static{
        ServiceLoader serviceLoader = ServiceLoader.load(UnitOfLength.class);
        for (Object unit : serviceLoader){

        }
    }

    @Test
    public void allUnitsContained(){
        collectionContains("m");
        collectionContains("km");
        collectionContains("mi");
        collectionContains("ft");
        collectionContains("LY");
        collectionContains("A");
        collectionContains("mm");
        collectionContains("cm");
    }

    private void collectionContains(String unitSymbol){
        boolean ok = false;
        for (UnitOfLength unit : UnitsOfLengthCollection.unitsOfLength) {
            ok = ok || unit.getUnit().equals(unitSymbol);
        }
        assertTrue(ok);
    }
}