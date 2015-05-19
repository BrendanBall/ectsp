import java.util.ArrayList;
import java.util.Arrays;
public class Mutator {

    protected City[] cities;
    protected int cityCount;
    protected int mutateProbability = 80;

    public Mutator(City[] cities) {
        this.cities = cities;
        this.cityCount = cities.length;
    }

    public Chromosome[] process(Chromosome[] chromosomes){
        for (int i = 0; i < chromosomes.length; i++){
            if (TSP.selectWithProb(mutateProbability)){
                chromosomes[i].randomMutate();

            }
        }
        return chromosomes;
    }
}
