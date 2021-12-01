package model.message;

public class ErrorMessage extends Message{
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
