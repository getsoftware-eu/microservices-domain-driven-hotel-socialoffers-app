package hotelico.service.booking.application.customer.domain.model;

public interface ICustomerPreferences {
    void setHideChromePushPopup(boolean hideChromePushPopup);

    void setPrefferedLanguage(String prefferedLanguage);

    void setAllowHotelNotification(boolean allowHotelNotification);

    void setHideHotelListPopup(boolean hideHotelListPopup);

    void setHideCheckinPopup(boolean hideCheckinPopup);

    String getPrefferedLanguage();

    boolean isAllowHotelNotification();

    void setHideWallPopup(boolean hideWallPopup);
}
