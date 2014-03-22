import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 12/24/13
 * Time: 6:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class testConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty
    private String test;

    public String getTest() {
        return test;
    }
}
