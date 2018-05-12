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
public class Data {
    int vars = 6;
    
    float variables[];
    int output;

    
    public Data() {
        variables = new float[vars]; 
        
    }

    public int getVars() {
        return vars;
    }

    public void setVars(int vars) {
        this.vars = vars;
    }

    public float[] getVariables() {
        return variables;
    }

    public void setVariables(float[] variables) {
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
