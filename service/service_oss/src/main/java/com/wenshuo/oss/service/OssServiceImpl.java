package com.wenshuo.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.wenshuo.oss.utils.Constants;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile multipartFile) {
        OSS ossClient = null;
        String s = UUID.randomUUID().toString().replaceAll("-", "")+multipartFile.getOriginalFilename() ;
        String s1 = new DateTime().toString("yyyy/MM/dd");
        String name = s1 + "/" + s;
        try {

            ossClient = new OSSClientBuilder().build(Constants.ENDPOINT, Constants.ACCESSKEYID, Constants.ACCESSKEYSECRET);
            ossClient.putObject(Constants.BUCKETNAME, name, multipartFile.getInputStream());
        } catch (OSSException | IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return "https://" + Constants.BUCKETNAME + "." + Constants.ENDPOINT + "/" +name;
    }
}
