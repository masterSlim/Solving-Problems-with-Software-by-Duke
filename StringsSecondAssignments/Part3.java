
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
int countGenes(String dna){
    int startIndex= 0;
    int cntr = 0;
    while (true){
        String currentGene = findGene(dna, startIndex);
        if(currentGene.isEmpty()){
            break;
        }
        cntr +=1;
        startIndex = dna.indexOf(currentGene, startIndex)+ currentGene.length();
        System.out.println("Current gene is: \n" + currentGene);
    }
    return cntr;
}
void testCountGenes(){
    String a = "ATG456789TAA345ATG677TGAATGAFGYHRNVATAG";
    System.out.println(a + " " + countGenes(a));
    String b = "DKLSGLSDGLK";
    System.out.println(b + " " + countGenes(b));
    String c = "ATG12312312301TGA0123123123123TGA123TAA";
    System.out.println(c + " " + countGenes(c));
    
}
}
