package com.sample.postgresql1.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import argo.jdom.JdomParser;
import argo.jdom.JsonNode;
import argo.jdom.JsonRootNode;

/**
 * CloudFoundry 의 App 으로 deploy되면서 binding 받은 AppService 정보를 직접 파싱하여 datasource 를 정의하고 싶다면, 이 클래스처럼 구성하면 됨. 
 * spring profile 값은 명시적인 지정이 없으면 default 이고, CloudFoundry 에 deploy 될 때는 cloud 라는 값으로 지정되므로
 * 이 config class 가 사용되기 위해서는 spring profile 을 cloud-myconfig 로 지정하여야 함. ( --spring.profiles.active=cloud-myconfig ) 
 */

@Configuration
@Profile("cloud-myconfig")
public class DbServiceConfig {
	
	Logger logger = LoggerFactory.getLogger(DbServiceConfig.class);
	
	@Bean
	public DataSource dataSource() {
		// AppService 에 대한 Binding 정보는 App 에게 VCAP_SERVICES 라는 이름의 환경변수로 전달이되며, 
		// Java 에서는 System.getenv() 함수를 사용하여 얻오올 수 있음.
		String vcap_services = System.getenv("VCAP_SERVICES");
        
        logger.info( "VCAP_SERVICES={}", vcap_services );

        DataSource db = null;

        if (vcap_services != null && vcap_services.length() > 0) {
        	
            try {

                // JSON parser 를 사용하여, datasource 를 구성하기 위한 정보들을 얻어옴.
                JsonRootNode root = new JdomParser().parse(vcap_services);
                
                JsonNode dbNode = root.getNode( "mysql_service" );
                JsonNode credentials = dbNode.getNode(0).getNode( "credentials" );
                
                logger.info("Credentials={}", credentials);
                
                // Grab login info for MySQL from the credentials node
                String hostname = credentials.getStringValue("host");
                logger.info("host={}", hostname);

                String name = credentials.getStringValue("database");
                logger.info("database={}", name);
                
                String user = credentials.getStringValue("username");
                logger.info("username={}", user);
                
                String password = credentials.getStringValue("password");
                logger.info("password={}", password);
                
                String jdbcUrl = credentials.getStringValue("jdbc_url");
                logger.info("jdbc_url={}", jdbcUrl);
                
                String port = credentials.getStringValue("port");
                logger.info("port={}", port);

                String dbUrl = "jdbc:mysql://" + hostname + ":"  + port + "/" + name;
                logger.info("result dbUrl={}", dbUrl);

                logger.info("Connecting to Mysql...");
                
                db = DataSourceBuilder.create()
	            	.driverClassName( "com.mysql.jdbc.Driver" )
	            	.url( dbUrl )
	            	.username( user )
	            	.password( password )
	            	.build();
            } 
            catch ( Exception ex ) {
            	logger.info("Caught error:\n{}" + ex);
                ex.printStackTrace();
            }
        }
        
        return db;  		
	}

}
