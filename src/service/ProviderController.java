package service;

import model.Account;
import model.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ProviderController {
    Map<String, Provider> providerMap = new HashMap<>();

    private final Logger logger = Logger.getLogger(ProviderController.class.getName());

    public ProviderController() {
    }

    public boolean addProvider(Provider provider) {
        if (!validateProvider(provider)) {
            logger.info("Error creating the provider " + provider.getId());
            return false;
        }
        providerMap.put(provider.getId(), provider);
        return true;
    }

    private boolean validateProvider(Provider provider) {
        return provider.getChannelsSupported() != null && provider.getChannelsSupported().size() > 0 && provider.getAccountsAssociated() != null && provider.getAccountsAssociated().size() > 0;
    }

    public Provider getProvider(String id) {
        return providerMap.get(id);
    }

    public boolean updateState(String id, boolean state) {
        Provider p = getProvider(id);
        if (p == null) {
            logger.info("Error updating the state of unknown provider");
            return false;
        }
        p.setProviderState(state);
        logger.info("updateState to " + p.toString());
        return true;
    }

    public void updateProvider(Provider p1Updated) {
        Provider p = getProvider(p1Updated.getId());
        if (p1Updated == null) {
            logger.info("Error updating the values of unknown provider , id= " + p1Updated.getId());
            return;
        }
        addProvider(p1Updated);
    }

    public void printProvider() {
        for (Provider p : providerMap.values()) {
            logger.info("p = " + p.toString());
        }
    }

    public boolean processRequest(Request request) {
        logger.info("Starting to process request = " +request.getId());
        if(sendToCorrectProvider(request) ) {
            return true;
        }
        return false;
    }

    private boolean sendToCorrectProvider(Request request) {
        for(Provider p: providerMap.values()) {
            if(p.providerState() && p.getChannelsSupported().contains(request.getChannelEnum())) { // if active
                logger.info("provider selected for request = " + request.getId() + " = " + p.getId());
                // find first as of now TODO : can be improved
                return p.processRequest(request, getOneAccount(p));
            }
        }
        return false;
    }

    private Account getOneAccount(Provider p) {
        return p.getAccountsAssociated().get(0); // first account always used
    }
}
