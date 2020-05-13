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
    public InputData loadData(String fileName) throws IOException, InputDataCreationException {
        return loadData(new File(fileName));
    }

    @Override
    public InputData loadData(File file) throws IOException, InputDataCreationException {
        double[] waveLengths;
        double[] signals;
        try (XSSFWorkbook book = new XSSFWorkbook(new FileInputStream(file))) {
            XSSFSheet sheet = book.getSheetAt(0);
            //todo последняя ячейка в xlsx не читается
            waveLengths = new double[sheet.getLastRowNum()];
            signals = new double[sheet.getLastRowNum()];
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                waveLengths[i] = sheet.getRow(i).getCell(0).getNumericCellValue();
                signals[i] = sheet.getRow(i).getCell(1).getNumericCellValue();
            }
        }
        return new InputData(waveLengths, signals);
    }
}
