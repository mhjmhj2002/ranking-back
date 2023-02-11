package com.mhj.ranking.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfiguration {

	@Bean(name = "customDataSource")
	@ConfigurationProperties("spring.datasource")
	public DataSource customDataSource() {
//		return DataSourceBuilder.create().build();

		HikariDataSource hikari = new HikariDataSource();
		hikari.setJdbcUrl("jdbc:mysql://localhost:3306/mhj06?useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false");
		hikari.addDataSourceProperty("driverClassName", "com.mysql.jdbc.Driver");
		hikari.addDataSourceProperty("username", "root");
		hikari.addDataSourceProperty("password", "2y244y26");

		hikari.setMaximumPoolSize(10);
		hikari.setMinimumIdle(10);
		hikari.setIdleTimeout(10000);

		hikari.addDataSourceProperty("cachePrepStmts", true);
		hikari.addDataSourceProperty("prepStmtCacheSize", 250);
		hikari.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
		hikari.addDataSourceProperty("useServerPrepStmts", true);
		hikari.addDataSourceProperty("cacheResultSetMetadata", true);
		return hikari;
	}
}
