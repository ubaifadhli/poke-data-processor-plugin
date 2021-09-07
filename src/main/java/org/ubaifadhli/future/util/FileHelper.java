package org.ubaifadhli.future.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {
    public static List<String> getTemplateFilenames() throws IOException {
        List<String> filenames = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(DirectoryConstant.TEMPLATE_FEATURE_FOLDER))) {
            for (Path path : stream)
                if (!Files.isDirectory(path))
                    filenames.add(path.getFileName().toString());
        }

        return filenames;
    }

    public static void saveTextToFile(String text, Path path) throws IOException {
        Files.createDirectories(path.getParent());

        BufferedWriter bufferedWriter = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        bufferedWriter.write(text);
        bufferedWriter.flush();
    }
}
