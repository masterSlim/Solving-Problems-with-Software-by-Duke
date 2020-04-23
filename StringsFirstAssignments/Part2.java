
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon){
        String gene = "no changes in gene";
        int startIndex = dna.indexOf(startCodon);
        if(startIndex == -1){
        return "no start index";
        }
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        if(stopIndex == -1){
            return "no stop index";
        }
        if((stopIndex-(startIndex+3))%3 == 0){
        gene = dna.substring(startIndex, stopIndex+3);
        }
        return gene;
    }
    public void testSimpleGene(){
        String ex1 = "ATTTATTGTGATGTAGTGGTAGTTAGATTAAGTA";
        System.out.println("DNA is: " + ex1);
        System.out.println("Gene is: " + findSimpleGene(ex1, "ATG", "TAA"));
        
        String ex2 = "AATGGTAGTAGTAA";
        System.out.println("DNA is: " + ex2);
        System.out.println("Gene is: " + findSimpleGene(ex2, "ATG", "TAA"));
        
        String ex3 = "atgagtattgtataa";
        System.out.println("DNA is: " + ex3);
        System.out.println("Gene is: " + findSimpleGene(ex3, "atg", "taa").toLowerCase());

        String ex4 = "AATATAGTAGTAA";
        System.out.println("DNA is: " + ex4);
        System.out.println("Gene is: " + findSimpleGene(ex4, "ATG", "TAA"));
                
        String ex5 = "AATAGGTAAGTAGTAA";
        System.out.println("DNA is: " + ex5);
        System.out.println("Gene is: " + findSimpleGene(ex5, "ATG", "TAA"));
    }

}
