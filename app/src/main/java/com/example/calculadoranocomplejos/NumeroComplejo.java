package com.example.calculadoranocomplejos;

public class NumeroComplejo {
    private float parteReal;
    private float parteImaginario;

    public NumeroComplejo (){

    }

    public NumeroComplejo(float parteReal, float parteImaginario) {
        this.parteReal = parteReal;
        this.parteImaginario = parteImaginario;
    }

    public float getParteReal() {
        return parteReal;
    }

    public void setParteReal(float parteReal) {
        this.parteReal = parteReal;
    }

    public float getParteImaginario() {
        return parteImaginario;
    }

    public void setParteImaginario(float parteImaginario) {
        this.parteImaginario = parteImaginario;
    }

    @Override
    public String toString() {
        return  ""+ parteReal + "+" + parteImaginario + 'i';
    }
}
