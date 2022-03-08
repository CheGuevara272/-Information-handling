package com.parshin.composite.reader.impl;

import com.parshin.composite.exception.CustomException;
import com.parshin.composite.reader.CustomReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;

public class CustomFileReader implements CustomReader {
    private static final Logger log = LogManager.getLogger();
    private static final CustomFileReader instance = new CustomFileReader();

    private CustomFileReader(){
    }

    public static CustomFileReader getInstance() {
        return instance;
    }

    @Override
    public String readFile(String fileName) throws CustomException {
        File file = new File(fileName);
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            log.log(Level.ERROR, "file wasn't read", e);
            throw new CustomException("file wasn't read", e);
        }
    }
}
