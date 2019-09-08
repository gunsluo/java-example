package com.example.yamldemo;

import com.esotericsoftware.yamlbeans.YamlException;
import com.example.yamldemo.config.Feature;
import com.example.yamldemo.config.Permission;
import com.example.yamldemo.config.ConfigUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class YamlDemoComponent {

    @Value("${permission.filename}")
    private String permissionFilename;

    @Bean
    public void init() throws FileNotFoundException, YamlException {
        Permission config = ConfigUtils.read("classpath:" + permissionFilename);
        for (Feature feature : config.getFeatures()) {
            System.out.println(feature.getName());
            for (String pp : feature.getPps()) {
                System.out.println("\t" + pp);
            }
            for (String role : feature.getRoles()) {
                System.out.println("\t" + role);
            }
        }


//        Object object = reader.read();
//        System.out.println(object);
//        Map map = (Map) object;
//        System.out.println(map.get("features"));
    }
}
