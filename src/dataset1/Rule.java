/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataset1;

/**
 *
 * @author maxnethercott
 */
public class Rule {
    
    int conLength = 5;
    
    
    int cond[];
    int output;

    public Rule() {
        cond = new int[conLength]; 
        
    }

    public int getConLength() {
        return conLength;
    }

    public void setConLength(int conLength) {
        this.conLength = conLength;
    }

    public int[] getCond() {
        return cond;
    }

    public void setCond(int[] cond) {
        this.cond = cond;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Rule{" + "cond=" + cond + ", output=" + output + '}';
    }
    
    
    
    
    
    
}
