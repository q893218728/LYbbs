package com.ly.bbs.provider;

import com.alibaba.fastjson.JSON;
import com.ly.bbs.entity.AccessToken;
import com.ly.bbs.entity.GitUser;
import com.ly.bbs.entity.GithubUser;
import com.ly.bbs.entity.User;
import com.ly.bbs.okhttpclient.Client;
import com.mysql.cj.util.TimeUtil;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 提供做成github三方登录的支持的类（属于业务层）
 */
@Component
public class GithubProvider {

    public String getAccesstoken(AccessToken accessToken) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost("https://github.com/login/oauth/access_token");
        String jsonString = JSON.toJSONString(accessToken);

        StringEntity entity = new StringEntity(jsonString, "UTF-8");

        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            String str =  EntityUtils.toString(responseEntity);
            String[] split = str.split("&");
            String tokenstr = split[0];
            String token = tokenstr.split("=")[1];
            System.out.println(token+321321);
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }

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
            System.out.println(str);
            String[] split = str.split("&");
            String tokenstr = split[0];
            String token = tokenstr.split("=")[1];
            System.out.println(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public User getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        /*Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();*/
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization","token "+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();

            GitUser gitUser = JSON.parseObject(str, GitUser.class);
            User user = new User();
            BeanUtils.copyProperties(gitUser,user);
            user.setHeadImg(gitUser.getAvatar_url());
            user.setEmail("github用户不显示邮箱");
            user.setPhone("github用户不显示电话");
            return user;
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        /*Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken).addHeader("Connection", "keep-alive")
                .build();

        try {
            Response response = Client.INSTANCE.getInstance().newCall(request).execute();
            String str = response.body().string();
            System.out.println(str);
            //将github返回的JSON格式的用户信息，转成对象。
            User user = JSON.parseObject(str, User.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;*/
    }
}
