package kr.co.crossarc.extract.value.file.rcs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.*;
import java.util.Map;

@Slf4j
public class FileExtractor {

    private final RCSFileParser rcsFileParser;
    private final ExtractedValueController extractedValueController;

    private File file;
    private FileReader fileReader;
    private BufferedReader bufferedReader;

    /**
     * constructor
     * @param fileName
     * @param rcsFileParser
     * @throws FileNotFoundException
     */
    public FileExtractor(String fileName, RCSFileParser rcsFileParser
                            , ExtractedValueController extractedValueController) throws FileNotFoundException {
        this(new File(fileName), rcsFileParser, extractedValueController);
    }

    /**
     * constructor
     * @param file
     * @throws FileNotFoundException
     */
    public FileExtractor(File file, RCSFileParser rcsFileParser, ExtractedValueController extractedValueController)
            throws FileNotFoundException {
        this.file = file;
        this.fileReader = new FileReader(this.file);
        this.bufferedReader = new BufferedReader(this.fileReader);
        this.rcsFileParser = rcsFileParser;
        this.extractedValueController = extractedValueController;
    }

    /**
     * read one file
     * @throws NumberFormatException
     * @throws IOException
     */
    public void readFile() throws NumberFormatException, IOException {
        String line = null;

        while ((line = this.readLines()) != null) {
            this.addValues(line);
        }
    }

    /**
     * read line
     * @return line
     * @throws IOException
     */
    public String readLines() throws IOException {
        StringBuilder sbLine = new StringBuilder();
        String line = null;

        if ((line = this.bufferedReader.readLine()) != null && line.length() > 0) {
            sbLine.append(line);
            readGroupFormatNextLine(sbLine, line);
        }

        return line == null
                ? null
                : sbLine.toString();
    }

    /**
     * if data format is group form, read next one line
     * @param sbLine
     * @param line
     * @throws IOException
     */
    private void readGroupFormatNextLine(StringBuilder sbLine, String line) throws IOException {
        if(this.rcsFileParser.isGroupFormat(line)) {
            sbLine.append(",").append(this.bufferedReader.readLine());
        }
    }

    /**
     * add extract values
     * @param line
     */
    public void addValues(String line) {
        if(!this.rcsFileParser.isGroupFormat(line)) {
            addValue(line);
            log.debug("add line :" + line);
            return;
        }

        for (String oneLine : line.split(":")[1].split(",")) {
            addValue(oneLine);
            log.debug("add lines :" + line);
        }
    }

    /**
     * add extract one value
     * @param line
     */
    public void addValue(String line) {
        try {
            Map.Entry<String, Map.Entry<String, String>> entry = this.rcsFileParser.parse(line);
            this.extractedValueController.addValue(entry);
        } catch (NumberFormatException e) {
            log.info("get error line : " + line);
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }

}
