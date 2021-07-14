package kr.co.crossarc.extract.values.wallmark.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @Builder @AllArgsConstructor @ToString
public class WallMarkRebar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wallId;

    private String story;

    private int verticalValue;

    private String verticalRebarDValue;

    private int verticalRebarInterval;

    private int horizonValue;

    private String horizonRebarDValue;

    private int horizonRebarInterval;

    public WallMarkRebar() {}

    public String toStringForPrintResult() {
        return "" +
                "wallId=" + wallId +
                ", story=" + story +
                ", verticalRebar=" + verticalValue + "." + verticalRebarDValue + "@" + verticalRebarInterval +
                ", horizonRebar=" + horizonValue + "." + horizonRebarDValue + "@" + horizonRebarInterval
                ;
    }
}
