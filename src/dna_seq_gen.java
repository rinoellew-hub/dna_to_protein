/**
 * Program name: dna_to_protein
 * Author: Rinoelle Wisco
 * Date: March 10th, 2024
 * Compiler: JDK 24.0.1
 * */
import java.util.Scanner;
import java.util.*;
import java.util.Random;

public class dna_seq_gen {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome user!");
        System.out.print("Please enter the length that you want your DNA sequence: ");


        int dna_len = scanner.nextInt();

        String [] arr = dnaSeq(dna_len);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }

    }

    public static String [] dnaSeq (int length) {

        Random rand = new Random();
        String [] arr = new String [length];


       for (int i = 0; i < arr.length; i++) {

           int r_num = rand.nextInt(100 - 1 + 1);

           if (r_num <= 25) {
               arr[i] = "A";
           } else if (r_num <= 50) {
               arr[i] = "T";
           } else if (r_num <= 75) {
               arr[i] = "C";
           } else {
               arr[i] = "G";
           }

       }
        return arr;
    }


}
