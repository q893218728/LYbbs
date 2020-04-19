package com.ly.bbs.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import cn.ucloud.ufile.http.OnProgressListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class UcloudProvider {

    @Value("${ucloud.ufile.public-key}")
    private String publicKey;
    @Value("${ucloud.ufile.private-key}")
    private String privateKey;
    String buildyoung = "buildyoung";


    public String upload(InputStream fileStream,String mimeType,String fileName){

        String generatedFileName;
        String[] filePaths = fileName.split("\\.");
        if(filePaths.length>1){
            generatedFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length-1];
        }else{
            return null;
        }


        try {
            //创建一个授权器
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(publicKey,privateKey);
            ObjectConfig config = new ObjectConfig("cn-bj","ufileos.com");

            PutObjectResultBean response = UfileClient.object(objectAuthorization,config)
                    .putObject(fileStream,mimeType)
                    .nameAs(generatedFileName)
                    .toBucket(buildyoung)
                    .setOnProgressListener(new OnProgressListener() {
                        @Override
                        public void onProgress(long bytesWritten, long contentLength) {

                        }
                    })
                    .execute();

            if(response != null && response.getRetCode() == 0){
                String url = UfileClient.object(objectAuthorization,config)
                        .getDownloadUrlFromPrivateBucket(generatedFileName,buildyoung,24*60*60*180)//180天有效
                        .createUrl();
                return url;
            }
        } catch (UfileClientException e) {
            e.printStackTrace();
        } catch (UfileServerException e) {
            e.printStackTrace();
        }



       return generatedFileName;
    }
}
