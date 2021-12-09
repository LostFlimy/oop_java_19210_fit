package model.message;

import java.io.Serializable;

public class PingMsg extends Message implements Serializable {

    public PingMsg(long msgSeq) {
        super(msgSeq);
        type = Type.PING;
    }
}
