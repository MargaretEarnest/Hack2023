package server;

public class Response implements Message{
    private final String content, webAddress;

    public Response(final String content, final String webAddress) {
        this.content = content;
        this.webAddress = webAddress;
    }
    @Override
    public String getContent() {
        return this.content;
    }

    @Override
    public String getWebAddress() {
        return this.webAddress;
    }
}
