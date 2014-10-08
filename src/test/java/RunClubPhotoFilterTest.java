import com.sun.jersey.api.client.Client;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 12/25/13
 * Time: 10:18 AM
 */
public class RunClubPhotoFilterTest {

    //    public static final String SERVICE_URL = "http://gow.nike.com.cn/nikerunclub/weixin";
//    public static final String SERVICE_URL = "http://gow.nike.com.cn/free/runclub/weixin";
    public static final String SERVICE_URL = "http://gow.nike.com.cn/nikerunclub/weixin";
//    public static final String SERVICE_URL = "http://localhost:8080/wechatentry";

    //    public static final String SERVICE_URL = "http://54.178.137.196/wechatentry";
//    http://54.178.136.153/wechatentry
//    public static final String SERVICE_URL = "http://runclub.nike.com.cn/wechatentry";
    @Test
    public void testWelcome2001() throws Exception {
        String message = "<xml><ToUserName><![CDATA[owF3Pjh_n3_CoAloLOTbmabyO3lM]]></ToUserName>\n" +
                "<FromUserName><![CDATA[FromUser]]></FromUserName>\n" +
                "<CreateTime>123456789</CreateTime>\n" +
                "<MsgType><![CDATA[event]]></MsgType>\n" +
                "<Event><![CDATA[subscribe]]></Event>\n" +
                "<EventKey><![CDATA[qrscene_2009]]></EventKey>\n" +
                "<Ticket><![CDATA[TICKET]]></Ticket>\n" +
                "</xml>";
        System.out.println(Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message));
    }

    @Test
    public void testWelcome2001_bidNumber() throws Exception {
        String message = "<xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName><![CDATA[owF3PjuWpYq_ZQpy7btIIT2PosF4]]></FromUserName> \n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[text]]></MsgType>\n" +
                " <Content><![CDATA[12345]]></Content>\n" +
                " <MsgId>1234567890123456</MsgId>\n" +
                " </xml>";
        String post = Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message);
        System.out.println(post);
    }

    @Test
    public void testWelcome2002() throws Exception {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "<FromUserName><![CDATA[owF3PjuWpYq_ZQpy7btIIT2PosF4]]></FromUserName>\n" +
                "<CreateTime>123456789</CreateTime>\n" +
                "<MsgType><![CDATA[event]]></MsgType>\n" +
                "<Event><![CDATA[SCAN]]></Event>\n" +
                "<EventKey><![CDATA[2002]]></EventKey>\n" +
                "<Ticket><![CDATA[TICKET]]></Ticket>\n" +
                "</xml>";
        System.out.println(Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message));
    }

    @Test
    public void testClickMenuToKickoffWorkflow() throws Exception {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[nikerunclub]]></ToUserName>\n" +
                "<FromUserName><![CDATA[12345]]></FromUserName>\n" +
                "<CreateTime>1348831860</CreateTime>\n" +
                "<MsgType><![CDATA[event]]></MsgType>\n" +
                "<Event><![CDATA[CLICK]]></Event>\n" +
                "<EventKey><![CDATA[ACTIVITY.CREATE_CREW]]></EventKey>\n" +
                "</xml>";
        System.out.println(Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message));
    }

    @Test
    public void testClickMenuToKickoffWorkflow1() throws Exception {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[nikerunclub]]></ToUserName>\n" +
                "<FromUserName><![CDATA[12345]]></FromUserName>\n" +
                "<CreateTime>1348831860</CreateTime>\n" +
                "<MsgType><![CDATA[event]]></MsgType>\n" +
                "<Event><![CDATA[CLICK]]></Event>\n" +
                "<EventKey><![CDATA[ACTIVITY.CREATE_CREW1]]></EventKey>\n" +
                "</xml>";
        System.out.println(Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message));
    }

    @Test
    public void testSendText() throws Exception {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[nikerunclub]]></ToUserName>\n" +
                "<FromUserName><![CDATA[12345]]></FromUserName>\n" +
                "<CreateTime>1348831860</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[E7D7C3]]></Content>\n" +
                "<MsgId>1234567890123456</MsgId>\n" +
                "</xml>";
        System.out.println(Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message));
    }

    @Test
    public void testSendPictureImage() throws Exception {
        String message = "<xml>\n" +
                "<ToUserName><![CDATA[nikerunclub]]></ToUserName>\n" +
                "<FromUserName><![CDATA[12345]]></FromUserName>\n" +
                "<CreateTime>1348831860</CreateTime>\n" +
                "<MsgType><![CDATA[image]]></MsgType>\n" +
                "<PicUrl><![CDATA[http://www.google.com/images/srpr/logo4w.png]]></PicUrl>\n" +
                "<MsgId>1234567890123456</MsgId>\n" +
                "</xml>";
        System.out.println(Client.create().resource(SERVICE_URL).type(MediaType.APPLICATION_XML).post(String.class, message));
    }

}
