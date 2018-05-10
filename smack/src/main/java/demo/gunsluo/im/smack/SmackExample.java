package demo.gunsluo.im.smack;


import org.jivesoftware.smack.*;
import org.jivesoftware.smack.sasl.javax.SASLGSSAPIMechanism;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class SmackExample {
    public static void main(String[] args) {
        SmackExample smackExample = new SmackExample();
        try {
            smackExample.join("192.168.2.23", 5222, "auth.meet.demo.com", true, "focus", "password3");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Ok");
    }

    public void join(String host, int port, String domain, boolean security, String user, String password) throws IOException, InterruptedException, XMPPException, SmackException {
        join(host, port, domain, security, false, user, password);
    }

    public void join(String host, int port, String domain, boolean security) throws IOException, InterruptedException, XMPPException, SmackException {
        join(host, port, domain, security, true, "", "");
    }

    private void join(String host, int port, String domain, boolean security, boolean anonymous, String user, String password) throws IOException, InterruptedException, XMPPException, SmackException {
        XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder()
                .setDebuggerEnabled(true)
                .setXmppDomain(domain)
                .setHost(host)
                .setPort(port);

        if (security) {
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

        if (anonymous) {
            configBuilder.performSaslAnonymousAuthentication();
        } else {
            configBuilder.setUsernameAndPassword(user, password);
        }

        AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());
        connection.connect();
        connection.login();
    }

    public void test(String host, int port, String domain, boolean security, boolean anonymous, String user, String password) throws IOException, InterruptedException, XMPPException, SmackException {
        XMPPTCPConnectionConfiguration.Builder connConfig
                = XMPPTCPConnectionConfiguration.builder()
                .setHost(host)
                .setPort(port)
                .setXmppDomain(domain);
        ReconnectionManager.setEnabledPerDefault(true);

        SASLAuthentication.unregisterSASLMechanism(
                SASLGSSAPIMechanism.class.getName());

        if (anonymous) {
            connConfig.performSaslAnonymousAuthentication();
        }

        if (security) {
            connConfig.setCustomX509TrustManager(new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] c, String s)
                        throws CertificateException {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] c, String s)
                        throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            });

            connConfig.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        } else {
            connConfig.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        }

        AbstractXMPPConnection connection = new XMPPTCPConnection(connConfig.build());
        connection.connect();
        connection.login(user, password);
    }
}