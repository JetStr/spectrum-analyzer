package ru.asu.master.core.io;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.asu.master.core.model.InputData;
import ru.asu.master.core.model.InputDataCreationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDataLoader implements DataLoader {

    @Override
    public InputData loadData(String fileName, String idealFileName) throws IOException, InputDataCreationException {
        return loadData(new File(fileName), new File(idealFileName));
    }

    @Override
    public InputData loadData(File inputData, File idealSpectrum) throws IOException, InputDataCreationException {
        return new InputData(parseXlsFile(inputData), parseXlsFile(idealSpectrum));
    }

    private static double[] parseXlsFile(File file) throws IOException {
        double[] data;
        try (XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(file))) {
            XSSFSheet sheet = book.getSheetAt(0);
            data = new double[sheet.getLastRowNum()];
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                data[i] = sheet.getRow(i).getCell(0).getNumericCellValue();
            }
        }
        return data;
    }
}
