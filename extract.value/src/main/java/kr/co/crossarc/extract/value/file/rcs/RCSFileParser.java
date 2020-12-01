package kr.co.crossarc.extract.value.file.rcs;


import org.springframework.stereotype.Component;

import java.util.*;

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
        if (!isDataFormat(line)) {
            return null;
        }

        if(this.isEqualFormat(line)) {
            return parsedEqual(line);
        } else if(this.isColonFormat(line)) {
            return parsedColon(line);
        }

        return null;
    }

    public List<Map.Entry<String, Map.Entry<String, String>>> parses(String line) {
        if(this.isGroupFormat(line)) {
            return parsedGroup(line);
        }
        return null;
    }

    public boolean isDataFormat(String line) {
        return line.startsWith("    ") && !line.startsWith("    [[[*]]]");
    }

    /**
     * line format is group data (case 1)
     * group data contains ':' , '='
     * @param line
     * @return
     */
    public boolean isGroupFormat(String line) {
        return line.contains(DELIMITER_COLON) && line.contains(DELIMITER_EQUAL) && this.containGroupKey(line);
    }

    public boolean containGroupKey(String line) {
        String trimLine = line.trim();
        return Arrays.stream(GroupKey.values()).anyMatch(groupKey -> {
                    return groupKey.toString().equalsIgnoreCase(trimLine);
                });
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
    public List<Map.Entry<String, Map.Entry<String, String>>> parsedGroup(String line) {
        String[] splitLine = line.split(DELIMITER_COLON)[1].trim().split(DELIMITER_COMMA);
        List<Map.Entry<String, Map.Entry<String, String>>> entryList = new ArrayList<>();

        for (String keyValue : splitLine) {
            entryList.add(this.parse(keyValue));
        }
        return entryList;
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
        String key = this.removeKeyDetails(line.substring(0, lastIndex));
        String val = line.substring(lastIndex + 1, line.length());

        return createKeyValueSimpleEntry(key, val);
    }

    /**
     * 키에 '=' 표시가 있는 경우, 첫번째 표시 앞의 값을 key로 반환합니다.
     * @param key
     * @return
     */
    public String removeKeyDetails(String key) {
        return key.contains(DELIMITER_EQUAL)
                ? key.split(DELIMITER_EQUAL)[0]
                : key;
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
        String unit = "";
        if(splitValue.length > 1) {
            unit = splitValue[1].split("\\.")[0];
        }
        return new AbstractMap.SimpleEntry<String, String>(
                    splitValue[0]
                    , unit
            );
    }
}
