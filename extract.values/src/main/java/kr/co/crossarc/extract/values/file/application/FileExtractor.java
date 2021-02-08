package kr.co.crossarc.extract.values.file.application;

import kr.co.crossarc.extract.values.wallmark.application.WallMarkRebarService;
import kr.co.crossarc.extract.values.wallmark.dto.WallMarkRebarOrigin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
public class FileExtractor {

    private final WallMarkRebarService wallMarkRebarService;

    private BufferedReader bufferedReader;

    public FileExtractor(WallMarkRebarService wallMarkRebarService) {
        this.wallMarkRebarService = wallMarkRebarService;
    }

    public void init(String fileName) throws FileNotFoundException {
        this.init(new File(fileName));
    }

    public void init(File file) throws FileNotFoundException {
        this.bufferedReader = new BufferedReader(new FileReader(file));
    }

    public void readLines() throws IOException {
        String line = null;
        String wallMark = null;

        while((line = this.bufferedReader.readLine()) != null && this.isNotSkipLine(line)) {
            try {
                line = line.trim();

                if(this.wallMarkRebarService.findWallMark(line).isPresent()) {
                    wallMark =  this.wallMarkRebarService.findWallMark(line).get();
                    log.debug("벽아이디 : [" + wallMark + "]");
                    continue;
                }
                this.wallMarkRebarService.saveWallMarkRebar(new WallMarkRebarOrigin(wallMark, line));
            } catch (Exception e) {
                log.error("오류 난 줄 : [" + line + "]");
                e.printStackTrace();
            }
        }
    }

    private boolean isNotSkipLine(String line) {
        line = line.trim();
        return line.length() != 0
                && !line.startsWith("-")
                && !line.startsWith("=");
    }

    public void printTemp() {
        this.wallMarkRebarService.findAll().stream().forEach(System.out::println);
    }

}
