package kr.co.crossarc.extract.value.file.rcs;

import java.math.BigDecimal;
import java.util.Map;

public class ExtractedValueController {

    private Map<String, ExtractedValue<BigDecimal>> extractedValueMap;

    public void createExtractedValue(String key) {
        if(this.extractedValueMap.containsKey(key)) {
            this.extractedValueMap.put(key, new ExtractedValue<BigDecimal>(key));
        }
    }

    /**
     * add value in ExtractedValue's Set
     * @param key
     * @param value
     */
    public void addValue(String key, BigDecimal value) {
        // check and init
        createExtractedValue(key);

        // add value
        this.extractedValueMap.get(key).getValues().add(value);
    }

}
