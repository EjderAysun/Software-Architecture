package a6_Abstract_Factory_Pattern;

import java.util.ArrayList;

abstract class CarFactory {
    abstract public Engine createEngine();
    abstract public Transmission createTransmission();
}

abstract class Part {
    protected int price;
    protected String name;

    public Part(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice(){ return price; }
    public String getName(){ return name; }
}

abstract class Engine extends Part{
    protected int price;
    protected String name;

    public Engine(int price, String name) {
        super(price, name);
    }
}

class OpelEngine extends Engine {
    public OpelEngine(int price, String name) {
        super(price, name);
    }
}

class FordEngine extends Engine {
    public FordEngine(int price, String name) {
        super(price, name);
    }
}

abstract class Transmission extends Part {
    protected int price;
    protected String name;

    public Transmission(int price, String name) {
        super(price, name);
    }
}

class FordTransmission extends Transmission {
    public FordTransmission(int price, String name) {
        super(price, name);
    }
}

class OpelTransmission extends Transmission {
    public OpelTransmission(int price, String name) {
        super(price, name);
    }
}

class OpelFactory extends CarFactory {
    public Engine createEngine() {
        return new OpelEngine(25000, "Opel Engine");
    }
    public Transmission createTransmission() {
        return new OpelTransmission(20000, "Opel Transmission");
    }
}

class FordFactory extends CarFactory {
    public Engine createEngine() {
        return new FordEngine(30000, "Ford Engine");
    }
    public Transmission createTransmission() {
        return new FordTransmission(40000, "Ford Transmission");
    }
}

class BuildCar {
    private ArrayList<Part> parts;
    public void CreateCar(CarFactory factory) {
        parts = new ArrayList<Part>();
        parts.add(factory.createEngine());
        parts.add(factory.createTransmission());
    }

    public void displayParts() {
        System.out.println("\tListing parts\n\t--------------");
        parts.forEach(p -> System.out.println("\t"+p.getName() + " " + p.getPrice()));
    }
}

public class AbstractFactoryPattern {
    public static void main(String[] args) {
        CarFactory FORD = new FordFactory();
        CarFactory OPEL = new OpelFactory();

        BuildCar car = new BuildCar();

        car.CreateCar(FORD);
        car.displayParts();
        car.CreateCar(OPEL);
        car.displayParts();
    }
}