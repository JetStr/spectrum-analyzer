package ru.asu.master.core.io;

import org.apache.commons.logging.Log;
import ru.asu.master.core.model.InputData;
import ru.asu.master.core.model.InputDataCreationException;
import ru.asu.master.core.utils.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOLayer {
    private static final Log logger = LoggerFactory.createLogger("IOLayer");

    public InputData getInputData(String fileName, String idealFileName) throws NotSupportedDataLoaderException, IOException, InputDataCreationException {
        logger.info("Загружаю данные из файла: " + fileName);
        return DataLoaderFactory.getDataLoader(getLoaderTypeFromFileName(fileName)).loadData(fileName, idealFileName);
    }

    public void saveData(String fileName, double[] data) {
        logger.info("Сохраняю данные в файл: " + fileName);
        File saves = new File("saves");
        if (!saves.exists()) {
            saves.mkdir();
        }
        try (FileWriter writer = new FileWriter(saves.getAbsolutePath() + "/" + fileName)) {
            for (double elem : data) {
                writer.write(elem + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            logger.error(e, e);
        }
    }

    public List<List<String>> parseSavedFiles() {
        File saves = new File("saves");
        if (!saves.exists()) {
            return Collections.emptyList();
        }

        String[] list = saves.list();
        if (list == null || list.length == 0) {
            return Collections.emptyList();
        }

        List<List<String>> data = new ArrayList<>();
        for (String fileName : list) {
            logger.info("Разбираю файл: " + fileName);
            List<String> fileData = new ArrayList<>();
            Matcher matcher = Pattern.compile("^(.*)_t1(.*)_t2(\\d+.\\d+)_(t3(.*)_)?mnk(.*)$").matcher(fileName);
            if (matcher.find()) {
                fileData.add(matcher.group(1));
                fileData.add(matcher.group(2));
                fileData.add(matcher.group(3));
                fileData.add(matcher.group(5));
                fileData.add(matcher.group(6));
                data.add(fileData);
            }
        }
        logger.info(data);
        return data;
    }

    private String getLoaderTypeFromFileName(String fileName) {
        if (fileName.endsWith(".xlsx")) {
            return DataLoaderFactory.EXCEL_LOADER;
        } else {
            return DataLoaderFactory.UNDEFINED;
        }
    }
}
