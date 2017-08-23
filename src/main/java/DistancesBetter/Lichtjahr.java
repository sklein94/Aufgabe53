package DistancesBetter;

import java.math.BigDecimal;

public class Lichtjahr implements UnitOfLength {

    private static String unit = "LY";
    static{
        UnitsOfLengthCollection.unitsOfLength.add(new Lichtjahr());
    }

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public BigDecimal getMetersPerUnit() {
        return new BigDecimal("9.461E+15");
    }
}
