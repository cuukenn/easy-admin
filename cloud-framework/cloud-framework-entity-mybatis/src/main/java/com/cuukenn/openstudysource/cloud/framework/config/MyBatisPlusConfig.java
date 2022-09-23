package com.cuukenn.openstudysource.cloud.framework.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.IllegalSQLInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.cuukenn.openstudysource.cloud.framework.auth.util.SecurityUtil;
import com.cuukenn.openstudysource.cloud.framework.entity.IEntity;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Date;

/**
 * mybatis-plus相关配置
 *
 * @author changgg
 */
@Configuration
public class MyBatisPlusConfig {
    private static final Logger log = LoggerFactory.getLogger(MyBatisPlusConfig.class);

    /**
     * 防止恶意全表更新和删除
     *
     * @return 插件
     */
    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        return new BlockAttackInnerInterceptor();
    }

    /**
     * 分页
     *
     * @return 插件
     */
    @Bean
    @ConditionalOnMissingBean(MybatisPlusInterceptor.class)
    public MybatisPlusInterceptor paginationInnerInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 创建时间、更新时间自动填充
     *
     * @return 时间元数据自动填充
     */
    @Bean
    @ConditionalOnMissingBean(MetaObjectHandler.class)
    public MetaObjectHandler timeMetaObjectWithoutSecurity() {
        log.info("register mataObjectHandler without security");
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                Date now = new Date();
                this.setFieldValByName(IEntity.CREATED_TIME, now, metaObject);
                this.setFieldValByName(IEntity.LAST_MODIFIED_TIME, now, metaObject);
                this.setFieldValByName(IEntity.LAST_MODIFIED_BY, getCurrentUser(), metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName(IEntity.LAST_MODIFIED_TIME, new Date(), metaObject);
                this.setFieldValByName(IEntity.LAST_MODIFIED_BY, getCurrentUser(), metaObject);
            }
        };
    }

    /**
     * sql性能规范
     *
     * @return 插件
     */
    @Bean
    @Profile("dev")
    public IllegalSQLInnerInterceptor innerInterceptor() {
        return new IllegalSQLInnerInterceptor();
    }

    /**
     * 获取当前用户信息
     *
     * @return 当前用户信息
     */
    private String getCurrentUser() {
        try {
            return SecurityUtil.getCurrentUsername();
        } catch (RuntimeException e) {
            log.error("get current user failed,use default instead,msg:{}", e.getMessage());
        }
        return IEntity.ANONYMOUS;
    }
}
