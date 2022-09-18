package com.cuukenn.openstudysource.cloud.framework;

import com.cuukenn.openstudysource.cloud.framework.config.MyBatisPlusConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author changgg
 */
@Configuration
@Import(MyBatisPlusConfig.class)
public class MyBatisAutoConfiguration {
}
