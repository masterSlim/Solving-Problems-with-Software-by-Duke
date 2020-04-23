
/**
 * Write a description of class Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences (String stringa, String stringb){
        int counter = 0;
        int index = 0;
        for (int i = 0; stringb.indexOf(stringa,index) >= 0; i++){
            counter += 1;
            System.out.println(counter+" " + index);
            index = stringb.indexOf(stringa, index)+1;
            
        }
        if(counter >=2){
            return true;
        }
        else{
            return false;
        }
    }
    public void testing(){
        String str1p1 = "aby";
        String str1p2 = "abyabyabyaby";
        System.out.println(twoOccurrences (str1p1, str1p2));
        System.out.println("The part of the string after "+ str1p1 +" in "
        + str1p2 + " is " + lastPart(str1p1, str1p2));
        
        String str2p1 = "acy";
        String str2p2 = "abyacyacyaby";
        System.out.println(twoOccurrences (str2p1, str2p2));
                System.out.println("The part of the string after "+ str2p1 +" in "
        + str2p2 + " is " + lastPart(str2p1, str2p2));
        
        String str3p1 = "azaz";
        String str3p2 = "coconut";
        System.out.println(twoOccurrences (str3p1, str3p2));
                        System.out.println("The part of the string after "+ str3p1 +" in "
        + str3p2 + " is " + lastPart(str3p1, str3p2));
        
        
        
    }
    public String lastPart(String stringa, String stringb){
        int index = stringb.indexOf(stringa);
        if (index >= 0){
        return stringb.substring(index+stringa.length()); 
    }
    else{
        return stringb;
    }
}
}