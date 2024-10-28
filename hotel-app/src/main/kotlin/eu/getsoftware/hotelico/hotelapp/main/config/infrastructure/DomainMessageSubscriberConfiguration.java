import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DomainMessageSubscriberConfiguration.class) // We need this import so this class is recognized as a ImportBeanDefinitionRegistrar and its registerBeanDefinitions method is called
public class DomainMessageSubscriberConfiguration extends AbstractDomainMessageSubscriberConfiguration {

    public static final String REPLY_QUEUE_NAME = "checkin-view.queue";

    @Override
    public String getServiceName() {
        return "checkin-view";
    }

    @Override
    public String getVerticalName() {
        return "checkin";
    }

    @Override
    protected List<SubscriberConfigurationItem> getSubscriberConfigurations() {
        return ImmutableList.of(
                SubscriberConfigurationItem.builder()
                        .receiveFromVerticalName("shop-admin")
                        .myVerticalName(getVerticalName())
                        .serviceName(getServiceName())
                        .routingKey("shop.shop.*.event")
                        .build(),
                SubscriberConfigurationItem.builder()
                        .receiveFromVerticalName(getVerticalName())
                        .myVerticalName(getVerticalName())
                        .serviceName(getServiceName())
                        .routingKey("checkin.checkin.*.event")
                        .routingKey("checkin.checkin-attribute.*.event")
                        .routingKey("checkin.checkin-image.*.event")
                        .routingKey("checkin.checkin-availability.updated.event")
                        .routingKey("checkin.category.*.event")
                        .routingKey("checkin.checkin-attribute-definition.*.event")
                        // events in response to reindex commands are send to checkin-view.queue directly with queue name as the
                        // routing key - we need this as a routing key to let such a message end up in the retry queue as well
                        .routingKey(REPLY_QUEUE_NAME)
                        .build()
        );
    }

    @Bean
    public MessageListenerAdapter domainMessageListenerAdapter(ThreadLocalTenantAccessor tenantAccessor,
                                                               ThreadLocalLocaleAccessor localeAccessor,
                                                               List<DomainMessageSubscriber> subscribers,
                                                               MessageConverter messageConverter
    ) {
        DomainMessageSubscriberHandler domainMessageSubscriberHandler = new LocalizedDomainMessageSubscriberHandler(tenantAccessor, localeAccessor, subscribers);
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(domainMessageSubscriberHandler);
        listenerAdapter.setMessageConverter(messageConverter);
        return listenerAdapter;
    }

    @Override
    protected DomainMessagePayloadSubtypeModule getDomainMessagePayloadSubtypeModule() {
        return new DomainMessagePayloadSubtypeModule(
                ShopPayload.class,
                ProductPayload.class,
                AttributePayload.class,
                ImagePayload.class,
                AvailabilityPayload.class,
                CategoryPayload.class,
                AttributeDefinitionPayload.class
        );
    }
}

/**
Copy
product-view/bindings.json
{
    "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product-view.queue",
        "arguments" : { }
},{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product.category.*.event",
        "arguments" : { }
        },{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product.product-attribute.*.event",
        "arguments" : { }
        },{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product.product-availability.updated.event",
        "arguments" : { }
        },{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product.product-image.*.event",
        "arguments" : { }
        },{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product.product.*.event",
        "arguments" : { }
        },{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "shop.shop.*.event",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product-view.queue",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product.category.*.event",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product.product-attribute.*.event",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product.product-availability.updated.event",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product.product-image.*.event",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product.product.*.event",
        "arguments" : { }
        },{
        "source" : "shop-admin.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "shop.shop.*.event",
        "arguments" : { }
        }
product-view/bindings.json
{
    "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product-view.queue",
        "arguments" : { }
},{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product.category.*.event",
        "arguments" : { }
        },{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product.product-attribute.*.event",
        "arguments" : { }
        },{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product.product-availability.updated.event",
        "arguments" : { }
        },{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product.product-image.*.event",
        "arguments" : { }
        },{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "product.product.*.event",
        "arguments" : { }
        },{
        "source" : "product-view.exchange.rx",
        "vhost" : "/",
        "destination" : "product-view.queue.rq",
        "destination_type" : "queue",
        "routing_key" : "shop.shop.*.event",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product-view.queue",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product.category.*.event",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product.product-attribute.*.event",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product.product-availability.updated.event",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product.product-image.*.event",
        "arguments" : { }
        },{
        "source" : "product.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "product.product.*.event",
        "arguments" : { }
        },{
        "source" : "shop-admin.exchange",
        "vhost" : "/",
        "destination" : "product-view.queue",
        "destination_type" : "queue",
        "routing_key" : "shop.shop.*.event",
        "arguments" : { }
        }
View
        Subscriber
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductMessageSubscriber implements DomainMessageSubscriber {

    private static final String PRODUCT = "product";

    private final ProductRepository productRepository;

    private final TenantAccessor tenantAccessor;

    @DomainMessageHandler("product.product.created.event")
    public void createProduct(DomainMessage<ProductPayload> message) {
        ProductPayload payload = message.getPayload();
        withMdc(PRODUCT, payload.getId()).execute(() -> {
            log.info("Processing event {}", message.getMessageType());
            Product product = toProduct(payload).build();
            productRepository.index(product);
        });
    }

    // ...

}
Deployment




