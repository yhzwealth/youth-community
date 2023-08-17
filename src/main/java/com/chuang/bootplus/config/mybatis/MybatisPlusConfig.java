package com.chuang.bootplus.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lcc
 * @create 2021-05-30
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
@Configuration
public class MybatisPlusConfig {
    // 扫描我们的 mapper 文件夹
    @MapperScan("com.chuang.bootplus.mapper")
    @EnableTransactionManagement

    /**
      配置类
     */
    @Configuration
    public class MyBatisPlusConfig {

        // 分页插件
        @Bean
        public MybatisPlusInterceptor mybatisPlusInterceptor() {
            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));//分页
            interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());//乐观锁
            return interceptor;
        }
    }
}
