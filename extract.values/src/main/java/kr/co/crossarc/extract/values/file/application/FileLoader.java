package kr.co.crossarc.extract.values.file.application;

import kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebar;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class FileLoader {

    public static void loadResult(String resultFileName, List<WallMarkRebar> result) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(resultFileName)));
        log.info("전체 건수 : " + result.size());
        for (WallMarkRebar wallMarkRebar : result) {
            bufferedWriter.write(wallMarkRebar.toStringForPrintResult());
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }
}
