package eu.getsoftware.hotelico.hotelapp.application.chat.domain.model;

import eu.getsoftware.hotelico.hotelapp.adapter.out.persistence.customer.model.CustomerRootEntity;
import eu.getsoftware.hotelico.hotelapp.application.customer.domain.model.ICustomerRootEntity;

import java.sql.Timestamp;

public interface IChatMessageView {
    boolean isActive();

    String getMessage();

    long getInitId();

    Timestamp getTimestamp();

    ICustomerRootEntity getSender();

    boolean idSeenByReceiver();

    boolean isDelieveredToReceiver();

    CustomerRootEntity getReceiver();

    String getSpecialChatContent();
}
