package POJO;

import java.util.Objects;

public class Country {
    private int id;
    private String countryName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Country(int id, String countryName) {
        this.id = id;
        this.countryName = countryName;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Country country = (Country) obj;
        return id == country.id && Objects.equals(countryName, country.countryName);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
