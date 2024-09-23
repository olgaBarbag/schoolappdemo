package gr.aueb.cf.schoolappdemo.dto;

public class UserReadOnlyDTO extends BaseUserDTO {
    private Integer id;

    public UserReadOnlyDTO() {
    }

    public UserReadOnlyDTO(String username, String password, Integer id) {
        super(username, password);
        this.id = id;
    }
}
