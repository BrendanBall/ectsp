import java.util.ArrayList;
import java.util.Arrays;
public class Mutator {

    protected City[] cities;
    protected int cityCount;
    protected int mutateProbability = 50;
    protected int invertProbability = 50;


    public Mutator(City[] cities) {
        this.cities = cities;
        this.cityCount = cities.length;
    }

    public Chromosome[] process(Chromosome[] chromosomes){
        for (int i = 0; i < chromosomes.length; i++){

            if (TSP.selectWithProb(mutateProbability)){
                chromosomes[i].randomMutate();

            }
            if (TSP.selectWithProb(invertProbability)){
                chromosomes[i] = invert(chromosomes[i]);

            }
        }
        return chromosomes;
    }

    private Chromosome invert(Chromosome c){
        int invertLength = TSP.randomInRange(3, cityCount/3);
        int start = TSP.randomInRange(1, cityCount - invertLength - 1);
        int end = start + invertLength;

        int temp;
        while (start < end){
            temp = c.cityList[start];
            c.cityList[start] = c.cityList[end];
            c.cityList[end] = temp;
            start++;
            end--;
        }
        c.calculateCost();
        return c;
    }
}
