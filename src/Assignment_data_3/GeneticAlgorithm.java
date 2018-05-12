/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment_data_3;

import geneticalgorithm.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author maxnethercott
 */
public class GeneticAlgorithm {

    public static double mutation_rate = 0.008;
    public static double crossover_rate = 0.8;
    public static int numR = 10;
    public static int numberRulesDataFile = 2000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Rule rule = new Rule();
        //read the file into the dataset. 
        Data dataSet[] = readFile();

        //create instance of random
        Random rand = new Random();
        //  System.out.println(rule.getConLength() +1);

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
        int counter = 0;
        //create population of genes and populate with floating point and integer output
        for (int i = 0; i < ind.popSize; i++) {

            for (int j = 0; j < ind.geneSize; j++) {
                counter++;
                if (counter % 13 == 0) {

                    population[i].gene[j] = rand.nextInt(2);
                } else {
                    population[i].gene[j] = rand.nextFloat();
                }

            }

            population[i].fitness = 0;

        }

        // printPopulation(population);
        //Evaluate each individual
        //fitness function
        // evaluateFitness(dataSet,population);
        fitnessFunction(population, dataSet);

        int genCounter = 0;

        //loop for 5000 generations whilst we havent found the solution. 
        while (genCounter < 1000 && !solutionFound(population)) {

            //  printPopulation(population);
            //Selection using the fittest parents.
            Selection(population, offspring);
            //rouletteWheelSelection(population,offspring);

            //add best offspring back into the population
            AddOffSpring(population, offspring);

            //evaluate the population using fitness function
            fitnessFunction(population, dataSet);

            //crossover to allow variation in population
            // crossover(population);
            //evaluate population after crossover
            fitnessFunction(population, dataSet);

            //mutate population using mutation rate
            mutation(population);

            //evaluate population- counting 1's
            fitnessFunction(population, dataSet);

            //printPopulation(population);
            //Print out the total fitness after mutation and crossover 
            System.out.println("The total fitness after cross over and mutation is: " + getTotalFitness(population, ind.popSize));
            System.out.println("The MEAN of the final population is: " + getMeanFitness(population, ind.popSize));

            //increment counter 
            genCounter++;
            System.out.println("**generation number** " + genCounter);
            System.out.println("highest fitness " + getFittest(population).fitness);

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
            if (population[i].fitness == 2000) {
                System.out.println("Individual has matched with a rule base of " + numR + " rules");
                System.out.println(Arrays.toString(population[i].gene));;

                // System.out.println(population[i].fitness);
                //System.out.println(population[i].gene);
                return true;
            }
        }
        return false;
    }

    public static void Selection(Individual[] population, Individual[] offspring) {
        Random rand = new Random();
        Individual ind = new Individual();
        //slection of fittest parents
        for (int i = 0; i < population.length; i++) {

            int parent1 = rand.nextInt(ind.popSize); //randomly choose parent
            int parent2 = rand.nextInt(ind.popSize); //randomly choose parent

            offspring[i] = new Individual();

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

    public static void fitnessFunction(Individual[] population, Data dataset[]) {
        Individual ind = new Individual();

        //evaluate each individual by counting the number of 1s after crossover and mutation
        for (int i = 0; i < ind.popSize; i++) {
            population[i].fitness = 0;
            population[i] = fitnessFunctionSingle(population[i], dataset);
        }

    }

    public static boolean matchedRule(float[] r, float[] d) {
        int k = 0;
        int count = 0;

        for (int i = 0; k != d.length; i++) {
            float firstVar = r[i];
            float secondVar = r[++i];
            // if (firstVar < secondVar) {
            if ((d[k] >= firstVar && d[k] <= secondVar)) {

                count++;
            }
            if ((d[k] <= firstVar && d[k] >= secondVar)) {
                count++;
            }

            if (firstVar == 2 && d[k] <= secondVar || secondVar == 2 && d[k] >= firstVar) {
                count++;
            }

            if (count == 6) {
                return true;
            }
            //}
            k++;
        }
        return false;
    }

    public static Individual fitnessFunctionSingle(Individual solution, Data dataSet[]) {
        int k = 0;

        //initialize rulebase
        Rule rulebase[] = new Rule[numR];

        //Create the rules
        for (int i = 0; i < numR; i++) {
            rulebase[i] = new Rule();
            for (int j = 0; j < rulebase[i].conLength; j++) {
                rulebase[i].cond[j] = solution.gene[k++];
            }
            rulebase[i].output = (int) solution.gene[k++];

        }

        //uisng the rules in the data set
        //If the data matches then increment the fitness count. 
        for (int i = 0; i < dataSet.length; i++) {
            for (int j = 0; j < numR; j++) {
                if (matchedRule(rulebase[j].cond, dataSet[i].variables)) {
                    if (dataSet[i].output == rulebase[j].output) {
                        // matched++;
                        solution.fitness++;
                        //System.out.println(matched + "CONDITIONS MATCHED");
                    }
                    break;
                }
            }
        }

        return solution;
    }

    public static void crossover(Individual[] population) {
        Individual ind = new Individual();
        Random rand = new Random();
        //crossover

        for (int i = 0; i < ind.popSize; i = i + 2) {
            if (crossover_rate * 100 > rand.nextInt(101)) {
                int splitPoint = rand.nextInt(ind.geneSize + 1);
                for (int j = splitPoint; j < ind.geneSize; j++) {
                    float temp = population[i].gene[j];
                    population[i].gene[j] = population[i + 1].gene[j];
                    population[i + 1].gene[j] = temp;

                }
            }
        }

    }

    public static void mutation(Individual[] population) {
        Individual ind = new Individual();
        Rule rule = new Rule();
        Random rand = new Random();
        //mutation

        for (int i = 0; i != ind.popSize; ++i) {
            for (int j = 0; j != population[i].geneSize; ++j) {
                if (j % (rule.conLength + 1) != 12) {
                    if (mutation_rate * 100 > rand.nextInt(101)) {
                        // population[i].gene[j] ^= 1;

                        float mutateNum = rand.nextFloat();
                        if ((population[i].gene[j] + mutateNum < 1)) {
                            population[i].gene[j] = population[i].gene[j] + mutateNum;
                            break;
                        } else if (population[i].gene[j] - mutateNum > 0) {
                            population[i].gene[j] = population[i].gene[j] - mutateNum;
                            break;
                        } else {
                            population[i].gene[j] = 2;
                            break;
                        }

                    }
                }

                if (rand.nextFloat() < mutation_rate) {
                    if (population[i].gene[j] == 1) {
                        population[i].gene[j] = 0;
                    } else if (population[i].gene[j] == 0) {
                        population[i].gene[j] = 1;
                    }
                }

            }
        }
    }

    public static void printPopulation(Individual[] population) {
        Individual ind = new Individual();
        //print out initial population and fitness. 
        for (int i = 0; i < ind.popSize; i++) {
            System.out.println("Initial population and fitness is: " + Arrays.toString(population[i].gene) + "fitness= " + population[i].fitness);
            System.out.println("Fitness of population: " + population[i].fitness);
        }

    }

    public static Data[] readFile() {

        Data DataSet[] = new Data[numberRulesDataFile];
        Scanner scan = new Scanner(GeneticAlgorithm.class.getResourceAsStream("dataSet3.txt"));
        int i = 0;
        while (scan.hasNextLine()) {
            DataSet[i] = new Data();
            String parts[] = scan.nextLine().split(" ");
            for (int j = 0; j < DataSet[i].vars; j++) {
                DataSet[i].variables[j] = Float.parseFloat(parts[j]);

            }
            DataSet[i].output = Integer.parseInt(parts[parts.length - 1]);
            //  System.out.println(Arrays.toString(DataSet[i].variables) + "Output: " + DataSet[i].output);
            i++;
        }
        return DataSet;
    }

    public static Individual getFittest(Individual population[]) {
        Individual ind = new Individual();

        for (int i = 0; i < ind.popSize; i++) {
            if (population[i].fitness > ind.fitness) {
                ind = population[i];
            }
        }

        return ind;
    }

    public static void rouletteWheelSelection(Individual[] population, Individual[] offspring) {
        Random rand = new Random();

        //find total fitness
        int totalFitness = 0;
        for (Individual individual : population) {
            totalFitness += individual.getFitness();
        }

        for (int i = 0; i < population.length; i++) {

            //roll the roulette wheel
            int stopped = rand.nextInt(totalFitness);

            int j = 0;
            for (j = 0; j < population.length; j++) {
                stopped = stopped - population[j].getFitness();
                if (stopped <= 0) {
                    break;
                }
            }

            offspring[i] = population[j];

        }
    }

}
