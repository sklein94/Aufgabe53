package DistancesBetter;

import java.math.BigDecimal;
import java.util.ServiceLoader;

public class Distance {
    private ServiceLoader<UnitOfLength> services = ServiceLoader.load(UnitOfLength.class);

    private UnitOfLength unitOfLength;
    private BigDecimal value;

    public Distance(BigDecimal value, UnitOfLength unit) throws Exception{
        this.setValue(value);
        this.unitOfLength = unit;
    }

    public Distance(String distance) {
        String[] splitted = this.splitArguments(distance);
        if (this.isValid(distance)){
            this.setValue(new BigDecimal(splitted[0]));
            for (UnitOfLength temp : services){
                if (temp.getUnit().equals(splitted[1])){
                    this.unitOfLength = temp;
                }
            }
        }
        else throw new NumberFormatException("Ungueltig ist: " + splitted[0] + "->" + splitted[1]);
    }

    private void setValue(BigDecimal bd){
        this.value = bd.abs();
    }

    public BigDecimal count() {
        return value;
    }

    public UnitOfLength unit() {
        return unitOfLength;
    }

    public Distance add(Distance distanceToAddTo) throws Exception{
        Distance secondDistance = distanceToAddTo.as(this.unit());
        return new Distance(count().add(secondDistance.count()),unit());
    }

    public Distance add(String distanceToAddTo) throws Exception{
        Distance distanceToAdd = new Distance(distanceToAddTo);
        return this.add(distanceToAdd);
    }

    @Override
    public String toString() {
        return this.value.stripTrailingZeros()+" "+this.unit().getUnit();
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Distance){
            Distance unit = (Distance) o;
            boolean ok = this.value.stripTrailingZeros().equals(unit.count().stripTrailingZeros());
            return ok && this.unitOfLength.getUnit().equals(unit.unit().getUnit());
        }
        else return false;
    }

    public Distance as(UnitOfLength unitOfLength) throws Exception{
        Distance distanceInMeters = new Distance(this.count().multiply(this.unit().getMetersPerUnit()), new Meter());
        return new Distance(distanceInMeters.count().divide(unitOfLength.getMetersPerUnit()), unitOfLength);
    }

    private boolean isValid(String stringToCheck) {
        String[] splitted = splitArguments(stringToCheck);

        try{
            new BigDecimal(splitted[0]);
        }
        catch (NumberFormatException arithmeticException){
            return false;
        }

        boolean ok = false;
        for (UnitOfLength temp : services) {
            String symbol = temp.getUnit();
            ok = ok || symbol.equals(splitted[1]);
        }
        return ok;
    }

    private String[] splitArguments(String stringToSplit) {
        String[] returnValue = new String[2];
        for (UnitOfLength temp : services) {
            String symbol = temp.getUnit();
            if (stringToSplit.contains(symbol)) {
                returnValue[0] = stringToSplit.substring(0, stringToSplit.indexOf(symbol)).replace(" ", "");
                returnValue[1] = stringToSplit.substring(stringToSplit.indexOf(symbol)).replace(" ", "");
                if (this.isNumber(returnValue[0])){
                    break;
                }
            }
        }
        return returnValue;
    }

    private boolean isNumber(String stringToCheck){
        try{
            new BigDecimal(stringToCheck);
            return true;
        }
        catch (NumberFormatException numberFormatException){
            return false;
        }
    }


}
