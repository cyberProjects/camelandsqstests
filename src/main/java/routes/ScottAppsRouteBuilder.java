package routes;

import org.apache.camel.ErrorHandlerFactory;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.services.sqs.SqsClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.aws2Sqs;

@ApplicationScoped
public class ScottAppsRouteBuilder extends RouteBuilder {
    @Inject
    private SqsClient sqsClient;

    @ConfigProperty(name = "queue.name")
    private String queueName;

//    @Override
//    public void configure() throws Exception {
//        from(aws2Sqs(queueName).amazonSQSClient(sqsClient).deleteAfterRead(true))
//                .process(x -> {
//                    System.out.println("Message received!");
//                    System.out.println("Throwing Exception");
//                    throw new RuntimeException("Testing queue");
//                })
//                .errorHandler(deadLetterChannel("aws2-sqs://dl_ScottApps").onPrepareFailure(new ScottAppsProcessor()));
//    }

    /**
     * How many times will .process attempt to process this Exchange
     * before sending it to the .errorHandler? What other information
     * is available in .process can we check: apprxReceiveCount? More,
     * .deleteAfterRead is
     *
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        from(aws2Sqs(queueName)
                .amazonSQSClient(sqsClient)
                .attributeNames("All")
                .messageAttributeNames("All")
                .autoCreateQueue(false)
                .deleteAfterRead(true))
                .process(new ScottAppsSuccessProcessor())
                .process(new ScottAppsSuccessProcessor2())
                .onException(Throwable.class)
                .process(new ScottAppsFailureProcessor());
//                .errorHandler(deadLetterChannel("aws2-sqs://dl_ScottApps")
//                        .deadLetterHandleNewException(false)
//                        .onPrepareFailure(new ScottAppsFailureProcessor()));
    }
}
