package SE_311_Theory.a1_Singleton_Pattern;

public class X {
    // The private reference to the one and only instance.
    private static X uniqueStaticPrivateXInstance;
    
    // The X Constructor. It is private!
    // No client can instantiate a Singleton object!
    private X() {}

    // Returns a reference to the single instance.
    // No client can instantiate a X object!
    public static X getInstance() {
        if(uniqueStaticPrivateXInstance == null) {
            uniqueStaticPrivateXInstance = new X();
        }
        return uniqueStaticPrivateXInstance;
    }
}

// NOTE: To make the code more understandable, you can put "Instance" word instead of every "X" word.