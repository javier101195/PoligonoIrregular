package com.example.javier.poligono_irregular;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioButton Rsi, Rno;
    Button next, calc;
    EditText etx1,etx2,ety1,ety2;
    TextView lado;
    String v1, v2, v3 , v4, peri;
    Integer cont=0, au=1;
    ArrayList<Integer> x = new ArrayList<Integer>();
    ArrayList <Integer> y = new ArrayList<Integer>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Rsi=(RadioButton)findViewById(R.id.radioButtonSi);
        Rno=(RadioButton)findViewById(R.id.radioButtonNo);
        next=(Button)findViewById(R.id.buttonNext);
        calc=(Button)findViewById(R.id.buttonCalc);
        etx1=(EditText)findViewById(R.id.etx1);
        etx2=(EditText)findViewById(R.id.etx2);
        ety1=(EditText)findViewById(R.id.ety1);
        ety2=(EditText)findViewById(R.id.ety2);
        lado=(TextView) findViewById(R.id.lado);

        lado.setText(""+au);

        Rsi.setEnabled(false);
        Rno.setChecked(true);
        next.setEnabled(true);





        Rsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Rsi.isChecked()==true) {
                    //código
                    Rno.setChecked(false);
                    calc.setEnabled(true);
                    next.setEnabled(false);
                }

            }
        });

        Rno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Rno.isChecked()==true) {
                    // código
                    Rsi.setChecked(false);
                    next.setEnabled(true);
                    calc.setEnabled(false);

                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etx1.getText().toString().isEmpty()||etx2.getText().toString().isEmpty()||
                        ety1.getText().toString().isEmpty()||ety2.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "Llene todos los campos",
                            Toast.LENGTH_SHORT).show();
                } else if (au<2){
                    ade();
                    au+=1;
                    calcular();
                    borrar();
                    Rsi.setEnabled(false);
                    Rno.setChecked(true);
                    //au+=1;

                } else if(au>=2){
                    ade();
                    au+=1;
                    calcular();
                    borrar();
                    Rsi.setEnabled(true);
                    Rsi.setChecked(false);
                    Rno.setChecked(true);
                }


            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etx1.getText().toString().isEmpty()||etx2.getText().toString().isEmpty()||
                        ety1.getText().toString().isEmpty()||ety2.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,
                            "Llene todos los campos",
                            Toast.LENGTH_SHORT).show();
                } else {

                    ade();

                    calcular();
                    peri = Integer.toString(cont);

                    Intent q = new Intent(MainActivity.this,Main2Activity.class);
                    q.putExtra("perimetro", peri);
                    q.putExtra("area",CalcularArea());
                    startActivity(q);

                }
            }
        });

    }

    public void calcular(){
        int c=0;
        v1 = etx1.getText().toString();
        v2 = etx2.getText().toString();
        v3 = ety1.getText().toString();
        v4 = ety2.getText().toString();

        int num1 = Integer.parseInt(v1);
        int num2 = Integer.parseInt(v2);
        int num3 = Integer.parseInt(v3);
        int num4 = Integer.parseInt(v4);

        int a = (int)Math.pow((num2-num1), 2);
        int b = (int)Math.pow((num4-num3), 2);
        c = (int)Math.sqrt(a+b);
        cont=cont+c;

        lado.setText(""+au);



    }

    public static int m (ArrayList<Integer> x,ArrayList<Integer> y){

        int suma1=0;
        int co = 0;
        int re = 0;
        try {

            //Lado x
            for(int c=0; c < x.size()+1; c++){
                co++;

                re += y.get(c) * x.get(co);
                suma1=re;

            }
        }

        catch (Exception e){

        }
        finally {

            return suma1;

        }
    }

    public static int mx (ArrayList<Integer> x,ArrayList<Integer> y){

        int suma1=0;
        int co = 0;
        int re = 0;
        try {

            //Lado y
            for(int c=0; c < y.size()+1; c++){
                co++;

                re += x.get(c) * y.get(co);
                suma1=re;

            }
        }

        catch (Exception e){

        }
        finally {

            return suma1;

        }
    }

    public String CalcularArea (){


        int valor1 = m(x,y);
        int valor2 =mx(x,y);
        int result = (Math.abs(valor1-valor2))/2;

        String resl = Integer.toString(result);

        return resl;

    }

    public void borrar() {
        etx1.setText("");
        etx2.setText("");
        ety1.setText("");
        ety2.setText("");
    }

    public void ade(){

        int x1 = Integer.parseInt(etx1.getText().toString());
        int x2 = Integer.parseInt(etx2.getText().toString());
        int y1 = Integer.parseInt(ety1.getText().toString());
        int y2 = Integer.parseInt(ety2.getText().toString());

        x.add(x1);
        y.add(y1);
        x.add(x2);
        y.add(y2);

    }


}
