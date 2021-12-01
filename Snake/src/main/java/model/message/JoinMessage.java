package model.message;

public class JoinMessage extends Message{
    private String name;

    public String getName() {
        return name;
    }

    public JoinMessage(long msgSeq, String name) {
        super(msgSeq);
        this.name = name;
        type = Type.JOIN;
    }
}
