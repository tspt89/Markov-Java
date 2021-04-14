import java.math.BigInteger;

public class Fraction {

    public BigInteger num;
    public BigInteger den;

    Fraction(BigInteger num, BigInteger den){
        this.num = num;
        this.den = den;

        this.reduce();
    }

    public Fraction reduce(){
        BigInteger d;
        d = gcd(num, den);

        if(d.intValue() != 0){
            this.num = this.num.divide(d);
            this.den = this.den.divide(d);
        }

        return this;
    }

    BigInteger gcd(BigInteger a, BigInteger b){
        //System.out.printf(" %s - %s\n",a,b);
        if (b.toString().equals("0"))
            return a;
        return gcd(b, a.mod(b).abs());
    }

    public void sumar(Fraction b){
        //System.out.println("Sumar fracciones: " + this.num + " / " + this.den + " + " + b.num + " / " + b.den);
        BigInteger num = new BigInteger(this.num.toString()).multiply(b.den)
                .add(new BigInteger(b.num.toString()).multiply(this.den));

        BigInteger den = new BigInteger(this.den.toString()).multiply(b.den);

        this.num = num;
        this.den = den;

        this.reduce();
    }

    public void restar(Fraction b){
        BigInteger num = new BigInteger(this.num.toString()).multiply(b.den)
                .subtract(new BigInteger(b.num.toString()).multiply(this.den));

        BigInteger den = new BigInteger(this.den.toString()).multiply(b.den);

        this.num = num;
        this.den = den;

        this.reduce();
    }

    public static Fraction multFractions(Fraction a, Fraction b ){
        BigInteger num = new BigInteger(a.num.toString()).multiply(b.num);
        BigInteger den = new BigInteger(a.den.toString()).multiply(b.den);
        //System.out.println("Mutl frac: " + a + " * " + b + " = " + new Fraction(num, den).reduce());
        return new Fraction(num, den).reduce();
    }

    public static Fraction divFractions(Fraction a, Fraction b ){
        BigInteger num = new BigInteger(a.num.toString()).multiply(b.den);
        BigInteger den = new BigInteger(a.den.toString()).multiply(b.num);
        //System.out.println("Mutl frac: " + a + " * " + b + " = " + new Fraction(num, den).reduce());
        return new Fraction(num, den).reduce();
    }

    public int getLength(){
        int n = (this.num).toString().length();
        int d = this.den.toString().length();

        return (n >= d)? n : d;
    }

    public float toFloat(){
        return  (this.num.floatValue() / this.den.floatValue());
    }

    @Override
    public String toString() {
        int l = this.getLength();
        return String.format("%"+l+"d/%"+l+"d",this.num,this.den);
    }

    public String toString(int l) {
        return String.format("%"+l+"d/%"+l+"d",this.num,this.den);
    }
}
