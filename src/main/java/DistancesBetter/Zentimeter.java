package DistancesBetter;

import java.math.BigDecimal;

public class Zentimeter implements UnitOfLength {
    private static String unit = "cm";
    static{
        UnitsOfLengthCollection.unitsOfLength.add(new Zentimeter());
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public BigDecimal getMetersPerUnit() {
        return new BigDecimal("0.01");
    }
}
