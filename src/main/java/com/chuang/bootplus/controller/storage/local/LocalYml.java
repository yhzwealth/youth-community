package com.chuang.bootplus.controller.storage.local;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

/**
 * @author lcc
 * @create 2021-05-28
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
@Repository
@Data
@Configuration
@ConfigurationProperties(prefix = "storage.local")
public class LocalYml {
    private String uploadAddress;
    private String visitAddress;
}
