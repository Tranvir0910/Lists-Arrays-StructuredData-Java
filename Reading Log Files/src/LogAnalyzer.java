import edu.duke.FileResource;

import java.util.ArrayList;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for(String s : fr.lines()){
             LogEntry entry = WebLogParser.parseEntry(s);
             records.add(entry);
         }
         printAll();
     }
     public int countUniqueIPs(){
         ArrayList<String> uniqueIPs = new ArrayList<>();
         for(LogEntry le : records){
             String ipAddr = le.getIpAddress();
             if(!uniqueIPs.contains(ipAddr)){
                 uniqueIPs.add(ipAddr);
             }
         }
         return uniqueIPs.size();
     }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
}
