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

public class Data {
    int vars = 5;
    
    int variables[];
    int output;

    
    public Data() {
        variables = new int[vars]; 
        
    }

    public int getVars() {
        return vars;
    }

    public void setVars(int vars) {
        this.vars = vars;
    }

    public int[] getVariables() {
        return variables;
    }

    public void setVariables(int[] variables) {
        this.variables = variables;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Data{" + "variables=" + variables + ", output=" + output + '}';
    }
    
    
    
    
    
}

