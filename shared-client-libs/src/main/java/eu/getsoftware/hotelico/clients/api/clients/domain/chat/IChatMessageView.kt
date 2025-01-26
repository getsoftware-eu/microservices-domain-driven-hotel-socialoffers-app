package eu.getsoftware.hotelico.clients.api.clients.domain.chat;

import java.sql.Timestamp;

public interface IChatMessageView {
    boolean isActive();

    String getMessage();

    long getInitId();

    Timestamp getTimestamp();

//    CustomerDomainEntityId getSenderId();

    boolean isSeenByReceiver();

    boolean isDelieveredToReceiver();

//    CustomerDomainEntityId getReceiverId();

    String getSpecialChatContent();
}
