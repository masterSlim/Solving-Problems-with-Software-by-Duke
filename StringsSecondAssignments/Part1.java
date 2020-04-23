
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
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
}
