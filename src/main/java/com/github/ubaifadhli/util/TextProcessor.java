package com.github.ubaifadhli.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {
    private static Pattern DOUBLE_CURLY_BRACES_REGEX_PATTERN = Pattern.compile("\\{\\{.*?}}");
    private static char NEW_LINE = '\n';


    public static String getProcessedText(Path path) throws IOException {
        StringBuilder featureText = new StringBuilder();

        Files.readAllLines(path).forEach(line -> {
            Matcher matcher = DOUBLE_CURLY_BRACES_REGEX_PATTERN.matcher(line);

            if (matcher.find()) {
                String propertyName = matcher.group()
                        .replace('{', ' ')
                        .replace('}', ' ')
                        .trim();

                featureText.append(getProcessedLineWithStoredData(line, propertyName));

            } else
                featureText.append(line).append(NEW_LINE);
        });

        return featureText.toString();
    }

    public static String getProcessedLineWithStoredData(String line, String propertyName) {
        List<String> storedData = PropertiesHelper.getPropertyAsList(propertyName);
        StringBuilder processedLines = new StringBuilder();

        storedData.forEach(data -> {
            String processedLine = line.replaceFirst(DOUBLE_CURLY_BRACES_REGEX_PATTERN.pattern(), data);
            processedLines.append(processedLine).append(NEW_LINE);
        });

        return processedLines.toString();
    }
}
