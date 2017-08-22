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
    public void toStringWorksCorrect() {
        Distance firstDistance = new Distance(new BigDecimal("12"), Length.m);
        assertTrue(firstDistance.toString().equals("12 m"));

        Distance secondDistance = new Distance(new BigDecimal("33.333"), Length.km);
        assertTrue(secondDistance.toString().equals("33.333 km"));
    }

    private void calculateDistance(String firstValue, String secondValue, String expectedResult, Length firstUnitOfLength, Length secondUnitOfLength) {
        Distance firstDistance = new Distance(new BigDecimal(firstValue), firstUnitOfLength);
        Distance secondDistance = new Distance(new BigDecimal(secondValue), secondUnitOfLength);

        Distance expectedDistance = new Distance(new BigDecimal(expectedResult), firstUnitOfLength);
        Distance resultingDistance = firstDistance.add(secondDistance);

        assertTrue("\nExpected: " + expectedResult.toString() + "\nActual: " + resultingDistance.toString(), expectedDistance.equals(resultingDistance));
    }
}