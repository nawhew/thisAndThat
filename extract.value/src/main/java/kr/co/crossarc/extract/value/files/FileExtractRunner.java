package kr.co.crossarc.extract.value.files;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Scanner;

@Component
@Slf4j
public class FileExtractRunner implements ApplicationRunner {

    private Scanner scanner;

    public FileExtractRunner() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // get File from system.in's file name
        File gotFile = this.getFile();

    }

    /**
     * get File from system.in's file name
     * @return file
     */
    public File getFile() {
        File file = null;
        while(file == null) {
            System.out.println("파일 혹은 디렉토리 경로를 입력하세요.");
            String fileName = this.scanner.next();
            file = this.makeFile(fileName);
        }
        return file;
    }

    /**
     * make file
     * @param fileName
     * @return file
     */
    public File makeFile(String fileName) {
        File file = null;
        try {
            file = new File(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

}
