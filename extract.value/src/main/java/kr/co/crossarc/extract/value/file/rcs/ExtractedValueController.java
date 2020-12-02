package kr.co.crossarc.extract.value.file.rcs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
            if(entry != null && (value = entry.getValue()) != null) {
                log.error("key: " + entry.getKey() + " / value: " + value.getKey() + " / unit: " + value.getValue());
            }
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
     * calculate max value from extracted value map
     * @param key
     * @return
     */
    public Optional<BigDecimal> calculateMax(String key) {
        return this.extractedValueMap.get(key).getValues().stream().max(BigDecimal::compareTo);
    }


    /**
     * calculate min value from extracted value map
     * @param key
     * @return
     */
    public Optional<BigDecimal> calculateMin(String key) {
        return this.extractedValueMap.get(key).getValues().stream().min(BigDecimal::compareTo);
    }

    /**
     * get count from extracted value map
     * @param key
     * @return
     */
    public int calculateCount(String key) {
        return this.extractedValueMap.get(key).getValues().size();
    }

    /**
     * calculate total sum from extracted value map
     * @param key
     * @return
     */
    public Optional<BigDecimal> calculateTotalSum(String key) {
        BigDecimal sum = new BigDecimal(0);
        this.extractedValueMap.get(key).getValues().stream().forEach(sum::add);
        return Optional.of(sum);
    }

    /**
     * calculate average from extracted value map
     * @param key
     * @return
     */
    public Optional<BigDecimal> calculateAverage(String key) {
        Optional<BigDecimal> optionalSum = this.calculateTotalSum(key);
        BigDecimal sum = null;
        if(optionalSum.isPresent()) {
            sum = optionalSum.get();
        }
        int count = this.calculateCount(key);

        if(sum != null) {
            return Optional.of(sum.divide(new BigDecimal(count)));
        }
        return Optional.empty();
    }


    /**
     * print all extracted-values
     */
    public void printAll() {
        this.extractedValueMap.keySet().stream().forEach(
                key -> System.out.println(this.extractedValueMap.get(key).toString())
        );
    }


    /**
     * print all extracted-values and calculate value
     */
    public void printSummary() {

        for (String key : this.extractedValueMap.keySet()) {
            System.out.println("======= key : " + key + "(" + this.calculateCount(key) + ")");
            System.out.println(this.extractedValueMap.get(key).toString());
            this.calculateTotalSum(key).ifPresent(System.out::println);
            this.calculateMax(key).ifPresent(System.out::println);
            this.calculateMin(key).ifPresent(System.out::println);
        }

    }

}
