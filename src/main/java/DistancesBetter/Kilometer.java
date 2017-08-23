package DistancesBetter;

import java.math.BigDecimal;

public class Kilometer implements UnitOfLength{
    private static String unit = "km";
    static{
        UnitsOfLengthCollection.unitsOfLength.add(new Kilometer());
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public BigDecimal getMetersPerUnit() {
        return new BigDecimal("1000");
    }
}
