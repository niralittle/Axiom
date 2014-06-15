package axiom.entity;

/**
 *
 * @author Nira
 */
public class Major {
    int id;
    String name;
    int facultyId;

    public Major(int id, String name, int facultyId) {
        this.id = id;
        this.name = name;
        this.facultyId = facultyId;
    }

    public Major() { 
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    


}
