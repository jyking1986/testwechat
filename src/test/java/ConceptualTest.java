import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 1/3/14
 * Time: 1:25 PM
 */
public class ConceptualTest {
    @Test
    public void testLongToDate() {
        //1389058954
        System.out.println(new Date());
        Date d = new Date(1383207385l * 1000);
        System.out.println(d);
    }

    @Test
    @Ignore
    public void testRegisterNikePlusAccount() {
//        dev
        String appName = "com.nike.brand.china.runclub";
        String clientId = "9d3f318b2839cc337ffa0a0312a300f6";
        String clientSecret = "0bf62c6547814bf1";
        String server = "https://api-preprod.nike.com";

        String urlFormat = "%s/v2.0/oauth2/register?client_id=%s&client_secret=%s&app=%s&format=json";

        String url = String.format(urlFormat, server, clientId, clientSecret, appName);

        ClientResponse response = Client.create().resource(url).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, "object=" + constructRegisterJson());

        System.out.println(response.getStatus());
        System.out.println(response.getEntity(String.class));
    }

    private String constructRegisterJson() {
        return "{\n" +
                "    \"User\": {\n" +
                "        \"email\": \"ethanwang004@akqa.com\",\n" +
                "        \"screenName\": \"ethanwang004\",\n" +
                "        \"firstName\": \"test1\",\n" +
                "        \"lastName\": \"test1\",\n" +
                "        \"password\": \"Qazxsw12\",\n" +
                "        \"passwordConfirm\": \"Qazxsw12\",\n" +
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
        String application = "com.nike.test";
//        String application = "com.nike.brand.Holiday12Game";
        String clientId = "d4daafa477980451225c6e4531bec3a1";
        String secret = "c64565ec625d4274";
        String refreshTokenBody = "{\"grant_type\": \"refresh_token\", refresh_token: \"%s\"}";
        String tokenBody = String.format(refreshTokenBody, "e4403947bb9d1a28523983027bb87311");
        String loginUrl = String.format(loginUrlFormat, application, clientId, secret);
        ClientResponse response = Client.create().resource(loginUrl).type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, tokenBody);
        System.out.println(response.getStatus());
        System.out.println(response.getEntity(String.class));
//        {
//            "access_token": "db0457b82777fcba06986f9c2fc283f8",
//                "refresh_token": "e4403947bb9d1a28523983027bb87311",
//                "expires_in": "3599"
//        }
    }

    @Test
    public void testNikePlusNSLLogin() {
//        String loginUrlFormat = "https://api.nike.com/nsl/v2.0/user/login?format=json&app=%s&client_id=%s&client_secret=%s&getMinimalData=false&%s";
        String loginUrlFormat = "https://api-preprod.nike.com/nsl/v2.0/user/login?format=json&app=%s&client_id=%s&client_secret=%s&getMinimalData=false&%s";
//        String loginUrlFormat = "https://api.stage.nike.com/nsl/v2.0/user/login?format=json&app=%s&client_id=%s&client_secret=%s&getMinimalData=false&%s";
//        String loginUrlFormat = "https://api-tie4.nike.com/nsl/v2.0/user/login?format=json&app=%s&client_id=%s&client_secret=%s&getMinimalData=false&%s";
        String application = "com.nike.brand.china.runclub";
        String clientId = "9d3f318b2839cc337ffa0a0312a300f6";
        String secret = "0bf62c6547814bf1";
        String credentialFormat = "email=%s&password=%s";
        String email = "ethanwang002@akqa.com";
        String password = "Qazxsw12";
//        String password = "invalid";
        String credential = String.format(credentialFormat, email, password);
        String loginUrl = String.format(loginUrlFormat, application, clientId, secret, credential);

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
}
