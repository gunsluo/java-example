package com.example.yamldemo.config;

import java.util.List;

public class Permission {
    private List<Feature> features;

    private List<PP> pps;

    private List<Filter> filters;

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<PP> getPps() {
        return pps;
    }

    public void setPps(List<PP> pps) {
        this.pps = pps;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }
}