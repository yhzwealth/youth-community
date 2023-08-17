package com.chuang.bootplus.controller.storage.qiniu;

import com.alibaba.fastjson.JSONObject;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.exception.BusException;
import com.qiniu.common.QiniuException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Api(tags = {"七牛"})
@RestController
@RequestMapping("file")
@CrossOrigin
public class QiniuFile {

    @Qualifier("qiniuServiceImpl")
    @Autowired
    private QiniuService qiniuService;

    @GetMapping("test")
    public String test(){
        return "测试成功";
    }

    @GetMapping("delete")
    public String testDelete() throws QiniuException {
        String result = qiniuService.delete("helloworld");
        return result;
    }

    @PostMapping(value = "uploadQiniu")
    public ApiResponse uploadImg(MultipartFile multipartFile) {
        JSONObject jo = new JSONObject();
        if(multipartFile==null){
            jo.put("fail!", 0);
            jo.put("message", "文件为空！你确定你选择对了？");
            throw new BusException(jo.toJSONString());
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
