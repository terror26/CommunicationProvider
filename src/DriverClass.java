import model.Account;
import model.ChannelEnum;
import model.Request;
import service.Provider;
import service.ProviderController;
import service.impl.SMSProvider;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverClass {

    private final Logger logger = Logger.getLogger(DriverClass.class.getName());

    public void startExecution() {
        try {
            Account acc1 = new Account("acc1", "pass1", ChannelEnum.SMS, ChannelEnum.OTP, ChannelEnum.SOUNDBOX);
            Account acc2 = new Account("acc2", "pass2", ChannelEnum.OTP, ChannelEnum.SOUNDBOX);
            Account acc3 = new Account("acc3", "pass3", ChannelEnum.OTP, ChannelEnum.SMS);

            Provider p1 = new SMSProvider("P1");
            p1.setChannelEnum(Arrays.asList(ChannelEnum.SMS, ChannelEnum.OTP));
            p1.setAccountsAccociated(Arrays.asList(acc1, acc2));
            Provider p2 = new SMSProvider("P2");
            p2.setChannelEnum(Arrays.asList(ChannelEnum.SMS, ChannelEnum.OTP));
            p2.setAccountsAccociated(Arrays.asList(acc2, acc3));

            ProviderController providerController = new ProviderController();
            providerController.addProvider(p1);
            providerController.addProvider(p2);

            if (providerController.getProvider(p1.getId()) == null) {
                logger.log(Level.SEVERE,"Error finding the p1");
            }

            providerController.updateState(p1.getId(), false); // p1 disabling

            // Updated p1 values
            Provider p1Updated = new SMSProvider("P1");
            p1Updated.setChannelEnum(Arrays.asList(ChannelEnum.SOUNDBOX, ChannelEnum.OTP, ChannelEnum.EMAIL));
            p1Updated.setAccountsAccociated(Arrays.asList(acc1));
            p1Updated.setProviderState(true);


            providerController.updateProvider(p1Updated); // update the provider p1 with the details of this new provider , id same and values different


            Request r1 = new Request("r1", ChannelEnum.EMAIL, "{\"sender\":\"kanishk\", \"receiver\":\"saqib\",\"subject\":\"first Email\", \"message\" :\"Hi there is this EMAIL working\" }");
            Request r2 = new Request("r2", ChannelEnum.SMS, "{\"mobileNumber\":\"12345\", \"message\":\"This is SMS\"}");

            logger.info("\n");
            providerController.printProvider(); //


            // 2 requests triggered
            try {
                boolean r1Result = providerController.processRequest(r1);
                logger.info("r1Result = " + r1Result);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "failed to process request = " + r1.getId() + " reason : = " + e.getMessage());
            }
            try {
                boolean r2Result = providerController.processRequest(r2);
                logger.info("Request = r2  result = " + r2Result);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "failed to process request = " + r2.getId() + " reason : = " + e.getMessage());
            }

        } catch (Exception err) {
            logger.info(err.getMessage());
        }
    }
}
