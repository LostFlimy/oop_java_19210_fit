package model.message;

public abstract class Message {
    private final long msgSeq;
    private int senderId;
    private int receiverId;
    protected Type type;

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public long getMsgSeq() {
        return msgSeq;
    }

    public Message(
            long msgSeq
    ){
        this.msgSeq = msgSeq;
    }

    public Message(
            long msgSeq,
            int senderId,
            int receiverId
            ) {
        this.msgSeq = msgSeq;
        this.receiverId = receiverId;
        this.senderId = senderId;
    }

    //PingMsg ping = 2;
    //SteerMsg steer = 3;
    //AckMsg ack = 4;
    //StateMsg state = 5;
    //AnnouncementMsg announcement = 6;
    //JoinMsg join = 7;
    //ErrorMsg error = 8;
    //RoleChangeMsg role_change = 9;

    public enum Type {
        PING(2),
        STEER(3),
        ACK(4),
        STATE(5),
        ANNOUNCEMENT(6),
        JOIN(7),
        ERROR(8),
        ROLECHANGE(9);

        private int val;

        private Type(int val) {
            this.val = val;
        }
    }
}
