package com.github.codegen;

import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Refer: https://mybatis.org/generator/running/runningWithJava.html
 * @author ouyangyewei
 * @date 2021-08-31
 **/
public class MyBatisGenerator {

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        try (InputStream inputStream = MyBatisGenerator.class.getResourceAsStream("/generator.xml")) {
            ConfigurationParser configurationParser = new ConfigurationParser(warnings);
            Configuration configuration = configurationParser.parseConfiguration(inputStream);
            DefaultShellCallback callback = new DefaultShellCallback(true);

            org.mybatis.generator.api.MyBatisGenerator generator = new org.mybatis.generator.api.MyBatisGenerator(configuration, callback, warnings);
            generator.generate(null);

            for (String warning : warnings) {
                System.out.println(warning);
            }
        }
    }
}

