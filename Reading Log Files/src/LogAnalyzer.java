import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
         //printAll();
     }
//     public int countUniqueIPs(){
//         ArrayList<String> uniqueIPs = new ArrayList<>();
//         for(LogEntry le : records){
//             String ipAddr = le.getIpAddress();
//             if(!uniqueIPs.contains(ipAddr)){
//                 uniqueIPs.add(ipAddr);
//             }
//         }
//         return uniqueIPs.size();
//     }
    public int countUniqueIPs() {
        ArrayList<LogEntry> uniqueIps = new ArrayList<LogEntry>();
        for (LogEntry le : records) {
            if (!uniqueIps.contains(le)) {
                uniqueIps.add(le);
            }
        }
        return uniqueIps.size();
    }
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public void printAllHigherThanNum(int num){
         ArrayList<LogEntry> statusCode = new ArrayList<LogEntry>();
         for(LogEntry le : records){
             int value = le.getStatusCode();
             if( value > num){
                 statusCode.add(le);
             }
         }
         for(LogEntry le : statusCode){
             System.out.println(le);
         }
     }
     public void uniqueIPVisitsOnDay(String someday){
        ArrayList<String> day = new ArrayList<String>();
        for(LogEntry le : records){
            String d = le.getAccessTime().toString();
            String ip = le.getIpAddress();
            d = d.substring(4, 4 + someday.length());
            if(d.equals(someday)) {
                String ipAddr = le.getIpAddress();
                if (!day.contains(ipAddr)) {
                    day.add(ipAddr);
                }
            }
        }
        for(String le : day){
            System.out.println(le);
        }
     }
     public int countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode >= low && statusCode <= high) {
                 String ipAddr = le.getIpAddress();
                 if (!uniqueIPs.contains(ipAddr)) {
                     uniqueIPs.add(ipAddr);
                 }
             }
         }
         return uniqueIPs.size();
     }
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                 counts.put(ip, 1);
             }else{
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         return counts;
     }
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int maxAppearIP = 0;
         for(String key : counts.keySet()){
             if(counts.get(key) > maxAppearIP)
                 maxAppearIP = counts.get((key));
         }
         return maxAppearIP;
     }
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> ips){
         ArrayList<String> mostIP = new ArrayList<>();
         int mostNumberVisitByIP = mostNumberVisitsByIP(ips);
         for(String key : ips.keySet()){
             if(ips.get(key) == mostNumberVisitByIP){
                 mostIP.add(key);
             }
         }
         return mostIP;
     }
     public HashMap<String, ArrayList<String>> iPsForDays(){
         HashMap<String, ArrayList<String>> dayToIPs = new HashMap<>();
         for(LogEntry le : records){

             String day = le.getAccessTime().toString();
             day = day.substring(4, 10);
             String ipAddr = le.getIpAddress();

             if(!dayToIPs.containsKey(day)){
                 ArrayList<String> ipsList = new ArrayList<String>();
                 ipsList.add(ipAddr);
                 dayToIPs.put(day, ipsList);
             }else{
                 dayToIPs.get(day).add(ipAddr);
             }
         }
         return dayToIPs;
     }
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> iPsForDays){
         int maxIP = 0;
         String maxDay = "";
         for(String s : iPsForDays.keySet()){
             if(iPsForDays.get(s).size() > maxIP) {
                 maxIP = iPsForDays.get(s).size();
                 maxDay = s;
             }
         }
         return maxDay;
     }
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> tmp, String mmmdd){
         ArrayList<String> maxIP = tmp.get(mmmdd);
         ArrayList<String> result = new ArrayList<>();
         HashMap<String, Integer> counts = new HashMap<>();
         for(String s : maxIP){
            if(!counts.containsKey(s)) {
                counts.put(s, 1);
            }else{
                counts.put(s, counts.get(s) + 1);
            }
         }
         int maxKey = mostNumberVisitsByIP(counts);
         for(String s : counts.keySet()){
             if(maxKey == counts.get(s))
                 result.add(s);
         }
         return result;
     }
}
