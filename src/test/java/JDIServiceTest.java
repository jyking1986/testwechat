import com.sun.jersey.api.client.Client;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 12/25/13
 * Time: 2:58 PM
 */
public class JDIServiceTest {

//    public static final String SERVICE_URL = "http://localhost:8080/wechatentry";
    public static final String SERVICE_URL = "http://ec2-54-250-243-36.ap-northeast-1.compute.amazonaws.com/wechatentry";

    @Test
    public void testSendPicture() {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[nikerunclub]]></ToUserName>\n" +
                "<FromUserName><![CDATA[12345]]></FromUserName>\n" +
                "<CreateTime>1348831860</CreateTime>\n" +
                "<MsgType><![CDATA[image]]></MsgType>\n" +
                "<PicUrl><![CDATA[https://www.google.com/images/srpr/logo4w.png]]></PicUrl>\n" +
                "<MsgId>1234567890123456</MsgId>\n" +
                "</xml>";
        System.out.println(Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message));
    }

    @Test
    public void testSendText() {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[nikerunclub]]></ToUserName>\n" +
                "<FromUserName><![CDATA[12345]]></FromUserName>\n" +
                "<CreateTime>1348831860</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[12345678901234567890]]></Content>\n" +
                "<MsgId>1234567890123456</MsgId>\n" +
                "</xml>";
        System.out.println(Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message));
    }

    @Test
    public void testSendMessage() {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[nikerunclub]]></ToUserName>\n" +
                "<FromUserName><![CDATA[12345]]></FromUserName>\n" +
                "<CreateTime>1348831860</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[12345678901234567890]]></Content>\n" +
                "<MsgId>1234567890123456</MsgId>\n" +
                "</xml>";
        System.out.println(Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message));
    }

    @Test
    public void testSendConfirm() {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[nikerunclub]]></ToUserName>\n" +
                "<FromUserName><![CDATA[12345]]></FromUserName>\n" +
                "<CreateTime>1348831860</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[y]]></Content>\n" +
                "<MsgId>1234567890123456</MsgId>\n" +
                "</xml>";
        System.out.println(Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message));
    }
}
