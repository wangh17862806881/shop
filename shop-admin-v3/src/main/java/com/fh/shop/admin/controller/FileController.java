package com.fh.shop.admin.controller;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.fh.shop.admin.common.ServerResponse;
import com.fh.shop.admin.util.FileUpload;
import com.fh.shop.admin.util.SystemConst;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {


//上传图片到oss
@RequestMapping("uploadFile")
public ServerResponse uploadFile(MultipartFile myfile, HttpServletRequest request){
    String upload = null;
    try {
         upload = FileUpload.upload(myfile.getInputStream(), myfile.getOriginalFilename());
    } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException();
    }
    return ServerResponse.success(upload);

   }




}
