/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment_data_3;

/**
 *
 * @author maxnethercott
 */
public class Rule {
    
    int conLength = 12;
    
    
    float cond[];
    int output;

    public Rule() {
        cond = new float[conLength]; 
        
    }

    public int getConLength() {
        return conLength;
    }

    public void setConLength(int conLength) {
        this.conLength = conLength;
    }

    public float[] getCond() {
        return cond;
    }

    public void setCond(float[] cond) {
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
