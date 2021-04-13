public class Fraction {

    public int num = 0;
    public int den = 0;

    Fraction(int num, int den){
        this.num = num;
        this.den = den;

        this.reduce();
    }

    public Fraction reduce(){
        int d;
        d = gcd(num, den);

        this.num = this.num / d;
        this.den = this.den / d;

        return this;
    }

    int gcd(int a, int b){
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public void sumFractions(Fraction b){
        int num = (this.num * b.den) + (b.num * this.den);
        int den = this.den * b.den;

        this.num = num;
        this.den = den;

        this.reduce();
    }

    public static Fraction multFractions(Fraction a, Fraction b ){
        int num = a.num * b.num;
        int den = a.den * b.den;

        return new Fraction(num, den).reduce();
    }

    public int getLength(){
        int n = Integer.toString(this.num).length();
        int d = Integer.toString(this.den).length();

        return (n >= d)? n : d;
    }

    public float toFloat(){
        return this.num / this.den;
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
