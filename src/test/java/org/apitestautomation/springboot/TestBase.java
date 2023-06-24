package org.apitestautomation.springboot;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Properties;

public class TestBase{
    private Properties props;


    @Getter
    @Setter
    protected String urlPath;

    public TestBase() {
        props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            String baseUrl = props.getProperty("api.url");
            String apiPath = props.getProperty("api.path");
            StringBuilder url = new StringBuilder(200);
            url.append(baseUrl);
            url.append(apiPath);
            setUrlPath(url.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
