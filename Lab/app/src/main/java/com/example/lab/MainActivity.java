package com.example.lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private TextView Result, Error, Operators, Table;
    private TextInputEditText AEditText,BEditText,ExtEditText;
    private TextInputLayout ALayout,BLayout,ExtLayout;
    private Button DeveloperPage;
    private static final String FILE_NAME = "config.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String OperatorsText = "Слабка Кон'юнкція - '&'\n" +
                "Слабка Диз'юнкція - 'v'\n" +
                "Сильна Кон'юнкція - '*'\n" +
                "Сильна Диз'юнкція - '+'\n" +
                "Заперечення - '!'\n" +
                "Штрих Шеффера - '|'\n" +
                "Стрілка Пірса - '?'\n";
        //Button
        DeveloperPage = (Button)findViewById(R.id.button_developer_page);
        //TextView
        Result = (TextView)findViewById(R.id.textView_result);
        Error = (TextView)findViewById(R.id.textView_error);
        Operators = (TextView)findViewById(R.id.textView_Operators);
        Table = (TextView)findViewById(R.id.textView_table);
        //setText OperatorsText
        Operators.setText(OperatorsText);
        //TextInputEditText
        AEditText = (TextInputEditText)findViewById(R.id.input_a);
        BEditText = (TextInputEditText)findViewById(R.id.input_b);
        ExtEditText = (TextInputEditText)findViewById(R.id.input_Expression);
        //TextInputLayout
        ALayout = (TextInputLayout)findViewById(R.id.input_text_a);
        BLayout = (TextInputLayout)findViewById(R.id.input_text_b);
        ExtLayout = (TextInputLayout)findViewById(R.id.input_text_Expression);
    }
    ArrayList<String> CopyArrList(ArrayList<String> e){
        ArrayList<String> copy = new ArrayList<String>();
        for(int i = 0; i < e.size(); i++) {
            copy.add(e.get(i));
        }
        return copy;
    }
    String GetExTable(ArrayList<String> ExListTable){
        ArrayList<String> ExList = CopyArrList(ExListTable);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        PrintArrayList(ExListTable);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String TableRez = "";
        String TableRowExt = "/ A/ B/";
        String TableRow1 = "/-1/-1/";
        String TableRow2 = "/-1/ 0/";
        String TableRow3 = "/-1/ 1/";
        String TableRow4 = "/ 0/-1/";
        String TableRow5 = "/ 0/ 0/";
        String TableRow6 = "/ 0/ 1/";
        String TableRow7 = "/ 1/-1/";
        String TableRow8 = "/ 1/ 0/";
        String TableRow9 = "/ 1/ 1/";

        ArrayList<String> ExList1 = CopyArrList(ExListTable);
        ArrayList<String> ExList2 = CopyArrList(ExListTable);
        ArrayList<String> ExList3 = CopyArrList(ExListTable);
        ArrayList<String> ExList4 = CopyArrList(ExListTable);
        ArrayList<String> ExList5 = CopyArrList(ExListTable);
        ArrayList<String> ExList6 = CopyArrList(ExListTable);
        ArrayList<String> ExList7 = CopyArrList(ExListTable);
        ArrayList<String> ExList8 = CopyArrList(ExListTable);
        ArrayList<String> ExList9 = CopyArrList(ExListTable);
        TableRow1 += GetTableRow(ExList1, "-1", "-1");
        TableRow2 += GetTableRow(ExList2, "-1", "0");
        TableRow3 += GetTableRow(ExList3, "-1", "1");
        TableRow4 += GetTableRow(ExList4, "0", "-1");
        TableRow5 += GetTableRow(ExList5, "0", "0");
        TableRow6 += GetTableRow(ExList6, "0", "1");
        TableRow7 += GetTableRow(ExList7, "1", "-1");
        TableRow8 += GetTableRow(ExList8, "1", "0");
        TableRow9 += GetTableRow(ExList9, "1", "1");

        System.out.println(TableRow1);
        System.out.println(TableRow2);
        System.out.println(TableRow3);
        System.out.println(TableRow4);
        System.out.println(TableRow5);
        System.out.println(TableRow6);
        System.out.println(TableRow7);
        System.out.println(TableRow8);
        System.out.println(TableRow9);
//        TableRowExt = TableRowExt.replaceAll("|","");
        int ExListTableSize = ExListTable.size();
        for(int i = 0; i < ExListTableSize; i++){
            if(ExListTable.get(i).equals("!")){
                String buff = ExListTable.get(i) + ExListTable.get(i+1);
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + "(" + buff + ")";
                ExListTable.set(i, Ex);
                ExListTable.remove(i + 1);
                ExListTableSize = ExListTableSize - 1;
            }
            if(ExListTable.get(i).equals("v")){
                if(ExListTable.get(i + 1).equals("!")){
                    String buff = ExListTable.get(i + 1) + ExListTable.get(i + 2);
                    buff = buff.replaceAll("/","");
                    String Ex = "(" + buff + ")";
                    TableRowExt += "/" + Ex;
                    ExListTable.set(i + 1, Ex);
                    ExListTable.remove(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.get(i - 1) + ExListTable.get(i) + ExListTable.get(i + 1);
                buff = buff.replaceAll("/","");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.set(i - 1, Ex);
                ExListTable.remove(i + 1);
                ExListTable.remove(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
            if(ExListTable.get(i).equals("?")){
                if(ExListTable.get(i + 1).equals("!")){
                    String buff = ExListTable.get(i + 1) + ExListTable.get(i + 2);
                    buff = buff.replaceAll("/","");
                    String Ex = "(" + buff + ")";
                    TableRowExt += "/" + Ex;
                    ExListTable.set(i + 1, Ex);
                    ExListTable.remove(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.get(i - 1) + ExListTable.get(i) + ExListTable.get(i + 1);
                buff = buff.replaceAll("/","");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.set(i - 1, Ex);
                ExListTable.remove(i + 1);
                ExListTable.remove(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
            if(ExListTable.get(i).equals("|")){
                if(ExListTable.get(i + 1).equals("!")){
                    String buff = ExListTable.get(i + 1) + ExListTable.get(i + 2);
                    buff = buff.replaceAll("/","");
                    String Ex = "(" + buff + ")";
                    TableRowExt += "/" + Ex;
                    ExListTable.set(i + 1, Ex);
                    ExListTable.remove(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.get(i - 1) + ExListTable.get(i) + ExListTable.get(i + 1);
                buff = buff.replaceAll("/","");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.set(i - 1, Ex);
                ExListTable.remove(i + 1);
                ExListTable.remove(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
            if(ExListTable.get(i).equals("&")){
                if(ExListTable.get(i + 1).equals("!")){
                    String buff = ExListTable.get(i + 1) + ExListTable.get(i + 2);
                    buff = buff.replaceAll("/","");
                    String Ex = "(" + buff + ")";
                    TableRowExt += "/" + Ex;
                    ExListTable.set(i + 1, Ex);
                    ExListTable.remove(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.get(i - 1) + ExListTable.get(i) + ExListTable.get(i + 1);
                buff = buff.replaceAll("/","");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.set(i - 1, Ex);
                ExListTable.remove(i + 1);
                ExListTable.remove(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
            if(ExListTable.get(i).equals("+")){
                if(ExListTable.get(i + 1).equals("!")){
                    String buff = ExListTable.get(i + 1) + ExListTable.get(i + 2);
                    buff = buff.replaceAll("/","");
                    String Ex = "(" + buff + ")";
                    TableRowExt += "/" + Ex;
                    ExListTable.set(i + 1, Ex);
                    ExListTable.remove(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.get(i - 1) + ExListTable.get(i) + ExListTable.get(i + 1);
                buff = buff.replaceAll("/","");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.set(i - 1, Ex);
                ExListTable.remove(i + 1);
                ExListTable.remove(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
            if(ExListTable.get(i).equals("*")){
                if(ExListTable.get(i + 1).equals("!")){
                    String buff = ExListTable.get(i + 1) + ExListTable.get(i + 2);
                    buff = buff.replaceAll("/","");
                    String Ex = "(" + buff + ")";
                    TableRowExt += "/" + Ex;
                    ExListTable.set(i + 1, Ex);
                    ExListTable.remove(i + 2);
                    ExListTableSize = ExListTableSize - 1;
                }
                String buff = ExListTable.get(i - 1) + ExListTable.get(i) + ExListTable.get(i + 1);
                buff = buff.replaceAll("/","");
                String Ex = "(" + buff + ")";
                TableRowExt += "/" + Ex;
                ExListTable.set(i - 1, Ex);
                ExListTable.remove(i + 1);
                ExListTable.remove(i);
                i--;
                ExListTableSize = ExListTableSize - 2;
            }
        }

        System.out.println("=================================TableRowExt");
        System.out.println(TableRowExt);
        PrintArrayList(ExListTable);
        System.out.println("=================================TableRowExt");
        TableRez += TableRowExt + "\n";
        TableRez += TableRow1 + "\n";
        TableRez += TableRow2 + "\n";
        TableRez += TableRow3 + "\n";
        TableRez += TableRow4 + "\n";
        TableRez += TableRow5 + "\n";
        TableRez += TableRow6 + "\n";
        TableRez += TableRow7 + "\n";
        TableRez += TableRow8 + "\n";
        TableRez += TableRow9;
        Table.setText(TableRez);
        return TableRowExt;
    }
    String GetTableRow(ArrayList<String> ExListt,String aZnach, String bZnach){
        ArrayList<String> ExListtt = ExListt;
        System.out.println("============= ExList===========  ");
        PrintArrayList(ExListtt);
        System.out.println("============= ExList=========== ");
        String rez = "";
        String inp_a = aZnach;
        System.out.println("inp_a : " + inp_a);
        String inp_b = bZnach;
        System.out.println("inp_b : " + inp_b);
        int a = Integer.parseInt(inp_a);
        int b = Integer.parseInt(inp_b);
        PrintArrayList(ExListtt);
        for(int i = 0; i < ExListtt.size(); i++) {
            if(ExListtt.get(i).equals("a")) {
                ExListtt.set(i, inp_a);
            }
            if(ExListtt.get(i).equals("b")) {
                ExListtt.set(i, inp_b);
            }
        }
        System.out.println("===================");
        PrintArrayList(ExListtt);
        int size = ExListtt.size();
        for(int i = 0; i < size; i++) {
            if(ExListtt.get(i).equals("!")) {
                rez += String.valueOf(Denial_t(ExListtt.get(i + 1))) + "/";
//                System.out.println("============= rez : " + rez);
                ExListtt.set(i, String.valueOf(Denial_t(ExListtt.get(i + 1))));
                ExListtt.remove(i + 1);
                size = size - 1;
            }
            if(ExListtt.get(i).equals("|")) {
                if(ExListtt.get(i + 1).equals("!")) {
                    rez += String.valueOf(Denial_t(ExListtt.get(i + 2))) + "/";
                    ExListtt.set(i + 1, String.valueOf(Denial_t(ExListtt.get(i + 2))));
                    ExListtt.remove(i + 2);
                    size = size - 1;
                }
                rez += String.valueOf(SchaeffersStroke(ExListtt.get(i - 1), ExListtt.get(i + 1))) + "/";
                ExListtt.set(i, String.valueOf(SchaeffersStroke(ExListtt.get(i - 1), ExListtt.get(i + 1))));
                System.out.println("i : " + i);
                System.out.println("size : " + size);
                ExListtt.remove(i - 1);
                ExListtt.remove(i);
                i = i - 1;
                size = size - 2;
            }
            if(ExListtt.get(i).equals("?")) {
                if(ExListtt.get(i + 1).equals("!")) {
                    rez += String.valueOf(Denial_t(ExListtt.get(i + 2))) + "/";
                    ExListtt.set(i + 1, String.valueOf(Denial_t(ExListtt.get(i + 2))));
                    ExListtt.remove(i + 2);
                    size = size - 1;
                }
                rez += String.valueOf(PiercesArrow(ExListtt.get(i - 1), ExListtt.get(i + 1))) + "/";
                ExListtt.set(i, String.valueOf(PiercesArrow(ExListtt.get(i - 1), ExListtt.get(i + 1))));
                System.out.println("i : " + i);
                System.out.println("size : " + size);
                ExListtt.remove(i - 1);
                ExListtt.remove(i);
                i = i - 1;
                size = size - 2;
            }
            if(ExListtt.get(i).equals("&")) {
                if(ExListtt.get(i + 1).equals("!")) {
                    rez += String.valueOf(Denial_t(ExListtt.get(i + 2))) + "/";
                    ExListtt.set(i + 1, String.valueOf(Denial_t(ExListtt.get(i + 2))));
                    ExListtt.remove(i + 2);
                    size = size - 1;
                }
                rez += String.valueOf(WeakConjunction(ExListtt.get(i - 1), ExListtt.get(i + 1))) + "/";
                ExListtt.set(i, String.valueOf(WeakConjunction(ExListtt.get(i - 1), ExListtt.get(i + 1))));
                System.out.println("i : " + i);
                System.out.println("size : " + size);
                ExListtt.remove(i - 1);
                ExListtt.remove(i);
                i = i - 1;
                size = size - 2;
            }
            if(ExListtt.get(i).equals("v")) {
                if(ExListtt.get(i + 1).equals("!")) {
                    rez += String.valueOf(Denial_t(ExListtt.get(i + 2))) + "/";
                    ExListtt.set(i + 1, String.valueOf(Denial_t(ExListtt.get(i + 2))));
                    ExListtt.remove(i + 2);
                    size = size - 1;
                }
                rez += String.valueOf(WeakDisjunction(ExListtt.get(i - 1), ExListtt.get(i + 1))) + "/";
                ExListtt.set(i, String.valueOf(WeakDisjunction(ExListtt.get(i - 1), ExListtt.get(i + 1))));
                System.out.println("i : " + i);
                System.out.println("size : " + size);
                ExListtt.remove(i - 1);
                ExListtt.remove(i);
                i = i - 1;
                size = size - 2;
            }
            if(ExListtt.get(i).equals("+")) {
                if(ExListtt.get(i + 1).equals("!")) {
                    rez += String.valueOf(Denial_t(ExListtt.get(i + 2))) + "/";
                    ExListtt.set(i + 1, String.valueOf(Denial_t(ExListtt.get(i + 2))));
                    ExListtt.remove(i + 2);
                    size = size - 1;
                }
                rez += String.valueOf(StrongDisjunction(ExListtt.get(i - 1), ExListtt.get(i + 1))) + "/";
                ExListtt.set(i, String.valueOf(StrongDisjunction(ExListtt.get(i - 1), ExListtt.get(i + 1))));
                System.out.println("i : " + i);
                System.out.println("size : " + size);
                ExListtt.remove(i - 1);
                ExListtt.remove(i);
                i = i - 1;
                size = size - 2;
            }
            if(ExListtt.get(i).equals("*")) {
                if(ExListtt.get(i + 1).equals("!")) {
                    rez += String.valueOf(Denial_t(ExListtt.get(i + 2))) + "/";
                    ExListtt.set(i + 1, String.valueOf(Denial_t(ExListtt.get(i + 2))));
                    ExListtt.remove(i + 2);
                    size = size - 1;
                }
                rez += String.valueOf(StrongConjunction(ExListtt.get(i - 1), ExListtt.get(i + 1))) + "/";
                ExListtt.set(i, String.valueOf(StrongConjunction(ExListtt.get(i - 1), ExListtt.get(i + 1))));
                System.out.println("i : " + i);
                System.out.println("size : " + size);
                ExListtt.remove(i - 1);
                ExListtt.remove(i);
                i = i - 1;
                size = size - 2;
            }
        }
        System.out.println("===================PrintArrayList");
        PrintArrayList(ExListtt);
        return rez;
    }
    public void onClickSave(View view) {
        String TextToSave;
        TextToSave = "A : " + AEditText.getText().toString() + "\n";
        TextToSave += "B : " + BEditText.getText().toString() + "\n";
        TextToSave += "Expression : " + ExtEditText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(TextToSave.getBytes());
            Toast.makeText(this,
                    "Save to " + getFilesDir() + "/" + FILE_NAME,
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void onClickResult(View view) {
        Error.setText("");
        Table.setText("");
        ALayout.setErrorEnabled(false);
        BLayout.setErrorEnabled(false);
        ExtLayout.setErrorEnabled(false);
        boolean isTrue = true;
        int rez;
        String ErrorMassage = "";

        if(!AEditText.getText().toString().toLowerCase().equals("1")) {
            if(!AEditText.getText().toString().toLowerCase().equals("-1")) {
                if(!AEditText.getText().toString().toLowerCase().equals("0")){
                    ALayout.setError("A is not current");
                    ErrorMassage += "|A is not current|\n";
                    isTrue = false;
                }
            }
        }

        if(!BEditText.getText().toString().toLowerCase().equals("1")) {
            if(!BEditText.getText().toString().toLowerCase().equals("-1")) {
                if(!BEditText.getText().toString().toLowerCase().equals("0")){
                    BLayout.setError("B is not current");
                    ErrorMassage += "|B is not current|\n";
                    isTrue = false;
                }
            }
        }
        String TableRez = "";
        String TableRowExt = "/ A/ B/";
        String TableRow1 = "/-1/-1/";
        String TableRow2 = "/-1/ 0/";
        String TableRow3 = "/-1/ 1/";
        String TableRow4 = "/ 0/-1/";
        String TableRow5 = "/ 0/ 0/";
        String TableRow6 = "/ 0/ 1/";
        String TableRow7 = "/ 1/-1/";
        String TableRow8 = "/ 1/ 0/";
        String TableRow9 = "/ 1/ 1/";

        String Exx = ExtEditText.getText().toString().toLowerCase();
        Exx = Exx.replaceAll("\\s+","").toLowerCase();
        ArrayList<String> ExxList = new ArrayList<String>();
        for(int i = 0; i < Exx.length(); i++) {
            ExxList.add(String.valueOf(Exx.charAt(i)));
        }
        TableRowExt +=GetExTable(ExxList);
        try{
            if(ExtEditText.getText().toString().isEmpty()) {
                ExtLayout.setError("Expression is not current");
                ErrorMassage += "|Expression is not current|\n";
                isTrue = false;
            }
            String Ex = ExtEditText.getText().toString().toLowerCase();
            Ex = Ex.replaceAll("\\s+","").toLowerCase();
            System.out.println(Ex);

            ArrayList<String> ExList = new ArrayList<String>();
            for(int i = 0; i < Ex.length(); i++) {
                ExList.add(String.valueOf(Ex.charAt(i)));
            }
            for(int i = 0; i < ExList.size(); i++) {
                if(i + 1 != ExList.size() && ExList.get(i).equals("a") && ExList.get(i + 1).equals("a")){
                    isTrue = false;
                }
                if(i + 1 != ExList.size() && ExList.get(i).equals("a") && ExList.get(i + 1).equals("b")){
                    isTrue = false;
                }
                if(i + 1 != ExList.size() && ExList.get(i).equals("b") && ExList.get(i + 1).equals("a")){
                    isTrue = false;
                }
                if(i + 1 != ExList.size() && ExList.get(i).equals("b") && ExList.get(i + 1).equals("b")){
                    isTrue = false;
                }
                if(i + 1 != ExList.size() && ExList.get(i).equals("!") && ExList.get(i + 1).equals("!")){
                    isTrue = false;
                }
                switch(ExList.get(i)){
                    case "a":
                    case "&":
                    case "|":
                    case "v":
                    case "b":
                    case "?":
                    case "+":
                    case "*":
                    case "!": {
                        break;
                    }
                    default:{
                        isTrue = false;
                        break;
                    }
                }
            }
            if(isTrue){
                String inp_a = AEditText.getText().toString();
                System.out.println("inp_a : " + inp_a);
                String inp_b = BEditText.getText().toString();
                System.out.println("inp_b : " + inp_b);
                int a = Integer.parseInt(inp_a);
                int b = Integer.parseInt(inp_b);
                PrintArrayList(ExList);
                for(int i = 0; i < ExList.size(); i++) {
                    if(ExList.get(i).equals("a")) {
                        ExList.set(i, inp_a);
                    }
                    if(ExList.get(i).equals("b")) {
                        ExList.set(i, inp_b);
                    }
                }
                System.out.println("===================");
                PrintArrayList(ExList);
                int size = ExList.size();
                for(int i = 0; i < size; i++) {
                    if(ExList.get(i).equals("!")) {
                        ExList.set(i, String.valueOf(Denial_t(ExList.get(i + 1))));
                        ExList.remove(i + 1);
                        size = size - 1;
                    }
                    if(ExList.get(i).equals("|")) {
                        if(ExList.get(i + 1).equals("!")) {
                            ExList.set(i + 1, String.valueOf(Denial_t(ExList.get(i + 2))));
                            ExList.remove(i + 2);
                            size = size - 1;
                        }
                        ExList.set(i, String.valueOf(SchaeffersStroke(ExList.get(i - 1), ExList.get(i + 1))));
                        System.out.println("i : " + i);
                        System.out.println("size : " + size);
                        ExList.remove(i - 1);
                        ExList.remove(i);
                        i = i - 1;
                        size = size - 2;
                    }
                    if(ExList.get(i).equals("?")) {
                        if(ExList.get(i + 1).equals("!")) {
                            ExList.set(i + 1, String.valueOf(Denial_t(ExList.get(i + 2))));
                            ExList.remove(i + 2);
                            size = size - 1;
                        }
                        ExList.set(i, String.valueOf(PiercesArrow(ExList.get(i - 1), ExList.get(i + 1))));
                        System.out.println("i : " + i);
                        System.out.println("size : " + size);
                        ExList.remove(i - 1);
                        ExList.remove(i);
                        i = i - 1;
                        size = size - 2;
                    }
                    if(ExList.get(i).equals("&")) {
                        if(ExList.get(i + 1).equals("!")) {
                            ExList.set(i + 1, String.valueOf(Denial_t(ExList.get(i + 2))));
                            ExList.remove(i + 2);
                            size = size - 1;
                        }
                        ExList.set(i, String.valueOf(WeakConjunction(ExList.get(i - 1), ExList.get(i + 1))));
                        System.out.println("i : " + i);
                        System.out.println("size : " + size);
                        ExList.remove(i - 1);
                        ExList.remove(i);
                        i = i - 1;
                        size = size - 2;
                    }
                    if(ExList.get(i).equals("v")) {
                        if(ExList.get(i + 1).equals("!")) {
                            ExList.set(i + 1, String.valueOf(Denial_t(ExList.get(i + 2))));
                            ExList.remove(i + 2);
                            size = size - 1;
                        }
                        ExList.set(i, String.valueOf(WeakDisjunction(ExList.get(i - 1), ExList.get(i + 1))));
                        System.out.println("i : " + i);
                        System.out.println("size : " + size);
                        ExList.remove(i - 1);
                        ExList.remove(i);
                        i = i - 1;
                        size = size - 2;
                    }
                    if(ExList.get(i).equals("*")) {
                        if(ExList.get(i + 1).equals("!")) {
                            ExList.set(i + 1, String.valueOf(Denial_t(ExList.get(i + 2))));
                            ExList.remove(i + 2);
                            size = size - 1;
                        }
                        ExList.set(i, String.valueOf(StrongConjunction(ExList.get(i - 1), ExList.get(i + 1))));
                        System.out.println("i : " + i);
                        System.out.println("size : " + size);
                        ExList.remove(i - 1);
                        ExList.remove(i);
                        i = i - 1;
                        size = size - 2;
                    }
                    if(ExList.get(i).equals("+")) {
                        if(ExList.get(i + 1).equals("!")) {
                            ExList.set(i + 1, String.valueOf(Denial_t(ExList.get(i + 2))));
                            ExList.remove(i + 2);
                            size = size - 1;
                        }
                        ExList.set(i, String.valueOf(StrongDisjunction(ExList.get(i - 1), ExList.get(i + 1))));
                        System.out.println("i : " + i);
                        System.out.println("size : " + size);
                        ExList.remove(i - 1);
                        ExList.remove(i);
                        i = i - 1;
                        size = size - 2;
                    }
                }
                System.out.println("===================PrintArrayList");
                PrintArrayList(ExList);
            }
            rez = Integer.valueOf(ExList.get(0));
            if(!ErrorMassage.isEmpty()){
                Error.setText(ErrorMassage);
            } else {
                if(rez == 1) {
                    Result.setText("True");
                }
                if(rez == -1) {
                    Result.setText("False");
                }
                if(rez == 0) {
                    Result.setText("undefined");
                }
            }
        } catch (Exception ex) {
            Result.setText("-----");
            ExtLayout.setError("Expression is not current");
            Error.setText("|Expression is not current|");
        }
        if(!isTrue){
            Result.setText("-----");
            ExtLayout.setError("Expression is not current");
            Error.setText("|Expression is not current|");
        }
    }
    void PrintArrayList(ArrayList<String> ExList){
        for(int i = 0; i < ExList.size(); i++){
            System.out.println(ExList.get(i));
        }
    }
    int setIntParam(String a){
        if(a.equals("1")){
            return 1;
        }
        if(a.equals("-1")){
            return -1;
        }
        return 0;
    }

    int Denial_t(String a){
        int a_param = setIntParam(a);
        if(a_param == 1) return -1;
        if(a_param == -1) return 1;
        return 0;
    }
    int WeakConjunction(String a, String b){
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if(a_param == b_param && a_param == 1) return 1;
        if(a_param == -1 || b_param == -1) return -1;
        return 0;
    }
    int WeakDisjunction(String a, String b){
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if(a_param == b_param && a_param == -1) return -1;
        if(a_param == 1 || b_param == 1) return 1;
        return 0;
    }
    int StrongConjunction(String a, String b){
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if(a_param == b_param && a_param == 1) return 1;
        if(a_param == b_param && a_param == 0) return -1;
        if(a_param == -1 || b_param == -1) return -1;
        return 0;
    }
    int StrongDisjunction(String a, String b){
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if(a_param == b_param && a_param == -1) return -1;
        if(a_param == b_param && a_param == 0) return 1;
        if(a_param == 1 || b_param == 1) return 1;
        return 0;
    }
    int PiercesArrow(String a, String b){
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if(a_param == b_param && a_param == -1) return 1;
        if(a_param == 1 || b_param == 1) return -1;
        return 0;
    }
    int SchaeffersStroke(String a, String b){
        int a_param = setIntParam(a);
        int b_param = setIntParam(b);
        if(a_param == b_param && a_param == 1) return -1;
        if(a_param == -1 || b_param == -1) return 1;
        return 0;
    }
    public void onClickDeveloperPage(View view) {
        DeveloperPage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("android.intent.action.Developer");
                        startActivity(intent);
                    }
                }
        );
    }
    public void onClickLoad(View view) {
        Table.setText("");
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String LoadText;

            while ((LoadText = br.readLine()) != null){
                sb.append(LoadText).append("\n");
            }
            Table.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}