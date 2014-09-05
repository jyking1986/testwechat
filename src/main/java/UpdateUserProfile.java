import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 7/22/14
 * Time: 12:32 PM
 */
public class UpdateUserProfile {

    final String URL_FORMAT = "http://runclub.nike.com.cn/apitool/nikepluslogin/%s";
    private final Client client;

    public UpdateUserProfile() {
        client = Client.create();
    }

    List<String> readFileInLine(InputStream fis) throws IOException {
        List<String> lines = new ArrayList<String>();
        BufferedReader br;
        String line;

        br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
        while ((line = br.readLine()) != null) {
            // Deal with the line
            lines.add(line.replace("\"", ""));
        }

        br.close();
        return lines;
    }

    protected List<String> loadNikePlusIds() throws IOException, URISyntaxException {
        return readFileInLine(UpdateUserProfile.class.getResourceAsStream("/nikeplususer.csv"));


    }

    public void sync(final String openId) {
        System.out.print(openId+": ");
        ClientResponse clientResponse = client.resource(String.format(URL_FORMAT, openId))
                .header("Authorization", "Basic cm9vdDpyb290")
                .get(ClientResponse.class);
        System.out.println(clientResponse.getStatus());
    }
}
