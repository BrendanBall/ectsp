import java.util.HashSet;

public class Recombinator {
    protected City[] cities;
    protected int cityCount;
    protected int recombineProbability = 60;
    protected int popSize;

    public Recombinator(City[] cities, int popSize) {
        this.cities = cities;
        this.cityCount = cities.length;
        this.popSize = popSize;

    }

    public Chromosome[] process(Chromosome[] chromosomes){
        Chromosome[] children = new Chromosome[popSize * 4];
        int a;
        int b;
        for (int i = 0; i < children.length; i+=2){
            a = TSP.randomInRange(chromosomes.length);
            b = TSP.randomInRange(chromosomes.length);
            while (b != a){b = TSP.randomInRange(chromosomes.length);}

            Chromosome c1 = new Chromosome(chromosomes[a]);
            Chromosome c2 = new Chromosome(chromosomes[b]);

            crossover(c1, c2);
            children[i] = c1;
            children[i+1] = c2;

        }
        return children;

    }


    private Chromosome[] recombine(Chromosome[] chromosomes){
        TSP.shuffleChromosomes(chromosomes);
        for (int i = 0; i < chromosomes.length-1; i+=2){
            if (TSP.selectWithProb(recombineProbability)) {
                crossover(chromosomes[i], chromosomes[i + 1]);
                chromosomes[i].calculateCost();
                chromosomes[i + 1].calculateCost();
            }

        }
        return chromosomes;
    }

    private void crossover(Chromosome c1, Chromosome c2){
        int[] city1 = c1.cityList.clone();
        int[] city2 = c2.cityList.clone();

        HashSet<Integer> hashSet1 = new HashSet<Integer>(cityCount);
        HashSet<Integer> hashSet2 = new HashSet<Integer>(cityCount);

        int randomsplit = TSP.randomInRange(1, cityCount - 1);
        for (int i = 0; i < randomsplit; i++){
            hashSet1.add(city1[i]);
            hashSet2.add(city2[i]);

        }
        int c1index = randomsplit;
        int c2index = randomsplit;

        for (int i = randomsplit; i < cityCount; i++){
            if (!hashSet1.contains(city2[i])){
                c1.cityList[c1index] = city2[i];
                c1index++;
            }
            if (!hashSet2.contains(city1[i])){
                c2.cityList[c2index] = city1[i];
                c2index++;
            }
        }
        for (int i = randomsplit; i < cityCount; i++){
            if (hashSet2.contains(city1[i])){
                c1.cityList[c1index] = city1[i];
                c1index++;
            }
            if (hashSet1.contains(city2[i])){
                c2.cityList[c2index] = city2[i];
                c2index++;
            }
        }


    }
}
