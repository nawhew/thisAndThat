package kr.co.crossarc.extract.values.file.application;

import kr.co.crossarc.extract.values.file.ui.InputView;
import kr.co.crossarc.extract.values.wallmark.application.WallMarkRebarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class FileRunner implements ApplicationRunner {

    private final FileExtractor fileExtractor;
    private final WallMarkRebarService wallMarkRebarService;

    public FileRunner(FileExtractor fileExtractor, WallMarkRebarService wallMarkRebarService) {
        this.fileExtractor = fileExtractor;
        this.wallMarkRebarService = wallMarkRebarService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String directory = InputView.inputDirectory();

        this.readDataFile(directory);

        FileLoader.loadResult(this.createResultFileName(directory) + ".txt"
                            , this.wallMarkRebarService.findGroupByRebars());
        ExcelLoader.loadResult(this.createResultFileName(directory) + ".xlsx"
                            , this.wallMarkRebarService.findGroupByRebars());
    }

    /**
     * 데이터 파일을 읽어 데이터를 DB에 넣습니다.
     * @param directory
     * @throws IOException
     */
    private void readDataFile(String directory) throws IOException {
        for (File file : new File(directory).listFiles()) {
            log.info("Read File : " + file.getName());
            this.fileExtractor.init(file);
            this.fileExtractor.readLines();
        }
    }

    /**
     * 현재 작업 디렉토리에 현재 시간으로 결과 파일명을 만들어 반환합니다.
     * @param directory 
     * @return
     */
    private String createResultFileName(String directory) {
        return directory + "\\result_"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}
