import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 7/22/14
 * Time: 12:38 PM
 */
public class UpdateUserProfileTest {
    @Test
    public void testSync() throws Exception {
        UpdateUserProfile profile = new UpdateUserProfile();
        List<String> stringList = profile.loadNikePlusIds();
        int count = stringList.size();
        int startIndex = 975;
        for (int i = startIndex; i < count; i++) {
            System.out.println(i + ": ");
            profile.sync(stringList.get(i));
        }
    }
}
