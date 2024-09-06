package eu.getsoftware.hotelico.customer.application.domain.model;

import java.util.Date;
import java.util.Optional;

public interface ICustomerDetails {

    Optional<String> getProfileImageLink();

    void setProfileImageLink(String profileImageLink);

    String getCity();

    void setBirthday(Date birthdayDate);

    void setProfileImageUrl(String s);

    void setCompany(String company);

    void setJobTitle(String jobTitle);

    void setOriginalCity(String originalCity);

    void setJobDescriptor(String jobDescriptor);

    String getProfileImageUrl();
}
