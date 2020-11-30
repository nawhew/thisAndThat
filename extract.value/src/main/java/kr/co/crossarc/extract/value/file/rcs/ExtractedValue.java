package kr.co.crossarc.extract.value.file.rcs;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class ExtractedValue<V> {

    private String key;

    private String unit;

    private Set<V> values;

    public ExtractedValue(String key) {
        this.key = key;
        this.values = new HashSet<>();
    }

    @Override
    public String toString() {
        return "ExtractedValue{" +
                "key='" + key + '\'' +
                ", unit='" + unit + '\'' +
                ", values=" + values +
                '}';
    }
}
