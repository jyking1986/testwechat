import com.akqa.wechat.messages.IMessage;
import com.akqa.wechat.messages.IMessageEnvelope;
import com.akqa.wechat.messages.IMessageEnveloperHandler;
import com.google.common.base.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: ethan.wang
 * Date: 12/24/13
 * Time: 6:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class engine implements IMessageEnveloperHandler {

    @Override
    public Optional<IMessage> handle(IMessageEnvelope messageEnvelope) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
