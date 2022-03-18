package com.parshin.composite.reader;

import com.parshin.composite.exception.CustomException;

public interface CustomReader {
    String readFile(String fileName) throws CustomException;
}
