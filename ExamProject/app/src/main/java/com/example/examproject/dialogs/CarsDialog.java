package com.example.examproject.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class CarsDialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String info = "Розділ Авто надає можлівість зберігати дані до бази даних.\n" +
                "Текстове вікно «Brand» та «Color» заповнюється текстовими даними.\n" +
                "Текстове вікно «Engine capacity» та «Price» заповнюється дробовими числами.\n" +
                "Текст який було введено у вікно «Brand» відповідає полю «бренд».\n" +
                "Текст який було введено у вікно «Color» відповідає полю «колір».\n" +
                "Текст який було введено у вікно «Engine capacity» відповідає полю «об'єм двигуна».\n" +
                "Текст який було введено у вікно «Price» відповідає полю «ціна».\n" +
                "Текст який було введено у вікно «Body type» відповідає полю «тип кузова».\n" +
                "Кнопка «SAVE» додає данні до SQLite бази даних.\n" +
                "Кнопка «LOAD» виводить данні з SQLite бази даних.\n" +
                "Кнопка «DELETE» видаляє данні з SQLite бази даних.\n" +
                "Кнопка «LOAD SAMPLE» видаляє данні з SQLite бази даних за вибіркою - " +
                "тільки червоні з кузовом універсал.\n" +
                "У нижній частині вікна знаходиться поле у яке виводяться " +
                "усі контакти номер яких закінчується на 7\n";
        builder.setTitle("Інформація про сторінку 'Авто'")
                .setMessage(info)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
