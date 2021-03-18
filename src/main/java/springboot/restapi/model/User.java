package springboot.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Column(name = "first_name", nullable = false)
        private String firstName;


        @Column(name = "last_name", nullable = true)
        private String lastName;

        @Column(name = "email", nullable = false, unique=true)
        @Email(message = "email should be a valid email")
        private String email;

        @Column(name = "dob", nullable = true)
        private Date dob;


        @Column(name = "marital_status", columnDefinition = "boolean default true")
        private Boolean maritalStatus;

        @Column(name = "password", nullable = true)
        private String password;

        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "otp_send_at", nullable = true)
        private Date otpSendAt;

    public User(String firstName, String lastName, String email, Date dob, Boolean maritalStatus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.maritalStatus = maritalStatus;
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
