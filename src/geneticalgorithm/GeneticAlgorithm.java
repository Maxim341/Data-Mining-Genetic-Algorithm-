/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author maxnethercott
 */
public class GeneticAlgorithm {

    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {

        //create instance of random
        Random rand = new Random();

        //create instance of class individual
        Individual ind = new Individual();
        //create array for offspring
        Individual offspring[] = new Individual[ind.popSize];
        //create array for population
        Individual population[] = new Individual[ind.popSize];

        //create new population
        population = new Individual[ind.popSize];

        for (int i = 0; i < ind.popSize; i++) {
            population[i] = new Individual();
        }

        //create population of genes and populate with binary string of 1 and 0's
        for (int i = 0; i < ind.popSize; i++) {

            //System.out.print(" gene " + i + "=");
            for (int j = 0; j < ind.geneSize; j++) {

                population[i].gene[j] = rand.nextInt(2);

            }

            population[i].fitness = 0;

        }

        //Evaluate each individual
        //fitness function
        for (int i = 0; i < ind.popSize; i++) {
            for (int j = 0; j < ind.geneSize; j++) {
                if (population[i].gene[j] == 1) {
                    population[i].fitness++;

                }
            }
        }

        int genCounter = 0;

        while (!solutionFound(population)) {

            //print out initial population
            printPopulation(population);
            //Selection using the fittest parents.
            Selection(population, offspring);
            //add best offspring back into the population
            AddOffSpring(population, offspring);
            //evaluate the population using fitness function
            fitnessFunction(population);
            //crossover to allow variation in population
            crossover(population);
            //evaluate population after crossover
            fitnessFunction(population);
            //mutate population using mutation rate
            mutation(population);
            //evaluate population- counting 1's
            fitnessFunction(population);

            printPopulation(population);

            //Print out the total fitness after mutation and crossover 
            System.out.println("The total fitness after cross over and mutation is: " + getTotalFitness(population, ind.popSize));
            System.out.println("The mean of the final population is: "+getMeanFitness(population,ind.popSize));

            //increment counter 
            genCounter++;
            System.out.println("generation number " + genCounter);

        }

    }

    public static int getTotalFitness(Individual pop[], int p) {
        int totalFitness = 0;
        for (int i = 0; i < p; i++) {
            totalFitness = totalFitness + pop[i].getFitness();
        }
        return totalFitness;
    }

    public static double getMeanFitness(Individual pop[], int p) {
        double meanFitness = getTotalFitness(pop, p) / (double) p;
        return meanFitness;
    }

    public static boolean solutionFound(Individual population[]) {
        for (int i = 0; i < population.length; i++) {
            if (population[i].fitness == 50) {
                System.out.println("Solution found");
                return true;
            }
        }
        return false;
    }

    public static void Selection(Individual[] population, Individual[] offspring) {
        Random rand = new Random();
        //slection of fittest parents
        for (int i = 0; i < population.length; i++) {

            int parent1 = rand.nextInt(50); //randomly choose parent
            int parent2 = rand.nextInt(50); //randomly choose parent

            if (population[parent1].fitness >= population[parent2].fitness) {
                offspring[i] = population[parent1];
            } else {
                offspring[i] = population[parent2];
            }

        }

    }

    public static void AddOffSpring(Individual[] population, Individual[] offspring) {
        //put offspring back into initial population
        Individual ind = new Individual();
        for (int i = 0; i < population.length; i++) {
            population[i] = new Individual();
            for (int j = 0; j < ind.geneSize; j++) {
                population[i].gene[j] = offspring[i].gene[j];
            }
            population[i].fitness = offspring[i].fitness;

        }

    }

    public static void fitnessFunction(Individual[] population) {
        Individual ind = new Individual();
        //evaluate each individual by counting the number of 1s after crossover and mutation
        for (int i = 0; i < ind.popSize; i++) {
            population[i].fitness = 0;
            population[i] = fitnessFunctionSingle(population[i]);
        }

    }
    
    public static Individual fitnessFunctionSingle(Individual ind) {
        for (int j = 0; j < ind.geneSize; j++) {
            if (ind.gene[j] == 1) {
                ind.fitness++;
                //System.out.println(offspring[i].fitness);
            }
        }
        return ind;
    }

    public static void crossover(Individual[] population) {
        Individual ind = new Individual();
        Random rand = new Random();
        //crossover
        double crossover_rate = 0.7;
        for (int i = 0; i < ind.popSize; i = i + 2) {
            if (crossover_rate * 100 > rand.nextInt(101)) {
                int splitPoint = rand.nextInt(ind.geneSize+1);
                for (int j = splitPoint; j < ind.geneSize; j++) {
                    int temp = population[i].gene[j];
                    population[i].gene[j] = population[i + 1].gene[j];
                    population[i + 1].gene[j] = temp;

                }
            }
        }

    }

    public static void mutation(Individual[] population) {
        Individual ind = new Individual();
        Random rand = new Random();
        //mutation
        double mutation_rate = 0.002;
        for (int i = 0; i != ind.popSize; ++i) {
            for (int j = 0; j != ind.geneSize; ++j) {
                if (mutation_rate * 100 > rand.nextInt(101)) {
                    population[i].gene[j] ^= 1;
                }
            }
        }

    }

    public static void printPopulation(Individual[] population) {
        Individual ind = new Individual();
        //print out initial population and fitness. 
        for (int i = 0; i < ind.popSize; i++) {
            System.out.println("Initial population and fitness is: " + Arrays.toString(population[i].gene) + "fitness= " + population[i].fitness);
        }

    }

}
