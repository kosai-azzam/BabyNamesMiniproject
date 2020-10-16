import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;
/**
 * Write a description of MiniP1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MiniP1 {
    
    public void totalNames (FileResource fr) {
     
        int total = 0;
        int totalB = 0;
        int totalG = 0;
        
        int totalN = 0;
        int totalBN = 0;
        int totalGN = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            total += numBorn;
            totalN ++;
            if (rec.get(1).equals("M")) {
             
                totalB += numBorn;
                totalBN ++;
            }
            else {
             
                totalG += numBorn;
                totalGN ++;
            }
        }
        System.out.println("Total Births = " + total); 
        System.out.println("Total Girl Births = " + totalG); 
        System.out.println("Total Boy Births = " + totalB); 
        System.out.println("Total Names = " + totalN); 
        System.out.println("Total Girl Names = " + totalGN); 
        System.out.println("Total Boy Names = " + totalBN); 
        
    }
    
    public int getRank (int year, String name, String gender) {
        int tempRank = 0;
        int rank = 0;
        String fileName = "yob"+year+".csv";
        //long rank = 0;
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
            
                if (rec.get(1).equals(gender)) {
                tempRank += 1 ;
                if (rec.get(0).equals(name)) {
                rank = tempRank;
                break;
             //rank = parser.getCurrentLineNumber();
            }
          }        
        }
        
        if (rank == 0) {
         
            rank = -1;
        }
        return rank;
    }
    
    public String getName (int year, int rank, String gender) {
        String fileName= "yob"+year+".csv"; 
        String name = "NO NAME";
        int currRank = 0;
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord rec : parser) {
         
            if (rec.get(1).equals(gender)) {
            
                currRank +=1;
                
                if (currRank == rank) {
                 
                    name = rec.get(0);
                    break;
                }
            }
        }
        return name; 
    }
    
    public void whatIsNameInYear (String name, int year, int newyear, String gender) {
        
        int rank = getRank(year, name, gender);
        String newName = getName(newyear, rank, gender);
        System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newyear);
    }
    
        public int yearOfHighestRank(String name, String gender){
        int currentMaxRankYear = -1;
        int currentMaxRank = -1;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            String currentYearS = f.getName().substring(3,7);
            int currentYear = Integer.parseInt(currentYearS);
            int tempRank = 0;
            int rank = -1;
            for (CSVRecord record: parser){
                if(record.get(1).equals(gender)){
                    tempRank += 1;
                    if(record.get(0).equals(name)){
                        rank = tempRank;
                        break;
                    }
                }
            }
            if (currentMaxRank == -1 && rank != -1){
                currentMaxRank = rank;
                currentMaxRankYear = currentYear;
            } else if(rank<currentMaxRank && rank != -1){
                    currentMaxRank = rank;
                    currentMaxRankYear = currentYear;
            }    
        }
        return currentMaxRankYear;
    }
    

    public void testYearOfHighestRank(){
        System.out.println(yearOfHighestRank("Sophia", "F"));
        System.out.println("---------------");
        System.out.println(yearOfHighestRank("Owen", "M"));
    }
    
    public void testGetName() {
    
        System.out.println("For RankF 5, Name is " + getName(1982, 450, "M"));
        //System.out.println("For RankM 3, Name is " + getName(2012, 3, "M"));
        //System.out.println("For RankM 5, Name is " + getName(2012, 5, "M"));
        //System.out.println("For RankM 1, Name is " + getName(2012, 1, "M"));
    }
    
    public void testTotalNames() {
     
        FileResource fr = new FileResource("C:/Users/LunarArcane/Desktop/Java Projects/Baby names/yob1905.csv");
        totalNames(fr);
    }
    
    public void testGetRank () {
     
        System.out.println("Rank is " + getRank(1960, "Emily", "F"));
        //System.out.println("Rank is " + getRank(2012, "Olivia", "F"));
       // System.out.println("Rank is " + getRank(2012, "qusai", "M"));
       // System.out.println("Rank is " + getRank(2012, "Noah", "M"));
        //System.out.println("Rank is " + getRank(2012, "William", "M"));
        
    }
    
     public int getRank1(int year,String name,String gender){
        FileResource fr = new FileResource("C:/Users/LunarArcane/Desktop/Java Projects/Baby names/us_babynames_test/yob2012short.csv");
        CSVParser parser = fr.getCSVParser(false);
        int rank = 1;
        for (CSVRecord record: parser){
            System.out.println(record.get(0));
            if(gender.equals("F")){
                if(!record.get(0).equals(name)){
                    rank = rank + 1;   
                    
                   }
                   
                }           
            else{
                if(record.get(1).equals("M")){
                    if(!record.get(0).equals(name)){
                        rank = rank + 1;
                        
                    }
                }
                
            }
            
        }
        return rank;
      }

}
