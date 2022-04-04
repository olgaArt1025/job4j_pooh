package ru.job4j.pooh;

public class Req {
    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;

    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        String[] line = content.split(System.lineSeparator());
        String[] words = line[0].split("/");
        String poohMode = words[1];
        String httpRequestType = words[0].trim();
        String[] sourceName = words[2].split(" ");
        String param = "";
        if ("POST".equals(httpRequestType)) {
            param = line[7];
        }
        if ("GET".equals(httpRequestType) && "topic".equals(words[1])) {
            String[] params = words[3].split(" ");
            param = params[0];
        }
        return new Req(httpRequestType, poohMode, sourceName[0], param);
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}
