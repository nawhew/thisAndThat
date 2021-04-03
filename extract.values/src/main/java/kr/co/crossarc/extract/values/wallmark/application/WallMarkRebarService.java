package kr.co.crossarc.extract.values.wallmark.application;

import kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebar;
import kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebarRepository;
import kr.co.crossarc.extract.values.wallmark.dto.WallMarkRebarRequest;
import kr.co.crossarc.extract.values.wallmark.dto.WallMarkRebarKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WallMarkRebarService {
    private static final String REGEX_WALL_MARK_DELIMITER = "=";
    private static final String PREFIX_WALL_MARK = "*.Wall Mark";

    private WallMarkRebarRepository wallMarkRebarRepository;

    public WallMarkRebarService(WallMarkRebarRepository wallMarkRebarRepository) {
        this.wallMarkRebarRepository = wallMarkRebarRepository;
    }

    public Optional<String> findWallMark(String line) {
        if(this.containWallMark(line)) {
            return Optional.of(line.split(REGEX_WALL_MARK_DELIMITER)[1].split(" ")[1]);
        }
        return Optional.empty();
    }

    private boolean containWallMark(String line) {
        return line.trim().startsWith(PREFIX_WALL_MARK);
    }

    public void saveWallMarkRebar(WallMarkRebarRequest wallMarkRebarRequest) {
        WallMarkRebar wallMarkRebar = wallMarkRebarRequest.toEntity();
        this.wallMarkRebarRepository.save(wallMarkRebar);
    }

    public List<WallMarkRebar> findAll() {
        return this.wallMarkRebarRepository.findAll();
    }

    public List<WallMarkRebar> findGroupByRebars() {
        return this.distinct(this.wallMarkRebarRepository.findGroupByRebars());
    }
    /*
     * 추후 변경 필요. WallMarkRebar 구조를 변경 할 것
     * */
    private List<WallMarkRebar> distinct(List<WallMarkRebar> wallMarkRebars) {
        Map<WallMarkRebarKey, WallMarkRebar> distinct = new HashMap<>();

        for (WallMarkRebar wallMarkRebar : wallMarkRebars) {
            WallMarkRebarKey key = WallMarkRebarKey.of(wallMarkRebar);
            if (distinct.containsKey(key)) {
                WallMarkRebar compRebar = distinct.get(key);

                // set new wall mark rebar
                WallMarkRebar newWallMarkRebar = WallMarkRebar.builder()
                        .id(compRebar.getId())
                        .story(wallMarkRebar.getStory())
                        .wallId(wallMarkRebar.getWallId())
                        .build();

                // set vertical
                if(wallMarkRebar.getVerticalValue() > compRebar.getVerticalValue()) {
                    newWallMarkRebar.setVerticalValue(wallMarkRebar.getVerticalValue());
                    newWallMarkRebar.setVerticalRebarDValue(wallMarkRebar.getVerticalRebarDValue());
                    newWallMarkRebar.setVerticalRebarInterval(wallMarkRebar.getVerticalRebarInterval());
                } else {
                    newWallMarkRebar.setVerticalValue(compRebar.getVerticalValue());
                    newWallMarkRebar.setVerticalRebarDValue(compRebar.getVerticalRebarDValue());
                    newWallMarkRebar.setVerticalRebarInterval(compRebar.getVerticalRebarInterval());
                }

                // set horizon
                if(wallMarkRebar.getHorizonValue() > compRebar.getHorizonValue()) {
                    newWallMarkRebar.setHorizonValue(wallMarkRebar.getHorizonValue());
                    newWallMarkRebar.setHorizonRebarDValue(wallMarkRebar.getHorizonRebarDValue());
                    newWallMarkRebar.setHorizonRebarInterval(wallMarkRebar.getHorizonRebarInterval());
                } else {
                    newWallMarkRebar.setHorizonValue(compRebar.getHorizonValue());
                    newWallMarkRebar.setHorizonRebarDValue(compRebar.getHorizonRebarDValue());
                    newWallMarkRebar.setHorizonRebarInterval(compRebar.getHorizonRebarInterval());
                }

                distinct.put(key, newWallMarkRebar);
                continue;
            }
            distinct.put(key, wallMarkRebar);
        }

        return distinct.keySet().stream()
                .map(key -> distinct.get(key))
                .sorted(Comparator.comparing(WallMarkRebar::getStory))
                .sorted(Comparator.comparing(WallMarkRebar::getWallId))
                .collect(Collectors.toList());
    }

    /*
    * 추후 변경 필요. WallMarkRebar 구조를 변경 할 것
    * */
    private List<WallMarkRebar> distinctOld(List<WallMarkRebar> wallMarkRebars) {
        Map<WallMarkRebarKey, WallMarkRebar> distinct = new HashMap<>();

        for (WallMarkRebar wallMarkRebar : wallMarkRebars) {
            WallMarkRebarKey key = WallMarkRebarKey.of(wallMarkRebar);
            if(distinct.containsKey(key)) {
                WallMarkRebar compRebar = distinct.get(key);

                // set new wall mark rebar
                WallMarkRebar newWallMarkRebar = WallMarkRebar.builder()
                        .id(compRebar.getId())
                        .story(wallMarkRebar.getStory())
                        .wallId(wallMarkRebar.getWallId())
                        .build();

                // set vertical rebar
                if(Integer.parseInt(wallMarkRebar.getVerticalRebarDValue())
                        > Integer.parseInt(compRebar.getVerticalRebarDValue())) {
                    newWallMarkRebar.setVerticalRebarDValue(wallMarkRebar.getVerticalRebarDValue());
                    newWallMarkRebar.setVerticalRebarInterval(wallMarkRebar.getVerticalRebarInterval());
                } else if(Integer.parseInt(wallMarkRebar.getVerticalRebarDValue())
                        == Integer.parseInt(compRebar.getVerticalRebarDValue())) {
                    if(wallMarkRebar.getVerticalRebarInterval() > compRebar.getVerticalRebarInterval()) {
                        newWallMarkRebar.setVerticalRebarDValue(compRebar.getVerticalRebarDValue());
                        newWallMarkRebar.setVerticalRebarInterval(compRebar.getVerticalRebarInterval());
                    } else {
                        newWallMarkRebar.setVerticalRebarDValue(wallMarkRebar.getVerticalRebarDValue());
                        newWallMarkRebar.setVerticalRebarInterval(wallMarkRebar.getVerticalRebarInterval());
                    }
                } else {
                    newWallMarkRebar.setVerticalRebarDValue(compRebar.getVerticalRebarDValue());
                    newWallMarkRebar.setVerticalRebarInterval(compRebar.getVerticalRebarInterval());
                }

                // set horizon rebar
                if(Integer.parseInt(wallMarkRebar.getHorizonRebarDValue())
                        > Integer.parseInt(compRebar.getHorizonRebarDValue())) {
                    newWallMarkRebar.setHorizonRebarDValue(wallMarkRebar.getHorizonRebarDValue());
                    newWallMarkRebar.setHorizonRebarInterval(wallMarkRebar.getHorizonRebarInterval());
                } else if(Integer.parseInt(wallMarkRebar.getHorizonRebarDValue())
                        == Integer.parseInt(compRebar.getHorizonRebarDValue())) {
                    if(wallMarkRebar.getHorizonRebarInterval() > compRebar.getHorizonRebarInterval()) {
                        newWallMarkRebar.setHorizonRebarDValue(compRebar.getHorizonRebarDValue());
                        newWallMarkRebar.setHorizonRebarInterval(compRebar.getHorizonRebarInterval());
                    } else {
                        newWallMarkRebar.setHorizonRebarDValue(wallMarkRebar.getHorizonRebarDValue());
                        newWallMarkRebar.setHorizonRebarInterval(wallMarkRebar.getHorizonRebarInterval());
                    }
                } else {
                    newWallMarkRebar.setHorizonRebarDValue(compRebar.getHorizonRebarDValue());
                    newWallMarkRebar.setHorizonRebarInterval(compRebar.getHorizonRebarInterval());
                }

                distinct.put(key, newWallMarkRebar);
                continue;
            }
            distinct.put(key, wallMarkRebar);
        }

        return distinct.keySet().stream()
                .map(key -> distinct.get(key))
//                .sorted((o1, o2) -> (int) (o1.getId() - o2.getId()))
                .sorted(Comparator.comparing(WallMarkRebar::getStory))
                .sorted(Comparator.comparing(WallMarkRebar::getWallId))
                .collect(Collectors.toList());
    }

}
