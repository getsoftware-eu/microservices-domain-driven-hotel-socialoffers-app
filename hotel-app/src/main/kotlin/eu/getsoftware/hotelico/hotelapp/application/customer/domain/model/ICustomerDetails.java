package eu.getsoftware.hotelico.hotelapp.application.customer.domain.model;

import java.util.Date;
import java.util.Optional;
import java.util.zip.ZipEntry;

public interface ICustomerDetails {

    Optional<String> getProfileImageLink();

    void setProfileImageLink(String profileImageLink);

    String getCity();

    void setCity(String city);
    
    void setBirthday(Date birthdayDate);

    void setProfileImageUrl(String s);

    void setCompany(String company);

    void setJobTitle(String jobTitle);

    void setOriginalCity(String originalCity);

    void setJobDescriptor(String jobDescriptor);

    String getProfileImageUrl();

    ZipEntry getBirthday();
}
