package routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScottAppsSuccessProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        try {
            System.out.println("ScottAppsSuccessProcessor received Exchange");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            // don't send email here
        }
    }
}
