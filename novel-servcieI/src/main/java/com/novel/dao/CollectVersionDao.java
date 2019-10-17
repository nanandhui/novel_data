package com.novel.dao;

import com.novel.model.CollectVersion;
import java.util.List;

public interface CollectVersionDao {
    public int insertModel(CollectVersion collectVersion);
    public List<CollectVersion> findAllModel();
    public List<CollectVersion> findModel(CollectVersion collectVersion);
    public int updateModel(CollectVersion collectVersion);
}
