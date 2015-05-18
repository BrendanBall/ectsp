import java.util.ArrayList;
import java.util.Arrays;
public class Mutator {

    protected City[] cities;
    protected int cityCount;

    public Mutator(City[] cities) {
        this.cities = cities;
        this.cityCount = cities.length;
    }

    public Chromosome[] process(Chromosome[] chromosomes){
        for (int i = 10; i < chromosomes.length; i++){
            chromosomes[i].randomMutate();
        }
        return chromosomes;
    }
}
