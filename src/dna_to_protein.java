/**
 * Program name: dna_to_protein
 * Author: Rinoelle Wisco
 * Date: March 9th, 2024
 * Compiler: JDK 24.0.1
 * */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class dna_to_protein {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome user!");
        String myInput = getInputFileName(scanner); // calls method to get user input of file name for their input


        HashMap <String, String> codonChart = getCodonChart();

        // Initialize the array with words from the input file
        String[] wordArr = array_Initialization(myInput);


    }

    /**
     * Prompts the user for the input file name
     *
     * @param input the scanner object to read user input
     * @return the input file name
     */
    public static String getInputFileName(Scanner input) {
        System.out.print("Enter an input file of your given DNA sequence you want to converge: ");
        return input.nextLine();
    }

    /**
     *
     * @param u
     * @param c
     * @param a
     * @param g
     * @return
     */
    public static HashMap<String, String> mergeCodonChart(HashMap<String, String> u, HashMap<String, String> c, HashMap<String, String> a, HashMap<String, String> g) {

        HashMap <String, String> combinedCodonChart = new HashMap<>(u);

        combinedCodonChart.putAll(c);
        combinedCodonChart.putAll(a);
        combinedCodonChart.putAll(g);


        return combinedCodonChart;
    }

    /**
     *
     * @return
     */
    public static HashMap<String, String> uGetCodonChart() {

        HashMap <String, String> uCodonChart = new HashMap<>();

        uCodonChart.put("UUU", "Phe");
        uCodonChart.put("UUC", "Phe");
        uCodonChart.put("UUA", "Leu");
        uCodonChart.put("UUG", "Leu");
        uCodonChart.put("UCU", "Ser");
        uCodonChart.put("UCC", "Ser");
        uCodonChart.put("UCA", "Ser");
        uCodonChart.put("UCG", "Ser");
        uCodonChart.put("UAU", "Tyr");
        uCodonChart.put("UAC", "Tyr");
        uCodonChart.put("UAA", "STOP");
        uCodonChart.put("UAG", "STOP");
        uCodonChart.put("UGU", "Cys");
        uCodonChart.put("UGC", "Cys");
        uCodonChart.put("UGA", "STOP");
        uCodonChart.put("UGG", "Trp");


        return uCodonChart;
    }

    /**
     *
     * @return
     */
    public static HashMap<String, String> cGetCodonChart() {

        HashMap <String, String> cCodonChart = new HashMap<>();

        cCodonChart.put("CUU", "Leu");
        cCodonChart.put("CUC", "Leu");
        cCodonChart.put("CUA", "Leu");
        cCodonChart.put("CUG", "Leu");
        cCodonChart.put("CCU", "Pro");
        cCodonChart.put("CCC", "Pro");
        cCodonChart.put("CCA", "Pro");
        cCodonChart.put("CCG", "Pro");
        cCodonChart.put("CAU", "His");
        cCodonChart.put("CAC", "His");
        cCodonChart.put("CAA", "Gin");
        cCodonChart.put("CAG", "Gin");
        cCodonChart.put("CGU", "Arg");
        cCodonChart.put("CGA", "Arg");
        cCodonChart.put("CGC", "Arg");
        cCodonChart.put("CGG", "Arg");


        return cCodonChart;
    }

    /**
     *
     * @return
     */
    public static HashMap<String, String> aGetCodonChart() {

        HashMap <String, String> aCodonChart = new HashMap<>();

        aCodonChart.put("AUU", "Ile");
        aCodonChart.put("AUA", "Ile");
        aCodonChart.put("AUC", "Ile");
        aCodonChart.put("AUG", "Met");
        aCodonChart.put("ACU", "Thr");
        aCodonChart.put("ACC", "Thr");
        aCodonChart.put("ACA", "Thr");
        aCodonChart.put("ACG", "Thr");
        aCodonChart.put("AAU", "Asn");
        aCodonChart.put("AAC", "Asn");
        aCodonChart.put("AAA", "Lys");
        aCodonChart.put("AAG", "Lys");
        aCodonChart.put("AGU", "Ser");
        aCodonChart.put("AGC", "Ser");
        aCodonChart.put("AGA", "Arg");
        aCodonChart.put("AGG", "Arg");



        return aCodonChart;
    }

    /**
     *
     * @return
     */
    public static HashMap<String, String> gGetCodonChart() {

        HashMap <String, String> gCodonChart = new HashMap<>();

        gCodonChart.put("GUU", "Val");
        gCodonChart.put("GUC", "Val");
        gCodonChart.put("GUA", "Val");
        gCodonChart.put("GUG", "Val");
        gCodonChart.put("GCU", "Ala");
        gCodonChart.put("GCC", "Ala");
        gCodonChart.put("GCA", "Ala");
        gCodonChart.put("GCG", "Ala");
        gCodonChart.put("GAU", "Asp");
        gCodonChart.put("GAC", "Asp");
        gCodonChart.put("GAA", "Glu");
        gCodonChart.put("GAG", "Glu");
        gCodonChart.put("GGU", "Gly");
        gCodonChart.put("GGC", "Gly");
        gCodonChart.put("GGA", "Gly");
        gCodonChart.put("GGG", "Gly");



        return gCodonChart;
    }

    /**
     * Builds a codon chart inside a Hash Map that gives a key and value ("CUU", "Leu")
     *
     * @return the codon chart to translate mRNA
     */
    public static HashMap<String, String> getCodonChart() {

        HashMap <String, String> uCodonChart = uGetCodonChart();
        HashMap <String, String> cCodonChart = cGetCodonChart();
        HashMap <String, String> aCodonChart = aGetCodonChart();
        HashMap <String, String> gCodonChart = gGetCodonChart();


        return mergeCodonChart(uCodonChart,cCodonChart, aCodonChart, gCodonChart);
    }

    /**
     * Initializes the array with words from the input file.
     *
     * @param fileName the name of the input file
     * @return an array of words from the file
     */
    public static String[] array_Initialization(String fileName) throws FileNotFoundException {
        int count = 0;

        // Open the file for reading
        File myFile = new File(fileName);
        Scanner myInput = new Scanner(myFile);

        // Count the number of words in the file
        while (myInput.hasNext()) {
            myInput.next();
            count++;
        }

        // Create an array of the appropriate size to store the words
        String[] wordArr = new String[count];
        myInput.close();

        // Fill the array with words from the file
        return array_Fill(wordArr, myFile);
    }

    /**
     * Fills the word array with words from the input file
     *
     * @param wordArr an array
     * @param myFile  the input file containing the words
     * @return the filled array
     */
    public static String[] array_Fill(String[] wordArr, File myFile) throws FileNotFoundException {
        Scanner myInput = new Scanner(myFile);

        // Fill the array with words from the file
        int i = 0;
        while (myInput.hasNext()) {
            wordArr[i] = myInput.next();
            i++;
        }
        myInput.close();

        return wordArr;
    }

}