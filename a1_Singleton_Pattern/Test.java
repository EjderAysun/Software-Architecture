package SE_311_Theory.a1_Singleton_Pattern;

public class Test {
    public static void main(String[] args) {
        // Here is a test program:
        X x1 = null, x2 = null;
        x1 = X.getInstance();
        System.out.println("First reference: <" + x1 + ">");
        x2 = X.getInstance();
        System.out.println("Second reference: <" + x2 + ">");
    }
}

// NOTE: To make the code more understandable, you can put "Instance" word instead of every "X" word.

/* Output:
    First reference: <The_Singleton_Pattern.RefA>
    Second reference: <The_Singleton_Pattern.RefA>
*/