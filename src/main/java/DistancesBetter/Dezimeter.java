package DistancesBetter;

import java.math.BigDecimal;

public class Dezimeter implements UnitOfLength {
    private static String unit = "dm";

    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public BigDecimal getMetersPerUnit() {
        return new BigDecimal("0.1");
    }
}
