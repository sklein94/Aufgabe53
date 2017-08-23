package DistancesBetter;

import Distances.Length;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.util.ServiceLoader;

import static org.junit.Assert.*;

public class DistanceTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    static{
        ServiceLoader serviceLoader = ServiceLoader.load(UnitOfLength.class);
        for (Object unit : serviceLoader){

        }
    }

    @Test
    public void checkDistanceCalculatesCorrect() throws Exception{
        this.calculateDistance("1m", "1m", "2m");
        this.calculateDistance("1m", "1km", "1001m");
        this.calculateDistance("1m", "1ft", "1.3048m");
        this.calculateDistance("1m", "1mi", "1633.9m");
        this.calculateDistance("1m", "1LY", "9461000000000001m");
        this.calculateDistance("1m", "1A", "1.0000000001m");
        this.calculateDistance("1m", "1mm", "1.001m");
        this.calculateDistance("1m", "1cm", "1.01m");
    }

    @Test
    public void exceptionWhenTryToUseTheseStringsAsDistance() throws Exception{
        this.exceptionOnString("1,2m");
        this.exceptionOnString("12massdd");
        this.exceptionOnString("1245");
        this.exceptionOnString("12-m");
    }

    @Test
    public void removesAlgebraicSignCorrect() {
        DistancesBetter.Distance firstDistance = new DistancesBetter.Distance("-12m");
        DistancesBetter.Distance secondDistance = new DistancesBetter.Distance("12m");
        assertEquals(firstDistance, secondDistance);
    }

    @Test
    public void toStringShouldBeCorrect() {
        this.distanceToString("12m", "12 m");
        this.distanceToString("-12m", "12 m");
        this.distanceToString("12.4564m", "12.4564 m");
        this.distanceToString("-1132.23245m", "1132.23245 m");
        this.distanceToString("12345mm", "12345 mm");
    }

    @Test
    public void convertDistancesShouldBeCorrect()throws Exception{
        this.convert("1ft", "0.3048m");
        this.convert("1km", "1000m");
        this.convert("1mi", "1632.9m");
        this.convert("1LY", "9.461E+15m");
        this.convert("1A", "1E-10m");


        this.convert("0.3048m", "1ft");
        this.convert("1000m", "1km");
        this.convert("1632.9m", "1mi");
        this.convert("9.461E+15m", "1LY");
        this.convert("1E-10m", "1A");
    }

    private void exceptionOnString(String stringToCheck) throws Exception{
        expectedException.expect(NumberFormatException.class);
        new Distance(stringToCheck);
    }

    private void calculateDistance(String firstDistanceString, String secondDistanceString, String expectedResultDistanceString) throws Exception{
        DistancesBetter.Distance firstDistance = new DistancesBetter.Distance(firstDistanceString);
        DistancesBetter.Distance secondDistance = new DistancesBetter.Distance(secondDistanceString);
        DistancesBetter.Distance expectedResultDistance = new DistancesBetter.Distance(expectedResultDistanceString);

        Distance resultDistance = firstDistance.add(secondDistance);

//        System.out.println(firstDistance);
//        System.out.println(secondDistance);
//        System.out.println(expectedResultDistance);
//        System.out.println(resultDistance);

        assertTrue("\nExpected: " + expectedResultDistance.toString() + "\nActual: " + resultDistance.toString(), expectedResultDistance.equals(resultDistance));
    }

    private void distanceToString(String valueToTest, String expectedValue){
        DistancesBetter.Distance distance = new DistancesBetter.Distance(valueToTest);
        assertEquals(expectedValue, distance.toString());
    }

    private void convert(String value, String expectedValue) throws Exception{
        DistancesBetter.Distance convertedDistance = new DistancesBetter.Distance(value).as(new Distance(expectedValue).unit());
        DistancesBetter.Distance expectedDistance = new DistancesBetter.Distance(expectedValue);
        assertEquals(expectedDistance, convertedDistance);
    }
}