package com.gp.service

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource
import org.springframework.context.annotation.*
import org.springframework.orm.hibernate5.HibernateTransactionManager
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScans(
    value = [
        ComponentScan("com.metricstream.grc")
    ]
)
class ApplicationConfig {
    @Autowired
    lateinit var env: Environment

    @Bean
    fun getDataSource(): DataSource {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = env!!.getProperty("db.driver")
        dataSource.url = env!!.getProperty("db.url")
        dataSource.username = env!!.getProperty("db.username")
        dataSource.password = env!!.getProperty("db.password")
        return dataSource
    }

    @Bean
    fun getSessionFactory(): LocalSessionFactoryBean {
        val factoryBean = LocalSessionFactoryBean()
        factoryBean.setDataSource(getDataSource())
        factoryBean.setPackagesToScan("com.metricstream.grc.service.dao")

        val props = Properties()
        props["hibernate.show_sql"] = env!!.getProperty("hibernate.show_sql")
        props["hibernate.hbm2ddl.auto"] = env!!.getProperty("hibernate.hbm2ddl.auto")
        props["hibernate.dialect"] = env!!.getProperty("hibernate.dialect")

        factoryBean.hibernateProperties = props
        return factoryBean
    }

    @Bean
    fun getTransactionManager(): HibernateTransactionManager {
        val transactionManager = HibernateTransactionManager()
        transactionManager.sessionFactory = getSessionFactory().getObject()
        return transactionManager
    }
}