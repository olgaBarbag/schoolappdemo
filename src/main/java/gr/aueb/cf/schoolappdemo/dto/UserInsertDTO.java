package gr.aueb.cf.schoolappdemo.dto;

public class UserInsertDTO extends BaseUserDTO {

    public UserInsertDTO() {
    }

    public UserInsertDTO(String username, String password, String confirmPassword) {
        super(username, password, confirmPassword);
    }
}
