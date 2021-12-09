package model.message;

import java.io.Serializable;

public class AckMessage extends Message implements Serializable {

    public AckMessage(long msgSeq, int senderId, int receiverId) {
        super(msgSeq, senderId, receiverId);
        type = Type.ACK;
    }
}
