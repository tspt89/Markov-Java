/*
* Tarea 2.2 Markov
*
* Alma Ayaquica A01324264
* Theo Salvador A01328274
*
* */

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

        for(int i = 1; i <= k; i++){
            System.out.println("P"+i);
            res.multMatriz(matriz);
            res.printMatrix();
        }
        
    }
}
