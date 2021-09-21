package com.wshid.deview.kafkastream.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class StreamsBuilderConfig {

	private final KafkaProperties kafkaProperties;

	@Autowired
	public StreamsBuilderConfig(KafkaProperties kafkaProperties) {
		this.kafkaProperties = kafkaProperties;
	}

	@Value("${spring.application.id}")
	private String applicationId;

	@Value("${spring.application.state-path}")
	private String statePath;

	@Value("${spring.kafka.metrics-recording-level}")
	private String metricsRecordingLevel;

	@Value("${spring.kafka.auto-offset-reset}")
	private String autoOffsetReset;

	@Value("${spring.kafka.num-stream-threads}")
	private String numStreamThreads;

	@Value("${spring.kafka.commit-interval-ms}")
	private String commitInterval;

	@Bean
	@Qualifier("KafkaStreamsConfigProps")
	public StreamsBuilderFactoryBean kStreamsConfigProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationId);
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		props.put(StreamsConfig.STATE_DIR_CONFIG, statePath);
		props.put(StreamsConfig.METRICS_RECORDING_LEVEL_CONFIG, metricsRecordingLevel);
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
		props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, numStreamThreads);
		props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, commitInterval);
		props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG, LogAndContinueExceptionHandler.class);
		return new StreamsBuilderFactoryBean(new KafkaStreamsConfiguration(props));
	}
}
