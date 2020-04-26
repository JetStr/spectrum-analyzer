package ru.asu.master.core.io;

import org.apache.commons.logging.Log;
import ru.asu.master.core.model.InputData;
import ru.asu.master.core.model.InputDataCreationException;
import ru.asu.master.core.utils.LoggerFactory;

import java.io.IOException;

public class IOLayer {
    private static final Log logger = LoggerFactory.createLogger("IOLayer");

    public InputData getInputData(String fileName) throws NotSupportedDataLoaderException, IOException, InputDataCreationException {
        logger.info("Загружаю данные из файла: " + fileName);
        return DataLoaderFactory.getDataLoader(getLoaderTypeFromFileName(fileName)).loadData(fileName);
    }

    private String getLoaderTypeFromFileName(String fileName) {
        if (fileName.endsWith(".xlsx")) {
            return DataLoaderFactory.EXCEL_LOADER;
        } else {
            return DataLoaderFactory.UNDEFINED;
        }
    }
}
