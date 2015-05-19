import java.util.ArrayList;

public class Selector {

    protected City[] cities;
    protected int cityCount;

    public Selector(City[] cities) {
        this.cities = cities;
        this.cityCount = cities.length;

    }

    public Chromosome[] select(Chromosome[] parents, Chromosome[] children){
        TSP.sortChromosomes(parents);
        TSP.sortChromosomes(children);
        Chromosome[] survivors = new Chromosome[parents.length];
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
        Chromosome[] children = new Chromosome[parents.length * 2];
        for (int i = 0; i < parents.length; i++){
            children[i*2] = new Chromosome(parents[i]);
            children[i*2+1] = new Chromosome(parents[i]);


        }
        TSP.shuffleChromosomes(children);
        return children;
    }



}
