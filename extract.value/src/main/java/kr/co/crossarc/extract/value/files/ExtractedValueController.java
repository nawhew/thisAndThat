package kr.co.crossarc.extract.value.files;

import java.util.Map;

public class ExtractedValueController<V> {

    private Map<String, ExtractedValue<V>> extractedValueMap;

    public void createExtractedValue(String key) {
        if(this.extractedValueMap.containsKey(key)) {
            this.extractedValueMap.put(key, new ExtractedValue<V>(key));
        }
    }

    /**
     * add value in ExtractedValue's Set
     * @param key
     * @param value
     */
    public void addValue(String key, V value) {
        // check and init
        createExtractedValue(key);

        // add value
        this.extractedValueMap.get(key).getValues().add(value);
    }

}
