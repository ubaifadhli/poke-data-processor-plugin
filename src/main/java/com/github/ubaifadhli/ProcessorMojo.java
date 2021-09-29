package com.github.ubaifadhli;

import com.github.ubaifadhli.util.TextProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import com.github.ubaifadhli.util.DirectoryConstant;
import com.github.ubaifadhli.util.FileHelper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Mojo(name = "process-data", defaultPhase = LifecyclePhase.GENERATE_TEST_RESOURCES)
public class ProcessorMojo extends AbstractMojo {
    private final String FEATURE_EXTENSION = ".feature";

    public static Duration getBetween(long startTime, long endTime) {
        Timestamp startTimestamp = new Timestamp(startTime);
        Timestamp endTimestamp = new Timestamp(endTime);

        return Duration.between(startTimestamp.toInstant(), endTimestamp.toInstant());
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        long startTime = System.currentTimeMillis();

        try {
            List<String> templateFilenames = FileHelper.getTemplateFilenames()
                    .stream()
                    .filter(filename -> filename.contains(FEATURE_EXTENSION))
                    .collect(Collectors.toList());

            log.info("Features template has been loaded.");

            for (String filename : templateFilenames) {
                Path templatePath = Paths.get(DirectoryConstant.TEMPLATE_FEATURE_FOLDER + filename);

                try {
                    String processedText = TextProcessor.getProcessedText(templatePath);

                    Path generatedPath = Paths.get(DirectoryConstant.GENERATED_FEATURE_FOLDER + filename);

                    try {
                        FileHelper.saveTextToFile(processedText, generatedPath);

                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error("Failed to save text to file.");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    log.error("Failed to process feature text.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            log.error("Failed to load features template.");
        }

        long endTime = System.currentTimeMillis();

        Duration elapsedTime = getBetween(startTime, endTime);

        StringBuilder executeDuration = new StringBuilder();

        if (elapsedTime.getSeconds() == 0L) {
            executeDuration.append("less than 1 second.");
        } else if (elapsedTime.getSeconds() == 1L) {
            executeDuration.append("1 second.");
        } else if (elapsedTime.getSeconds() == 2L) {
            executeDuration.append(elapsedTime.getSeconds()).append(" seconds.");
        }

        log.info("Feature file has been processed in {}", executeDuration);
    }
}
