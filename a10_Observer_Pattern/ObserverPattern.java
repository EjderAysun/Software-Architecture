package a10_Observer_Pattern;

import java.util.ArrayList;

// Subject ==> Stock
abstract class Stock {
    protected String _symbol;  // Internal subject state
    protected double _price;  // Internal subject state
    protected ArrayList<Investor> _investors = new ArrayList<>();

    public Stock(String symbol, double price) {
        _symbol = symbol;
        _price = price;
    }

    // Register the Observers
    public void Attach (Investor investor) {
        _investors.add(investor);
    }

    // Unregister from the list of Observers.
    public void Detach (Investor investor) {
        for (int i = 0; i < _investors.size(); i++) {
            if(_investors.get(i).getName() == investor.getName()) {
                _investors.remove(i);
                return;
            }
        }
    }

    // Notify the Observers.
    public void Notify() {
        // set argument to something that helps
        // tell the Observers what happened
        for (int i = 0; i < _investors.size(); i++) {
            _investors.get(i).Update(this);
        }
    }

    public String getSymbol() {return _symbol;}
    void setSymbol(String value) {_symbol = value;}
    public double getPrice() {return 0;}
    abstract public void setPrice(double value);
}

// 'ConcreteSubject' ==> IBM
class IBM extends Stock {
    // Constructor
    public IBM (String symbol, double price) {
        super(symbol, price);
    }
    public double getPrice(){return _price;}
    public void setPrice(double value){
        // Whenever a change happens to _price,
        // notify observers.
        _price = value;
        Notify();
    }
}


// Observer ==> Abstract Observer
interface Observer {
    public void Update(Stock stock);
}

// Concrete Observer ==> Investor
class Investor implements Observer {
    private Stock _stock;
    private String _investorName;
    private String _stockName;  // Internal Observer state
    private double _stockPrice;  // Internal Observer state

    // Constructor
    public Investor(String name) {
        _investorName = name;
    }

    public void Update(Stock stock) {
        _stock = stock;  // Reference to subject
        _stockPrice = stock.getPrice();
        _stockName = stock.getSymbol();
        System.out.println("Notified " + _investorName + " of " + _stockName + "'s " + " change to " + _stockPrice);
    }

    public Stock getStock(){return _stock;}
    public void setStock(Stock value) {_stock = value;}
    public String getName() {return _investorName;}
}

public class ObserverPattern {
    public static void main(String[] args) {
         // Create investors
        Investor s = new Investor("Ejder");
        Investor b = new Investor("Ali");

        // Create IBM stock and attach investors
        IBM ibm = new IBM("IBM", 120.00);
        s.setStock(ibm);   
        b.setStock(ibm);
        ibm.Attach(s);
        ibm.Attach(b);

        // Change price, which notifies investors
        ibm.setPrice(120.10);
        ibm.setPrice(121.00);
        ibm.setPrice(120.50);
        ibm.setPrice(120.75);

        System.out.println("Removing Ali from Notification list");
        ibm.Detach(b);
        ibm.setPrice(121);
        ibm.setPrice(122);
        ibm.setPrice(123);
        ibm.setPrice(124);
        ibm = null;  // Candidate for garbage Collection.  // DANGER !!!!!!
        // We have a dangling reference in our Observer.
        // Remember our "implementation issues".
        System.out.println(s.getStock());  // Reference has a value.
    }
}