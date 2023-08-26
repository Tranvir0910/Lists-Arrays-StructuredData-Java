import java.util.HashMap;

public class Tester {
    public void testLogAnalyzer(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int UniqueIPs = la.countUniqueIPs();
        System.out.println("They are " + UniqueIPs + " IPs");
        System.out.println("-----------------------------------------------");
        la.printAllHigherThanNum(400);
        System.out.println("-----------------------------------------------");
        la.uniqueIPVisitsOnDay("Sep 24");
        System.out.println("-----------------------------------------------");
        System.out.println(la.countUniqueIPsInRange(200, 299));
        System.out.println("---------------------Assignment 2--------------------------");
        HashMap<String, Integer> result = la.countVisitsPerIP();
        System.out.println("Most number visit by IP : " + la.mostNumberVisitsByIP(result));
        System.out.println(la.iPsMostVisits(result));
        System.out.println(la.iPsForDays());
        System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
    }
}
