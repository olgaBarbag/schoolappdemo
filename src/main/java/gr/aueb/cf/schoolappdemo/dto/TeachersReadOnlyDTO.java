package gr.aueb.cf.schoolappdemo.dto;

public class TeachersReadOnlyDTO extends BaseTeacherDTO{

    private Integer id;

    public TeachersReadOnlyDTO() {
    }

    public TeachersReadOnlyDTO(Integer id, String firstname, String lastname) {
        super(firstname, lastname);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
