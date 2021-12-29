# poke-data-processor-plugin

A simple Maven plugin to populate Cucumber feature files with provided data from properties.

Published on JitPack.

## Prerequisites
- Your testing project uses Cucumber and generates JSON as an output

## Usage Guide
Add this snippet into your `pom.xml` file. `<pluginRepositories>` tag is needed because I use JitPack to publish this plugin.
```
    <pluginRepositories>
        <pluginRepository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </pluginRepository>
    </pluginRepositories>

    <build>
        <plugins>
            <plugin>
                <groupId>org.ubaifadhli.future</groupId>
                <artifactId>poke-data-processor-plugin</artifactId>
                <version>1.0.8</version>
                <executions>
                    <execution>
                        <id>process-data</id>
                        <phase>generate-test-resources</phase>
                        <goals>
                            <goal>process-data</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

You're pretty much done. Congratulations!

This plugin will be executed in `generate-test-resources` phase.

## File Path Information
| Path | Description | 
| ----------- | ----------- |
| src/test/resources/features-template | Path where my plugin expects the feature templates will be |
| src/test/resources/features | Path where the feature files would be generated |
