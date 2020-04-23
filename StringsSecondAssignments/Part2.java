
/**
 * Write a description of class Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    int howMany (String stringA, String stringB){
    int cntr = 0;
    int index = 0;
    while (true){
        int a = stringB.indexOf(stringA, index);
        if(a == -1){
        break;
        }
        cntr += 1;
        index = a + stringA.length();
    }
    return cntr;
}
void testHowMany(){
    String a = "AAA";
    String b = "BAAAAAAAAAAAAAA";
    System.out.println("String A: " + a + " String B: " + b + "\n" + howMany(a,b));
        String c = "AA";
    String d = "IAAAIAAAAIAAAAAIAA";
    System.out.println("String A: " + c + " String B: " + d + "\n" + howMany(c,d));
}
}
