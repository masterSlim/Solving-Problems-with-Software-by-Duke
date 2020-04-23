import edu.duke.*;
import org.apache.commons.csv.*;

public class Part1 {
    public static void main(String[] args) {
        Part1 p1 = new Part1();
        p1.testBigExporters();
        p1.testCountryInfo();
        p1.testListExTwPr();
        p1.testNumbOfExprtrs();
    }

    CSVParser tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        return parser;
    }

    String countryInfo(CSVParser parser, String country) {
        String exportItem1 = "";
        String exportItem2 = "";
        for (CSVRecord record : parser) {
            String getCountry = record.get("Country");
            if (getCountry.toUpperCase().equals(country.toUpperCase())) {
                System.out.println(record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)"));
                return getCountry;
            }
        }
        return "NOT FOUND";
    }

    void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem1) && record.get("Exports").contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }

    int numberOfExporters(CSVParser parser, String exportItem) {
        int counter = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)) {
                counter += 1;
            }
        }
        return counter;
    }

    void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > amount.length()) {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }

    void testBigExporters() {
        bigExporters(tester(), "$999,999,999,999");
    }

    void testCountryInfo() {
        System.out.println(countryInfo(tester(), "Nauru"));
    }

    void testListExTwPr() {
        listExportersTwoProducts(tester(), "cotton", "flowers");
    }

    void testNumbOfExprtrs() {
        System.out.println(numberOfExporters(tester(), "cocoa"));
    }
}
