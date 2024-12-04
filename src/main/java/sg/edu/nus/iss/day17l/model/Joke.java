package sg.edu.nus.iss.day17l.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Joke {

    @Positive(message = "ID must be a postive number.")
    // @NotBlank(message = "ID cannot be blank.") // This does not work as notblank is for strings
    @NotNull(message = "ID must not be null.")
    private Integer id;

    @NotBlank(message = "Type cannot be blank.")
    private String type;

    @NotBlank(message = "Setup cannot be blank.")
    private String setup;

    @NotBlank(message = "Punchline cannot be blank.")
    private String punchline;

    

    // Constructors
    public Joke() {
    }
    
    public Joke(String type, String setup, String punchline, Integer id) {
        this.type = type;
        this.setup = setup;
        this.punchline = punchline;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "," + type + "," + setup + "," + punchline + ",";
    }
    
}
