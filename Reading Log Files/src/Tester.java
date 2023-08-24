public class Tester {
    public void testLogAnalyzer(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int UniqueIPs = la.countUniqueIPs();
        System.out.println("They are " + UniqueIPs + " IPs");
    }
}
