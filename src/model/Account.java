package model;

import java.util.Arrays;

public class Account {
    private String username;
    private String password;
    final ChannelEnum[] channelsSupported;
    public Account(String username, String password,ChannelEnum... channels) {
        this.username = username;
        this.password = password;
        this.channelsSupported = channels;
    }

    public ChannelEnum[] getChannelsSupported() {
        return channelsSupported;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", channelsSupported=" + Arrays.toString(channelsSupported) +
                '}';
    }
}
