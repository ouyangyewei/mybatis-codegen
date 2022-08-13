package com.github.codegen;

import com.github.dao.domain.Project;
import com.github.dao.domain.ProjectExample;
import com.github.dao.mapper.ProjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MBGTester {

    @Autowired
    private ProjectMapper projectMapper;

    private static int projectId;

    @Test
    @Order(1)
    public void testInsert() {
        // insert：插入完整记录，要求字段完整
        // insertSelective：插入记录，允许插入部分字段

        Project project = new Project();
        project.setCode(1L);
        project.setName("PROJ-1");
        project.setDescription("testing desc");
        project.setFlag((byte) 1);
        project.setCreateTime(new Date());
        project.setUpdateTime(new Date());
        int nEffectRows = this.projectMapper.insert(project);

        // 获取刚插入的记录的自增主键ID
        projectId = project.getId();
        Assert.isTrue(nEffectRows == 1);
    }

    /**
     * 条件查询
     */
    @Test
    @Order(2)
    public void testConditionQuery() {
        // selectByPrimaryKey：根据主键查询
        // selectByExample：根据条件查询

        ProjectExample example = new ProjectExample();
        example.createCriteria().andCodeIsNotNull();
        List<Project> projects = this.projectMapper.selectByExample(example);
        Assert.notEmpty(projects);
    }

    /**
     * 分页查询
     */
    @Test
    @Order(3)
    public void testPagingQuery() {
        ProjectExample example = new ProjectExample();
        example.createCriteria().andCodeIsNotNull();
        example.setOffset(0);
        example.setLimit(100);
        List<Project> projects = this.projectMapper.selectByExample(example);
        Assert.notEmpty(projects);
    }

    @Test
    @Order(4)
    public void testUpdate() {
        // updateByPrimaryKey：根据主键更新
        // updateByPrimaryKeySelective：根据主键更新部分字段
        // updateByExample：根据条件更新
        // updateByExampleSelective：根据条件更新部分字段

        Project project = new Project();
        project.setId(projectId);
        project.setCode(100L);
        project.setName("PROJ-1");
        project.setCreateTime(new Date());
        project.setUpdateTime(new Date());

        int nEffectRows = this.projectMapper.updateByPrimaryKey(project);
        Assert.isTrue(nEffectRows == 1);
    }

    @Test
    @Order(5)
    public void testDelete() {
        // deleteByPrimaryKey：根据主键删除
        // deleteByExample：根据条件删除

        int nEffectRows = this.projectMapper.deleteByPrimaryKey(projectId);
        Assert.isTrue(nEffectRows == 1);
    }
}
