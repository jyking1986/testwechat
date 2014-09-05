import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 12/25/13
 * Time: 11:52 AM
 */
public class PhotoFilterServiceTest {
    private String server = "http://localhost:8080";

    //    private String server = "http://54.199.129.225";

    @Test
    public void testPerformance() throws InterruptedException {
        int i = 1000 * 1000;
        while (i-- > 0) {
            testPostImageInRestfulWay();
            Thread.sleep(2 * 1000);
            testGeneratePosterInRestfulWay();

        }

    }

    @Test
    public void testUpdateQRCodeLink() {
        List<String> links = new ArrayList<>();
        links.add("http://weixin.qq.com/g/AQV4Z9VnkqINGCG/1");
        links.add("http://weixin.qq.com/g/AX13Kqoz4c68O+aB1");

        ClientResponse response = Client.create().resource("http://localhost:8080/api/group/qrcode/link")
                .header("Authorization", "Basic cm9vdDpyb290")
                .type(MediaType.APPLICATION_JSON).post(ClientResponse.class, links);

        System.out.println(response.getStatus());
        System.out.println(response.getEntity(String.class));
    }

    @Test
    public void testPostImageInRestfulWay() {
        System.out.println(Client.create().resource(server + "/imagefilter/postimage").type(MediaType.APPLICATION_JSON).post(String.class, "{\"image\":\"https://www.google.com/images/srpr/logo4w.png\",\"user\":1234}"));
    }

    @Test
    public void testGeneratePosterInRestfulWay() {
        System.out.println(Client.create().resource(server + "/imagefilter/generateposter").type(MediaType.APPLICATION_JSON).post(String.class, "{\"text\":\"i like it\",\"user\":1234,\"source\":\"test\"}"));
    }

    @Test
    public void testPostImageInWeChatWay() {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[nikerunclub]]></ToUserName>\n" +
                "<FromUserName><![CDATA[12345]]></FromUserName>\n" +
                "<CreateTime>1348831860</CreateTime>\n" +
                "<MsgType><![CDATA[image]]></MsgType>\n" +
                "<PicUrl><![CDATA[https://www.google.com/images/srpr/logo4w.png]]></PicUrl>\n" +
                "<MsgId>1234567890123456</MsgId>\n" +
                "</xml>";
        System.out.println(Client.create().resource(server + "/wechatentry").type(MediaType.APPLICATION_XML).post(String.class, message));
    }

    @Test
    public void testGeneratePosterInWeChatWay() {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[nikerunclub]]></ToUserName>\n" +
                "<FromUserName><![CDATA[12345]]></FromUserName>\n" +
                "<CreateTime>1348831860</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[This is a test]]></Content>\n" +
                "<MsgId>1234567890123456</MsgId>\n" +
                "</xml>";
        System.out.println(Client.create().resource(server + "/wechatentry").type(MediaType.APPLICATION_XML).post(String.class, message));
    }
}
