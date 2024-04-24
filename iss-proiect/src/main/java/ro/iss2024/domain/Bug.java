package ro.iss2024.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Bug{

    private String name;

    private String description;

    private StatusBug statusBug;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bug(String name, String description, StatusBug statusBug){
        this.name = name;
        this.description = description;
        this.statusBug = statusBug;
    }

    public Bug(){

    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }


    public void setStatusBug(StatusBug statusBug){
        this.statusBug = statusBug;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public StatusBug getStatusBug(){
        return statusBug;
    }

}
