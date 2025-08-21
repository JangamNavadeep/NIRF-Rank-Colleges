package com.example.nirfrankclg;
public class College {
    private String name;
    private int nirfrank;
    private String domain;
    private String details;




    // Constructor to initialize the fields
    public College(String name, int nirfrank, String domain  ,String details) {
        this.name = name;
        this.nirfrank = nirfrank;
        this.domain = domain;

        this.details = details;

    }

    // Getter methods
    public String getName() {
        return name;
    }

    public int getnirfrank() {
        return nirfrank;
    }

    public String getDomain() {
        return domain;
    }
    public String getDetails() {
        return details;
    }


}
