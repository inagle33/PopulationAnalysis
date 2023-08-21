/**
 * Use files with population data for different states from years 2010 to 2019 to output county names, 
 * their 2010 population, 2019 population, and the difference in population during that span of time.
 *
 * @author Isaac Nagle
 * @version 11012022
 *
 */
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class DataAnalysisLab {
   
    /**
    * Reads the names of the counties from the file and outputs them in a comma separated list.
    * 
    * @param fname name of file that contains the county names
    * @param num number of counties
    * @param cNames array for names of counties
    * @param first array for 2010 populations 
    * @param second array for 2019 populations 
    */
   public static void fileData(String fname, int num, String[] cNames, int[] first, int[] second) {
        Scanner inFN = null; //create scanner for file
        try {
           File statePop = new File(fname); //opens file
           inFN = new Scanner(statePop); //scans file
           int i = 0; //creates int for index of arrays
           inFN.nextLine(); //skips first line of fine
           while (inFN.hasNext()) { //while loop to assign the arrays with the necessary variables
              String line = inFN.nextLine(); //creates string that holds the next line of the line
              String[] vals = line.split(","); //makes an array with the value of String line
              cNames[i] = vals[0];                 //assigns arrays with values from file
              first[i] = Integer.parseInt(vals[1]); //turns the population numbers from file to integers
              second[i] = Integer.parseInt(vals[vals.length-1]);
                 i++; //updates index
           }
        } catch (FileNotFoundException e) {
           System.out.println("Error!");
        }
   }
   
   /**
    * Output the names of the counties and then using the arrays for the 2010 and 2019 populations,
    * subtract them to find the population difference over that span of time.
    * 
    * @param num number of counties
    * @param cNames array for names of counties
    * @param first array for 2010 populations 
    * @param second array for 2019 populations
    */
   public static void popDifference(int num, String[] cNames, int[] first, int[] second) {
        int[] diff = new int[num]; //creates array for the difference of each county
        for (int i = 0; i < num; i++) { //loop for assigning int[] diff with its values for each county
           diff[i] = second[i] - first[i]; //population difference is the difference between the two arrays
           System.out.println(cNames[i] + " " + diff[i]); //outputs the county name and its population difference
        }
   }
   
   public static void main(String[] args) {
      Scanner scnr = new Scanner(System.in); //create scanner object
      System.out.print("Enter a data file to read: "); //prompt user for a file
      String fileName = scnr.nextLine(); //creates string for file name and assigns it to next input
      System.out.print("Enter the number of counties: "); //prompt user for number of counties
      int numCounties = scnr.nextInt(); //creates int for number of counties and assigns it to next input
      String[] countyNames = new String[numCounties]; //creates array for county names
      int[] firstPop = new int[numCounties];          //creates array for 2010 populations
      int[] secondPop = new int[numCounties];         //creates array for 2019 populations
      fileData(fileName, numCounties, countyNames, firstPop, secondPop); //method call for the file data
      System.out.println("DEBUG: " + Arrays.toString(countyNames)); //outputs contents of arrays
      System.out.println("DEBUG: " + Arrays.toString(firstPop));
      System.out.println("DEBUG: " + Arrays.toString(secondPop));
      popDifference(numCounties, countyNames, firstPop, secondPop); //method call for population difference
      scnr.close(); //close scanner
   }
}
