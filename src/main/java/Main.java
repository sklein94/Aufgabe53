import Distances.Length;
import DistancesBetter.Distance;
import DistancesBetter.UnitOfLength;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args){
        ServiceLoader<UnitOfLength> serviceLoader = ServiceLoader.load(UnitOfLength.class);

        for (UnitOfLength o : serviceLoader){
            System.out.print(((UnitOfLength) o).getMetersPerUnit()+ "m -> 1");
            System.out.println(((UnitOfLength) o).getUnit());
        }
    }
}
