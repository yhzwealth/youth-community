package com.chuang.bootplus.controller.storage.local;

import com.chuang.bootplus.base.utils.ApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lcc
 * @create 2021-05-28
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
@Api(tags = {"本地上传文件"})
@RestController
@RequestMapping("storage/local")
public class DemoController {
    @Autowired
    LocalService service;

    @ApiOperation(value = "本地上传")
    @ApiImplicitParam(
            name = "file",
            value = "文件",
            required = true,
            dataType = "__File"
    )
    @PostMapping("upload")
    public ApiResponse upload(@RequestParam(value = "file")MultipartFile file){
        return service.uploadFile(file);
    }
}
