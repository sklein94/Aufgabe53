package DistancesBetter;

import java.math.BigDecimal;

public class Angström implements UnitOfLength{

    private static String unit = "A";
    static{
        UnitsOfLengthCollection.unitsOfLength.add(new Angström());
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public BigDecimal getMetersPerUnit() {
        return new BigDecimal("1E-10");
    }
}
