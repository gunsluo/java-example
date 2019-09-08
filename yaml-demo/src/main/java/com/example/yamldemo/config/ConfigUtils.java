package com.example.yamldemo.config;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ConfigUtils {
    public static Permission read(String path) throws FileNotFoundException, YamlException {
        File file = ResourceUtils.getFile(path);
        YamlReader reader = new YamlReader(new FileReader(file));
        return reader.read(Permission.class);
    }
}
