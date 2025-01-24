package eu.getsoftware.hotelico.streams.topdeal;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.getsoftware.hotelico.streams.topdeal.model.Deal;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class KafkaStreamTopDealApp implements CommandLineRunner {

	@Autowired
	private Properties kafkaStreamsProperties;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamTopDealApp.class, args);
	}

	@Override
	public void run(String... args) {
		StreamsBuilder builder = new StreamsBuilder();

		// Создание ObjectMapper для работы с JSON
		ObjectMapper objectMapper = new ObjectMapper();
		
		// Создание топологии Kafka Streams
		KStream<String, String> sourceViewStream = builder.stream("deal-view-topic");
		KStream<String, String> sourceOrderStream = builder.stream("deal-order-topic");

//		join(sourceViewStream,sourceOrderStream)
		
		sourceViewStream.filter((key, value) -> {
			try {
				// Преобразование JSON-сообщения в объект
				Deal hotelDeal = objectMapper.readValue(value, Deal.class);
				return hotelDeal.getViews() > 1000; // Фильтр по views
			} catch (Exception e) {
				System.err.println("Ошибка обработки сообщения: " + e.getMessage());
				return false; // Игнорируем некорректные сообщения
			}
		}).mapValues(value -> value.toUpperCase()).to("topdeal-topic");

		// Запуск Kafka Streams
		KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), kafkaStreamsProperties);
		Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
		kafkaStreams.start();

		System.out.println("Kafka Streams application started...");
	}
	
	
}