import java.util.HashSet;

public class Recombinator {
    protected City[] cities;
    protected int cityCount;

    public Recombinator(City[] cities) {
        this.cities = cities;
        this.cityCount = cities.length;

    }

    public Chromosome[] process(Chromosome[] chromosomes){
        TSP.sortChromosomes(chromosomes);
        for (int i = 1; i < 11; i+=2){
            recombine(chromosomes[i], chromosomes[i+1]);
            chromosomes[i].calculateCost();
            chromosomes[i+1].calculateCost();

        }
        return chromosomes;
    }

    private void recombine(Chromosome c1, Chromosome c2){
        int[] city1 = c1.cityList.clone();
        int[] city2 = c2.cityList.clone();

        HashSet<Integer> hashSet1 = new HashSet<Integer>(cityCount);
        HashSet<Integer> hashSet2 = new HashSet<Integer>(cityCount);

        int randomsplit = cityCount / 2;
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
