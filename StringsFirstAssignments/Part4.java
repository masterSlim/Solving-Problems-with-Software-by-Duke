
/**
 * Write a description of class Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.net.*;
import edu.duke.*;
import java.io.*;

public class Part4 {
    public void parceURL(){
        URLResource link = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String line : link.lines()){
            String line1 = line.toLowerCase();
            int yt = line1.indexOf("youtube.com");
            if(yt >= 0){
                int q1 = line.lastIndexOf("\"", yt);
                int q2 = line.indexOf("\"", q1+1); 
                System.out.println(line.substring(q1+1,q2));
            }
                //для обработки нужна копия ссылки в нижнем регистре, 
                //но после получения индекса нужно выводить оригинал
        }
    }    
}
