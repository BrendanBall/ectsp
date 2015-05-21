import java.util.ArrayList;

public class Selector {

    protected City[] cities;
    protected int cityCount;
    protected int popSize;


    public Selector(City[] cities, int popSize) {
        this.cities = cities;
        this.cityCount = cities.length;
        this.popSize = popSize;


    }

    public Chromosome[] select(Chromosome[] parents, Chromosome[] children){
        TSP.sortChromosomes(parents);
        TSP.sortChromosomes(children);
        Chromosome[] survivors = new Chromosome[popSize];
        int p = 0;
        int c = 0;
        for (int i = 0; i < survivors.length; i++){
            if (parents[p].getCost() < children[c].getCost()){
                survivors[i] = parents[p];
                p++;
            }
            else{
                survivors[i] = children[c];
                c++;
            }
        }


        return survivors;
    }

    public Chromosome[] duplicate(Chromosome[] parents){
        int duplicates = 4;
        Chromosome[] children = new Chromosome[parents.length * duplicates];
        for (int i = 0; i < parents.length; i++){
            for (int j=0; j < duplicates; j++){

            children[i*duplicates + j] = new Chromosome(parents[i]);

            }
        }
        TSP.shuffleChromosomes(children);
        return children;
    }


    public Chromosome[] tournament(Chromosome[] parents){
        TSP.shuffleChromosomes(parents);
        int groupsize = 70;
        int numWinners = parents.length / groupsize;
        Chromosome[] winners = new Chromosome[numWinners];

        for (int i = 0; i < winners.length; i++){
            winners[i] = new Chromosome(groupMin(parents, i * groupsize, i * groupsize + groupsize));
        }
        TSP.shuffleChromosomes(winners);

        return winners;
    }

    public Chromosome groupMin(Chromosome[] chromosomes, int start, int end){
        Chromosome minChromosome = chromosomes[0];
        for (int i = start + 1; i < end; i++ ){
            if (chromosomes[i].getCost() < minChromosome.getCost() ){
                minChromosome = chromosomes[i];
            }
        }
        return minChromosome;
    }

}
