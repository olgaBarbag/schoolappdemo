package gr.aueb.cf.schoolappdemo.dto;

public class TeacherUpdateDTO extends BaseTeacherDTO {
    private Integer id;

    public TeacherUpdateDTO() {
    }

    public TeacherUpdateDTO(String firstname, String lastname, Integer id) {
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
