package stagearea;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author trojan101
 */
public class userDetailsModel {

    private final StringProperty name;
    private final StringProperty age;
    private final StringProperty origin;
    private final StringProperty destination;
    private final StringProperty days;
    //private final StringProperty id;

    //Default constructor
    public userDetailsModel(String name, String age, String origin, String destination, String days) {
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleStringProperty(age);
        this.origin = new SimpleStringProperty(origin);
        this.destination = new SimpleStringProperty(destination);
        this.days = new SimpleStringProperty(days);
    }

    //Setters
    public void setName(String value) {
        name.set(value);
    }

    public void setAge(String value) {
        age.set(value);
    }

    public void setOrigin(String value) {
        origin.set(value);
    }

    public void setDestination(String value) {
        destination.set(value);
    }

    public void setDays(String value) {
        days.set(value);
    }

    //  Getters
    public String getName() {
        return name.get();
    }

    public String getAge() {
        return age.get();
    }

    public String getOrigin() {
        return origin.get();
    }

    public String getDestination() {
        return destination.get();
    }

    public String getDays() {
        return days.get();
    }

    // Set Properties VALUES
    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty ageProperty() {
        return age;
    }

    public StringProperty originProperty() {
        return origin;
    }

    public StringProperty destinationProperty() {
        return destination;
    }

    public StringProperty daysProperty() {
        return days;
    }

}
