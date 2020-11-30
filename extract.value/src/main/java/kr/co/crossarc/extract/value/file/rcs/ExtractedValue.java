package kr.co.crossarc.extract.value.file.rcs;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
public class ExtractedValue<V> {

    private String key;

    private String unit;

    private List<V> values;

    public ExtractedValue(String key, String unit) {
        this.key = key;
        this.unit = unit;
        this.values = new ArrayList<>();
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
