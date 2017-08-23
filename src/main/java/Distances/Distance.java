package Distances;

import java.math.BigDecimal;

public class Distance {

    private BigDecimal distance;
    private Length unitOfLength;

    public BigDecimal count() {
        return distance;
    }

    public Length unit() {
        return unitOfLength;
    }

    public Distance(BigDecimal distance, Length unitOfLength) {
        this.distance = distance.abs();
        this.unitOfLength = unitOfLength;
    }

    public Distance add(Distance distanceToAdd) {
        BigDecimal actualDistance = this.count().add(distanceToAdd.as(this.unitOfLength).count());
        return new Distance(actualDistance, this.unitOfLength);
    }

    @Override
    public String toString() {
        return this.count().stripTrailingZeros()+ " " + this.unitOfLength;
    }

    public Distance as(Length unitOfLength) {
        BigDecimal distanceInMeter = this.toMeter(this.count(), this.unit()).count();
        BigDecimal distanceResult = distanceInMeter;
        switch (unitOfLength) {
            case ft:
                distanceResult = distanceInMeter.divide(new BigDecimal("0.3048"));
                break;
            case km:
                distanceResult = distanceInMeter.divide(new BigDecimal("1000"));
                break;
            case LY:
                distanceResult = distanceInMeter.divide(new BigDecimal("9.461E+15"));
                break;
            case mi:
                distanceResult = distanceInMeter.divide(new BigDecimal("1632.9"));
                break;
            case A:
                distanceResult = distanceInMeter.divide(new BigDecimal("10E-10"));
                break;
            default:
                distanceResult = distanceInMeter;
        }
        return new Distance(distanceResult, unitOfLength);
    }

    private Distance toMeter(BigDecimal value, Length unitOfLength) {
        Distance result = new Distance(new BigDecimal(0), Length.m);
        switch (unitOfLength) {
            case m:
                result = new Distance(value, unitOfLength);
                break;
            case ft:
                result = new Distance(value.multiply(new BigDecimal("0.3048")), Length.m);
                break;
            case km:
                result = new Distance(value.multiply(new BigDecimal("1000")), Length.m);
                break;
            case LY:
                result = new Distance(value.multiply(new BigDecimal("9.461E+15")), Length.m);
                break;
            case mi:
                result = new Distance(value.multiply(new BigDecimal("1632.9")), Length.m);
                break;
            case A:
                result = new Distance(value.multiply(new BigDecimal("10E-10")), Length.m);
                break;
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean ok = false;
        if (obj instanceof Distance) {
            Distance testObject = (Distance) obj;
            ok = this.count().compareTo(testObject.as(this.unitOfLength).count()) == 0;
        }
        return ok;
    }

}
