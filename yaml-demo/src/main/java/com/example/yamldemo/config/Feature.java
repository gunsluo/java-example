package com.example.yamldemo.config;

import java.util.List;

public class Feature {
    private String name;
    private List<String> pps;
    private List<String> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPps() {
        return pps;
    }

    public void setPps(List<String> pps) {
        this.pps = pps;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
