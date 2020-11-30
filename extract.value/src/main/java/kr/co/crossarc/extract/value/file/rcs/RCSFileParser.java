package kr.co.crossarc.extract.value.file.rcs;


import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.Map;

/**
 * RCS File Data format
 * -. title
 * case 1. group : key=val, key=val, ..., key=val
 *                 key=val, key=val, ..., key=val
 * case 2. key : val
 * case 3. key = val
 * case 4. key = detail = val
 * value is value + white space + unit + end
 */
@Component
public class RCSFileParser {

    private static final String DELIMITER_COLON = ":";
    private static final String DELIMITER_EQUAL = "=";
    private static final String DELIMITER_COMMA = ",";

    /**
     * pared from line (contain key, value data)
     * @param line
     * @return Entry: key, value(value, unit)
     */
    public Map.Entry<String, Map.Entry<String, String>> parse(String line) {
        if(this.isEqualFormat(line)) {
            return parsedEqual(line);
        } else if(this.isColonFormat(line)) {
            return parsedColon(line);
        } else if(this.isGroupFormat(line)) {
            return parsedGroup(line);
        }

        return null;
    }

    /**
     * line format is group data (case 1)
     * group data contains ':' , '='
     * @param line
     * @return
     */
    public boolean isGroupFormat(String line) {
        return line.contains(DELIMITER_COLON) && line.contains(DELIMITER_EQUAL);
    }

    /**
     * line format is single data (case 2)
     * single data(case 2) contains ':'
     * @param line
     * @return
     */
    public boolean isColonFormat(String line) {
        return line.contains(DELIMITER_COLON) && !line.contains(DELIMITER_EQUAL);
    }

    /**
     * line format is single data (case 3, 4)
     * single data(case 3, 4) contains '='
     * @param line
     * @return
     */
    public boolean isEqualFormat(String line) {
        return !line.contains(DELIMITER_COLON) && line.contains(DELIMITER_EQUAL);
    }

    // yet
    public Map.Entry<String, Map.Entry<String, String>> parsedGroup(String line) {

        return null;
    }

    /**
     * line parse colon (case 2)
     * @param line
     * @return Entry: key and value (value contain value and unit)
     */
    public Map.Entry<String, Map.Entry<String, String>> parsedColon(String line) {
        String[] splitLine = line.split(DELIMITER_COLON);

        return createKeyValueSimpleEntry(splitLine[0], splitLine[1]);
    }


    /**
     * line parse last equal (case 3, 4)
     * @param line
     * @return Entry: key and value (value contain value and unit)
     */
    public Map.Entry<String, Map.Entry<String, String>> parsedEqual(String line) {
        int lastIndex = line.lastIndexOf(DELIMITER_EQUAL);
        String key = line.substring(0, lastIndex);
        String val = line.substring(lastIndex, line.length());

        return createKeyValueSimpleEntry(key, val);
    }

    /**
     * create key and value entry
     * @param key
     * @param value
     * @return Entry: key and value (value contain value and unit)
     */
    private Map.Entry<String, Map.Entry<String, String>> createKeyValueSimpleEntry(String key, String value) {
        return new AbstractMap.SimpleEntry<String, Map.Entry<String, String>>(
                key.trim()
                , this.parsedValue(value)
        );
    }

    /**
     * value parsed to value and unit
     * @param value
     * @return Entry: value and unit
     */
    public Map.Entry<String, String> parsedValue(String value) {
        String[] splitValue = value.trim().split("\\s");
        return new AbstractMap.SimpleEntry<String, String>(
                    splitValue[0]
                    , splitValue[1].split("\\.")[0]
            );
    }
}
