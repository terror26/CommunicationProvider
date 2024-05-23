package model;

public class Request {

    private final String id;
    private final ChannelEnum channelEnum;
    private final String jsonBody;

    public Request(String id,ChannelEnum channelEnum, String jsonBody) {
        this.id = id;
        this.channelEnum = channelEnum;
        this.jsonBody = jsonBody;
    }

    public String getId() {
        return id;
    }

    public ChannelEnum getChannelEnum() {
        return channelEnum;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", channelEnum=" + channelEnum +
                ", jsonBody='" + jsonBody + '\'' +
                '}';
    }
}
