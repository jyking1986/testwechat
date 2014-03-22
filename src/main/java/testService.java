import com.akqa.wechat.resource.WeChatEntryPointResource;
import com.akqa.wechat.resource.WeChatMessageBodyReader;
import com.akqa.wechat.resource.WeChatMessageBodyWriter;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 12/24/13
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class testService extends Service<testConfiguration> {
    public static void main(String[] args) throws Exception {
        new testService().run(args);
    }

    @Override
    public void initialize(Bootstrap<testConfiguration> bootstrap) {
        bootstrap.setName("Nike JDI Service");
    }

    @Override
    public void run(testConfiguration configuration, Environment environment) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
        environment.addProvider(WeChatMessageBodyReader.class);
        environment.addProvider(WeChatMessageBodyWriter.class);
        environment.addResource(new WeChatEntryPointResource(new engine(), null, "test"));
    }
}
