package job.model;

public class Job {
    private int id;
    private String title;
    private String description;
    private String location;
    private String company;
    private double salary;
    private String recruiterEmail;  

  
    public Job(int id, String title, String description, String location, String company, double salary, String recruiterEmail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.company = company;
        this.salary = salary;
        this.recruiterEmail = recruiterEmail;
    }

   
    public Job(String title, String description, String location, String company, double salary, String recruiterEmail) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.company = company;
        this.salary = salary;
        this.recruiterEmail = recruiterEmail;
    }

   
    public int getId() { 
    	return id; 
    }
    
    public String getTitle() { 
    	return title; 
    }
    
    public String getDescription() {
    	return description; 
    }
    
    public String getLocation() { 
    	return location; 
    }
    
    public String getCompany() { 
    	return company; 
    }
    
    public double getSalary() { 
    	return salary; 
    }
    
    public String getRecruiterEmail() {
    	return recruiterEmail; 
    }

    public void setId(int id) { 
    	this.id = id; 
    }
    
    public void setTitle(String title) { 
    	this.title = title; 
    }
    
    public void setDescription(String description) { 
    	this.description = description; 
    }
    
    public void setLocation(String location) { 
    	this.location = location; 
    }
    
    public void setCompany(String company) { 
    	this.company = company; 
    }
    
    public void setSalary(double salary) {
    	this.salary = salary;
    }
    
    public void setRecruiterEmail(String recruiterEmail) { 
    	this.recruiterEmail = recruiterEmail; 
    }
    
    @Override
    public String toString() {
        return "Job ID: " + id + 
               "\nTitle: " + title + 
               "\nDescription: " + description + 
               "\nLocation: " + location + 
               "\nCompany: " + company + 
               "\nSalary: $" + salary + 
               "\nRecruiter Email: " + recruiterEmail + "\n";
    }
}
