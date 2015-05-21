import java.util.ArrayList;
import java.util.Arrays;
public class Mutator {

    protected City[] cities;
    protected int cityCount;
    protected int mutateProbability = 50;
    protected int invertProbability = 60;


    public Mutator(City[] cities) {
        this.cities = cities;
        this.cityCount = cities.length;
    }

    public Chromosome[] process(Chromosome[] chromosomes){
        for (int i = 0; i < chromosomes.length; i++){

            if (TSP.selectWithProb(mutateProbability)){
                randomMutate(chromosomes[i]);

            }
            if (TSP.selectWithProb(invertProbability)){
                invert(chromosomes[i]);

            }
            
        }
        return chromosomes;
    }

    private void invert(Chromosome c){
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
    }

    private void randomMutate(Chromosome c){
        int rand1;
        int rand2;
        int temp;
        int generationRatio = (TSP.maxGeneration - TSP.generation) / 10;
        int numMutations =  generationRatio > 1 ? generationRatio : 1;

        for (int i = 0; i <  numMutations; i++){
            rand1 = TSP.randomInRange(c.cityList.length);
            rand2 = TSP.randomInRange(c.cityList.length);
            temp = c.cityList[rand1];
            c.cityList[rand1] = c.cityList[rand2];
            c.cityList[rand2] = temp;

        }
        c.calculateCost();

    }
}
