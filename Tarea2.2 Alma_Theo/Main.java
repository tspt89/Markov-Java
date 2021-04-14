/*
* Tarea 2.2 Markov
*
* Alma Ayaquica A01324264
* Theo Salvador A01328274
*
* */

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tam = 0, k = 0;
        Matriz matriz;

        tam = Integer.parseInt(sc.nextLine());
        matriz = new Matriz(tam, tam);

        matriz.fillMatrix(sc);

        if(!matriz.checkProbability())
            System.exit(-1);

        k = Integer.parseInt(sc.nextLine());

        Matriz res = matriz.copiarMatriz();

        System.out.println("P1");
        res.printMatrix();
        for(int i = 2; i <= k; i++){
            System.out.println("P"+i);
            res.multMatriz(matriz);
            res.printMatrix();
        }


        //x(M-I) = 0
        Matriz id = new Matriz(tam,tam); //Matriz de Identidad
        for(int i = 0; i < tam; i++){
            for(int j=0; j < tam; j++){
                if(i == j){
                    id.fracciones[i][j] = new Fraction(new BigInteger("1"),new BigInteger("1"));
                } else {
                    id.fracciones[i][j] = new Fraction(new BigInteger("0"),new BigInteger("1"));
                }
            }
        }

        //Vector
        Matriz vec = matriz.copiarMatriz();
        vec.getVectorProbabilidad();
    }
}
