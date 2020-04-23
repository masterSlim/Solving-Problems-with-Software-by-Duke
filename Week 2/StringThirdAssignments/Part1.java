
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import  edu.duke.*;

public class Part1 {
    int findStopCodon (String dna, int startIndex, String stopCodon){
        int stopIndex=dna.indexOf(stopCodon, startIndex);
        int currentStart = startIndex;
        while((stopIndex != -1) && (stopIndex - startIndex)%3 !=0){
            stopIndex = dna.indexOf(stopCodon, currentStart); 
            currentStart = stopIndex+1;            
        }
        if (stopIndex == -1){
        return dna.length();
        }
        return stopIndex;
    }
    
    void testFindStopCodon(){
        System.out.println(findStopCodon("TAA3456789TAA12", 3, "TAA"));
        System.out.println(findStopCodon("12345678", 3, "TAA"));
        System.out.println(findStopCodon("AABCTAG7TAAG", 3, "TAA"));
        
    }
    String findGene(String dna, int startIndex){
        String gene = "";
        int startCodonIndex = dna.indexOf("ATG", startIndex);
        if (startCodonIndex != -1){
        int stopTAA = findStopCodon(dna, startCodonIndex, "TAA");        
        int stopTAG = findStopCodon(dna, startCodonIndex, "TAG");  
        int stopTGA = findStopCodon(dna, startCodonIndex, "TGA");  
        int temp = Math.min(stopTAA, stopTAG);
        temp = Math.min(temp, stopTGA);
        if(temp !=-1 && temp != dna.length()){        
            gene = dna.substring(startCodonIndex, temp+3);
        }            
        }else{
            return gene; 
        }
    return gene;
    }
    
    void testFindGene(){
    String noAtg = "NAJJDSDJGBNTAA";
    String atgStop = "012ATG678901TAA";
    String atgMultStop = "01ATG5678TGA23TAA8901234";
    String atgFirstStop = "0123TAA789ATG345";
    String atgNoStop = "01234ATG890123";
    System.out.println("Gene is: " + noAtg + " "+ findGene(noAtg, 0));
    System.out.println("Gene is: " + atgStop + " " + findGene(atgStop, 0));
    System.out.println("Gene is: " + atgMultStop + " " + findGene(atgMultStop, 0));
    System.out.println("Gene is: " + atgFirstStop + " " + findGene(atgFirstStop, 0));
    System.out.println("Gene is: " + atgNoStop + " " + findGene(atgNoStop, 0));
    }
    
    void printAllGenes(String dna){
       int startIndex = 0;
       while (true){
           String currentGene = findGene(dna, startIndex);
           if (currentGene.isEmpty()){
               break;
           }
           System.out.println(currentGene);
           startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
           
    }
}
StorageResource getAllGenes(String dna){
        StorageResource genes = new StorageResource();
       int startIndex = 0;
       while (true){
           String currentGene = findGene(dna, startIndex);
           if (currentGene.isEmpty()){
               break;
           }
           genes.add(currentGene);
           System.out.println("finded gene: " + currentGene);
           startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
           
    }
    return genes;
}
    float cgRatio (String dna){
    int counter = 0;
    for(char chr : dna.toCharArray()){
    if(chr =='C' || chr =='G'){
        counter +=1;
    }
    }
    return (float) counter/dna.length();
}
    int countCTG(String dna){
        int indexCTG = dna.indexOf("CTG", 0);
        int counter = 0;
        while(indexCTG != -1){
            indexCTG = dna.indexOf("CTG", indexCTG+3); 
            counter +=1;            
        }
        return counter;
}
   void processGene(StorageResource sr){
       int counterSixteen = 0;
       int counterCG = 0;
       String longest = "";
       for(String s : sr.data()){           
           if(s.length() > 60){
               System.out.println("Longer than 60: " + s);
               counterSixteen +=1;
            }
           if (cgRatio(s) > 0.35){
            System.out.println("CG ratio is higer than 0.35: " + s);
            counterCG +=1;
           }
           if (s.length()>longest.length()){
            longest = s;
            }
        }
            System.out.println("Longer than sixteen: " + counterSixteen);
            System.out.println(counterCG);
            System.out.println(longest.length());
}
void testProcessGene(){
    StorageResource sr = new StorageResource();
    sr.add("ATGATATATATATAACTG");
    sr.add("ATGGTGTGTGTGTAAATGCTCTCTCTCATAACCCCCTG");
    sr.add("ATATATATATAACTG");
    System.out.println("First test \n");
    processGene(sr);
    FileResource fr = new FileResource();
    String dna = fr.asString().toUpperCase();
    StorageResource srr = getAllGenes(dna);
    System.out.println("Number of genes: " + srr.size());
    processGene(getAllGenes(dna));
}
    void testCountCTG(){
        FileResource fr = new FileResource();
       String dna = fr.asString().toUpperCase();
    System.out.println(countCTG(dna));
}
   void testCgRatio(){
        System.out.println(cgRatio("CGGGCAAAAA"));
   }
    void testGetAllGenes(){
        for(String a : getAllGenes("01ATG5678TGA23TAA8901234").data()){
            System.out.println(a);
        }
   }
}

