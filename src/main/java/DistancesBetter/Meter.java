package DistancesBetter;

import java.math.BigDecimal;

public class Meter implements UnitOfLength {
    private static String unit = "m";
    static{
        UnitsOfLengthCollection.unitsOfLength.add(new Meter());
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public BigDecimal getMetersPerUnit() {
        return new BigDecimal("1");
    }
}
