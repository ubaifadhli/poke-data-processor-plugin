# poke-data-processor-plugin

A simple Maven plugin to populate Cucumber feature files with provided data from properties.

## Prerequisites
- Your testing project uses Cucumber
- You have an GitHub account

## Usage Guide
Create `settings.xml` file in `~/.m2/` directory and copy the snippet below. If you already have one, adjust accordingly. 
```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <pluginRepositories>
        <pluginRepository>
          <id>github</id>
          <url>https://maven.pkg.github.com/ubaifadhli/*</url>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>YOUR_GITHUB_USERNAME</username>
      <password>YOUR_GITHUB_PERSONAL_ACCESS_TOKEN</password>
    </server>
  </servers>
</settings>
```
Note that the snippet uses `pluginRepositories` instead of the usual `repositories` because this project will be used as a Maven plugin.
Also paste your GitHub username and PAT there. Your GitHub PAT also needs to have `read:packages` scope access (more about this [here](https://docs.github.com/en/packages/learn-github-packages/about-permissions-for-github-packages#about-scopes-and-permissions-for-package-registries)). 

After that, include the plugin into your project by adding this snippet into your `pom.xml` file.
```
    <build>
        <plugins>
            <plugin>
                <groupId>org.ubaifadhli.future</groupId>
                <artifactId>poke-data-processor-plugin</artifactId>
                <version>1.0.6</version>
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
