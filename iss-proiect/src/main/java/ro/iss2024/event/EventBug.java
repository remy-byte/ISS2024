package ro.iss2024.event;

public class EventBug implements Event{
    private String name;
    private String description;

    public EventBug(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
