package com.wshid.deview.kafkastream;

import java.util.Locale;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpperCaseTransformer {

	@Value("${spring.kafka.src-topic-name}")
	private String srcTopicName;

	@Value("${spring.kafka.dest-topic-name}")
	private String destTopicName;

	@Autowired
	@Qualifier("KafkaStreamsConfigProps")
	StreamsBuilder builder;

	@Bean
	public void process() {
		KStream<String, String> stream = builder.stream(srcTopicName, Consumed.with(Serdes.String(), Serdes.String()));

		stream
			.mapValues(value -> value.toUpperCase(Locale.ROOT))
			.to(destTopicName, Produced.with(Serdes.String(), Serdes.String()));

	}
}
