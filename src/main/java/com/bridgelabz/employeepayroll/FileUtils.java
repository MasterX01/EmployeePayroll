package com.bridgelabz.employeepayroll;

import java.io.File;

public class FileUtils {
    // To delete files
    public static boolean deleteFiles(File filesToBedeleted) {
        File path[] = filesToBedeleted.listFiles();
        if (path != null) {
            for (File eachFile : path) {
                deleteFiles(eachFile);
            }
        }
        return filesToBedeleted.delete();
    }
}
