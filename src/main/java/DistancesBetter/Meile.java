package DistancesBetter;

import java.math.BigDecimal;

public class Meile implements UnitOfLength {
    private static String unit = "mi";
    static{
        UnitsOfLengthCollection.unitsOfLength.add(new Meile());
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public BigDecimal getMetersPerUnit() {
        return new BigDecimal("1632.9");
    }
}
