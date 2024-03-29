import java.util.ArrayList;
import java.util.Random;

class Chromosome implements Comparable<Chromosome> {

    /**
     * The list of cities, which are the genes of this chromosome.
     */
    protected int[] cityList;
    protected City[] cities;
    /**
     * The cost of following the cityList order of this chromosome.
     */
    protected double cost;

    /**
     * @param cities The order that this chromosome would visit the cities.
     */
    Chromosome(City[] cities) {
        this.cities = cities;
        Random generator = new Random();
        cityList = new int[cities.length];
        //cities are visited based on the order of an integer representation [o,n] of each of the n cities.
        for (int x = 0; x < cities.length; x++) {
            cityList[x] = x;

        }

        //shuffle the order so we have a random initial order
        for (int y = 0; y < cityList.length; y++) {
            int temp = cityList[y];
            int randomNum = generator.nextInt(cityList.length);
            cityList[y] = cityList[randomNum];
            cityList[randomNum] = temp;
        }

        calculateCost();
    }

    Chromosome(Chromosome s){
        this.cities = s.cities;
        this.cityList = s.cityList.clone();
        this.cost = s.getCost();
    }

    public void randomMutate(){
        Random generator = new Random();
        int rand1;
        int rand2;
        int temp;
        int numMutations = 2;

        for (int i = 0; i <  numMutations; i++){
            rand1 = generator.nextInt(cityList.length);
            rand2 = generator.nextInt(cityList.length);
            temp = cityList[rand1];
            cityList[rand1] = cityList[rand2];
            cityList[rand2] = temp;

        }
        calculateCost();

    }

    /**
     * Calculate the cost of the specified list of cities.
     *
     *
     */
    void calculateCost() {
        cost = 0;
        for (int i = 0; i < cityList.length - 1; i++) {
            double dist = cities[cityList[i]].proximity(cities[cityList[i + 1]]);
            cost += dist;
        }
    }

    /**
     * Get the cost for this chromosome. This is the amount of distance that
     * must be traveled.
     */
    double getCost() {
        return cost;
    }

    /**
     * @param i The city you want.
     * @return The ith city.
     */
    int getCity(int i) {
        return cityList[i];
    }

    /**
     * Set the order of cities that this chromosome would visit.
     *
     * @param list A list of cities.
     */
    void setCities(int[] list) {
        for (int i = 0; i < cityList.length; i++) {
            cityList[i] = list[i];
        }
    }

    /**
     * Set the index'th city in the city list.
     *
     * @param index The city index to change
     * @param value The city number to place into the index.
     */
    void setCity(int index, int value) {
        cityList[index] = value;
    }

    /**
     * Sort the chromosomes by their cost.
     *
     * @param chromosomes An array of chromosomes to sort.
     * @param num         How much of the chromosome list to sort.
     */
    public static void sortChromosomes(Chromosome chromosomes[], int num) {
        Chromosome ctemp;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < num - 1; i++) {
                if (chromosomes[i].getCost() > chromosomes[i + 1].getCost()) {
                    ctemp = chromosomes[i];
                    chromosomes[i] = chromosomes[i + 1];
                    chromosomes[i + 1] = ctemp;
                    swapped = true;
                }
            }
        }
    }

    @Override
    public int compareTo(Chromosome c) {
        return ((Double)this.getCost()).compareTo(c.getCost());

    }
}
