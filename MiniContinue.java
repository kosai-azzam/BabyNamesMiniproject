import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
/**
 * Write a description of MiniContinue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniContinue {
    
       private int getRank(FileResource excelFile, String name, String gender){
        CSVParser parser = excelFile.getCSVParser(false);
        int tempRank = 0;
        int rank = 0;
        for (CSVRecord record: parser){
            if(record.get(1).equals(gender)){
                tempRank += 1;
                if(record.get(0).equals(name)){
                    rank = tempRank;
                    break;
                }
            }
        }
        if (rank==0){
            rank = -1;
        }
        return rank;
    }

            private double getAverageRank(String name, String gender){
        double sumOfRank = 0.0;
        double numberOfYears = 0.0;
        double averageRank = -1.0;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            numberOfYears += 1.0;
            FileResource fr = new FileResource(f);
            int rank = getRank(fr, name, gender);
            if (rank != -1){
                sumOfRank += (double)rank;
            }            
        }
        if(sumOfRank != 0.0){
            averageRank = sumOfRank/numberOfYears;
        }
        return averageRank;
    }
    
        private int getTotalBirthsRankedHigher(int year, String name, String gender){
        //total number of births of those names with the same gender and same year who 
        //are ranked higher than name
        //get FileName 
        String fileName = "yob"+year+"short.csv";
        FileResource excelFile = new FileResource(fileName);
        int rankOfName = getRank(excelFile, name, gender);
        int tempRank = 0;
        int totalBirths = 0;
        CSVParser parser = excelFile.getCSVParser(false);
        for(CSVRecord currentRow: parser){
            if(currentRow.get(1).equals(gender)){
                tempRank+=1;
                if(tempRank < rankOfName){
                    totalBirths += Integer.parseInt(currentRow.get(2));
                }
            }   
        }
        
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher(){
        
        System.out.println(getTotalBirthsRankedHigher(2012,"Ethan", "M"));
    }
    
    public void testGetAverageRank(){
        System.out.println(getAverageRank("Mason", "M"));
        System.out.println(getAverageRank("Jacob", "M"));
    }
    
}
