/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.cuukenn.easyframework.caffeine.jmx.mxbean;

/**
 * caffeine监控mxbean
 *
 * @author changgg
 */
public interface CaffeineSamplerMxBean {
    /**
     * 实现名称
     *
     * @return String
     */
    default String getName() {
        return "caffeine-sample";
    }

    /**
     * 缓存名称
     *
     * @return String
     */
    String getCacheName();

    /**
     * 请求次数
     *
     * @return long
     */
    long getRequestCount();

    /**
     * 命中次数
     *
     * @return long
     */
    long getHitCount();

    /**
     * 未命中次数
     *
     * @return long
     */
    long getMissCount();

    /**
     * 加载成功次数
     *
     * @return long
     */
    long getLoadSuccessCount();

    /**
     * 加载失败次数
     *
     * @return long
     */
    long getLoadFailureCount();

    /**
     * 加载失败占比
     *
     * @return double
     */
    double getLoadFailureRate();

    /**
     * 加载总耗时
     *
     * @return long
     */
    long getTotalLoadTime();

    /**
     * 回收总次数
     *
     * @return long
     */
    long getEvictionCount();

    /**
     * 回收总权重
     *
     * @return long
     */
    long getEvictionWeight();

    /**
     * 重置监控状态
     */
    void clear();
}
