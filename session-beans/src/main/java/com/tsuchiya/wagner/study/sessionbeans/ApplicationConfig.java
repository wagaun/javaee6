package com.tsuchiya.wagner.study.sessionbeans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class was made to test the concept of a singleton bean that manages concurrency by itself. It runs on
 * startup and the method annotated with PostConstruct will run.
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class ApplicationConfig {

    private Properties properties;

    private String myProperty;

    private Integer index;

    @PostConstruct
    public void applicationStartup() throws IOException {
        Properties prop = new Properties();
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("application-config.properties");
            prop.load(is);
        } catch (IOException e) {
            throw e;
        } catch (RuntimeException e) {
            throw e;
        }
        this.properties = prop;
        index = 0;
    }

    public String getMyProperty() {
        if(myProperty == null) {
            myProperty = properties.getProperty("com.tsuchiya.wagner.study.sessionbeans.myproperty");
        }
        return myProperty;
    }

    // Is synchronized to be thread safe
    public synchronized void incrementIndex() {
        // If PostConstruct is not executed should throw npe
        index++;
    }

    public Integer getIndex() {
        return index;
    }
}