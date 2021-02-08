package kr.co.crossarc.extract.values.file.application;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class FileRunner implements ApplicationRunner {

    private final FileExtractor fileExtractor;

    public FileRunner(FileExtractor fileExtractor) {
        this.fileExtractor = fileExtractor;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        this.fileExtractor.init("C:\\Users\\we hwan\\Documents\\카카오톡 받은 파일\\T1.txt");

        this.fileExtractor.readLines();

        this.fileExtractor.printTemp();
    }
}
