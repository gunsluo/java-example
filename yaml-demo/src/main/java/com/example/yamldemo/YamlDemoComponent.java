package com.example.yamldemo;

import com.esotericsoftware.yamlbeans.YamlException;
import com.example.yamldemo.config.*;
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

        System.out.println("-------------feature");
        for (Feature feature : config.getFeatures()) {
            System.out.println(feature.getName());
            for (String pp : feature.getPps()) {
                System.out.println("\t" + pp);
            }
            for (String role : feature.getRoles()) {
                System.out.println("\t" + role);
            }
        }

        System.out.println("-------------pp");
        for (PP pp : config.getPps()) {
            System.out.println(pp.getName());
            System.out.println(pp.getDescription());
            System.out.println(pp.getResources());
            System.out.println(pp.getActions());
            for (Condition condition : pp.getConditions()) {
                System.out.println("\t" + condition.getName());
                System.out.println("\t" + condition.getType());
                for (Attribute attribute : condition.getAttributes()) {
                    System.out.println("\t\t" + attribute.getName());
                    System.out.println("\t\t" + attribute.getType());
                    System.out.println("\t\t" + attribute.isRequired());
                }
            }
        }

        System.out.println("-------------filter");
        for (Filter filter : config.getFilters()) {
            System.out.println(filter.getResource());
            System.out.println(filter.getAction());
            System.out.println(filter.getAttribute());
            System.out.println(filter.getColumn());
            System.out.println(filter.getOperator());
            System.out.println(filter.getConjunction());
        }
//        Object object = reader.read();
//        System.out.println(object);
//        Map map = (Map) object;
//        System.out.println(map.get("features"));
    }
}
