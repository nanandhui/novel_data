package com.novel.model;

import java.io.Serializable;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/09/26 11:39 
 */
public class Novel_Site implements Serializable {
    private int id;
    private String site_name;
    private String site_url;
    private int site_level;
    private int site_class;
    private String site_encoding;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getSite_url() {
        return site_url;
    }

    public void setSite_url(String site_url) {
        this.site_url = site_url;
    }

    public int getSite_level() {
        return site_level;
    }

    public void setSite_level(int site_level) {
        this.site_level = site_level;
    }

    public int getSite_class() {
        return site_class;
    }

    public void setSite_class(int site_class) {
        this.site_class = site_class;
    }

    public String getSite_encoding() {
        return site_encoding;
    }

    public void setSite_encoding(String site_encoding) {
        this.site_encoding = site_encoding;
    }

    public Novel_Site(int id, String site_name, String site_url, int site_level, int site_class, String site_encoding) {
        this.id = id;
        this.site_name = site_name;
        this.site_url = site_url;
        this.site_level = site_level;
        this.site_class = site_class;
        this.site_encoding = site_encoding;
    }

    public Novel_Site() {
    }

    @Override
    public String toString() {
        return "Novel_Site{" + "id=" + id + ", site_name='" + site_name + '\'' + ", site_url='" + site_url + '\'' + ", site_level="
                + site_level + ", site_class=" + site_class + ", site_encoding='" + site_encoding + '\'' + '}';
    }
}
