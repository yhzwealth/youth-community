package com.chuang.bootplus.controller.storage.qiniu;

import com.chuang.bootplus.base.utils.ApiResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lcc
 * @create 2021-06-03
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
@RestController
@RequestMapping("storage/qiniu")
public class QiniuDemoController {
    @Autowired
    QiniuFileService qiniuService;

    @ApiOperation(value = "七牛上传")
    @ApiImplicitParam(
            name = "file",
            value = "文件",
            required = true,
            dataType = "__File"
    )
    @PostMapping("upload")
    public ApiResponse upload(MultipartFile file){
        return qiniuService.uploadFile(file);
    }
}
