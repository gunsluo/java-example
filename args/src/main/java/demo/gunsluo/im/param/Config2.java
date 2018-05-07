package demo.gunsluo.im.param;

import org.jitsi.meet.OSGiBundleConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Config2
        extends OSGiBundleConfig {
    /**
     * The locations of the OSGi bundles (or rather of the class files of their
     * <tt>BundleActivator</tt> implementations) comprising Jitsi Videobridge.
     * An element of the <tt>BUNDLES</tt> array is an array of <tt>String</tt>s
     * and represents an OSGi start level.
     */
    private static final String[][] BUNDLES
            = {
            {
                    "org/jitsi/eventadmin/Activator"
            },
            {
                    "org/jitsi/service/libjitsi/LibJitsiActivator"
            },
            {
                    "net/java/sip/communicator/util/UtilActivator",
                    "net/java/sip/communicator/impl/fileaccess/FileAccessActivator"
            },
            {
                    "net/java/sip/communicator/impl/configuration/ConfigurationActivator"
            },
            {
                    "net/java/sip/communicator/impl/resources/ResourceManagementActivator"
            },
            {
                    "net/java/sip/communicator/impl/netaddr/NetaddrActivator"
            },
            {
                    "net/java/sip/communicator/impl/packetlogging/PacketLoggingActivator"
            },
            {
                    "net/java/sip/communicator/service/gui/internal/GuiServiceActivator"
            },
            {
                    "net/java/sip/communicator/service/protocol/media/ProtocolMediaActivator"
            },
            {
                    "org/jitsi/videobridge/eventadmin/callstats/Activator"
            },
            {
                    "org/jitsi/videobridge/VideobridgeBundleActivator"
            },
            {
                    "org/jitsi/videobridge/version/VersionActivator"
            },
            {
                    // The HTTP/JSON API of Videobridge is started after and in a start
                    // level separate from Videobridge because the HTTP/JSON API is
                    // useless if Videobridge fails to start.
                    "org/jitsi/videobridge/rest/RESTBundleActivator",
                    "org/jitsi/videobridge/rest/PublicRESTBundleActivator",
                    // The statistics/health reports are a non-vital, optional,
                    // additional piece of functionality of the Videobridge.
                    // Consequently, they do not have to be started before the
                    // Videobridge. Besides, they employ OSGi and, hence, they should be
                    // capable of acting as a plug-in. They do not have to be started
                    // before the HTTP/JSON API because the HTTP/JSON API (1) exposes
                    // the vital, non-optional, non-additional pieces of functionality
                    // of the Videobridge and (2) it pulls, does not push.
                    "org/jitsi/videobridge/stats/StatsManagerBundleActivator",
                    "org/jitsi/videobridge/EndpointConnectionStatus"
            },
            {
                    "org/jitsi/videobridge/octo/OctoRelayService"
            }
    };

    @Override
    protected String[][] getBundlesImpl() {
        return BUNDLES;
    }

    @Override
    public Map<String, String> getSystemPropertyDefaults() {
        // "super" is setting defaults common to all components
        Map<String, String> defaults = super.getSystemPropertyDefaults();

        String true_ = Boolean.toString(true);
        String false_ = Boolean.toString(false);

        // It makes no sense for Jitsi Videobridge to pace its RTP output.
        defaults.put(
                "net.java.sip.communicator.impl.neomedia.video.maxbandwidth",
                Integer.toString(Integer.MAX_VALUE));


        // Disable packet logging.
        defaults.put(
                "net.java.sip.communicator.packetlogging.PACKET_LOGGING_ENABLED",
                false_);


        // Enable picture ID rewriting by default, as jumping picture IDs cause
        // recent versions of Chrome to crash.
        defaults.put(
                "org.jitsi.videobridge.ENABLE_VP8_PICID_REWRITING", true_);

        // This causes RTP/RTCP packets received before the DTLS agent is ready
        // to decrypt them to be dropped. Without it, these packets are passed
        // on without decryption and this leads to:
        // 1. Garbage being sent to the endpoints (or at least something they
        //    cannot decrypt).
        // 2. Failed attempts to parse encrypted RTCP packets (in a compound
        //    packet, the headers of all but the first packet are encrypted).

        // This is currently disabled, because it makes DTLS mandatory, and
        // thus breaks communication with jigasi and jitsi.
        //defaults.put(
        //        "org.jitsi.impl.neomedia.transform.dtls.DtlsPacketTransformer"
        //            + ".dropUnencryptedPkts",
        //        true_);

        // make sure we use the properties files for configuration
        defaults.put(
                "net.java.sip.communicator.impl.configuration.USE_PROPFILE_CONFIG",
                true_);

        // callstats-java-sdk
        getCallStatsJavaSDKSystemPropertyDefaults(defaults);

        return defaults;
    }

    public static void getCallStatsJavaSDKSystemPropertyDefaults(Map<String, String> defaults) {
        getCallStatsJavaSDKSystemPropertyDefaults("log4j2.xml", defaults, "log4j.configurationFile");
        getCallStatsJavaSDKSystemPropertyDefaults("callstats-java-sdk.properties", defaults, "callstats.configurationFile");
    }

    private static void getCallStatsJavaSDKSystemPropertyDefaults(String fileName, Map<String, String> defaults, String propertyName) {
        List<File> files = new ArrayList();
        files.add(new File("config", fileName));
        files.add(new File(fileName));
        String scHomeDirName = System.getProperty("net.java.sip.communicator.SC_HOME_DIR_NAME");
        File dir;
        if (!isNullOrEmpty(scHomeDirName)) {
            String scHomeDirLocation = System.getProperty("net.java.sip.communicator.SC_HOME_DIR_LOCATION");
            if (!isNullOrEmpty(scHomeDirLocation)) {
                dir = new File(scHomeDirLocation, scHomeDirName);
                if (dir.isDirectory()) {
                    int i = 0;

                    for (int end = files.size(); i < end; ++i) {
                        files.add(new File(dir, ((File) files.get(i)).getPath()));
                    }
                }
            }
        }

        Iterator var9 = files.iterator();

        while (var9.hasNext()) {
            dir = (File) var9.next();
            if (dir.exists()) {
                defaults.put(propertyName, dir.getAbsolutePath());
                break;
            }
        }

    }

    public static boolean isNullOrEmpty(String s) {
        return isNullOrEmpty(s, true);
    }

    public static boolean isNullOrEmpty(String s, boolean trim) {
        if (s == null) {
            return true;
        } else {
            if (trim) {
                s = s.trim();
            }

            return s.length() == 0;
        }
    }
}
