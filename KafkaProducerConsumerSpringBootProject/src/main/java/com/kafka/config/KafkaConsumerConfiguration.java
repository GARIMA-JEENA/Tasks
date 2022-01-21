package com.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kafka.document.InputFormat;

@EnableKafka
@Configuration
public class KafkaConsumerConfiguration {

	@Bean
	public ConsumerFactory<String, InputFormat> userConsumerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "SpringGroup");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer<>(InputFormat.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, InputFormat> userKafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, InputFormat> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userConsumerFactory());
		return factory;
	}

//	 @Bean
//	    public ConsumerFactory<String, String> consumerFactory() {
//	        Map<String, Object> config = new HashMap<String, Object>();
//
//	        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//	        config.put(ConsumerConfig.GROUP_ID_CONFIG, "j");
//	        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//	        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//
//	        return new DefaultKafkaConsumerFactory<>(config);
//	    }
//
//	    @Bean
//	    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
//	        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
//	        factory.setConsumerFactory(consumerFactory());
//	        return factory; 
//	    }
}
