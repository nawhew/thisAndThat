package kr.co.crossarc.extract.values.wallmark.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WallMarkRebarRepository extends JpaRepository<WallMarkRebar, Long> {

    /**
     * 철근 콘트리트 정보를 벽ID, 층, 수직 콘크리트 D 값, 수평 콘크리트 D 값 별로 묶어 조회합니다.
     * @return
     */
    @Query(value = "SELECT NEW kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebar(" +
            " WMR.id" +
            ", WMR.wallId" +
            ", WMR.story" +
            ", WMR.verticalValue" +
            ", WMR.verticalRebarDValue" +
            ", WMR.verticalRebarInterval" +
            ", WMR.horizonValue" +
            ", WMR.horizonRebarDValue" +
            ", WMR.horizonRebarInterval" +
            ")" +
            " FROM WallMarkRebar WMR" )
//            " GROUP BY (WMR.wallId, WMR.story)" +
//            " ORDER BY (WMR.wallId, WMR.story) DESC")
    List<WallMarkRebar> findGroupByRebars();


    // back up
//    @Query(value = "SELECT NEW kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebar(" +
//            " MIN(WMR.id)" +
//            ", WMR.wallId" +
//            ", WMR.story" +
//            ", WMR.verticalRebarDValue" +
//            ", MIN(WMR.verticalRebarInterval)" +
//            ", WMR.horizonRebarDValue" +
//            ", MIN(WMR.horizonRebarInterval))" +
//            " FROM WallMarkRebar WMR" +
//            " GROUP BY (WMR.wallId, WMR.story, WMR.verticalRebarDValue, WMR.horizonRebarDValue)" +
//            " ORDER BY (WMR.wallId, WMR.story) DESC")
}
