package com.novel.model;

import java.io.Serializable;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/09/27 15:54 
 */
public class NovelTitle implements Serializable {
    private int id ;
    private int site_id;
    private String novel_name;
    private String novle_url;
    private int novel_type;
    private String version;
    private String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }

    public String getNovel_name() {
        return novel_name;
    }

    public void setNovel_name(String novel_name) {
        this.novel_name = novel_name;
    }

    public String getNovle_url() {
        return novle_url;
    }

    public void setNovle_url(String novle_url) {
        this.novle_url = novle_url;
    }

    public int getNovel_type() {
        return novel_type;
    }

    public void setNovel_type(int novel_type) {
        this.novel_type = novel_type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
