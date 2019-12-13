package io.batcloud.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

import tk.mybatis.spring.annotation.MapperScan;


@Configuration
@MapperScan(basePackages = GoodLibraryDSConfig.PACKAGE, sqlSessionFactoryRef = GoodLibraryDSConfig.DS_NAME + "SqlSessionFactory")
public class GoodLibraryDSConfig {

	static final String DS_NAME = "ads_goods_library";
    // 精确到 AdsExchangeRevenue 目录，以便跟其他数据源隔离

    static final String pkgName = "adsGoodsLibrary";
    static final String PACKAGE = "io.batcloud.mapper." + pkgName;
    static final String MAPPER_LOCATION = "classpath:mapper/" + DS_NAME + "/**.xml";

 
    @Bean(name = DS_NAME + "DataSource")
    @ConfigurationProperties(prefix="spring.datasource.ads-good-library") //配置的前缀
    public DataSource GoodLibraryDataSource() {
        DruidDataSource ds = DruidDataSourceBuilder.create().build();
        return ds;
    }
 
    @Bean(name = DS_NAME + "TransactionManager")
    public DataSourceTransactionManager AdsExchangeRevenueTransactionManager() {
        return new DataSourceTransactionManager(GoodLibraryDataSource());
    }
 
    @Bean(name = DS_NAME + "SqlSessionFactory")
    public SqlSessionFactory AdsExchangeRevenueSqlSessionFactory(@Qualifier(DS_NAME + "DataSource") DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}