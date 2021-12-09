package model.message;

import model.NodeRole;

import java.io.Serializable;

public class RoleChangeMsg extends Message implements Serializable {
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
