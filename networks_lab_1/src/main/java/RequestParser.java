import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {
    private String address;
    private String operation;

    public void parse(String request) {
        Pattern addrPattern = Pattern.compile("([0-9])+\\W([0-9])+\\W([0-9]+)\\W(([0-9])+)");
        Pattern operPattern = Pattern.compile("start");
        Matcher matcher = addrPattern.matcher(request);
        address = request.substring(matcher.start(), matcher.end());

        matcher = operPattern.matcher(request);
        operation = request.substring(matcher.start(), matcher.end());
    }

    public String getAddr() {
        return address;
    }

    public String getOperation() {
        return operation;
    }
}
