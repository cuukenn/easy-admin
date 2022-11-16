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

import io.github.cuukenn.easyframework.caffeine.jmx.mxbean.CaffeineSamplerMxBean;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 注册MXBean
 *
 * @author changgg
 */
public final class CaffeineMxBeanRegister {
	private CaffeineMxBeanRegister() {
	}

	/**
	 * 注册MXBean
	 */
	public static void register(CaffeineSamplerMxBean mxBean) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName objectName = new ObjectName("javax.cache:type=caffeineCacheSampler,name=" + mxBean.getCacheName());
		mbs.registerMBean(mxBean, objectName);
	}
}
