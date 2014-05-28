import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.CharacterCodingException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 1/3/14
 * Time: 1:25 PM
 */
public class ConceptualTest {

    public void testDownLoadImage(final URL url, final String fileName) throws Exception {
        long startTime = System.currentTimeMillis();

        URLConnection connection = url.openConnection();
        InputStream in = connection.getInputStream();
        int contentLength = connection.getContentLength();
        ByteArrayOutputStream tmpOut = new ByteArrayOutputStream(contentLength);

        byte[] buf = new byte[1024];
        while (true) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }
            tmpOut.write(buf, 0, len);
        }

        in.close();
        tmpOut.close();
        File file = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        tmpOut.writeTo(fileOutputStream);

//        URLConnection connection = url.openConnection();
//        BufferedInputStream stream = new BufferedInputStream(connection.getInputStream());
//        int available = stream.available();
//        byte b[] = new byte[available];
//        stream.read(b);
//        File file = new File(fileName);
//        OutputStream out = new FileOutputStream(file);
//        out.write(b);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }

    @Test
    public void testDownLoad() throws Exception {

        testDownLoadImage(new URL("http://runclub.nike.com.cn/api/certificate/generate?openid=owF3PjkjNHPdgm378Fs0LfvhGHEU"), "fromgow.jpg");

        testDownLoadImage(new URL("http://s3-ap-northeast-1.amazonaws.com/su14/sharecertificate/oaSyGt20ODjRm6ETwBOSwmKSzOxg.jpg"), "fromaws.jpg");

        Thread.sleep(1000 * 3);
    }

    @Test
    public void testRemoveNonUTF8Char() throws CharacterCodingException, UnsupportedEncodingException {
//        String result = Client.create().resource("https://api.weixin.qq.com/cgi-bin/user/info?access_token=11YGVp1Hu4Q4DuuxIaXc3qcy51-t5zLqIDfclYduOb1H5T_9zyG0_p7GvLKID2LZdTS1E5h7riOhtnpXOBiosKddDDbA9qCwGDRLjFhZWpn0FNial2VasnRJoI1lbljnRoWiosLxu3NzaWC9dmFGNQ&openid=oaSyGt1L1rsykx2TjORnTp_P1T_g")
//                .get(String.class);
//        System.out.println(result);

        String result = "{\"subscribe\":1,\"openid\":\"oaSyGt1L1rsykx2TjORnTp_P1T_g\",\"nickname\":\"那那👻\",\"sex\":2,\"language\":\"zh_CN\",\"city\":\"闸北\",\"province\":\"上海\",\"country\":\"中国\",\"headimgurl\":\"http:\\/\\/wx.qlogo.cn\\/mmopen\\/aG4HdpHVR40iaQGcuW0SEYu4HDTCEM5pLS7P4v9Qu0XOGdCUYpKqY1hbvgc8rBH3B847WHg5KUbZKKVG6TxouNQ\\/0\",\"subscribe_time\":1392800331}\n";
//        System.out.println("Remove these characters: äó".replaceAll("[^(\\x20-\\x7F)]", ""));
        System.out.println(result.replaceAll("[(\\xE63E-\\xE757)]", ""));

        String name = "那那👻";
        StringBuffer stringBuffer = new StringBuffer();
        final int length = name.length();
        for (int offset = 0; offset < length; ) {
            final int codePoint = name.codePointAt(offset);
            if (codePoint > 0xE000) {
                stringBuffer.append(' ');
            } else {
                stringBuffer.append(Character.toChars(codePoint));
            }
            offset += Character.charCount(codePoint);
        }

        System.out.println(stringBuffer.toString());


//        CharsetDecoder utf8Decoder = Charset.forName("UTF-8").newDecoder();
//        utf8Decoder.onMalformedInput(CodingErrorAction.IGNORE);
//        utf8Decoder.onUnmappableCharacter(CodingErrorAction.IGNORE);
//        ByteBuffer bytes = ByteBuffer.wrap(result.getBytes("UTF-8"));
//        CharBuffer parsed = utf8Decoder.decode(bytes);
//        System.out.println(String.copyValueOf(parsed.array()));
    }

    @Test
    public void testBadNickName() {
//        String result = Client.create().resource("https://api.weixin.qq.com/cgi-bin/user/info?access_token=R0UxT5WTkXltWjXjA4G97vbF7h4bocjw0f-QvQYYei4C9aJ4YGJ-5_mx2jh4toaw_zhnt1NJbK9poT-kial-K8mP9kPRNTpn37JDxJkPGdYPUVK24y6cGzWcQsBfPLOhWxmpJaGqAfVXU1Md-pFuIA&openid=oaSyGt9gto3_QcU-LCgQ9Fv2luPs")
//                .get(String.class);
//        System.out.println(result);

        String name = "  Claire  ";

        StringBuffer stringBuffer = new StringBuffer();
        final int length = name.length();
        for (int offset = 0; offset < length; ) {
            final int codePoint = name.codePointAt(offset);
            if (codePoint > 0xE000) {
                stringBuffer.append(' ');
                System.out.println(codePoint);
            } else {
                stringBuffer.append(Character.toChars(codePoint));
            }
            offset += Character.charCount(codePoint);
        }

        System.out.println(stringBuffer.toString());
    }

    @Test
    public void testLongToDate() {
        //1389058954
        System.out.println(new Date());
        Date d = new Date(1383207385l * 1000);
        System.out.println(d);
    }

    public void testRegisterNikePlusAccount(String user) {
//        dev
        String appName = "com.nike.brand.china.runclub";
        String clientId = "9d3f318b2839cc337ffa0a0312a300f6";
        String clientSecret = "0bf62c6547814bf1";
        String server = "https://api-preprod.nike.com";

        String urlFormat = "%s/v2.0/oauth2/register?client_id=%s&client_secret=%s&app=%s&format=json";

        String url = String.format(urlFormat, server, clientId, clientSecret, appName);

        ClientResponse response = Client.create().resource(url).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, "object=" + constructRegisterJson(user));

        System.out.println(response.getStatus());
        System.out.println(response.getEntity(String.class));
    }

    @Test
    @Ignore
    public void createBulkAccountForTesting() {
        String names = "Vivian," +
                "Kelly," +
                "Connie," +
                "Doris," +
                "Riaad," +
                "Mike," +
                "Lorna," +
                "Sunny," +
                "Nicolas," +
                "Dave";
        String[] nameList = names.split(",");
        for (String s : nameList) {
            testRegisterNikePlusAccount(s.trim().toLowerCase());
        }
    }

    private String constructRegisterJson(final String user) {
        return "{\n" +
                "    \"User\": {\n" +
                "        \"email\": \"" + user + "@akqa.com\",\n" +
                "        \"screenName\": \"" + user + "001\",\n" +
                "        \"firstName\": \"test1\",\n" +
                "        \"lastName\": \"test1\",\n" +
                "        \"password\": \"Runfree2014\",\n" +
                "        \"passwordConfirm\": \"Runfree2014\",\n" +
                "        \"dobDay\": \"1\",\n" +
                "        \"dobMonth\": \"3\",\n" +
                "        \"dobYear\": \"1988\",\n" +
                "        \"locale\": \"en_US\",\n" +
                "        \"ageLimit\": \"19\",\n" +
                "        \"birthplace\": \"oregon\",\n" +
                "        \"school\": \"DAVV\",\n" +
                "        \"position\": \"goalkeeper\",\n" +
                "        \"favouriteGameMusic\": \"favouriteGameMusic\",\n" +
                "        \"strengthAndWeakness\": \"strengthAndWeakness\",\n" +
                "        \"spendFreeTimeIn\": \"spendFreeTimeIn\",\n" +
                "        \"playsGameLike\": \"playsGameLike\",\n" +
                "        \"gameRole\": \"gameRole\",\n" +
                "        \"coachView\": \"coachView\",\n" +
                "        \"teamKind\": \"teamKind\",\n" +
                "        \"teamPlayedFor\": \"teamPlayedFor\",\n" +
                "        \"bio\": \"SW1A_1AA_UK\",\n" +
                "        \"postion\": \"position\",\n" +
                "        \"membershipId\": \"1442988\",\n" +
                "        \"registrationSiteId\": \"193828\",\n" +
                "        \"receiveEmail\": \"true\",\n" +
                "        \"mobileNumber\": \"3332221988\",\n" +
                "        \"mobileCarrier\": \"sprint\",\n" +
                "        \"mobileScreenName\": \"ddd221988\",\n" +
                "        \"temporaryPassword\": \"testoasswird\",\n" +
                "        \"gender\": \"female\",\n" +
                "        \"passwordQuestion\": \"mypasswordQuestion\",\n" +
                "        \"passwordAnswer\": \"mypasswordAnwser\",\n" +
                "        \"jersey\": \"JERSEY\",\n" +
                "        \"favposition\": \"favposition\",\n" +
                "        \"height\": \"2\",\n" +
                "        \"shoesize\": \"10\",\n" +
                "        \"playstyle\": \"smart\",\n" +
                "        \"gotomove\": \"yes\",\n" +
                "        \"homeAddress\": {\n" +
                "            \"address1\": \"testaddress1\",\n" +
                "            \"address2\": \"testaddress2\",\n" +
                "            \"city\": \"Beaverton\",\n" +
                "            \"state\": \"OR\",\n" +
                "            \"postalCode\": \"97006\",\n" +
                "            \"country\": \"US\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

    @Test
    @Ignore
    public void testNikePlusNSLRefreshToken() {
        String loginUrlFormat = "https://api.nike.com/nsl/v2.0/user/login?format=json&app=%s&client_id=%s&client_secret=%s";
        String application = "com.nike.brand.china.runclub";
        String clientId = "b1e41b998e0793241fbad69b5ed13649";
        String secret = "f6bdc17b47839831";
        String refreshTokenBody = "{\"grant_type\": \"refresh_token\", refresh_token: \"%s\"}";
        String tokenBody = String.format(refreshTokenBody, "sfyIQs8dw1hfcRfvFKLv8cDcP6kBV7Yu");
        String loginUrl = String.format(loginUrlFormat, application, clientId, secret);
        System.out.println(loginUrl);
        System.out.println(tokenBody);
        ClientResponse response = Client.create().resource(loginUrl).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, tokenBody);

        System.out.println(response.getStatus());
        System.out.println(response.getEntity(String.class));
//        {
//            "access_token": "ee901f0d84f55c8e0ef9f8a2b5ed2b37",
//                "refresh_token": "a90d9a6c72ff988bb5915579c3d73568",
//                "expires_in": "3599"
//        }
    }

    @Test
    public void testNikePlusNSLLogin() throws UnsupportedEncodingException {
        String loginUrlFormat = "https://api.nike.com/nsl/v2.0/user/login?format=json&app=%s&client_id=%s&client_secret=%s&getMinimalData=false&%s";
//        String loginUrlFormat = "https://api-preprod.nike.com/nsl/v2.0/user/login?format=json&app=%s&client_id=%s&client_secret=%s&getMinimalData=false&%s";
//        String loginUrlFormat = "https://api.stage.nike.com/nsl/v2.0/user/login?format=json&app=%s&client_id=%s&client_secret=%s&getMinimalData=false&%s";
//        String loginUrlFormat = "https://api-tie4.nike.com/nsl/v2.0/user/login?format=json&app=%s&client_id=%s&client_secret=%s&getMinimalData=false&%s";
        String application = "com.nike.brand.china.runclub";
//        String clientId = "9d3f318b2839cc337ffa0a0312a300f6" pre-prod;
        String clientId = "b1e41b998e0793241fbad69b5ed13649";
//        String secret = "0bf62c6547814bf1"; pre-prod
        String secret = "f6bdc17b47839831";
        String credentialFormat = "email=%s&password=%s";
//        String email = "ethanwang002@akqa.com"; pre-prod
        String email = "ygn2166@163.com";
        String password = "Ygn#018776#";
//        String password = "invalid";
        String credential = String.format(credentialFormat, email, password);
        String loginUrl = String.format(loginUrlFormat, application, clientId, secret, URLEncoder.encode(credential, "UTF-8"));

        System.out.println(credential);
        System.out.println(loginUrl);

        ClientResponse response = Client.create().resource(loginUrl).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, credential);

        System.out.println(response.getStatus());
        System.out.println(response.getEntity(String.class));
