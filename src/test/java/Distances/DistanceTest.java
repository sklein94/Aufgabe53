package Distances;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DistanceTest {
    @Test
    public void checkDistanceCalculatesCorrect() {
        this.calculateDistance("1", "1", "2", Length.m, Length.m);
        this.calculateDistance("1", "1", "1.3048", Length.m, Length.ft);
        this.calculateDistance("1", "1", "1001", Length.m, Length.km);
        this.calculateDistance("1", "1", "1633.9", Length.m, Length.mi);
        this.calculateDistance("1", "1", "9461000000000001", Length.m, Length.LY);
        this.calculateDistance("1", "1", "1.000000001", Length.m, Length.A);
    }

    @Test
    public void removesAlgebraicSignCorrect() {
        Distance firstDistance = new Distance(new BigDecimal("-12"), Length.m);
        Distance secondDistance = new Distance(new BigDecimal("12"), Length.m);
        assertEquals(firstDistance, secondDistance);
    }

    @Test
    public void toStringShouldBeCorrect() {
        this.distanceToString("12", Length.m, "12 m");
        this.distanceToString("25", Length.km, "25 km");
        this.distanceToString("3.1250", Length.mi, "3.125 mi");
        this.distanceToString("3.3304500", Length.ft, "3.33045 ft");
    }

    @Test
    public void convertDistancesShouldBeCorrect(){
        this.convert("1", Length.ft, "0.3048", Length.m);
        this.convert("1", Length.km, "1000", Length.m);
        this.convert("1", Length.mi, "1632.9", Length.m);
        this.convert("1", Length.LY, "9.461E+15", Length.m);
        this.convert("1", Length.A, "10E-10", Length.m);


        this.convert("0.3048", Length.m, "1", Length.ft);
        this.convert("1000", Length.m, "1", Length.km);
        this.convert("1632.9", Length.m, "1", Length.mi);
        this.convert("9.461E+15", Length.m, "1", Length.LY);
        this.convert("10E-10", Length.m, "1", Length.A);
    }

    private void calculateDistance(String firstValue, String secondValue, String expectedResult, Length firstUnitOfLength, Length secondUnitOfLength) {
        Distance firstDistance = new Distance(new BigDecimal(firstValue), firstUnitOfLength);
        Distance secondDistance = new Distance(new BigDecimal(secondValue), secondUnitOfLength);

        Distance expectedDistance = new Distance(new BigDecimal(expectedResult), firstUnitOfLength);
        Distance resultingDistance = firstDistance.add(secondDistance);

        assertTrue("\nExpected: " + expectedResult.toString() + "\nActual: " + resultingDistance.toString(), expectedDistance.equals(resultingDistance));
    }

    private void convert(String value, Length unitOfLength, String expectedValue, Length unitOfLengthToConvertTo){
        Distance firstDistance = new Distance(new BigDecimal(value), unitOfLength).as(unitOfLengthToConvertTo);
        Distance convertedDistance = new Distance(new BigDecimal(expectedValue), unitOfLengthToConvertTo);

        assertEquals(firstDistance, convertedDistance);
    }

    private void distanceToString(String value, Length unitOfLength, String expectedString){
        Distance distance = new Distance(new BigDecimal(value), unitOfLength);
        assertEquals(expectedString, distance.toString());
    }
}