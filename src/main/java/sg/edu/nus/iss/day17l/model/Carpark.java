package sg.edu.nus.iss.day17l.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public class Carpark {

    @PositiveOrZero(message = "Please enter a positive ID.")
    private Integer id;

    @NotBlank(message = "Address cannot be blank.") // cannot be null, blank or just white spaces
    private String address;

    @NotBlank(message = "Category cannot be blank.")
    private String category;

    @NotBlank(message = "Weekday rate 1 cannot be blank.")
    private String wdRate1;

    @NotBlank(message = "Weekday rate 2 cannot be blank.")
    private String wdRate2;

    @NotBlank(message = "Saturday rate cannot be blank.")
    private String satRate;

    @NotBlank(message = "Sunday public holiday rate cannot be blank.")
    private String sundayPhRate;
    
    public Carpark() {
    }

    public Carpark(Integer id, String address, String category, String wdRate1, String wdRate2, String satRate,
            String sundayPhRate) {
        this.id = id;
        this.address = address;
        this.category = category;
        this.wdRate1 = wdRate1;
        this.wdRate2 = wdRate2;
        this.satRate = satRate;
        this.sundayPhRate = sundayPhRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWdRate1() {
        return wdRate1;
    }

    public void setWdRate1(String wdRate1) {
        this.wdRate1 = wdRate1;
    }

    public String getWdRate2() {
        return wdRate2;
    }

    public void setWdRate2(String wdRate2) {
        this.wdRate2 = wdRate2;
    }

    public String getSatRate() {
        return satRate;
    }

    public void setSatRate(String satRate) {
        this.satRate = satRate;
    }

    public String getSundayPhRate() {
        return sundayPhRate;
    }

    public void setSundayPhRate(String sundayPhRate) {
        this.sundayPhRate = sundayPhRate;
    }

    @Override
    public String toString() {
        return id + "," + address + "," + category + "," + wdRate1 + "," + wdRate2 + "," + satRate + "," + sundayPhRate;
    }
    
    
}
