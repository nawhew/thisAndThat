package kr.co.crossarc.extract.values.file.application;

import kr.co.crossarc.extract.values.wallmark.domain.WallMarkRebar;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

@Slf4j
public class ExcelLoader {


    /**
     * 철근콘크리트 결과를 엑셀에 저장합니다.
     * 추후 인터페이스로 업무 별로 하도록 분리하기.
     * @param resultFileName
     * @param result
     * @throws IOException
     */
    public static void loadResult(String resultFileName, List<WallMarkRebar> result) throws IOException {
        log.info("전체 건수 : " + result.size());

        FileOutputStream fileOutputStream = new FileOutputStream(resultFileName);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("result");

        addFirstRowColumnInfo(sheet);
        addRowWallMarkRebar(result, sheet);

        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    /**
     * 첫 줄에 컬럼 정보를 추가합니다.
     * @param sheet
     */
    private static void addFirstRowColumnInfo(XSSFSheet sheet) {
        XSSFRow columnInfoRow = sheet.createRow(0);
        columnInfoRow.createCell(0).setCellValue("wall-id");
        columnInfoRow.createCell(1).setCellValue("story");
        columnInfoRow.createCell(2).setCellValue("vertical rebar");
        columnInfoRow.createCell(3).setCellValue("horizon rebar");
        columnInfoRow.createCell(4).setCellValue("vertical value");
        columnInfoRow.createCell(5).setCellValue("horizon value");
    }

    /**
     * 두번째 줄부터 철근콘크리트의 결과 데이터를 추가합니다.
     * @param result
     * @param sheet
     */
    private static void addRowWallMarkRebar(List<WallMarkRebar> result, XSSFSheet sheet) {
        for (int i = 1; i <= result.size(); i++) {
            XSSFRow row = sheet.createRow(i);
            WallMarkRebar wallMarkRebar = result.get(i - 1);
            row.createCell(0).setCellValue(wallMarkRebar.getWallId());
            row.createCell(1).setCellValue(wallMarkRebar.getStory());
            row.createCell(2).setCellValue(wallMarkRebar.getVerticalRebarDValue()
                                                        + "@" + wallMarkRebar.getVerticalRebarInterval());
            row.createCell(3).setCellValue(wallMarkRebar.getHorizonRebarDValue()
                                                        + "@" + wallMarkRebar.getHorizonRebarInterval());
            row.createCell(4).setCellValue(wallMarkRebar.getVerticalValue());
            row.createCell(5).setCellValue(wallMarkRebar.getHorizonValue());
        }
    }
}
