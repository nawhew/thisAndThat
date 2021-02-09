package kr.co.crossarc.extract.values.wallmark.application;

import kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebar;
import kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebarRepository;
import kr.co.crossarc.extract.values.wallmark.dto.WallMarkRebarRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WallMarkRebarService {

    private WallMarkRebarRepository wallMarkRebarRepository;

    public WallMarkRebarService(WallMarkRebarRepository wallMarkRebarRepository) {
        this.wallMarkRebarRepository = wallMarkRebarRepository;
    }

    public Optional<String> findWallMark(String line) {
        if(this.containWallMark(line)) {
            return Optional.of(line.split("=")[1].split(" ")[1]);
        }
        return Optional.empty();
    }

    private boolean containWallMark(String line) {
        return line.trim().startsWith("*.Wall Mark");
    }

    public void saveWallMarkRebar(WallMarkRebarRequest wallMarkRebarRequest) {
        WallMarkRebar wallMarkRebar = wallMarkRebarRequest.toEntity();
        this.wallMarkRebarRepository.save(wallMarkRebar);
    }

    public List<WallMarkRebar> findAll() {
        return this.wallMarkRebarRepository.findAll();
    }

    public List<WallMarkRebar> findGroupByRebars() {
        return this.wallMarkRebarRepository.findGroupByRebars();
    }

}
