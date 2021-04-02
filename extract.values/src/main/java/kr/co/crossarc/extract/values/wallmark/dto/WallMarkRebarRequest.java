package kr.co.crossarc.extract.values.wallmark.dto;

import kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebar;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter @Setter @ToString @Slf4j
public class WallMarkRebarRequest {
    private static final String REGEX_REBAR_DELIMITER = "@";
    private static final String REGEX_REBAR_AS_DELEMITER = "\\.";

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

    public WallMarkRebarRequest() {
    }

    public WallMarkRebarRequest(String wallId, String line) {
        String[] splitLine = this.deleteBracket(line).split("\\s+");

        if(isData(splitLine[0])) {
            this.wallId = wallId;
            this.story = splitLine[0];
            this.HTw = splitLine[1];
            this.hw = splitLine[2];
            this.fck = splitLine[3];
            this.fy = splitLine[4];
            this.fys = splitLine[5];
            this.pu = splitLine[6];
            this.mc1 = splitLine[7];
            this.vu1 = splitLine[8];
            this.verticalRebar = splitLine[9];
            this.horizonRebar = splitLine[10];
            this.endRebar = splitLine[11];
        } else {
            throw new IllegalArgumentException("데이터가 아닙니다. (*F, B*, PIT)");
        }
    }

    private boolean isData(String story) {
        return story.matches("^[0-9]*F") || story.matches("^B[0-9]*") || story.equals("PIT");
    }

    private String deleteBracket(String line) {
        while (line.contains("(")) {
            int bracketStartIndex = line.indexOf("(");
            int bracketEndIndex = line.indexOf(")");
            line = line.substring(0, bracketStartIndex)
                    + line.substring(bracketEndIndex + 1);
            log.trace("괄호 삭제 : " + line);
        }
        return line;
    }

    public WallMarkRebar toEntity() {
        log.trace("변경 요청 :" + this.toString());
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
        return this.verticalRebar.split(REGEX_REBAR_DELIMITER)[0].split(REGEX_REBAR_AS_DELEMITER)[1]
                .replace("D", "");
    }

    private int findVerticalRebarInterval() {
        return Integer.parseInt(this.verticalRebar.split(REGEX_REBAR_DELIMITER)[1]);
    }

    private String findHorizonRebarDValue() {
        return this.horizonRebar.split(REGEX_REBAR_DELIMITER)[0].split(REGEX_REBAR_AS_DELEMITER)[1]
                .replace("D", "");
    }

    private int findHorizonRebarInterval() {
        return Integer.parseInt(this.horizonRebar.split(REGEX_REBAR_DELIMITER)[1]);
    }
}