//        sample output:
//        {
//        "access_token":"9490a110f145a59ab2c256e3f2b63865",
//                "expires_in":"3599",
//                "refresh_token":"e4403947bb9d1a28523983027bb87311",
//                "pin":"3CDB55ED-1925-4F45-876F-35C3D3733DEB",
//                "User":{
//            "id":"10331433382",
//                    "profileId":"2db237aa-dab8-407d-acfc-06abb8ff826c",
//                    "screenName":"Ethanwang112233",
//                    "firstName":"Ethan",
//                    "lastName":"wang",
//                    "isOwner":"true",
//                    "isViewer":"true",
//                    "profileUrl":"http://my.nike.com//Ethanwang112233",
//                    "thumbnailUrl":"http://www.nike.com/nsl/services/thumbnails/person/10331433382",
//                    "visibility":"SOCIAL",
//                    "categories":[],
//            "locale":"zh_CN",
//                    "email":"ethan.wang.j@gmail.com",
//                    "userType":"defaultUser",
//                    "dobMonth":"3",
//                    "dobDay":"1",
//                    "dobYear":"1901",
//                    "login":"ethan.wang.j@gmail.com",
//                    "receiveEmail":"true",
//                    "gender":"male",
//                    "registrationSiteId":"1091",
//                    "homeAddress":{
//                "country":"CN"
//            },
//            "imageUrl":"",
//                    "userEmailSettings":{
//                "notifyRelationshipRequest":true,
//                        "notifyNewFriendshipRequest":true
//            },
//            "sitePreferences":{
//                "dateFormat":"m",
//                        "distanceUnit":"m",
//                        "weightUnit":"l",
//                        "dayOfWeek":"m"
//            },
//            "broadcastProperties":[],
//            "autoFriend":"false",
//                    "displayName":"Ethanwang112233"
//        }
//        }
    }

    @Test
    public void testGetActivityData() throws UnsupportedEncodingException {
        //accessToken=1a7502034e06e3521cb3418c63ce83ea
        //refreshToken=bd50d81b6ddaa66359f9d8dd37074be2
//        String listActivitiesUrl = "https://api.nike.com/v1/me/sport/activities?access_token=b3f0417aeb1c76ac6ac67846592fd85d";
        String listActivitiesUrl = "https://api.nike.com/me/sport/activities/RUNNING?access_token=b3f0417aeb1c76ac6ac67846592fd85d";

//        String url = "https://api.nike.com/me/sport/activities/{0}?access_token={1}";
        String url = "https://api.nike.com/me/sport/activities/{0}?access_token={1}&offset={2}&count={3}&startDate={4}&endDate={5}";


        String requestUrl = MessageFormat.format(url, "RUNNING", "b3f0417aeb1c76ac6ac67846592fd85d", 1, 10, "2013-01-01", "2015-01-01");


        //requestUrl = URLEncoder.encode(requestUrl, Charsets.UTF_8.displayName());

        System.out.println("requestUrl = " + requestUrl);

        //Accept: application/json
        //Content-Type: application/json
//        "access_token":"dvwOfT3hDHrL39IeKT08NIKGThXk",
//                "expires_in":"3600",
//                "refresh_token":"vLZQxCzFlYdXIN3q2WNxEUIy6mZ9swDr",
        String result = Client.create().resource("https://api.nike.com/me/sport/activities/RUNNING?access_token=GTkG2pS2TrPN6LtIpATf8We9Ph8o&offset=1&count=1000&startDate=2013-05-07&endDate=2015-05-10")
                .header("Accept", MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("appid", "com.nike.brand.china.runclub")
                .get(String.class);

        System.out.println(result);

    }

    @Test
    public void testGetUserProfile() {
        String result = Client.create().resource("https://api.nike.com/v2.0/me/activities/summary?" +
                "access_token=wDiSTZUwiVU5sxDG6FjIJQy0LwEM")
                .header("Accept", MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("appid", "com.nike.brand.china.runclub")
                .get(String.class);

        System.out.println(result);
    }

    @Test
    public void testGetUserProfileInfo() {
        String result = Client.create().resource("https://api.nike.com/v2.0/me/snapshot?" +
                "access_token=wDiSTZUwiVU5sxDG6FjIJQy0LwEM")
                .header("Accept", MediaType.APPLICATION_JSON)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .header("appid", "com.nike.brand.china.runclub")
                .get(String.class);

        System.out.println(result);
    }

    @Test
    public void testTimeZone() {
//        TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");
////        TimeZone timeZone = TimeZone.getTimeZone("Etc/GMT-8");
////        TimeZone timeZone = TimeZone.getTimeZone("test");
//        DateTimeZone.getAvailableIDs();
//        for (String zones : DateTimeZone.getAvailableIDs()) {
//            System.out.println(zones);
//        }
//        System.out.println();

//        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
//
//        Date parse = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").("2014-03-23T10:38:23Z");
//        DateTime dateTime = fmt.parseDateTime("2014-03-23T10:38:23Z").withZone();
//        DateTime dateTime1 = dateTime.toDateTime(DateTimeZone.UTC);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ").withZone(DateTimeZone.forID("Etc/GMT-8"));


        System.out.println(formatter.parseDateTime("2014-03-23T10:38:23Z").withZone(DateTimeZone.forID("Asia/Shanghai")));
//        https://api.stage.nike.com/nsl/user/friend/list?app=com.nike.brand.china.runclub&format=json&startIndex=1&count=1

    }

    @Test
    public void testGetFriend() {
        String api = "https://api.nike.com/nsl/user/friend/list?access_token=862a0568d467405e3a449c5baf9babe8&app=com.nike.brand.china.runclub&format=json&startIndex=0&count=20";
        String s = Client.create().resource(api).get(String.class);

        System.out.println(s);
    }

    @Test
    public void testTimeSpan() {
        DateTime dateTime = DateTimeFormat.forPattern("HH:mm:ss.SSS").parseDateTime("0:51:56.123");
        System.out.println(dateTime.get(DateTimeFieldType.secondOfDay()));
    }

    @Test
    public void testSync() {
        String openids = "| oaSyGt0T8-2OZuWJ7cHw0HgB6A-A |\n" +
                "| oaSyGt0XwFEIsMqDWgLdOmpfE6fE |\n" +
                "| oaSyGt1L1rsykx2TjORnTp_P1T_g |\n" +
                "| oaSyGt1ugYuQm9-6k0nQSFfnT6YQ |\n" +
                "| oaSyGt20ODjRm6ETwBOSwmKSzOxg |\n" +
                "| oaSyGt3fl42DH4a8aAN-ZTj7rHYI |\n" +
                "| oaSyGt5n5oKSChQ-Lo_JxcrtpT2E |\n" +
                "| oaSyGt5Y_dXXHoZj1KlCcGVd-F1c |\n" +
                "| oaSyGt6BiJfBt3jFhw63x0uhUId0 |\n" +
                "| oaSyGt7vczN1nwuWw0wunCteCGsA |\n" +
                "| oaSyGt84Wxa6Dkyu7GFBr1D89q1M |\n" +
                "| oaSyGt9rnkFjZ__7NPcDiZC8QAgQ |\n" +
                "| oaSyGtyuA23lBTFIGlukKi5gb4Vg |\n" +
                "| oaSyGtz90CocGk61dmNi1XBp5M6o |\n" +
                "| oaSyGt_SR5c79QPYaFWzLcmw5Szg |";
        String[] openidList = openids.split("\n");

        String urlformat = "http://114.215.189.62/apitool/activitydata/%s";
//        String urlformat = "http://localhost:8080/apitool/activitydata/%s";
        Client client = Client.create();
        for (String s : openidList) {
            s = s.replace("|", "").trim();
            System.out.println(client.resource(String.format(urlformat, s))
                    .header("Authorization", "Basic cm9vdDpyb290")
                    .get(String.class));
        }

    }

    @Test
    public void testHash() {
        String a = "buzzards";
        String b = "righto";

        assertThat(a.hashCode(), equalTo(b.hashCode()));

        HashMap<String, String> map = new HashMap<>();
        map.put(a, "test1");
        map.put(b, "test2");

        assertThat(map.get(a), equalTo("test1"));
        assertThat(map.get(b), equalTo("test2"));
    }

    @Test
    public void testForceSyncData() {
        String emails="| 3579661@qq.com           |\n" +
                "| 526057970@qq.com         |\n" +
                "| 564811882@qq.com         |\n" +
                "| chass.chiu@gmail.com     |\n" +
                "| kawaicoco@hotmail.com    |\n" +
                "| kellylaulyy@gmail.com    |\n" +
                "| lorna.luo@nike.com       |\n" +
                "| optest201209@gmail.com   |\n" +
                "| optest201209@hotmail.com |\n" +
                "| paul_smout@yahoo.co.uk   |\n" +
                "| sj830628@hotmail.com     |\n" +
                "| sunnyoo35@hotmail.com    |\n" +
                "| yacacia@163.com          |\n" +
                "| ygn2166@163.com          |\n" +
                "| zjingqiu@yahoo.com       |";


    }
}
