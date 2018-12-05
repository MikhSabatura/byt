import java.util.*;

public class Configuration {
    public int interval;

    public int duration;

    public int departure;

    /*
     * problem: long method, code duplication
     * solution: split load method into 3 and moved duplicated functionality to a separate method
     * */
    public void load(Properties props) throws ConfigurationException {
        interval = extractInterval(props);
        duration = extractDuration(props);
        departure = extractDeparture(props);
    }

    private int extractInterval(Properties props) throws ConfigurationException {
        return extractPropertyValue(props, "interval");
    }


    private int extractDuration(Properties props) throws ConfigurationException {
        int duration = extractPropertyValue(props, "duration");
        if ((duration % interval) != 0) {
            throw new ConfigurationException("duration % interval");
        }
        return duration;
    }

    private int extractDeparture(Properties props) throws ConfigurationException {
        int departure = extractPropertyValue(props, "departure");
        if ((departure % interval) != 0) {
            throw new ConfigurationException("departure % interval");
        }
        return departure;
    }

    private int extractPropertyValue(Properties props, String propName) throws ConfigurationException {
        String valueString;
        int value;

        valueString = props.getProperty(propName);
        if (valueString == null) {
            throw new ConfigurationException(propName + " interval");
        }
        value = Integer.parseInt(valueString);
        if (value <= 0) {
            throw new ConfigurationException("monitor " + propName + " > 0");
        }
        return value;
    }
}
