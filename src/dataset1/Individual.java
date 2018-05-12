/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataset1;

import geneticalgorithm.*;

/**
 *
 * @author maxnethercott
 */
public class Individual {
    
   public int popSize = 100;
   public int geneSize = 60;
    
    
    int gene[];
    int fitness;

    public int getPopSize() {
        return popSize;
    }

    public void setPopSize(int popSize) {
        this.popSize = popSize;
    }

    public int getGeneSize() {
        return geneSize;
    }

    public void setGeneSize(int geneSize) {
        this.geneSize = geneSize;
    }

    public int[] getGene() {
        return gene;
    }

    public void setGene(int[] gene) {
        this.gene = gene;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }


   
    
    
  
    public Individual() {
       gene = new int[geneSize];
     
       fitness=0;
    }

    
     
    
    
    @Override
    public String toString() {
        return "Individual{gene=" + gene.toString() + ", fitness=" + fitness + '}';
    }
    
    
    

}
