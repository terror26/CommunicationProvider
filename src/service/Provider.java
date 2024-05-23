package service;

import model.Account;
import model.ChannelEnum;
import model.Request;

import java.util.List;

public interface Provider {
    boolean processRequest(Request request, Account account);

    List<ChannelEnum> getChannelsSupported();

    void setChannelEnum(List<ChannelEnum> channelEnumList);
    void setAccountsAccociated(List<Account> accountList);

    List<Account> getAccountsAssociated();

    String getId();

    boolean providerState(); // provider active or not

    void setProviderState(boolean state);

    String toString();
}
