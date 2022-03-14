package com.example.calculadoranocomplejos;

import java.util.ArrayList;

public class OperacionesNumerosComplejos {

    public NumeroComplejo SumarComplejos (NumeroComplejo complejo1, NumeroComplejo complejo2) {
        NumeroComplejo resultadoSuma = new NumeroComplejo();

        resultadoSuma.setParteReal(complejo1.getParteReal() + complejo2.getParteReal());
        resultadoSuma.setParteImaginario(complejo1.getParteImaginario() + complejo2.getParteImaginario());

        return resultadoSuma;
    }

    public NumeroComplejo RestarComplejos  (NumeroComplejo complejo1, NumeroComplejo complejo2) {
        NumeroComplejo resultadoResta = new NumeroComplejo();

        resultadoResta.setParteReal(complejo1.getParteReal() - complejo2.getParteReal());
        resultadoResta.setParteImaginario(complejo1.getParteImaginario() - complejo2.getParteImaginario());

        return  resultadoResta;
    }

    public NumeroComplejo MultiplicarComplejos  (NumeroComplejo complejo1, NumeroComplejo complejo2) {
        NumeroComplejo resultadoMulti = new NumeroComplejo();

        resultadoMulti.setParteReal( (complejo1.getParteReal() * complejo2.getParteReal()) - (complejo1.getParteImaginario() * complejo2.getParteImaginario()));
        resultadoMulti.setParteImaginario( (complejo1.getParteImaginario() * complejo2.getParteReal()) + (complejo1.getParteReal() * complejo2.getParteImaginario()));

        return resultadoMulti;
    }

    public NumeroComplejo DivisionComplejos (NumeroComplejo complejo1, NumeroComplejo complejo2) {
        NumeroComplejo resultadoDivision = new NumeroComplejo();

        NumeroComplejo conjugadoDenominador = new NumeroComplejo(complejo2.getParteReal(), -complejo2.getParteImaginario());

        NumeroComplejo multiNumerador, multiDenominador = new NumeroComplejo();

        //(Z1 * Z2') / (Z2 * Z2') = Z4 / Z5.real

        multiNumerador = MultiplicarComplejos(complejo1, conjugadoDenominador);
        multiDenominador = MultiplicarComplejos(complejo2, conjugadoDenominador);

        resultadoDivision.setParteReal( multiNumerador.getParteReal() / multiDenominador.getParteReal());
        resultadoDivision.setParteImaginario( multiNumerador.getParteImaginario() / multiDenominador.getParteReal());

        return resultadoDivision;
    }

    public NumeroComplejo PotenciasComplejos (NumeroComplejo complejo1, int exponente) {
        NumeroComplejo resultadoPotencia = new NumeroComplejo();

        int noMultiConsecutivas = exponente - 1;

        resultadoPotencia = complejo1;

        for (int i = 0; i < noMultiConsecutivas ; i++) {
            resultadoPotencia = MultiplicarComplejos(resultadoPotencia, complejo1);
        }

        return resultadoPotencia;
    }

    public ArrayList<NumeroComplejo> RaicesComplejas (NumeroComplejo complejo1, int raiz) {
        ArrayList<NumeroComplejo> raices = new ArrayList<>();

        double r = sacarModulo(complejo1);
        double arg = sacarArgumento(complejo1);

        //r^1/raiz
        double R = Math.pow( r, (1.0/raiz) );

        for (int i = 0; i < raiz ; i++) {
            NumeroComplejo resultadoRaiz = new NumeroComplejo();

            //angulo
            double theta = (arg + (360 * i)) / raiz;
            theta = Math.toRadians(theta);

            resultadoRaiz.setParteReal((float) ( R * Math.cos(theta)));
            resultadoRaiz.setParteImaginario((float) (R * Math.sin(theta)));

            raices.add(i, resultadoRaiz);
        }

        return raices;
    }

    public double sacarModulo (NumeroComplejo complejo) {
        double r;

        r = Math.sqrt(Math.pow(complejo.getParteReal(), 2.0) + Math.pow(complejo.getParteImaginario(), 2.0));

        return r;
    }

    public double sacarArgumento (NumeroComplejo complejo) {

        double arg, division;
        division = complejo.getParteImaginario() / complejo.getParteReal();
        arg = Math.atan(division);
        arg = Math.toDegrees(arg);

        if (complejo.getParteImaginario() > 0 && complejo.getParteReal() > 0)
            return arg;

        if (complejo.getParteImaginario() >= 0 && complejo.getParteReal() < 0)
            return 180 + arg;

        if (complejo.getParteImaginario() < 0 && complejo.getParteReal() < 0)
            return arg - 180;

        if (complejo.getParteImaginario() > 0 && complejo.getParteReal() == 0)
            return 90;

        if (complejo.getParteImaginario() < 0 && complejo.getParteReal() == 0)
            return -90;

        else
            return arg = 0;

    }

    public NumeroComplejo exponencial (NumeroComplejo complejo) {
        NumeroComplejo resultadoExponencial = new NumeroComplejo();

        double ex = Math.exp(complejo.getParteReal());
        double y = complejo.getParteImaginario();

        resultadoExponencial.setParteReal((float) (ex * Math.cos(y)));
        resultadoExponencial.setParteImaginario((float) (ex * Math.sin(y)));

        return resultadoExponencial;
    }

    public NumeroComplejo seno (NumeroComplejo complejo) {
        NumeroComplejo resultadoSeno= new NumeroComplejo();

        NumeroComplejo i = new NumeroComplejo(0 , 1 );
        NumeroComplejo iNega = new NumeroComplejo(0, -1);
        NumeroComplejo dos = new NumeroComplejo(2,0);

        NumeroComplejo iz = MultiplicarComplejos(i, complejo);
        NumeroComplejo izNeg = MultiplicarComplejos(iNega, complejo);

        NumeroComplejo eiz = exponencial(iz);
        NumeroComplejo eizNeg = exponencial(izNeg);

        NumeroComplejo numerador = RestarComplejos(eiz, eizNeg);
        NumeroComplejo denominador = MultiplicarComplejos(dos, i);

        resultadoSeno = DivisionComplejos(numerador, denominador);

        return resultadoSeno;
    }

    public NumeroComplejo coseno (NumeroComplejo complejo) {
        NumeroComplejo resultadoCoseno = new NumeroComplejo();

        NumeroComplejo i = new NumeroComplejo(0 , 1 );
        NumeroComplejo iNega = new NumeroComplejo(0, -1);
        NumeroComplejo dos = new NumeroComplejo(2,0);

        NumeroComplejo iz = MultiplicarComplejos(i, complejo);
        NumeroComplejo izNeg = MultiplicarComplejos(iNega, complejo);

        NumeroComplejo eiz = exponencial(iz);
        NumeroComplejo eizNeg = exponencial(izNeg);

        NumeroComplejo numerador = SumarComplejos(eiz, eizNeg);
        NumeroComplejo denominador = dos;

        resultadoCoseno = DivisionComplejos(numerador, denominador);

        return  resultadoCoseno;
    }

    public NumeroComplejo tangenteZ1Z2 (NumeroComplejo complejo1, NumeroComplejo complejo2) {
        NumeroComplejo resultadoTangente = new NumeroComplejo();

        NumeroComplejo sumaZ1Z2 = SumarComplejos(complejo1, complejo2);

        resultadoTangente = DivisionComplejos(seno(sumaZ1Z2), coseno(sumaZ1Z2));

        return resultadoTangente;
    }
}
