package com.mspr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ContentSuppressor {

    public void deleteFiles(List<File> filesToBeDeleted){
        for (File file: filesToBeDeleted){
            file.delete();
        }
    }
    public List<File> getAllFilesInAFolder(String directoryName) throws IOException {
        List<File> filesInFolder = Files.walk(Paths.get("out/"+directoryName))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        return filesInFolder;
    }
}
