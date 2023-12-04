package com.example.restassuredproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class FileUtils {

    private FileUtils(){

    }

    public static File getResourceFile(String file) {
        return new File(Objects.requireNonNull(FileUtils.class.getClassLoader().getResource(file)).getPath());
    }

}
