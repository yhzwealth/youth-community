package com.chuang.bootplus.controller.storage.qiniu;

import com.alibaba.fastjson.JSONObject;
import com.chuang.bootplus.base.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

/**
 * @author lcc
 * @create 2021-06-03
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class QiniuFileService {
    @Autowired
    QiniuServiceImpl qiniuService;
    public ApiResponse uploadFile(MultipartFile multipartFile) {
        JSONObject jo = new JSONObject();
        if(multipartFile==null){
            jo.put("fail!", 0);
            jo.put("message", "文件为空！你确定你选择对了？");
            return new ApiResponse(jo.toJSONString());
        }
        String fileName = System.currentTimeMillis()+multipartFile.getOriginalFilename();
        String result=null;
        if (multipartFile.isEmpty()) {
            jo.put("success", 0);
            jo.put("fileName", "");
        }
        try {
            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
            result = qiniuService.uploadFile(inputStream,fileName);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
        return new ApiResponse(result);
    }
}
