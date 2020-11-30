package kr.co.crossarc.extract.value.file.rcs;

import lombok.extern.slf4j.Slf4j;

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
    public void read() throws NumberFormatException, IOException {
        String line = null;
        int lineNumber = 0;
        while ((line = this.bufferedReader.readLine()) != null) {
            try {
                lineNumber++;
                Map.Entry<String, Map.Entry<String, String>> entry = this.rcsFileParser.parse(line);
                this.extractedValueController.addValue(entry);
            } catch (NumberFormatException e) {
                log.info("get line(" + lineNumber + ") : " + line);
                e.printStackTrace();
            }
        }
    }

}
