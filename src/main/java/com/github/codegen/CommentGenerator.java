package com.github.codegen;

import org.apache.logging.log4j.util.Strings;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 注释生成器
 * <p>
 *     根据元数据表的字段注释给JavaBean字段添加注释
 * </p>
 * @author ouyangyewei
 * @date 2021-09-01
 **/
public class CommentGenerator extends DefaultCommentGenerator {
    private boolean addRemarkComments = false;

    /**
     * 设置用户配置的参数
     * @param properties
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    /**
     * 根据表注释设置类文件注释
     * @param topLevelClass
     * @param introspectedTable
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (this.addRemarkComments) {
            topLevelClass.addJavaDocLine("/**");
            topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
            topLevelClass.addJavaDocLine(" * @author MyBatis Generator");
            topLevelClass.addJavaDocLine(" * @date " + DATE_FORMAT.format(new Date()));
            topLevelClass.addJavaDocLine(" */");
        }
    }

    /**
     * 给字段添加注释
     * @param field
     * @param introspectedTable
     * @param introspectedColumn
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        // 根据参数和备注信息判断是否添加备注信息
        if (this.addRemarkComments && StringUtility.stringHasValue(remarks)) {
            // 数据库中特殊字符需要转义
            if (remarks.contains("\"")) {
                remarks = remarks.replace("\"","'");
            }
            // model的字段添加注解
            field.addJavaDocLine("/**" + Strings.LINE_SEPARATOR + "     * " + remarks + Strings.LINE_SEPARATOR + "     */");
        }
    }
}