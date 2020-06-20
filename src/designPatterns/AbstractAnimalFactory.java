package designPatterns;

// Java Program to demonstrate the
// working of Abstract Factory Pattern



public class AbstractAnimalFactory {

    public SpeciesFactory getSpeciesFactory(String type) {
        if (type.contains("Air"))
            return new AirAnimalFactory();
        else if(type.contains("Water"))
            return new WaterAnimalFactory();
        else // if(type.contains("Terr"))
            return new TerrestrialAnimalFactory();
    }

}