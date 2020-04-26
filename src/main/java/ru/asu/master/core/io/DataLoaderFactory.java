package ru.asu.master.core.io;

final class DataLoaderFactory {
    static final String EXCEL_LOADER = "excel-loader";
    static final String UNDEFINED = "undefined";

    private DataLoaderFactory() {
    }

    static DataLoader getDataLoader(String type) throws NotSupportedDataLoaderException {
        if (EXCEL_LOADER.equals(type)) {
            return new ExcelDataLoader();
        } else {
            throw new NotSupportedDataLoaderException("Не найдена реализация загрузчика данных для type = " + type);
        }
    }
}
