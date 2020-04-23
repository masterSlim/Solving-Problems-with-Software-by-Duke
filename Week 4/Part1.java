
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Part1 {
    void totalBirths (FileResource fr){
        int boys = 0;
        int girls = 0;
        int total = 0;
    for(CSVRecord rd : fr.getCSVParser(false)){
        if(rd.get(1).equals("M")){
        boys += 1;        
        }else{
        girls +=1;   
        }
        total+=1;    
    }
    System.out.println("Boys: " + boys + "\nGirls: " + girls + "\nTotal: " + total);
    }
    int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fr = new FileResource();
        for (CSVRecord rd : fr.getCSVParser(false)){            
            if(rd.get(1).equals(gender)) {
                                rank+=1;
                                if (rd.get(0).equals(name)){
                                    System.out.println("Rank: " + rank);
                                    return rank;                                
                                }
            }
        }
        return -1;
    }
    String getName(int year, int rank, String gender){
       int counter = 1;
       FileResource fr = new FileResource();
       for (CSVRecord rd : fr.getCSVParser(false)){
           if(rank == -1){
                return "NO NAME";
            }
            if(rd.get(1).equals(gender)){
                    if(counter < rank){
                        counter+=1;
                    }else{
                        System.out.println("Counter: " + counter);
                        return rd.get(0);                        
                    }
            }        
       }
       return "NO NAME";
    }
    
    void whatIsNameInYear(String name, int year, int newYear, String gender){
    int rank = getRank(year, name, gender);
    String newName = getName(newYear, rank, gender);
        if(gender.equals("F")){
            System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
        }else{
            System.out.println(name + " born in " + year + " would be " + newName + " if he was born in " + newYear);    
        }
    }
    
    int yearOfHigestRank(String name, String gender){
        int higest = 0;
        String year = "-1";
        DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()){
        FileResource fr = new FileResource(f);  
        int rank = 0;
        int tempRank = 0;
        boolean trigger = false;
        for (CSVRecord rd : fr.getCSVParser(false)){            
            if(rd.get(1).equals(gender)) {
                                tempRank+=1;
                                if (rd.get(0).equals(name)){
                                rank = tempRank;
                                trigger = true;
                                }
            }
        }  
        if(higest == 0 && rank != 0){
        higest = rank;
        year = f.getName().substring(3,7);
        }
        if(rank < higest && trigger == true){
        higest= rank;
        year = f.getName().substring(3, 7);
        }        
    }
    return Integer.parseInt(year);
    }    
    double getAverageRank(String name, String gender){
    int counter = 0;
    double sum = 0.0;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()){
        FileResource fr = new FileResource(f);  
        int rank = 0;
        int tempRank = 0;
        for (CSVRecord rd : fr.getCSVParser(false)){            
            if(rd.get(1).equals(gender)) {
                                tempRank+=1;
                                if (rd.get(0).equals(name)){
                                rank = tempRank;
                                }
            }
        }
        counter +=1;
        sum +=rank;
    }
    if(sum == 0.0){
    return -1;
    }else{
    return sum/counter;
}
}
int getTotalBirthsRankedHigher(int year, String name, String gender){
    FileResource fr = new FileResource();
    int counter = 0;
    int rank = 0;
    int sum = 0;
    for (CSVRecord rd : fr.getCSVParser(false)){
        if(rd.get(1).equals(gender)){             
            if(rd.get(0).equals(name)){  
                return sum;
            }else{
                sum += Integer.parseInt(rd.get(2));                
            }
            
}
    }
    /*counter = 0;
        for (CSVRecord rd : fr.getCSVParser(false)){
            if(rd.get(1).equals(gender)){        
                if(counter != rank){
                    sum += Integer.parseInt(rd.get(2));
                    System.out.println("+");
                }
                counter +=1;
            }
        }*/
        return sum;
    }      
void testGetTotalBirthsRankedHigher(){
    System.out.println("Emily " + getTotalBirthsRankedHigher(1990, "Emily", "F"));
}
void testGetAverageRank(){
System.out.println("Susan " + getAverageRank("Susan", "F"));
}

    void testYearOfHigestRank(){
        System.out.println("Genevieve " + yearOfHigestRank("Genevieve", "F"));
        System.out.println("Mich " + yearOfHigestRank("Mich", "M"));
    }
    
    void testWhatIsNameInYear(){
    whatIsNameInYear("Susan", 1974, 2014, "F");
}
    
    void testGetName(){
        System.out.println(getName(2012, 350, "F"));
    }
    
    void testGetRank(){
        int rank = getRank(1971, "Emily", "F");
    System.out.println("Emily: " + rank);
    }
        void testTotalBirths (){
        FileResource fr = new FileResource();
    totalBirths(fr);
    }
}
