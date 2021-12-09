package model.message;

import java.io.Serializable;

public class ErrorMessage extends Message implements Serializable {
    private String msg;

    public ErrorMessage(long msgSeq, String msg) {
        super(msgSeq);
        this.msg = msg;
        type = Type.ERROR;
    }

    public String getMsg() {
        return msg;
    }
}
