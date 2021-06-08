package com.example.lab_2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;

import com.example.lab_2.db.MyDbManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Объявляем использование элемента TextView:
    public TextView contacts;
    public Spinner spinnerBodyType;
    private MyDbManager myDbManager;
    private EditText editTextBrand,
            editTextColor,
            editTextEngineCapacity,
            editTextPrice,
            editTextDelD;
    private TextView textViewDataFromDB;
    private Button buttonMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbManager = new MyDbManager(this);
        editTextBrand = findViewById(R.id.editTextBrand);
        editTextColor = findViewById(R.id.editTextColor);
        editTextEngineCapacity = findViewById(R.id.editTextEngineCapacity);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextDelD = findViewById(R.id.editTextDelD);
        textViewDataFromDB = findViewById(R.id.textViewDataFromDB);
        spinnerBodyType = (Spinner) findViewById(R.id.spinnerBodyType);

        ArrayAdapter<String> listBodyType = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.BodyType));
        listBodyType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBodyType.setAdapter(listBodyType);
        //Связываемся с нашим элементом TextView:
        contacts = (TextView) findViewById(R.id.tv);
        //String text = spinnerBodyType.getSelectedItem().toString();

        //Метод получения контактных данных
        //readFromDB();
        getContacts();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myDbManager.openDb();
        readFromDB();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDbManager.closeDb();
    }

    private void readFromDB(){
        List<String> listID = myDbManager.readIDFromDb();
        List<String> listBrand = myDbManager.readBrandFromDb();
        List<String> listBodyType = myDbManager.readBodyTypeFromDb();
        List<String> listColor = myDbManager.readColorFromDb();
        List<String> listEngineCapacity = myDbManager.readEngineCapacityFromDb();
        List<String> listPrice = myDbManager.readPriceFromDb();
        String resultStringBrand = " | ID | Brand | Body type | Color | Engine capacity | Price $ | \n";
        for(int i = 0; i < listBrand.size(); i++){
            resultStringBrand += " | " + listID.get(i) +
                    " | " + listBrand.get(i) +
                    " | " + listBodyType.get(i) +
                    " | " + listColor.get(i) +
                    " | " + listEngineCapacity.get(i) +
                    " | " + listPrice.get(i) + "$ | \n";
        }
        textViewDataFromDB.setText(resultStringBrand);
        double average = 0.0;
        for(String ec : listEngineCapacity){
            average += Double.parseDouble(ec);
        }
        average = average / listEngineCapacity.size();
        textViewDataFromDB.append("Average engine capacity : " + average);
    }

    public void onClickSave(View view){
        boolean isHasValues = true;
        if(isNullOrEmpty(editTextBrand.getText().toString())){
            isHasValues = false;
        }
        if(isNullOrEmpty(editTextColor.getText().toString())){
            isHasValues = false;
        }
        if(isNullOrEmpty(editTextEngineCapacity.getText().toString())){
            isHasValues = false;
        }
        if(isNullOrEmpty(editTextPrice.getText().toString())){
            isHasValues = false;
        }
        if(isHasValues) {
            myDbManager.insertToDb(editTextBrand.getText().toString(),
                    spinnerBodyType.getSelectedItem().toString(),
                    editTextColor.getText().toString(),
                    Double.parseDouble(editTextEngineCapacity.getText().toString()),
                    Double.parseDouble(editTextPrice.getText().toString()));
            readFromDB();
            editTextBrand.setText("");
            editTextColor.setText("");
            editTextEngineCapacity.setText("");
            editTextPrice.setText("");
        }
    }
    public void onClickLoad(View view){
        readFromDB();
    }
    public void onClickDelete(View view){
        if(!isNullOrEmpty(editTextDelD.getText().toString())){
            myDbManager.deleteByID(editTextDelD.getText().toString());
            readFromDB();
            editTextDelD.setText("");
        }
    }
    public void onClickLoadSample(View view){
        List<String> listID = myDbManager.readIDFromDb();
        List<String> listBrand = myDbManager.readBrandFromDb();
        List<String> listBodyType = myDbManager.readBodyTypeFromDb();
        List<String> listColor = myDbManager.readColorFromDb();
        List<String> listEngineCapacity = myDbManager.readEngineCapacityFromDb();
        List<String> listPrice = myDbManager.readPriceFromDb();
        String resultStringBrand = " | ID | Brand | Body type | Color | Engine capacity | Price $ | \n";
        List<String> buffEngineCapacity = new ArrayList<>();
        for(int i = 0; i < listBrand.size(); i++){
            if(listBodyType.get(i).equals("station wagon") && listColor.get(i).toLowerCase().equals("red")){
                resultStringBrand += " | " + listID.get(i) +
                        " | " + listBrand.get(i) +
                        " | " + listBodyType.get(i) +
                        " | " + listColor.get(i) +
                        " | " + listEngineCapacity.get(i) +
                        " | " + listPrice.get(i) + "$ | \n";
                buffEngineCapacity.add(listEngineCapacity.get(i));

            }
        }
        textViewDataFromDB.setText(resultStringBrand);
        double average = 0.0;
        for(String ec : buffEngineCapacity){
            average += Double.parseDouble(ec);
        }
        average = average / buffEngineCapacity.size();
        textViewDataFromDB.append("Average engine capacity : " + average);
    }
    public void onClickMap(View view){
        Intent intent = new Intent(this, ActivityMap.class);
        startActivity(intent);
    }
    public void onClickOpenInfo(View view){
        Intent intent = new Intent(this, ActivityDevInfo.class);
        startActivity(intent);
    }
    //Описываем метод:
    public void getContacts() {

        String phoneNumber = null;

        //Связываемся с контактными данными и берем с них значения id контакта, имени контакта и его номера:
        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;



        StringBuffer output = new StringBuffer();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(CONTENT_URI, null,null, null, null);

        //Запускаем цикл обработчик для каждого контакта:
        if (cursor.getCount() > 0) {

            //Если значение имени и номера контакта больше 0 (то есть они существуют) выбираем
            //их значения в приложение привязываем с соответствующие поля "Имя" и "Номер":
            while (cursor.moveToNext()) {
                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));


                //Получаем имя:
                if (hasPhoneNumber > 0) {
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null,
                            Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);

                    //и соответствующий ему номер:
                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        if(phoneNumber.charAt(name.length() - 1) == '7'){
                            output.append("\n Name: " + name);
                            output.append("\n Phone number: " + phoneNumber);
                        }
                    }
                }
                output.append("\n");
            }

            //Полученные данные отображаем с созданном элементе TextView:
            contacts.setText(output);
        }
    }
    public static boolean isNullOrEmpty(String a) {
        return a == null || a.isEmpty();
    }
}