import java.io.*;
import java.net.*;
import com.sun.ejte.ccl.reporter.*;

/*
 * Unit test for 6269102 ("SSL termination is not working, Appserver replaces
 * the https to http during redirection").
 *
 * This test
 *
 * - sets the authPassthroughEnabled property of http-listener-1 to TRUE [1],
 * - configures a server-name attribute for http-listener-1 with a value of
 *   https://lbhost:8888 [2],
 * - includes a 'Proxy-keysize' header with a value > 0 in the request [3],
 *
 * and expects that the host name and port# of the server-name attribute be
 * reflected in the Location response header, along with an https scheme
 * (due to [1] and [3] above for AS installations that do not bundle native
 * webcore, and due to the https scheme in the server-name attribute (see [2])
 * for AS installations that do bundle the native webcore (since the request
 * for /{contextRoot}, which results in a redirect to /{contextRoot}/, is
 * handled by the native webcore and not forwarded to the web container)).
 */
public class WebTest {

    private static SimpleReporterAdapter stat
        = new SimpleReporterAdapter("appserv-tests");

    private static final String TEST_NAME =
        "auth-passthrough-send-redirect-with-https-server-name";

    private String host;
    private String port;
    private String contextRoot;

    public WebTest(String[] args) {
        host = args[0];
        port = args[1];
        contextRoot = args[2];
    }
    
    public static void main(String[] args) {
        stat.addDescription("Unit test for 6269102");
        WebTest webTest = new WebTest(args);
        webTest.doTest();
        stat.printSummary(TEST_NAME);
    }

    public void doTest() {
     
        try { 
            testRemoteAddress();
        } catch (Exception ex) {
            ex.printStackTrace();
            stat.addStatus(TEST_NAME, stat.FAIL);
        }
    }

    private void testRemoteAddress() throws Exception {
         
        Socket sock = new Socket(host, new Integer(port).intValue());
        OutputStream os = sock.getOutputStream();
        String get = "GET " + contextRoot + " HTTP/1.0\n";
        System.out.println(get);
        os.write(get.getBytes());
        os.write("Proxy-keysize: 512\n".getBytes());
        os.write("\n".getBytes());
        
        InputStream is = sock.getInputStream();
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));

        String line = null;
        while ((line = bis.readLine()) != null) {
            if (line.startsWith("Location:")) {
                break;
            }
        }

        if (line != null) {
            System.out.println("Location header: " + line);

            String location = line.substring("Location:".length()).trim();
            String expectedLocation = "https://lbhost:8888" + contextRoot
                                                                    + "/";
            if (expectedLocation.equals(location)) {
                stat.addStatus(TEST_NAME, stat.PASS);
            } else {
                System.err.println("Wrong Location response header, expected: "
                                   + expectedLocation
                                   + ", received: " + location);
                stat.addStatus(TEST_NAME, stat.FAIL);
            }
        } else {
            System.err.println("Missing Location response header");
            stat.addStatus(TEST_NAME, stat.FAIL);
        }
    }
}
