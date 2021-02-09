package kr.co.crossarc.extract.values.file.application;

import kr.co.crossarc.extract.values.file.ui.InputView;
import kr.co.crossarc.extract.values.wallmark.application.WallMarkRebarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
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

        for (File file : new File(directory).listFiles()) {

            log.info("Read File : " + file.getName());
            this.fileExtractor.init(file);

            this.fileExtractor.readLines();
        }

        FileLoader.loadResult(this.createResultFileName(directory)
                            , this.wallMarkRebarService.findGroupByRebars());
    }

    private String createResultFileName(String directory) {
        return directory + "\\result_"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
                + ".txt";
    }
}
