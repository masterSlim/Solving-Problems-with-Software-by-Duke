
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;

public class Part2 {
    CSVParser parser(){
    FileResource fr = new FileResource();
    CSVParser parser = fr.getCSVParser();
    return parser;
    }
    
    CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser){
            if (largestSoFar == null){
                largestSoFar = currentRow;
            }else
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if(currentTemp < largestTemp && currentTemp != -9999){
                largestSoFar = currentRow;
                }
            }
        }
        return largestSoFar;
    }
    String fileWithColdestTemperature(){
        CSVRecord coldest = null;
        File coldestFile = null;
        DirectoryResource dr = new DirectoryResource();
    for (File f: dr.selectedFiles()){
        FileResource fr = new FileResource(f);
        CSVRecord rd = coldestHourInFile(fr.getCSVParser());
        if (coldest == null){
        coldest = rd;
        }
        else{
        if(Double.parseDouble(rd.get("TemperatureF")) < Double.parseDouble(coldest.get("TemperatureF"))){
            coldest = rd;
            coldestFile = f;
        }
    }      
    }
    
    return coldestFile.getAbsolutePath();
}
CSVRecord lowestHumidityInFile(CSVParser parser){
    CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser){
            if (largestSoFar == null){
                largestSoFar = currentRow;
            }else
            {if(!currentRow.get("Humidity").equals("N/A")){
                double currentTemp = Double.parseDouble(currentRow.get("Humidity"));
                double largestTemp = Double.parseDouble(largestSoFar.get("Humidity"));
                if(currentTemp < largestTemp && currentTemp != -9999){
                largestSoFar = currentRow;
            }
        }   
    }
        }
        return largestSoFar;
    
}
CSVRecord lowestHumidityInManyFiles(){
    //остановился на пункте 4
    CSVRecord lowestHumidity = null;
        DirectoryResource dr = new DirectoryResource();
    for (File f: dr.selectedFiles()){
        FileResource fr = new FileResource(f);
        CSVRecord rd = lowestHumidityInFile(fr.getCSVParser());
        if (lowestHumidity == null){
        lowestHumidity = rd;
        }
        else{
        if(Double.parseDouble(rd.get("Humidity")) < Double.parseDouble(lowestHumidity.get("Humidity"))){
            lowestHumidity = rd;
        }
    }      
    }
    
    return lowestHumidity;
}
double averageTemperatureInFile(CSVParser parser){
    double sum = 0.0;
    int counter = 0;
    for(CSVRecord rd: parser){
        if(Double.parseDouble(rd.get("TemperatureF") )!= -9999){
        double temp = Double.parseDouble(rd.get("TemperatureF"));
        sum += temp;
        counter +=1;
    }
    }
    return sum/counter;
}
double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
    double sum = 0;
    int counter = 0;
    for (CSVRecord rd : parser){
    if(Integer.parseInt(rd.get("Humidity")) >= value){
    if(Double.parseDouble(rd.get("TemperatureF")) != -9999){
        sum += Double.parseDouble(rd.get("TemperatureF"));
        counter += 1;   
    }
    }
    }
    return sum/counter;
}
void testFileWithColdestTemperature(){
    File coldest = new File (fileWithColdestTemperature());
    System.out.println("Coldest day in the file: " + coldest.getName());
    FileResource fr = new FileResource(coldest);
    System.out.println("Coldest temperature in the file is: " + coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
    System.out.println("All the Temperatures on the coldest day were: ");
    for (CSVRecord record : fr.getCSVParser()){
        System.out.println(record.get("DateUTC"));
    }
}
    void testColdestHourInFile(){
        CSVRecord r = coldestHourInFile(parser());
    System.out.println(r.get("TemperatureF") + " time " + r.get("DateUTC"));
    }
    void testHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);    
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    void testFileWithLowestHumidity(){
    CSVRecord rd = lowestHumidityInManyFiles();
    System.out.println("Lowest humidity was " + rd.get("Humidity") + " at " + rd.get("DateUTC"));
}
void testAverageTemperatureInFile(){
    System.out.println("Average temperature in file is " + averageTemperatureInFile(parser()));
}
void testAverageTemperatureWithHighHumidityInFile(){
System.out.println("Average temp when high Humidity is " + averageTemperatureWithHighHumidityInFile(parser(), 80));
}
}