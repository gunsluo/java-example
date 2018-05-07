package demo.gunsluo.im;


import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SmackExample {
    public static void main(String[] args) throws IOException, InterruptedException, XMPPException, SmackException {
//        byte[] digest = {-38, 25, 13, -87, -7, 62, 17, 60, 68, -93, 113, 94, 58, 22, -22, -16, -34, 77, -116, -50};
//        String version = Base64.encodeToString(digest);
//        System.out.println("====>" + version);
//        byte[] digest = Base64.decode("luoji");
//        System.out.println("====>" + digest);

        XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder()
                .setDebuggerEnabled(true)
                .setXmppDomain("meet.demo.com")
                //.setHostAddress(InetAddress.getByName("192.168.2.180"))
                .setHost("192.168.2.23")
                .setPort(5222);

        if (false) {
            configBuilder.setCustomX509TrustManager(new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            });
        } else {
            configBuilder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        }

        if (true) {
            configBuilder.performSaslAnonymousAuthentication();
        } else {
            configBuilder.setUsernameAndPassword("luoji", "password");
        }

        AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());
        connection.connect();
        connection.login();
        System.out.println("Ok");
        //connection.login("focus", "password3");

        /*
        XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                .setUsernameAndPassword("username", "password")
                .setXmppDomain("jabber.org")
                .setHost("earl.jabber.org")
                .setPort(8222)
                .build();

        AbstractXMPPConnection conn2 = new XMPPTCPConnection(config);
        conn2.connect().login();
        */
    }
}