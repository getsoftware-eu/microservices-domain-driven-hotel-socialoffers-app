package eu.getsoftware.hotelico.infrastructure.hotel.model;//package eu.getsoftware.hotelico.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.io.Serializable;
//
///**
// * Entity! with DOZER it will be mapped to client Model Object
// */
//
//@Entity
//@Table(name = "user")
//public class User implements Serializable {
//
//    private static final long serialVersionUID = -6809049173391335091L;
//
//    @Id
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    private int id;
//
//    public User() {
//        super();
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    @Column
//    private String firstName;
//
//    @Column
//    private String lastName;
//
//    @Column
//    private String password;
//
//    @Column
//    private String email;
//
//    @Column
//    private String company;
//
//    @Column
//    private String jobTitle;
//
//    @Column
//    private String jobDescriptor;
//
//    @Column
//    private int hotelId;
//
//    //    @Column
//    //    private boolean authLinkedIn;   
//
//    //    @Column
//    //    private Integer hotelId;
//
//
//    //    @Column
//    //    private List<String> languages;
//    //   
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    //    public int getHotelId()
//    //    {
//    //        return hotelId;
//    //    }
//    //
//    //    public void setHotelId(int hotelId)
//    //    {
//    //        this.hotelId = hotelId;
//    //    }
//
//    public String getPassword()
//    {
//        return password;
//    }
//
//    public String getEmail()
//    {
//        return email;
//    }
//
//    public String getCompany()
//    {
//        return company;
//    }
//
//    public String getJobTitle()
//    {
//        return jobTitle;
//    }
//
//    //    public List<String> getLanguages()
//    //    {
//    //        return languages;
//    //    }
//
//    public void setPassword(String password)
//    {
//        this.password = password;
//    }
//
//    public void setEmail(String email)
//    {
//        this.email = email;
//    }
//
//    public void setCompany(String company)
//    {
//        this.company = company;
//    }
//
//    public void setJobTitle(String jobTitle)
//    {
//        this.jobTitle = jobTitle;
//    }
//
//    //    public void setLanguages(List<String> languages)
//    //    {
//    //        this.languages = languages;
//    //    }
//
//    //    public void setHotelId(Integer hotelId)
//    //    {
//    //        this.hotelId = hotelId;
//    //    }
//
//    //    public boolean isAuthLinkedIn()
//    //    {
//    //        return authLinkedIn;
//    //    }
//    //
//    //    public void setAuthLinkedIn(boolean authLinkedIn)
//    //    {
//    //        this.authLinkedIn = authLinkedIn;
//    //    }
//
//    public int getHotelId()
//    {
//        return hotelId;
//    }
//
//    public void setHotelId(int hotelId)
//    {
//        this.hotelId = hotelId;
//    }
//
//    public String getJobDescriptor()
//    {
//        return jobDescriptor;
//    }
//
//    public void setJobDescriptor(String jobDescriptor)
//    {
//        this.jobDescriptor = jobDescriptor;
//    }
//}
