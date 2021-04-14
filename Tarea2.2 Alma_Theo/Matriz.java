import javax.swing.*;
import java.math.BigInteger;
import java.util.Scanner;

public class Matriz {
    Fraction[][] fracciones;
    private int n = 0, m = 0;

    public Matriz(int m, int n){
        fracciones = new Fraction[m][n];
        this.n = n;
        this.m = m;
    }

    public void fillMatrix(Scanner sc){
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                System.out.println("M["+i+","+j+"]");
                long num = Long.parseLong(sc.nextLine());
                long den = Integer.parseInt(sc.nextLine());
                fracciones[i][j] = new Fraction(BigInteger.valueOf(num),BigInteger.valueOf(den));
            }
        }
    }

    public Matriz fillWithZeros(){
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                this.fracciones[i][j] = new Fraction(BigInteger.valueOf(0),BigInteger.valueOf(1));
            }
        }
        return this;
    }

    public void printMatrix(){
        int maxLength = 0;
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                if(maxLength < fracciones[i][j].getLength())
                    maxLength = fracciones[i][j].getLength();

        for(int i = 0; i < m; i++){
            for(int j=0; j < n; j++){
                if(j == 0) System.out.print("| " + fracciones[i][j].toString(maxLength) + " ");
                else if(j == (n-1)) System.out.println(fracciones[i][j].toString(maxLength) + "|");
                else System.out.print(fracciones[i][j].toString(maxLength) + " ,");
            }
        }
        System.out.println();
    }

    public void printFloatMatrix(){
        int maxLength = 0;
        for(int i = 0; i < m; i++)
            for(int j = 0; j < n; j++)
                if(maxLength < fracciones[i][j].getLength())
                    maxLength = Float.toString(fracciones[i][j].toFloat()).length();

        for(int i = 0; i < m; i++){
            for(int j=0; j < n; j++){
                if(j == 0) System.out.printf("| %3.4f ", fracciones[i][j].toFloat());
                else if(j == (m-1)) System.out.printf("%3.4f|\n",fracciones[i][j].toFloat());
                else System.out.printf("%3.4f ",fracciones[i][j].toFloat());
            }

        }
    }

    public boolean checkProbability(){
        Fraction res;

        for(int i =0; i < m; i++){
            res = new Fraction(new BigInteger("0"),new BigInteger("1"));
            for(int j=0; j < n; j++){
                res.sumar(fracciones[i][j]);
            }
            if(res.toFloat() != 1.0f){
                System.out.println("La suma de probabilidades en la columna: " + (i+1) + " no es igual a 1.");
                return false;
            }
        }
        return true;
    }

    public void multMatriz(Matriz a){
        Matriz res = new Matriz(this.n, a.m).fillWithZeros();
        int fila = this.m, col = a.n;

        for(int i = 0; i < col; i++){
            for(int j = 0; j < fila; j++){
                for(int k = 0; k < col; k++){
                    res.fracciones[i][j].sumar(Fraction.multFractions(this.fracciones[i][k],a.fracciones[k][j]));
                }
            }
        }
        this.fracciones = res.fracciones;
    }

    public Matriz copiarMatriz(){
        int m = this.m, n = this.n;
        Matriz matriz = new Matriz(m, n);

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                matriz.fracciones[i][j] = new Fraction(fracciones[i][j].num,fracciones[i][j].den);
            }
        }
        return matriz;
    }

    public void restarMatriz(Matriz a){
        if(this.m != a.m || this.n != a.n){
            System.out.println("No se puede realizar esta operacion con dimensiones diferentes");
            return;
        }
        int n = this.n, m = this.m;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                this.fracciones[i][j].restar(a.fracciones[i][j]);
            }
        }
    }

    public void sumarMatriz(Matriz a){
        if(this.m != a.m || this.n != a.n){
            System.out.println("No se puede realizar esta operacion con dimensiones diferentes");
            return;
        }
        int n = this.n, m = this.m;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                this.fracciones[i][j].sumar(a.fracciones[i][j]);
            }
        }
    }

    public static Matriz obtainVars(int n){
        Matriz m = new Matriz(n-1,n);
        for(int i = 0; i < m.m; i++){
            for(int j = 0; j < m.n; j++){
                if(i == j){
                    m.fracciones[i][j] = new Fraction(new BigInteger("1"), new BigInteger("1"));
                } else if(i == m.n-1){
                    m.fracciones[i][j] = new Fraction(new BigInteger("1"), new BigInteger("1"));
                } else {
                    m.fracciones[i][j] = new Fraction(new BigInteger("0"), new BigInteger("1"));
                }
            }

        }

        m.printMatrix();
        return m;
    }

    public static Matriz getVar(int pos, int tam){
        Matriz var = new Matriz(1,tam);
        if(pos == tam-1){
            //Llenar de 1's
            for(int i = 0; i < tam; i++)
                var.fracciones[0][i] = new Fraction(new BigInteger("1"), new BigInteger("1"));
        } else {
            for(int i = 0; i < tam; i++){
                if(i == pos)
                    var.fracciones[0][i] = new Fraction(new BigInteger("1"), new BigInteger("1"));
                else
                    var.fracciones[0][i] = new Fraction(new BigInteger("0"), new BigInteger("1"));
            }
        }
        return var;
    }

    public void multVector(Fraction f){
        for(int i = 0; i < m; i++){
            for(int j=0; j < n; j++){
                this.fracciones[i][j] = Fraction.multFractions(this.fracciones[i][j],f);
            }
        }
    }

    public void copyRow(int row, Matriz a){
        for(int i = 0; i < a.n; i++){
            this.fracciones[row][i] = a.fracciones[0][i];
        }
    }

    public void getVectorProbabilidad(){
        Matriz cp = this.copiarMatriz();
        Matriz res = new Matriz(cp.m-1,cp.n);

        /*System.out.println();
        System.out.println("Res: "+res.m + "x" + res.n);
        cp.printMatrix();*/
        int filas = res.m, col = res.n;


        if(cp.m == 2 && cp.n == 2){
            for(int j = 0; j < filas; j++){
                Matriz sum = new Matriz(1,col).fillWithZeros();
                //System.out.println("Nueva matriz");
                //sum.printMatrix();
                //System.out.println("Nueva matriz");
                for(int i = 0; i < col; i++){
                    //System.out.println(i + "x" + col);
                    //getVar(i,col).printMatrix();

                    //System.out.println("Fraccion: " + this.fracciones[i][j]);
                    Matriz tmp = getVar(i,col);
                    if(this.fracciones[i][j].toFloat() >= 0 && i == 0 && j == 0){
                        this.fracciones[0][0].num = new BigInteger("-1").multiply(this.fracciones[0][0].num);
                    }
                    tmp.multVector(this.fracciones[i][j]);
                    //tmp.printMatrix();

                    //System.out.println();
                    //tmp.printMatrix();

                    sum.sumarMatriz(tmp);
                    //System.out.println("Suma total");
                    //sum.printMatrix();
                }
                if(cp.m == 2 && cp.n == 2){
                    //System.out.println(" PASA SUMA ");
                    sum.sumarMatriz(getVar(j,col));

                }
                /*System.out.println(" SUMA ");
                sum.printMatrix();*/
                res.copyRow(j,sum);

                //System.out.println("Fin suma");

            }
            //res.printMatrix();
            Fraction r = Fraction.divFractions(res.fracciones[0][1],res.fracciones[0][0]);
            Fraction comp = new Fraction(new BigInteger("1"), new BigInteger("1"));
            comp.restar(r);
            System.out.println("Vector Punto Fijo: (" + r + ", " + comp + ")");
        } else {
            System.out.println("Profe, solo podemos leer matrices de 2x2, lo que se encuentra adentro de la condicion,\n" +
                    "funcionaria, pero no nos dio tiempo de aplicar GaussJordan para matrices mayores a 2. Pero si quiere\n" +
                    "despues se lo explicamos para que entienda la idea, y tengamos mas puntos que lo actual :)");
        }


    }

    

}
