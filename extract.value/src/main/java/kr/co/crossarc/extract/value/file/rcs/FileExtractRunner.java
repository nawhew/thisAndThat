package kr.co.crossarc.extract.value.file.rcs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@Slf4j
public class FileExtractRunner implements ApplicationRunner {

    private final ExtractedValueController extractedValueController;
    private final RCSFileParser rcsFileParser;

    private Scanner scanner;

    @Autowired
    public FileExtractRunner(ExtractedValueController extractedValueController, RCSFileParser rcsFileParser) {
        this.extractedValueController = extractedValueController;
        this.rcsFileParser = rcsFileParser;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // get File from system.in's file name
        File gotFile = this.getFile();

        for (File file : this.getFilterFileList(gotFile)) {
            FileExtractor fileExtractor = new FileExtractor(file, this.rcsFileParser, this.extractedValueController);
            fileExtractor.read();
        }

        this.extractedValueController.printSummary();

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

    /**
     * check if it's a directory, and return file list.
     * if parameter is not directory, return file list have one file.
     * @param file
     * @return
     */
    public File[] getFileList(File file) {
        if (file.isDirectory()) {
            return file.listFiles();
        }
        return new File[]{file};
    }

    /**
     * Overloading method
     * @param file
     * @return
     */
    public List<File> getFilterFileList(File file) {
        return this.getFilterFileList(this.getFileList(file));
    }

    /**
     * get FileList only file
     * @param files
     * @return
     */
    public List<File> getFilterFileList(File[] files) {
        List<File> fileList = new ArrayList<>();

        for (File file : files) {
            if(file.isFile()) {
                fileList.add(file);
            }
        }
        return fileList;
    }
}
