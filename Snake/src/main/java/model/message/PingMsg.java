package model.message;

public class PingMsg extends Message{

    public PingMsg(long msgSeq) {
        super(msgSeq);
        type = Type.PING;
    }
}
