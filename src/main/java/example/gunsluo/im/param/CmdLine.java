package example.gunsluo.im.param;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CmdLine {
    private static final Logger logger = Logger.getLogger(CmdLine.class.getName());
    private Map<String, String> argMap = new HashMap();
    private List<String> requiredArgs = new ArrayList();

    public CmdLine() {
    }

    public void addRequiredArgument(String reqArg) {
        reqArg = this.cleanHyphens(reqArg);
        if (!this.requiredArgs.contains(reqArg)) {
            this.requiredArgs.add(reqArg);
        }

    }

    public void removeRequiredArgument(String reqArg) {
        reqArg = this.cleanHyphens(reqArg);
        this.requiredArgs.remove(reqArg);
    }

    public List<String> getRequiredArguments() {
        return Collections.unmodifiableList(this.requiredArgs);
    }

    private String cleanHyphens(String arg) {
        if (arg.startsWith("--")) {
            return arg.substring(2);
        } else {
            return arg.startsWith("-") ? arg.substring(1) : arg;
        }
    }

    public void parse(String[] args) throws Exception {
        String[] var2 = args;
        int var3 = args.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String arg = var2[var4];
            arg = this.cleanHyphens(arg);
            int eqIdx = arg.indexOf("=");
            if (eqIdx <= 0) {
                logger.log(Level.WARNING, "Skipped invalid cmd line argument: " + arg);
            } else if (eqIdx == arg.length() - 1) {
                logger.log(Level.WARNING, "Skipped empty cmd line argument: " + arg);
            } else {
                String key = arg.substring(0, eqIdx);
                String val = arg.substring(eqIdx + 1);
                this.argMap.put(key, val);
            }
        }

        List<String> leftReqArgs = new ArrayList(this.requiredArgs);
        leftReqArgs.removeAll(this.argMap.keySet());
        if (!leftReqArgs.isEmpty()) {
            throw new Exception("Some of required arguments were not specified: " + leftReqArgs.toString());
        }
    }

    public String getOptionValue(String opt) {
        return (String) this.argMap.get(this.cleanHyphens(opt));
    }

    public String getOptionValue(String opt, String defaultValue) {
        String val = this.getOptionValue(opt);
        return val != null ? val : defaultValue;
    }

    public int getIntOptionValue(String opt, int defaultValue) {
        String val = this.getOptionValue(opt);
        if (val == null) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(val);
            } catch (NumberFormatException var5) {
                return defaultValue;
            }
        }
    }
}
