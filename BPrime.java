import java.math.BigInteger;

public class BPrime {
    private BigInteger primeNum;

    private boolean isPrime(BigInteger s) {
        boolean prime = true;
        BigInteger d = BigInteger.valueOf(2);
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(s.divide(d)) < 0; i = i.add(BigInteger.valueOf(1))) {
            if (s.mod(i) == BigInteger.ZERO) {
                prime = false;
                break;
            }
        }
        return prime;
    }

    public BPrime() {
        this.primeNum = new BigInteger("2");
    }

    public BPrime(BPrime b) {
        this.primeNum = b.primeNum;
    }

    public BPrime(String s) throws nonPrime {
        this.primeNum = new BigInteger(s);
        if (isPrime(primeNum) == false) {
            throw new nonPrime();
        }

    }

    public BPrime Increment() {
        primeNum = primeNum.add(BigInteger.valueOf(1));
        while (isPrime(primeNum) == false) {
            primeNum = primeNum.add(BigInteger.valueOf(1));
        }

        return this;
    }

    public BPrime Decrement() throws DecrementError {
        if (primeNum.compareTo(BigInteger.valueOf(2)) == 0) {
            throw new DecrementError();
        } else {
            primeNum = primeNum.subtract(BigInteger.valueOf(1));
            while (isPrime(primeNum) == false) {
                primeNum = primeNum.subtract(BigInteger.valueOf(1));
            }
        }
        return this;
    }

    public BPrime Add(int n) {
        int count = 0;
        BigInteger num = primeNum;
        if (n > 0) {
            while (count <= n) {
                num = num.add(BigInteger.valueOf(1));
                if (isPrime(num) == true) {
                    count++;
                }
            }
            primeNum = num;
        }
        return this;
    }

    public BPrime Assign(BPrime b) {
        this.primeNum = b.primeNum;
        return this;
    }

    public String ToString() {
        return primeNum.toString();
    }

    class nonPrime extends Exception {
        public nonPrime() {
            super("Number is not Prime");
        }
    }

    class DecrementError extends Exception {
        public DecrementError() {
            super("No smaller prime numbers");
        }
    }

    public static void main(String[] args) throws nonPrime, DecrementError {
        BPrime primeDefault = new BPrime();
        System.out.println("Prime Number from Default Consuctor: " + primeDefault.primeNum);

        System.out.println("____________________________________________________________________");

        BPrime primeConstuctor = new BPrime("13");
        System.out.println("Prime Number from Constructor: " + primeConstuctor.primeNum);

        System.out.println("____________________________________________________________________");

        BPrime primeCopy = new BPrime(primeConstuctor);
        System.out.println("Copy of Previous prime number: " + primeCopy.primeNum + " Original Address: "
                + primeConstuctor + " Copy Address: " + primeCopy);

        System.out.println("____________________________________________________________________");
        System.out.println("Increment");
        for (int i = 0; i < 100; i++) {
            primeConstuctor.Increment();
            System.out.println(primeConstuctor.primeNum);
        }

        System.out.println("____________________________________________________________________");
        System.out.println("Decrement");
        for (int i = 0; i < 100; i++) {
            primeConstuctor.Decrement();
            System.out.println(primeConstuctor.primeNum);
        }

        System.out.println("____________________________________________________________________");
        System.out.println("300th next prime number");
        primeConstuctor.Add(300);
        System.out.println(primeConstuctor.primeNum);

        System.out.println("____________________________________________________________________");
        System.out.println("Assign prime number to 13");
        primeConstuctor.Assign(primeCopy);
        System.out.println(primeConstuctor.primeNum);

        System.out.println("____________________________________________________________________");
        System.out.println("Prime number as String");
        System.out.println(
                primeConstuctor.ToString() + " Type: " + primeConstuctor.ToString().getClass().getSimpleName());

    }
}
