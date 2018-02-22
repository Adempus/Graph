public class City {
    private String name;
    private String capitol;
    private String country;
    private long population;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return this.name;
    }

    public String getCapitol() {
        return this.capitol;
    }

    public void setCapitol(String capitol) {
        this.capitol = capitol;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = this.country == null ? country : this.country;
    }

    public long getPopulation() {
        return this.population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}
