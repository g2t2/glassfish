import java.io.*;
import java.net.*;
import com.sun.ejte.ccl.reporter.*;

/*
 * Unit test for CR 4882996:
 * request.getAttribute("javax.servlet.error.request_uri") is not working ..
 *
 * The following response body lines must be returned in order for this unit
 * test to succeed:
 *
 *  /web-javax-servlet-error-request-uri-static-resource/junk
 *  404
 *  /web-javax-servlet-error-request-uri-static-resource/404handler.jsp
 *  http://<host>:<port>/web-javax-servlet-error-request-uri-static-resource/404handler.jsp
 */
public class WebTest {

    private static final String TEST_NAME = "javax-servlet-error-request-uri-static-resource";

    private static SimpleReporterAdapter stat
        = new SimpleReporterAdapter("appserv-tests");

    private String host;
    private String port;
    private String contextRoot;

    public WebTest(String[] args) {
        host = args[0];
        port = args[1];
        contextRoot = args[2];
    }
    
    public static void main(String[] args) {
        stat.addDescription("Unit test for 4882996");
        WebTest webTest = new WebTest(args);
        try {
            webTest.doTest();
        } catch (Exception ex) {
            System.out.println(TEST_NAME + " test failed");
            stat.addStatus(TEST_NAME, stat.FAIL);
            ex.printStackTrace();
        }
	stat.printSummary();
    }

    public void doTest() throws Exception {
     
        Socket sock = new Socket(host, new Integer(port).intValue());
        OutputStream os = sock.getOutputStream();
        String get = "GET " + contextRoot + "/junk HTTP/1.0\n";
        System.out.println(get);
        os.write(get.getBytes());
        os.write("\n".getBytes());
        
        InputStream is = sock.getInputStream();
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));

        String line = null;
        int i=0; 
        while ((line = bis.readLine()) != null) {
            System.out.println(i++ + ": " + line);
            if (line.equals(contextRoot + "/junk")) {
                break;
            }
        }

        if (line != null
                && ((line = bis.readLine()) != null)
                && line.equals("404")
                && ((line = bis.readLine()) != null)
                && line.equals(contextRoot + "/404handler.jsp")
                && ((line = bis.readLine()) != null)
                && (
                    line.equals("http://" + host + ":" + port
                               + contextRoot + "/404handler.jsp") ||
                    line.equals("http://" + 
                                InetAddress.getLocalHost().getHostName() + 
                                ":" + port + contextRoot + "/404handler.jsp") 
                               )) {
            stat.addStatus(TEST_NAME, stat.PASS);
        } else {
            stat.addStatus(TEST_NAME, stat.FAIL);
        }
    }

}
