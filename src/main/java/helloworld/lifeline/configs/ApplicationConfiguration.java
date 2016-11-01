package helloworld.lifeline.configs;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.catalina.session.StandardSessionFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import helloworld.lifeline.controller.LoginController;
import helloworld.lifeline.misc.Logger;
import inti.ws.spring.exception.client.ForbiddenException;

@Configuration
@EnableTransactionManagement
/***
 * For sessopn Management
 * 
 * @author anoop
 *
 */
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

	@Value("${db.driver}")
	private String DB_DRIVER;

	@Value("${db.password}")
	private String DB_PASSWORD;

	@Value("${db.url}")
	private String DB_URL;

	@Value("${db.username}")
	private String DB_USERNAME;

	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto}")
	private String HIBERNATE_HBM2DDL_AUTO;

	@Value("${entitymanager.packagesToScan}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
		hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		sessionFactoryBean.setHibernateProperties(hibernateProperties);

		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	/***
	 * Registering our Interceptor to intercept all @Controller requests before
	 * they are handled by their respective controllers
	 * 
	 * @author anoop
	 *
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TransactionInterceptor());

	}

}

/***
 * For session Management
 * 
 * @author anoop
 *
 */
class TransactionInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = Logger.getInstance(TransactionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("Request "+request.getRequestURI()+" preprocessing in TransactionInterceptor");
		StandardSessionFacade session = (StandardSessionFacade) request.getSession();
		if (request.getRequestURI().toLowerCase().contains(("/error"))) {
			logger.error("Request for error page : Request preprocessing in TransactionInterceptor");
			return true;
		}

		if (!request.getRequestURI().toLowerCase().contains(("/home")) && session.getAttribute("user") == null) {

			logger.error("Request "+ request.getRequestURI() +" rejected :User is not logged-in");
			throw new ForbiddenException("User is not logged-in");
		}
		logger.info("Valid request  "+request.getRequestURI()+" : Request preprocessing in TransactionInterceptor");
		return true;
	}
}
