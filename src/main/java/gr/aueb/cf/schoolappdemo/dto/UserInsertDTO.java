package gr.aueb.cf.schoolappdemo.dto;

public class UserInsertDTO extends BaseUserDTO {

    private String role;

    public UserInsertDTO() {
    }

    public UserInsertDTO(String username, String password, String confirmPassword, String role) {
        super(username, password, confirmPassword);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
