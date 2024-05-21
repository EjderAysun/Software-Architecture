package a7_Facade_Pattern;

class Customer {
    private String name;
    // Constructor
    public Customer(String name) {this.name = name;}
    public String getName() {return name;}
}

// Subsystem Class .1 "Bank"
class Bank {
    public Boolean hasSufficientSavings(Customer c, int amount) {
        System.out.println("Check bank balance of " + c.getName() +
                           " for the amount " + amount + " TL.");
        return true;
    }
}

// Subsystem Class 2. "Credit"
class Credit {
    public Boolean HasGoodCredit(Customer c) {
        System.out.println("Check credit for " + c.getName() + ".");
        return true;
    }
}

// Subsystem Class 3. "Loan"
class Loan {
    public Boolean HasNoBadLoans(Customer c) {
        System.out.println("Check outstanding loans for " + c.getName() + ".");
        return true;
    }
}

// Facade. "Mortgage"
class Mortgage {
    private Bank bank;
    private Loan loan;
    private Credit credit;

    public Mortgage() {
        bank = new Bank();
        loan = new Loan();
        credit = new Credit();
    }

    public Boolean isEligable(Customer cust, int amount) {
        System.out.println(cust.getName() + " applies for " + amount + " TL loan.");
        Boolean eligable = true;

        // Check creditworthiness of applicant
        if(!bank.hasSufficientSavings(cust, amount)) {
            eligable = false;
        } else if (!loan.HasNoBadLoans(cust)) {
            eligable = false;
        } else if (!credit.HasGoodCredit(cust)) {
            eligable = false;
        }
        return eligable;
    }
}

public class FacadePattern {
    public static void main(String[] args) {
        // Facade
        Mortgage mortgage = new Mortgage();
        // Evaluate mortgage eligibility for the customer.
        Customer customer = new Customer("Ejder Aysun");
        Boolean eligable = mortgage.isEligable(customer, 200000);
        System.out.println(customer.getName() + " has been " + (eligable ? "approved." : "rejected."));
    }
}