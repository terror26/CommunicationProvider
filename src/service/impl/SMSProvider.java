package service.impl;

import model.Account;
import model.ChannelEnum;
import model.Request;
import service.Provider;
import utils.ChannelValidator;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SMSProvider implements Provider {

    private final Logger logger = Logger.getLogger(SMSProvider.class.getName());
    private final String id;
    private List<ChannelEnum> channelEnumList;
    private List<Account> accountList;

    private boolean providerState;

    public SMSProvider(String id) {
        this.id = id;
        this.providerState = true;
    }

    @Override
    public boolean processRequest(Request request, Account account) {
        if(!ChannelValidator.validateRequest(request)) {
            logger.info("Validation failed for the request = " + request.getId());
            return false;
        }
        logger.info("Provider = " + this.getId() + " Processing request =" + request.toString() + " account credentials = " + account.toString());
        // API call to correct place
        return true;
    }

    @Override
    public List<ChannelEnum> getChannelsSupported() {
        return channelEnumList;
    }

    @Override
    public void setChannelEnum(List<ChannelEnum> channelEnumList) {
        this.channelEnumList = channelEnumList;
    }

    @Override
    public void setAccountsAccociated(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public List<Account> getAccountsAssociated() {
        return accountList;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean providerState() {
        return providerState;
    }

    @Override
    public void setProviderState(boolean state) {
        this.providerState = state;
    }

    @Override
    public String toString() {
        return "SMSProvider{" +
                "id='" + id + '\'' +
                ", channelEnumList=" + Arrays.toString(channelEnumList.toArray()) +
                ", accountList=" + Arrays.toString(accountList.toArray()) +
                ", providerState=" + providerState +
                '}';
    }
}
