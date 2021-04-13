import javax.swing.*;
import java.util.Scanner;

public class Matriz {
    private Fraction[][] fracciones;
    private int n = 0, m = 0;

    public Matriz(int m, int n){
        fracciones = new Fraction[n][m];
        this.n = n;
        this.m = m;
    }

    public void fillMatrix(Scanner sc){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int num = Integer.parseInt(sc.nextLine());
                int den = Integer.parseInt(sc.nextLine());
                fracciones[i][j] = new Fraction(num,den);
            }
        }
    }

    public Matriz fillWithZeros(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                this.fracciones[i][j] = new Fraction(0,1);
            }
        }
        return this;
    }

    public void printMatrix(){
        int maxLength = 0;
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(maxLength < fracciones[i][j].getLength())
                    maxLength = fracciones[i][j].getLength();

        for(int i = 0; i < n; i++){
            for(int j=0; j < m; j++){
                if(j == 0) System.out.print("| " + fracciones[i][j].toString(maxLength) + " ");
                else if(j == (m-1)) System.out.println(fracciones[i][j].toString(maxLength) + "|");
                else System.out.print(fracciones[i][j].toString(maxLength) + " ");
            }

        }
    }

    public boolean checkProbability(){
        Fraction res;

        for(int i =0; i < n; i++){
            res = new Fraction(0,1);
            for(int j=0; j < m; j++){
                res.sumFractions(fracciones[i][j]);
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
                    res.fracciones[i][j].sumFractions(Fraction.multFractions(this.fracciones[i][k],a.fracciones[k][j]));
                }
            }
        }
        this.fracciones = res.fracciones;
    }

    public Matriz copiarMatriz(){
        int m = this.m, n = this.n;
        Matriz matriz = new Matriz(m, n);

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                matriz.fracciones[i][j] = new Fraction(fracciones[i][j].num,fracciones[i][j].den);
            }
        }
        return matriz;
    }

}
