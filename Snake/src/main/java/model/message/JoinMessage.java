package model.message;

import java.io.Serializable;

public class JoinMessage extends Message implements Serializable {
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
