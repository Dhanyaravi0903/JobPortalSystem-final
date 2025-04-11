package job.model;

public class User {
    private String name;
    private String email;
    private String password;
    private String role;
    private String company;
    private String idProof;

   
    public User(String name, String email, String password, String role, String company, String idProof) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.company = company;
        this.idProof = idProof;
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }
}
