package DistancesBetter;

import java.math.BigDecimal;

public class Fuß implements UnitOfLength {
    private static String unit = "ft";
    static{
        UnitsOfLengthCollection.unitsOfLength.add(new Fuß());
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public BigDecimal getMetersPerUnit() {
        return new BigDecimal("0.3048");
    }
}
