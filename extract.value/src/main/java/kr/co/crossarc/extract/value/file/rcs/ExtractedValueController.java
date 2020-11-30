package kr.co.crossarc.extract.value.file.rcs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class ExtractedValueController {

    private Map<String, ExtractedValue<BigDecimal>> extractedValueMap = new HashMap<>();

    public void createExtractedValue(String key, String unit) {
        if(!this.extractedValueMap.containsKey(key)) {
            this.extractedValueMap.put(key, new ExtractedValue<BigDecimal>(key, unit));
        }
    }

    public void addValue(Map.Entry<String, Map.Entry<String, String>> entry) throws NumberFormatException{
        Map.Entry<String, String> value = null;
        try {
            if(entry != null && (value = entry.getValue()) != null) {
                this.addValue(entry.getKey(), new BigDecimal(value.getKey()), value.getValue());
            }
        } catch (Exception e) {
            log.error("추출 값 추가 중 오류.");
            log.error(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    /**
     * add value in ExtractedValue's Set
     * @param key
     * @param value
     */
    public void addValue(String key, BigDecimal value, String unit) {
        // check and init
        this.createExtractedValue(key, unit);

        // add value
        this.extractedValueMap.get(key).getValues().add(value);
    }

    /**
     * print all extracted-values
     */
    public void printSummary() {
        this.extractedValueMap.keySet().stream().forEach(
                key -> System.out.println(this.extractedValueMap.get(key).toString())
        );
    }

}
