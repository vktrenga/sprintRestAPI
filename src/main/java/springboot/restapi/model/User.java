package springboot.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import springboot.restapi.config.ConstantVariables;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = ConstantVariables.USER_TABLE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(min = 2, message = "First Name should have atleast 2 characters")
    private String firstName;

    private String lastName;
    private Date dob;
    private Boolean maritalStatus;
    private String password;
    private Date otpSendAt;
    @Email
    @NotBlank
    private String email;

    public User(String firstName, String lastName, String email, Date dob, Boolean maritalStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.maritalStatus = maritalStatus;
    }
    public User() {

    }


        public long getId() {
            return id;
        }

        public void setId(long id ) {
            this.id  = id;
        }

        public void setFirstName(String firstName){
            this.firstName = firstName;
        }

        public String getFirstName(){
             return firstName;
        }

        public void setLastName(String lastName){
            this.lastName = lastName;
        }

        public String getLastName(){
            return lastName;
        }

        public void setEmail(String email){
            this.email = email;
        }

        public String getEmail(){
            return email;
        }

        public void setMaritalStatus(Boolean marital_status){
            this.maritalStatus = marital_status;
        }

        public Boolean getMaritalStatus(){
          return maritalStatus;
        }

        public void setDob(Date dob){
            this.dob = dob;
        }

        public Date getDob(){
            return  dob;
        }

        public void setPassword(String password){
            this.password = password;
        }

        @JsonIgnore
        public String getPassword(){
            return password;
        }

        public void setOtpSendAt(Date otpSendAt){
            this.otpSendAt = otpSendAt;
        }

        @JsonIgnore
        public Date getOtpSendAt(){
            return otpSendAt;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", email='" + email + '\'' +
                    ", maritalStatus=" + maritalStatus +
                    ", dob='" + dob + '\'' +
                    '}';
        }
}
