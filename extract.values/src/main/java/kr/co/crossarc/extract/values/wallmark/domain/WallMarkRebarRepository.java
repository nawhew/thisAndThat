package kr.co.crossarc.extract.values.wallmark.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WallMarkRebarRepository extends JpaRepository<WallMarkRebar, Long> {

    /**
     *
     * @return
     */
    @Query(value = "SELECT NEW kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebar(" +
            " MIN(WMR.id)" +
            ", WMR.wallId" +
            ", WMR.story" +
            ", WMR.verticalRebarDValue" +
            ", MIN(WMR.verticalRebarInterval)" +
            ", WMR.horizonRebarDValue" +
            ", MIN(WMR.horizonRebarInterval))" +
            " FROM WallMarkRebar WMR" +
            " GROUP BY (WMR.wallId, WMR.story, WMR.verticalRebarDValue, WMR.horizonRebarDValue)" +
            " ORDER BY (WMR.wallId, WMR.story) DESC")
    List<WallMarkRebar> findGroupByRebars();
}
