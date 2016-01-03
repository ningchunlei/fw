package com.sqsd.framework.mybatis;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 存在多个Datasource时，必须指定一个Primary，否则boot的autoconfigure报错
 * @author fangxm
 *
 */

@Configuration
public class PrimaryMysqlConfig {

	@Primary
    @Bean(destroyMethod="close")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        return dataSource;
    }
}
