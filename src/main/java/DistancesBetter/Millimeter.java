package DistancesBetter;

import java.math.BigDecimal;

public class Millimeter implements UnitOfLength {
    private static String unit = "mm";
    static{
        UnitsOfLengthCollection.unitsOfLength.add(new Millimeter());
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public BigDecimal getMetersPerUnit() {
        return new BigDecimal("0.001");
    }
}
