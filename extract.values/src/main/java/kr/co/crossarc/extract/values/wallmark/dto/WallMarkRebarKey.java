package kr.co.crossarc.extract.values.wallmark.dto;

import kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebar;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter @AllArgsConstructor
public class WallMarkRebarKey {

    private String wallId;

    private String story;

    public static WallMarkRebarKey of(WallMarkRebar wallMarkRebar) {
        return new WallMarkRebarKey(wallMarkRebar.getWallId(), wallMarkRebar.getStory());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WallMarkRebarKey that = (WallMarkRebarKey) o;
        return Objects.equals(wallId, that.wallId) &&
                Objects.equals(story, that.story);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wallId, story);
    }
}
