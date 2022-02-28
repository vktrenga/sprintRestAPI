package springboot.restapi.config;

public class ConstantVariables {
    //API
    public final static String API = "/api";
    public final static String AUTHENTICATE = "/authenticate";
    public final static String USERS = "/users";
    public final static String GET_USER = "/users/{id}";
    public final static String OTP_SEND = "/otpsend";

    //messages
    public final static String EMAIL_CONTENT = "Your OTP for access API is ";
    public final static String MESSAGE_KEY = "message";
    public final static String OTP_SEND_MESSAGE = "OTP send your email ";
    public final static String OTP_SUBJECT = "OTP to access API";
    public final static String DELETE_KEY = "deleted";
    public final static String INVALID_EMAIL = "Invalid Email";
    public final static String OTP_EXPIRED = "OTP already expired";
    public final static String DATA_NOT_FOUND = "Data Not Found";
    public final static String VALIDATION_FAILED = "Validation Failed";
    public final static String USER_NOT_FOUND = "User not found with email: ";

    //JWT
    public final static String UNAUTHORIZED = "Unauthorized";
    public final static String JWT_EXPIRED = "JWT Token has expired";
    public final static String JWT_TOKEN_NOT_FOUND = "Unable to get JWT Token";
    public final static String JWT_TOKEN_NOT_START_WITH_BEARER = "JWT Token does not begin with Bearer String";
    public final static String JWT_BEARER = "Bearer ";
    public final static String JWT_HEADER = "Authorization";
    public final static String USER_DISABLED = "USER_DISABLED ";
    public final static String INVALID_CREDENTIALS = "INVALID_CREDENTIALS ";

    //Model Variable
    public final static String FIRST_NAME = "first_name";
    public final static String LAST_NAME = "last_name";
    public final static String EMAIL = "email";
    public final static String DOB = "dob";
    public final static String MARITAL_STATUS = "marital_status";
    public final static String PASSWORD = "password";
    public final static String OTP_SEND_AT = "otp_send_at";
    public final static String USER_TABLE = "user";

    //Model validation message
    public final static String FIRST_NAME_EMPTY = "first name must not be empty";
    public final static String EMAIL_EMPTY = "email should be a valid email";


}
