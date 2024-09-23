package gr.aueb.cf.schoolappdemo.dto;

public class UserReadOnlyDTO extends BaseUserDTO {
    private Integer id;

    public UserReadOnlyDTO() {
    }

    public UserReadOnlyDTO( Integer id, String username, String password) {
        super(username, password);
        this.id = id;
    }
}
