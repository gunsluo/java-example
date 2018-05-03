package example.gunsluo.im.param;

import java.util.Map;

public class ParamDemo {
    public static void main(String[] args) throws Exception {
        CmdLine cmdLine = new CmdLine();

        cmdLine.addRequiredArgument("--secret");

        // We may end execution here if one of required arguments is missing
        cmdLine.parse(args);

        String componentDomain = cmdLine.getOptionValue("domain");
        String host = cmdLine.getOptionValue(
                "--host",
                componentDomain == null ? "localhost" : componentDomain);
        String secret = cmdLine.getOptionValue("--secret");

        System.setProperty("org.jitsi.jicofo.HOSTNAME", host);


        System.out.println("host:" + host);
        System.out.println("domain:" + componentDomain);
        System.out.println("secret:" + secret);


        Config2 config = new Config2();
        Map<String, String> properties = config.getSystemPropertyDefaults();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}
