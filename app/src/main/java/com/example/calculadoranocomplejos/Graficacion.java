package com.example.calculadoranocomplejos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.juang.jplot.PlotPlanitoXY;

import java.util.ArrayList;

public class Graficacion extends AppCompatActivity {

    private PlotPlanitoXY plot1, plot2, plot3;
    private LinearLayout pantalla1, pantalla2, pantalla3;
    Context context;

    float [] Xsuma, Ysuma, Xresta, Yresta, Xmulti, Ymulti, Xdivi, Ydivi,
            Xpoten, Ypoten, Xexp, Yexp, Xsen, Ysen, Xcos, Ycos, Xtan, Ytan, Xraices, Yraices;

    MainActivity resultadosCalcu = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graficas);
        context = this;
        pantalla1 = (LinearLayout) (findViewById(R.id.pantalla1));
        pantalla2 = (LinearLayout) (findViewById(R.id.pantalla2));
        pantalla3 = (LinearLayout) (findViewById(R.id.pantalla3));

        Xsuma = new float[1];
        Ysuma = new float[1];
        Xsuma[0] = MainActivity.resultadoSuma.getParteReal(); Ysuma[0] =MainActivity.resultadoSuma.getParteImaginario();

        Xresta = new float[1];
        Yresta = new float[1];
        Xresta[0] = MainActivity.resultadoResta.getParteReal(); Yresta[0] = MainActivity.resultadoResta.getParteImaginario();

        Xmulti = new float[1];
        Ymulti = new float[1];
        Xmulti[0] = MainActivity.resultadoMulti.getParteReal(); Ymulti[0] = MainActivity.resultadoMulti.getParteImaginario();

        Xdivi = new float[1];
        Ydivi = new float[1];
        Xdivi[0] = MainActivity.resultadoDivi.getParteReal(); Ydivi[0] = MainActivity.resultadoDivi.getParteImaginario();

        Xpoten = new float[1];
        Ypoten = new float[1];
        Xpoten[0] = MainActivity.resultadoPotencia.getParteReal(); Ypoten[0] = MainActivity.resultadoPotencia.getParteImaginario();

        Xexp = new float[1];
        Yexp = new float[1];
        Xexp[0] = MainActivity.resultadoExpon.getParteReal(); Yexp[0] = MainActivity.resultadoExpon.getParteImaginario();

        Xsen = new float[1];
        Ysen = new float[1];
        Xsen[0] = MainActivity.resultadoSeno.getParteReal(); Ysen[0] = MainActivity.resultadoSeno.getParteImaginario();

        Xcos = new float[1];
        Ycos = new float[1];
        Xcos[0] = MainActivity.resultadoCoseno.getParteReal(); Ycos[0] = MainActivity.resultadoCoseno.getParteImaginario();

        Xtan = new float[1];
        Ytan = new float[1];
        Xtan[0] = MainActivity.resultadoTangenteZ1Z2.getParteReal(); Ytan[0] = MainActivity.resultadoTangenteZ1Z2.getParteImaginario();

        Xraices = new float[MainActivity.raices.size()];
        Yraices = new float[MainActivity.raices.size()];
        for (int i = 0; i < MainActivity.raices.size() ; i++) {
            Xraices[i] = MainActivity.raices.get(i).getParteReal();
            Yraices[i] = MainActivity.raices.get(i).getParteImaginario();
        }

        plot1 = new PlotPlanitoXY(context,"SUMA, RESTA, MULTI, DIV, POTEN","reales","imaginarios");
        plot2 = new PlotPlanitoXY(context,"EXP, SEN, COS, TAN Z1+Z2","reales","imaginarios");
        plot3 = new PlotPlanitoXY(context,"RAICES","reales","imaginarios");

        plot1.SetSerie1(Xsuma,Ysuma,"Suma",10,true);// el 5 es el tamaño de punto "true" es para unir los puntos
        plot1.SetSerie2(Xresta, Yresta, "Resta", 10, true, 1);
        plot1.SetSerie3(Xmulti, Ymulti, "Multiplicacion", 10, true, 1);
        plot1.SetSerie4(Xdivi, Ydivi, "Division", 10, true, 1);
        plot1.SetSerie5(Xpoten, Ypoten, "Potencia", 10, true, 1);

        plot2.SetSerie1(Xexp, Yexp, "Exponencial", 10, true);
        plot2.SetSerie2(Xsen, Ysen, "Seno z1", 10, true, 1);
        plot2.SetSerie3(Xcos, Ycos, "Coseno z1", 10, true, 1);
        plot2.SetSerie4(Xtan, Ytan, "tanente z1+z2", 10, true, 1);

        plot3.SetSerie1(Xraices, Yraices, "Raices", 10, true);

        plot1.SetColorSerie1(2, 103, 193);
        plot1.SetColorSerie2(255, 227, 227);
        plot1.SetColorSerie3(139, 232, 203);
        plot1.SetColorSerie4(249, 110, 70);
        plot1.SetColorSerie5(249, 200, 70);

        plot2.SetColorSerie1(58, 190, 255);
        plot2.SetColorSerie2(204, 41, 54);
        plot2.SetColorSerie3(255, 159, 28);
        plot2.SetColorSerie4(63, 125, 32);

        plot3.SetColorSerie1(181, 255, 225);

        plot1.SetHD(true);
        plot1.SetTouch(true);
        plot2.SetHD(true);
        plot2.SetTouch(true);
        plot3.SetHD(true);
        plot3.SetTouch(true);

        pantalla1.addView(plot1);
        pantalla2.addView(plot2);
        pantalla3.addView(plot3);
    }

}
