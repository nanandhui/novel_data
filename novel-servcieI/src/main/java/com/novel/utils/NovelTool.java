package com.novel.utils;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/08/21 17:07 
 */
public class NovelTool {
    public static String getPrivateKey(){
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        String key = s.replace("-", "");
        return key;
    }


    public static String readURL(String url,String encoding){
        String result = "";
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build(); CloseableHttpResponse httpResponse = httpClient.execute(new NovelSpiderHttpGet(url))) {
            result = EntityUtils.toString(httpResponse.getEntity(),encoding);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public static String firstVersion(String type){
        String version = "";
        try {
            version = version(type);
        }catch (Exception e){
            e.printStackTrace();
            version="0.0.1";
        }
        return version;
    }

    public static String version(String version) throws Exception{
       StringBuffer steadyVersion =new StringBuffer();
       steadyVersion.append(version+":"+"(create:"+TimeTool.getToDayDateStr()+"):"+1);
       return steadyVersion.toString();
    }

    public static String maintenanceVersion(String oldVersion){
        String newVersion="";
        if (!StringUtils.isEmpty(oldVersion)){
            String[] split = oldVersion.split(":");
            String s = split[split.length - 1];
            Integer integer = Integer.valueOf(s);
            split[split.length - 1]=String.valueOf(integer.intValue()+1);
            newVersion = String.join(":", split);
        }else {
            newVersion=firstVersion("test");
        }
        return newVersion;
    }
}
