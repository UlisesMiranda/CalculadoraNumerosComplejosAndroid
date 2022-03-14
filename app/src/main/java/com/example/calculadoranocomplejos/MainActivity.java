package com.example.calculadoranocomplejos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    EditText z1Real, z1Imaginario, z2Real, z2Imaginario, gradoExponente, gradoRaiz, txtResultados;
    Button calcular, graficar;

    float f_z1Real, f_z1Imaginario, f_z2Real, f_z2Imaginario;
    int exponentePotencia, exponenteRaiz;
    String str_resultados;

    static NumeroComplejo resultadoSuma, resultadoResta, resultadoMulti, resultadoDivi,
            resultadoPotencia, resultadoExpon, resultadoSeno, resultadoCoseno, resultadoTangenteZ1Z2;
    static double argumento, modulo;
    static ArrayList<NumeroComplejo> raices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        z1Real = (EditText)findViewById(R.id.z1Real);
        z1Imaginario = (EditText)findViewById(R.id.z1Imaginario);
        z2Real = (EditText) findViewById(R.id.z2Real);
        z2Imaginario = (EditText)findViewById(R.id.z2Imaginario);
        txtResultados = (EditText)findViewById(R.id.txtResultados);

        gradoExponente = (EditText)findViewById(R.id.gradoExponente);
        gradoRaiz = (EditText)findViewById(R.id.gradoRaiz);

        calcular = (Button)findViewById(R.id.calcular);
        graficar = (Button)findViewById(R.id.graficar);

        calcular.setOnClickListener(this);
        graficar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v instanceof Button) {

            if(v.getId() == calcular.getId()) {
                f_z1Real = Float.parseFloat(z1Real.getText().toString());
                f_z1Imaginario = Float.parseFloat(z1Imaginario.getText().toString());

                f_z2Real = Float.parseFloat(z2Real.getText().toString());
                f_z2Imaginario = Float.parseFloat(z2Imaginario.getText().toString());

                exponentePotencia = Integer.parseInt(gradoExponente.getText().toString());
                exponenteRaiz = Integer.parseInt(gradoRaiz.getText().toString());

                NumeroComplejo z1 = new NumeroComplejo(f_z1Real, f_z1Imaginario);
                NumeroComplejo z2 = new NumeroComplejo(f_z2Real, f_z2Imaginario);

                OperacionesNumerosComplejos operacion = new OperacionesNumerosComplejos();

                resultadoSuma = operacion.SumarComplejos(z1, z2);
                resultadoResta = operacion.RestarComplejos(z1, z2);
                resultadoMulti = operacion.MultiplicarComplejos(z1, z2);
                resultadoDivi = operacion.DivisionComplejos(z1, z2);
                resultadoPotencia = operacion.PotenciasComplejos(z1, exponentePotencia);
                modulo = operacion.sacarModulo(z1);
                argumento = operacion.sacarArgumento(z1);
                raices = operacion.RaicesComplejas(z1, exponenteRaiz);
                resultadoExpon = operacion.exponencial(z1);
                resultadoSeno = operacion.seno(z1);
                resultadoCoseno = operacion.coseno(z1);
                resultadoTangenteZ1Z2 = operacion.tangenteZ1Z2(z1, z2);

                str_resultados = ImprimirResultados(resultadoSuma, resultadoResta, resultadoMulti, resultadoDivi, resultadoPotencia,
                        modulo, argumento, raices, resultadoExpon, resultadoSeno, resultadoCoseno, resultadoTangenteZ1Z2);

                txtResultados.setText(str_resultados);

            }
            else if (v.getId() == graficar.getId()) {
                Intent graficacion = new Intent(this, Graficacion.class);
                startActivity(graficacion);
            }
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public String ImprimirResultados(NumeroComplejo resSuma, NumeroComplejo resResta, NumeroComplejo resMulti, NumeroComplejo resDivi,
                                     NumeroComplejo resPotencia, double modulo, double argumento, ArrayList<NumeroComplejo> raices,
                                     NumeroComplejo resultadoExpon, NumeroComplejo resultadoSeno, NumeroComplejo resultadoCoseno,
                                     NumeroComplejo resultadoTangenteZ1Z2) {

        final StringBuilder sb = new StringBuilder("Resultados{\n");
        sb.append("\nSuma =").append(resSuma);
        sb.append("\nResta = ").append(resResta);
        sb.append("\nMultiplicacion = ").append(resMulti);
        sb.append("\nDivision = ").append(resDivi);
        sb.append("\nPotencia z1^n= ").append(resPotencia);
        sb.append("\nModulo |z1|= ").append(modulo);
        sb.append("\nArgumento arg(z)= ").append(argumento);
        sb.append("\nRaices z1^1/m: \n").append(raices).append("\n");
        sb.append("\nExponencial e^z1: ").append(resultadoExpon);
        sb.append("\nSeno(z1): ").append(resultadoSeno);
        sb.append("\nCoseno(z1): ").append(resultadoCoseno);
        sb.append("\nTan(z1 + z2): ").append(resultadoTangenteZ1Z2);
        sb.append("\n}");
        return sb.toString();
    }

    public NumeroComplejo getResultadoSuma() {
        return resultadoSuma;
    }

    public NumeroComplejo getResultadoResta() {
        return resultadoResta;
    }

    public NumeroComplejo getResultadoMulti() {
        return resultadoMulti;
    }

    public NumeroComplejo getResultadoDivi() {
        return resultadoDivi;
    }

    public NumeroComplejo getResultadoPotencia() {
        return resultadoPotencia;
    }

    public NumeroComplejo getResultadoExpon() {
        return resultadoExpon;
    }

    public NumeroComplejo getResultadoSeno() {
        return resultadoSeno;
    }

    public NumeroComplejo getResultadoCoseno() {
        return resultadoCoseno;
    }

    public NumeroComplejo getResultadoTangenteZ1Z2() {
        return resultadoTangenteZ1Z2;
    }

    public double getArgumento() {
        return argumento;
    }

    public double getModulo() {
        return modulo;
    }

    public ArrayList<NumeroComplejo> getRaices() {
        return raices;
    }

    public int getExponenteRaiz (){
        return exponenteRaiz;
    }
}
