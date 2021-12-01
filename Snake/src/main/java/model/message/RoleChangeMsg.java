package model.message;

import model.NodeRole;

public class RoleChangeMsg extends Message{
    private NodeRole sender;
    private NodeRole receiver;

    public NodeRole getSender() {
        return sender;
    }

    public NodeRole getReceiver() {
        return receiver;
    }

    public RoleChangeMsg(long msgSeq, int senderId, int receiverId, NodeRole sender, NodeRole receiver) {
        super(msgSeq, senderId, receiverId);
        this.sender = sender;
        this.receiver = receiver;
        type = Type.ROLECHANGE;
    }
}
