package eu.getsoftware.hotelico.clients.api.clients.domain.chat;

import java.sql.Timestamp;

public interface IChatMessageView {
    boolean isActive();

    String getMessage();

    long getInitId();

    Timestamp getTimestamp();

    long getSenderId();

    boolean isSeenByReceiver();

    boolean isDelieveredToReceiver();

    long getReceiverId();

    String getSpecialChatContent();
}
