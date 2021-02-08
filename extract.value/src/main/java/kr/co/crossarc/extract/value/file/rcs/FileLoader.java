package kr.co.crossarc.extract.value.file.rcs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLoader {

    private File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public void loadSummary(String fileName) {

    }

    public void loadLine(String line) throws IOException {
        this.bufferedWriter.write(line);
        this.bufferedWriter.newLine();
    }
}
