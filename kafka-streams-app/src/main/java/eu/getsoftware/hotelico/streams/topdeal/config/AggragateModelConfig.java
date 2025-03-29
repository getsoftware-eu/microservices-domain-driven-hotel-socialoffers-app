//package eu.getsoftware.hotelico.streams.topdeal.config;
//
//import eu.getsoftware.hotelico.streams.topdeal.model.ViewTopDealAggregate;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.KTable;
//import org.apache.kafka.streams.kstream.Materialized;
//import org.springframework.context.annotation.Bean;
//
//import java.util.function.BiFunction;
//
//public class AggragateModelConfig {
//
//    @Bean
//    public BiFunction<KStream<String, String>, KStream<String, String>, KStream<String, ViewTopDealAggregate>> viewOrderAggregate() {
//        return (views, orders) -> {
//
//            // View Topic!!! aggregate views per deal
//            KTable<String, Integer> countViews = views.groupBy((k, v) -> k).aggregate(
//                    () -> 0,
//                    (key, newValue, aggValue) -> Integer.valueOf(newValue) + aggValue, //increment
//                    Materialized.with(Serdes.String(), Serdes.Integer())); //eu KTable: aggegatedNumber(views) pro product 
//
////			countViews.toStream().foreach( (a,b) -> {
////				System.out.println("countViews " + a + " " + b);
////			});
//
//            // Order Topic!!! aggregate orders per product
//            KTable<String, Integer> countOrders = orders.groupBy((k, v) -> k).aggregate(
//                    () -> 0,
//                    (key, newValue, aggValue) -> Integer.valueOf(newValue) + aggValue, //increment
//                    Materialized.with(Serdes.String(), Serdes.Integer()));
//
////			countOrders.toStream().foreach( (a,b) -> {
////				System.out.println("countOrders " + a + " " + b);
////			});
//
//            // eu: left join diese zwei Ktables of aggregatedNumbers
//            KTable<String, ViewTopDealAggregate> joined = countViews.leftJoin(countOrders, (leftValue, rightValue) -> ViewTopDealAggregate.builder()
//                            .amountViews(leftValue) //eu: number(views)
//                            .amountOrders(rightValue != null ? rightValue : 0).build(), /* ValueJoiner */ //eu: number(orders)
//                    Materialized.with(Serdes.String(), new JsonSerde<>(ViewTopDealAggregate.class)));
//
////			joined.toStream().foreach( (a, b)  -> {
////				System.out.println("joined: " + a + " " + b.getAmountViews() + " " + b.getAmountOrders());
////			});
//
//            // stream joined table
//            return joined.toStream();
//        };
//    }
//}
