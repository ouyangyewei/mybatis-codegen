package com.github.codegen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ouyangyewei
 * @date 2021-08-31
 **/
@Configuration
@MapperScan({"com.github"})
public class MyBatisConfig {
}
