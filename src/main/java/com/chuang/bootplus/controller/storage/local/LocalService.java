package com.chuang.bootplus.controller.storage.local;

import com.alibaba.fastjson.JSONObject;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.exception.BusException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author lcc
 * @create 2021-05-28
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
@Service
public class LocalService {

    @Resource
    LocalYml localYml;

    public ApiResponse uploadFile(MultipartFile file){
        //实例化json数据
        JSONObject jo = new JSONObject();
        if(file==null){
            throw new BusException("文件为空，上传失败！");
        }
        String fileName = System.currentTimeMillis()+file.getOriginalFilename();
        String filePath = localYml.getUploadAddress();
        try {
            uploadFile(file.getBytes(), filePath, fileName);
            jo.put("fileUrl", localYml.getVisitAddress()+fileName);
            jo.put("fileName", fileName);
        } catch (Exception e) {
            throw new BusException("文件上传失败");
        }
        //返回json
        return new ApiResponse(jo);
    }

    private static void uploadFile(byte[] file, String filePath, String fileName)throws Exception{
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+"/"+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
