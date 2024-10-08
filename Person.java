import java.util.ArrayList;
import java.util.List;

public class Person {
    private String identifier; 
    private String name;
    private String surname;
    private String birthDate;
    private String gender;
    private String birthplace;
    private String hometown;
    private List<String> studiedAt;
    private List<String> workedAt;
    private List<String> movies;
    private String groupCode;

    // Constructor with default empty values
    public Person(String identifier, String name, String surname, String birthDate, String gender, 
                  String birthplace, String hometown, List<String> studiedAt, List<String> workedAt, 
                  List<String> movies, String groupCode) {
        this.identifier = identifier != null ? identifier : "";
        this.name = name != null ? name : "";
        this.surname = surname != null ? surname : "";
        this.birthDate = birthDate != null ? birthDate : "";
        this.gender = gender != null ? gender : "";
        this.birthplace = birthplace != null ? birthplace : "";
        this.hometown = hometown != null ? hometown : "";
        this.studiedAt = studiedAt != null ? studiedAt : new ArrayList<>();
        this.workedAt = workedAt != null ? workedAt : new ArrayList<>();
        this.movies = movies != null ? movies : new ArrayList<>();
        this.groupCode = groupCode != null ? groupCode : "";
    }

    // Getters and setters remain the same
    public String getIdentifier() { return identifier; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getBirthDate() { return birthDate; }
    public String getGender() { return gender; }
    public String getBirthplace() { return birthplace; }
    public String getHometown() { return hometown; }
    public List<String> getStudiedAt() { return studiedAt; }
    public List<String> getWorkedAt() { return workedAt; }
    public List<String> getMovies() { return movies; }
    public String getGroupCode() { return groupCode; }

    // Methods to update individual fields
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public void setGender(String gender) { this.gender = gender; }
    public void setBirthplace(String birthplace) { this.birthplace = birthplace; }
    public void setHometown(String hometown) { this.hometown = hometown; }
    public void setStudiedAt(List<String> studiedAt) { this.studiedAt = studiedAt; }
    public void setWorkedAt(List<String> workedAt) { this.workedAt = workedAt; }
    public void setMovies(List<String> movies) { this.movies = movies; }
    public void setGroupCode(String groupCode) { this.groupCode = groupCode; }
}
