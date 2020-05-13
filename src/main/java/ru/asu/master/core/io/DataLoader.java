package ru.asu.master.core.io;

import ru.asu.master.core.model.InputData;
import ru.asu.master.core.model.InputDataCreationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface DataLoader {

    InputData loadData(String fileName) throws IOException, InputDataCreationException;

    InputData loadData(File file) throws IOException, InputDataCreationException;
}
