package utils;

import model.ChannelEnum;
import model.Request;

public class ChannelValidator {

    public static boolean validateRequest(Request request) {
        //mobile number and the message
        if (ChannelEnum.SMS.equals(request.getChannelEnum())) {
            return request.getJsonBody().contains("mobileNumber") && request.getJsonBody().contains("message");
        } else if (ChannelEnum.EMAIL.equals(request.getChannelEnum())) {
            return request.getJsonBody().contains("sender") && request.getJsonBody().contains("receiver") && request.getJsonBody().contains("subject") && request.getJsonBody().contains("message");
        }
        return false; // unrecognized channel
    }
    //sender":"kanishk", "reciever":"saqib","subject":"first Email", "message" :"Hi there is this EMAIL working"
}
