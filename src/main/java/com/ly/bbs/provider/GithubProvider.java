package com.ly.bbs.provider;

import com.alibaba.fastjson.JSON;
import com.ly.bbs.entity.AccessToken;
import com.ly.bbs.entity.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 提供做成github三方登录的支持的类（属于业务层）
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessToken accessToken) {
        //1MediaType 指明传递数据的类型
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        //2创建一个http客户端
        OkHttpClient client = new OkHttpClient();
        //3请求体为mediaType格式的JSON字符串
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessToken));
        //4创建一个post请求，指明url 请求体
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        //通过request创建出一个Call对象，Call对象的execute发送请求，并且返回的是response对象
        //execute方式是发送同步请求，enqueue（穿一个回调函数）是异步请求
        try (Response response = client.newCall(request).execute()) {
            //得到response对象的响应体字符串
            String str = response.body().string();
            String[] split = str.split("&");
            String tokenstr = split[0];
            String token = tokenstr.split("=")[1];
            System.out.println(str);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
            //将github返回的JSON格式的用户信息，转成对象。
            GithubUser githubUser = JSON.parseObject(str, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
