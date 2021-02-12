package kr.co.crossarc.extract.values.wallmark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Builder @AllArgsConstructor @ToString
public class WallMarkRebar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wallId;

    private String story;

    private String verticalRebarDValue;

    private int verticalRebarInterval;

    private String horizonRebarDValue;

    private int horizonRebarInterval;

    public WallMarkRebar() {}

    public String toStringForPrintResult() {
        return "" +
                "wallId=" + wallId +
                ", story=" + story +
                ", verticalRebar" + verticalRebarDValue + "@" + verticalRebarInterval +
                ", horizonRebarDValue=" + horizonRebarDValue + "@" + horizonRebarInterval
                ;
    }
}
