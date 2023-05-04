package configuration;

import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class SqsClientBuilder {
    @Inject
    AppAwsCredentials credentials;

    @Produces
    public SqsClient getSqsClient() {
        return SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}
