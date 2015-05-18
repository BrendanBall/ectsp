import java.util.ArrayList;

public class Selector {

    protected City[] cities;
    protected int cityCount;

    public Selector(City[] cities) {
        this.cities = cities;
        this.cityCount = cities.length;

    }

    public Chromosome[] select(Chromosome[] chromosomes){
        return chromosomes;
    }
}
