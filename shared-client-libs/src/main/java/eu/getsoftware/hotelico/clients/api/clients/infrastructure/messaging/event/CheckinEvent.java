package eu.getsoftware.hotelico.hotelapp.application.checkin.domain.message.event;

public class CheckinEvent extends DomainMessagePayload {
    private final String userId;
    private final String checkinStatus;

    public CheckinEvent(String userId, String checkinStatus) {
        this.userId = userId;
        this.checkinStatus = checkinStatus;
    }

    public String getUserId() {
        return userId;
    }

    public String getCheckinStatus() {
        return checkinStatus;
    }

    @Override
    public String toString() {
        return "CheckinEvent{" +
                "userId='" + userId + '\'' +
                ", checkinStatus='" + checkinStatus + '\'' +
                '}';
    }
}
