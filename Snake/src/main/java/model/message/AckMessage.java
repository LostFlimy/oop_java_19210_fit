package model.message;

public class AckMessage extends Message{

    public AckMessage(long msgSeq, int senderId, int receiverId) {
        super(msgSeq, senderId, receiverId);
        type = Type.ACK;
    }
}
