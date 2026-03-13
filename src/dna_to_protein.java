/**
 * Program name: dna_to_protein
 * Author: Rinoelle Wisco
 * Date: March 9th, 2024
 * Compiler: JDK 24.0.1
 * */

import java.io.*;
import java.util.Scanner;
import java.util.*;

public class dna_to_protein {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome user! ");
        System.out.println("I am a program that can take a random DNA sequence and can transcribe and translate the sequence into a " +
                "their respective text file.\n");


        dna_seq_gen.getSeq();

         //Initialize the array with words from the input file
        char [] sequence = temp_Initialization("dna.txt");


        String [] transcription = dna_convertTo_mRNA(sequence);
        String [] triplet = mRNA_tripletConversion(transcription);
        String [] translation = mRNA_convertTo_Protein(triplet);

        printFileMRNA(triplet);
        printFileProtein(translation);

    }

    public static void printFileMRNA(String [] str) throws IOException {

        PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("mrna.txt", true)));;
        int index = 0;
        for (String s : str) {
            output.print(s + " ");

            if(index == str.length - 1) {
                output.println();
            }

            index++;
        }

        output.close();


    }

    public static void printFileProtein(String [] str) throws IOException {
        PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("protein.txt", true)));
        int index = 0;
        for (String s : str) {

            if(index < str.length - 1) {
                output.print(s + "-");
            } else if (index == str.length - 1){
                output.println();
            }else {
                output.print(s + " ");
            }
            index++;
        }
        output.close();
    }

    public static String [] charToString (char [] arr) {

        String [] new_arr = new String [arr.length];

        for (int i = 0; i < arr.length; i++) {

            char tempChar = arr[i];
            String tempStr = tempChar + "";

            new_arr[i] = tempStr;
        }

        return new_arr;
    }

    /**
     * Prompts the user for the output file name.
     *
     * @param input the scanner object to read user input
     * @return the output file name
     */
    public static String getOutputFileName(Scanner input) {
        System.out.print("Enter an output file name: ");
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

    public static char [] temp_Initialization (String fileName) throws FileNotFoundException {
        File myFile = new File(fileName);
        Scanner myInput = new Scanner(myFile);

        String temp = " ";

        while (myInput.hasNext()) {
            temp = myInput.nextLine();
        }

        myInput.close();

        char [] dna_seq = new char [temp.length()];

        int i = 0;
        for (char c : temp.toCharArray()) {
            dna_seq[i] = c;
            i++;
        }
        return dna_seq;
    }

    public static String[] dna_convertTo_mRNA (char [] arr) {

        String [] str = charToString(arr);

        String [] mRNA = new String [arr.length];

        for (int i = 0; i < arr.length; i++) {

            String temp = str[i];

            switch (temp) {
                case "A" -> mRNA[i] = "U";
                case "T" -> mRNA[i] = "A";
                case "C" -> mRNA[i] = "G";
                case "G" -> mRNA[i] = "C";
            }
        }

        return  mRNA;
    }

    /**
     *
     * @param arr
     * @return
     */
    public static String [] mRNA_tripletConversion (String [] arr) {


        int new_len = (arr.length)/(3);
        String [] mRNA_triplet = new String [new_len];
        int firstNuc = 0;
        int thirdNuc = 2;
        for (int i = 0; i < new_len; i++) {

            String codon = "";

            for (int j = firstNuc; j <= thirdNuc;j++) {

                String temp = arr[j];

                codon += temp;
            }

            mRNA_triplet[i] = codon;

            firstNuc = firstNuc + 3;
            thirdNuc = thirdNuc + 3;

        }

        return  mRNA_triplet;

    }

    /**
     *
     * @param arr
     * @return
     */
    public static String[] mRNA_convertTo_Protein (String [] arr) {

        String [] protein = new String [arr.length];

        HashMap <String, String> codonChart = getCodonChart();

        for (int i = 0; i < arr.length; i++) {

            String temp = arr[i];

            if(getSTOP(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            }else if (getMET(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            }else if (getGLY(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            }else if (getALA(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            }else if (getSER(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            }else if (getTHR(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            }else if (getVAL(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            }else if (getPRO(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getLEU(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getARG(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getPHE(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getTYR(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getCYS(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getTRP(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getHIS(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getGIN(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getASN(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getLYS(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getASP(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            } else if (getGLU(arr[i])) {
                protein[i] = codonChart.get(arr[i]);
            }   else if (getILE(arr[i])) {
                    protein[i] = codonChart.get(arr[i]);
            }

        }

        return  protein ;
    }

    public static boolean getSTOP(String str) {
        return str.equals("UAA") || str.equals("UAG") || str.equals("UGA");
    }

    public static boolean getMET(String str) {
        return str.equals("AUG");
    }

    public static boolean getTRP (String str) {
        return str.equals("UGG");
    }

    public static boolean getGLY(String str) {
        return str.equals("GGU") || str.equals("GGC") || str.equals("GGA") || str.equals("GGG");
    }

    public static boolean getSER(String str) {
        return str.equals("UCU") || str.equals("UCC") || str.equals("UCA") || str.equals("UCG") || str.equals("AGU") || str.equals("AGC");
    }

    public static boolean getALA(String str) {
        return str.equals("GCU") || str.equals("GCC") || str.equals("GCA") || str.equals("GCG");
    }

    public static boolean getTHR(String str) {
        return str.equals("ACU") || str.equals("ACC") || str.equals("ACA") || str.equals("ACG");
    }

    public static boolean getVAL(String str) {
        return str.equals("GUU") || str.equals("GUC") || str.equals("GUA") || str.equals("GUG");
    }

    public static boolean getLEU(String str) {
        return str.equals("CUU") || str.equals("CUC") || str.equals("CUA") || str.equals("CUG") || str.equals("UUA")|| str.equals("UUG");
    }

    public static boolean getPRO(String str) {
        return str.equals("CCU") || str.equals("CCC") || str.equals("CCA") || str.equals("CCG");
    }

    public static boolean getARG(String str) {
        return str.equals("CGU") || str.equals("CGC") || str.equals("CGA") || str.equals("CGG") || str.equals("AGA") || str.equals("AGG");
    }

    public static boolean getPHE(String str) {
        return str.equals("UUU") || str.equals("UUC");
    }

    public static boolean getTYR(String str) {
        return str.equals("UAU") || str.equals("UAC");
    }

    public static boolean getCYS (String str) {
        return str.equals("UGU") || str.equals("UGC");
    }

    public static boolean getHIS (String str) {
        return str.equals("CAU") || str.equals("CAC");
    }

    public static boolean getGIN (String str) {
        return str.equals("CAA") || str.equals("CAG");
    }

    public static boolean getASN (String str) {
        return str.equals("AAU") || str.equals("AAC");
    }

    public static boolean getLYS (String str) {
        return str.equals("AAA") || str.equals("AAG");
    }

    public static boolean getASP (String str) {
        return str.equals("GAU") || str.equals("GAC");
    }

    public static boolean getGLU (String str) {
        return str.equals("GAA") || str.equals("GAG");
    }

    public static boolean getILE (String str) {
        return str.equals("AUU") || str.equals("AUC")|| str.equals("AUA");
    }




}