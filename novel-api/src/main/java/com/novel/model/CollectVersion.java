package com.novel.model;

import java.io.Serializable;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/10/09 10:15 
 */
public class CollectVersion implements Serializable {
    private int id;
    private int title_id;
    private String collect_version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTitle_id() {
        return title_id;
    }

    public void setTitle_id(int title_id) {
        this.title_id = title_id;
    }

    public String getCollect_version() {
        return collect_version;
    }

    public void setCollect_version(String collect_version) {
        this.collect_version = collect_version;
    }
}
