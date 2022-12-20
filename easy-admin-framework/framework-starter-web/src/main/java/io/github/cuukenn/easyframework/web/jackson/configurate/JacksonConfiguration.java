/*
 * Copyright 2022 changgg.
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
package io.github.cuukenn.easyframework.web.jackson.configurate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.github.cuukenn.easyframework.web.jackson.DatePattern;
import io.github.cuukenn.easyframework.web.jackson.JacksonTimeModel;
import io.github.cuukenn.easyframework.web.toolkit.LocalDateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * @author changgg
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(JacksonConfiguration.class);

	@Bean
	@ConditionalOnMissingBean
	public Jackson2ObjectMapperBuilderCustomizer customizer() {
		logger.info("[register jackson customizer]");
		return builder -> {
			builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
			builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
			builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
			builder.serializerByType(Long.class, ToStringSerializer.instance);
			builder.modules(new JacksonTimeModel());
			builder.createXmlMapper(false).build().getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
				@Override
				public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
					gen.writeObject("");
				}
			});
		};
	}

	@Bean
	@Primary
	@ConditionalOnMissingBean
	public ObjectMapper jacksonObjectMapper() {
		logger.info("[register jacksonObjectMapper]");
		JsonMapper.Builder builder = JsonMapper.builder();
		builder.serializationInclusion(JsonInclude.Include.NON_EMPTY);
		builder.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		builder.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS, true);
		builder.configure((JsonParser.Feature.ALLOW_SINGLE_QUOTES), true);
		JsonMapper jsonMapper = builder.build();
		JavaTimeModule javaTimeModule = new JavaTimeModule();
		javaTimeModule.addSerializer(BigDecimal.class, new JsonSerializer<BigDecimal>() {
			@Override
			public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				DecimalFormat fNum = new DecimalFormat("#.##");
				gen.writeString(fNum.format(value));
			}
		});
		javaTimeModule.addSerializer(Long.class, new JsonSerializer<Long>() {
			@Override
			public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeString(value.toString());
			}
		});
		javaTimeModule.addSerializer(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
			@Override
			public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				gen.writeString(LocalDateTimeUtil.format(value, DatePattern.NORM_DATETIME_PATTERN));
			}
		});
		javaTimeModule.addDeserializer(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
			@Override
			public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext ctx) throws IOException, JacksonException {
				return LocalDateTimeUtil.parse(jsonParser.getText(), DatePattern.NORM_DATETIME_PATTERN);
			}
		});
		jsonMapper.registerModule(javaTimeModule);
		return jsonMapper;
	}
}
