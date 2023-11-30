package Use_Case;
import java.util.*;
////import java.io.*;
//package com.DataFlair.FileOpening;
//import java.awt.Desktop;
//import java.io.*;
//import org.apache.commons.text.StringEscapeUtils;
public class Writer {

    public Writer(){
        
    }


















    /* 

        Scanner scan = new Scanner(System.in);
        String species;
        String MSP;

        // Enter username and press Enter
        System.out.println("Enter species abbreviation");
        species = scan.nextLine();
        System.out.println("Enter MSP name");
        MSP = scan.nextLine();
        try {
            File tempFile = new File(species + ".txt");
            if (tempFile.createNewFile()) {
                System.out.println("File created: " + tempFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(species + ".txt");
            myWriter.write("sed -n '/" + MSP + "/,/>/p' " +
                    "~/MSP_Files/protein_seq_MSP_only_final_CLATE_unannotated.fa | head -n-1 | tblastn -query - " +
                    "-subject ~/MSP_Files/Caenorhabditis_Genome_Sequences/" + species + ".fa -evalue .01 -outfmt 6 |" +
                    " awk '$3 >= 70' > blast_results_" + species);
            myWriter.write("\n\n");
            myWriter.write("awk '$9 < $10' blast_results_" + species + " | awk -v OFS=\"" + "\\" + "t\"" + " '{print $2,$9,$10}' > blast_hit_coordinates_" + species);
            myWriter.write("\n");
            myWriter.write("awk '$9 > $10' blast_results_" + species + " | awk -v OFS=\"" + "\\" + "t\"" + " '{print $2,$10,$9}' >> blast_hit_coordinates_" + species);
            myWriter.write("\n\n");
            myWriter.write("awk '$3 == \"exon\"' ~/MSP_Files/Caenorhabditis_Gene_Annotations/" + species + ".gff3 | bedtools intersect -a blast_hit_coordinates_" + species +  " -b - -v");
            myWriter.write("\n\n");
            myWriter.write("sed -n '/" + MSP + "/,/>/p' ~/MSP_Files/protein_seq_MSP_only_final_CLATE_unannotated.fa | head -n-1 | tblastn -query - -subject ~/MSP_Files/Caenorhabditis_Genome_Sequences/" + species + ".fa | less > blast_check_" + species);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        /* 
        try {
            File tempFileTwo = new File(species + ".sh");
            if (tempFileTwo.createNewFile()) {
                System.out.println("File created: " + tempFileTwo.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriterTwo = new FileWriter(species + ".sh");
            myWriterTwo.write("#!/bin/bash");
            myWriterTwo.write("\n\n");
            myWriterTwo.write("while IFS= read -r line; do");
            myWriterTwo.write("\n");
            myWriterTwo.write("\teval \"$line\"");
            myWriterTwo.write("\n");
            myWriterTwo.write("done < " + species + ".txt");
            //myWriter.write("\n\n");
            //myWriter.write("sed -n '/" + MSP + "/,/>/p' ~/MSP_Files/protein_seq_MSP_only_final_CLATE_unannotated.fa | head -n-1 | tblastn -query - -subject ~/MSP_Files/Caenorhabditis_Genome_Sequences/" + species + ".fa | less > blast_check_" + species);
            myWriterTwo.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        
        try
        {
            String fileName = species + ".txt";
            File file_open = new File(fileName);
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("Desktop Support Not Present in the system.");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file_open.exists())
                desktop.open(file_open);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        } 
        
    } */
}