package kr.co.crossarc.extract.values.wallmark.dto;

import kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebar;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class WallMarkRebarOrigin {

    private String wallId;

    private String story;

    private String HTw;

    private String hw;

    private String fck;

    private String fy;

    private String fys;

    private String pu;

    private String mc1;
    private String mc2;
    private String mc3;

    private String vu1;
    private String vu2;
    private String vu3;

    private String verticalRebar;

    private String horizonRebar;

    private String endRebar;

    public WallMarkRebarOrigin() {
    }

    public WallMarkRebarOrigin(String wallId, String line) {
        String[] splitLine = line.split("\\s+");
        this.wallId = wallId;
        this.story = splitLine[0];
        this.HTw = splitLine[1];
        this.hw = splitLine[2];
        this.fck = splitLine[3];
        this.fy = splitLine[4];
        this.fys = splitLine[5];
        this.pu = splitLine[6];
        this.mc1 = splitLine[7];
        this.mc2 = splitLine[8];
        this.mc3 = splitLine[9];
        this.vu1 = splitLine[10];
        this.vu2 = splitLine[11];
        this.vu3 = splitLine[12];
        this.verticalRebar = splitLine[13];
        this.horizonRebar = splitLine[14];
        this.endRebar = splitLine[15];
    }

    public WallMarkRebar toEntity() {
        System.out.println("변경 요청 :" + this.toString());
        return WallMarkRebar.builder()
                .wallId(this.wallId)
                .story(this.story)
                .verticalRebarDValue(this.findVerticalRebarDValue())
                .verticalRebarInterval(this.findVerticalRebarInterval())
                .horizonRebarDValue(this.findHorizonRebarDValue())
                .horizonRebarInterval(this.findHorizonRebarInterval())
                .build();
    }

    private String findVerticalRebarDValue() {
        return this.verticalRebar.split("@")[0].split("\\.")[1];
    }

    private int findVerticalRebarInterval() {
        return Integer.parseInt(this.verticalRebar.split("@")[1]);
    }

    private String findHorizonRebarDValue() {
        return this.horizonRebar.split("@")[0].split("\\.")[1];
    }

    private int findHorizonRebarInterval() {
        return Integer.parseInt(this.horizonRebar.split("@")[1]);
    }
}