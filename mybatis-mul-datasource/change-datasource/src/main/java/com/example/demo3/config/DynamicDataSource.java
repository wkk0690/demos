package com.example.demo3.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.stat.DruidDataSourceStatManager;
import com.example.demo3.pojo.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Set;

/**
 * @auther zhoupan
 * @date 2019/4/8 20:43
 * @info
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    private boolean debug = true;
    private Map<Object, Object> dynamicTargetDataSources;
    private Object dynamicDefaultTargetDataSource;

    @Override
    protected Object determineCurrentLookupKey() {
        String datasource = DBContextHolder.getDataSource();
        if (StringUtils.isEmpty(datasource)) {
            //throw new RuntimeException("未获取到数据源");
            System.out.println("未获取到数据源");
        }
        if (!this.dynamicTargetDataSources.containsKey(datasource)) {
            //throw new RuntimeException("不存在的数据源");
            System.out.println("不存在的数据源");
        }
        return datasource;
    }

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        this.dynamicTargetDataSources = targetDataSources;
    }

    // 创建数据源
    public boolean createDataSource(Map map) throws Exception {

        String key = map.get("datasourceId") + "";
        String driveClass = "com.mysql.jdbc.Driver";
        String url = map.get("url") + "";
        String username = map.get("username") + "";
        String password = map.get("password") + "";

        Class.forName(driveClass);
        DriverManager.getConnection(url, username, password);// 相当于连接数据库

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName(key);
        druidDataSource.setDriverClassName(driveClass);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.init();
        this.dynamicTargetDataSources.put(key, druidDataSource);
        setTargetDataSources(this.dynamicTargetDataSources);// 将map赋值给父类的TargetDataSources
        super.afterPropertiesSet();// 将TargetDataSources中的连接信息放入resolvedDataSources管理
        log.info(String.format("数据源%s创建成功", key));
        return true;
    }

    /**
     * Specify the default target DataSource, if any.
     * <p>
     * The mapped value can either be a corresponding
     * {@link javax.sql.DataSource} instance or a data source name String (to be
     * resolved via a {@link #setDataSourceLookup DataSourceLookup}).
     * <p>
     * This DataSource will be used as target if none of the keyed
     * {@link #setTargetDataSources targetDataSources} match the
     * {@link #determineCurrentLookupKey()} current lookup key.
     */
    @Override
    public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        this.dynamicDefaultTargetDataSource = defaultTargetDataSource;
    }

    /**
     * @param debug
     *            the debug to set
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @return the dynamicTargetDataSources
     */
    public Map<Object, Object> getDynamicTargetDataSources() {
        return dynamicTargetDataSources;
    }

    /**
     * @param dynamicTargetDataSources
     *            the dynamicTargetDataSources to set
     */
    public void setDynamicTargetDataSources(Map<Object, Object> dynamicTargetDataSources) {
        this.dynamicTargetDataSources = dynamicTargetDataSources;
    }

    /**
     * @return the dynamicDefaultTargetDataSource
     */
    public Object getDynamicDefaultTargetDataSource() {
        return dynamicDefaultTargetDataSource;
    }

    /**
     * @param dynamicDefaultTargetDataSource
     *            the dynamicDefaultTargetDataSource to set
     */
    public void setDynamicDefaultTargetDataSource(Object dynamicDefaultTargetDataSource) {
        this.dynamicDefaultTargetDataSource = dynamicDefaultTargetDataSource;
    }
}
