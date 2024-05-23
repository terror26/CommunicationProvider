import model.Account;
import model.ChannelEnum;
import model.Request;
import service.Provider;
import service.ProviderController;
import service.impl.SMSProvider;

import java.util.Arrays;
import java.util.logging.Logger;

public class Main {
    /*
    addProvider(Provider provider)
This function is to create and store the provider.
It can only be created if it passes all necessary validations.

getProvider(String providerId)
To get the provider details for the given providerId.

updateState(String providerId, boolean active)
To update the state of the provider to active/inactive

updateProvider(Provider provider)
To update the details of an existing provider.

processRequest(Request)
     */

    private final static Logger logger = Logger.getLogger(Main.class.getName());

    private final DriverClass driverClass = new DriverClass();
    public static void main(String[] args) {
        new Main().driverClass.startExecution();
    }
}