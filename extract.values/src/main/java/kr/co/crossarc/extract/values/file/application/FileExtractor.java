package kr.co.crossarc.extract.values.file.application;

import kr.co.crossarc.extract.values.wallmark.application.WallMarkRebarService;
import kr.co.crossarc.extract.values.wallmark.dto.WallMarkRebarRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;

import java.io.*;

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

        while((line = this.bufferedReader.readLine()) != null) {
            try {
                line = line.trim();
                log.trace(line);
                 if(this.isSkipLine(line)) {
                     continue;
                 }
                if(this.wallMarkRebarService.findWallMark(line).isPresent()) {
                    wallMark =  this.wallMarkRebarService.findWallMark(line).get();
                    log.info("벽아이디 : [" + wallMark + "]");
                    continue;
                }
                if(wallMark == null) {
                    continue;
                }
                this.wallMarkRebarService.saveWallMarkRebar(new WallMarkRebarRequest(wallMark, line));
            } catch (Exception e) {
                log.trace("오류 난 줄 : [" + line + "]");
                log.trace(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    private boolean isSkipLine(String line) {
        return line.length() <= 0
                || line.startsWith("-")
                || line.startsWith("=")
//                && this.startPattern(line)
                ;
    }

    private boolean startPattern(String line) {
        return line.startsWith(" \\d+F") || line.startsWith(" B\\d+") || line.startsWith(" PIT");
    }

    public void printTemp() {
        this.wallMarkRebarService.findGroupByRebars().stream().forEach(System.out::println);
    }

}
