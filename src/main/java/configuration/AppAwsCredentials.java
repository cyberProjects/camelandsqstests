package configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.auth.credentials.AwsCredentials;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AppAwsCredentials implements AwsCredentials {
    @ConfigProperty(name = "aws.id")
    private String awsId;

    @ConfigProperty(name = "aws.key")
    private String awsKey;

    @Override
    public String accessKeyId() {
        return awsId;
    }

    @Override
    public String secretAccessKey() {
        return awsKey;
    }
}
