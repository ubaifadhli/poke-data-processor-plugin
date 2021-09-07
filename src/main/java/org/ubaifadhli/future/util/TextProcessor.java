package org.ubaifadhli.future.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {
    private static Pattern REGEX_PATTERN = Pattern.compile("[^{}]+(?=})");

    public static String getProcessedText(Path path) throws IOException {
        StringBuilder featureText = new StringBuilder();

        Files.readAllLines(path).forEach(line -> {
            Matcher matcher = REGEX_PATTERN.matcher(line);

            if (matcher.find()) {
                String propertyName = matcher.group().trim();
                featureText.append(getExampleDataText(propertyName));

            } else
                featureText.append(line).append("\n");
        });

        return featureText.toString();
    }

    public static String getExampleDataText(String propertyName) {
        List<String> exampleData = PropertiesHelper.getPropertyAsList(propertyName);
        StringBuilder exampleDataText = new StringBuilder();

        for (String singleData : exampleData)
            exampleDataText.append("      | ").append(singleData).append(" |").append("\n");

        return exampleDataText.toString();
    }
}
