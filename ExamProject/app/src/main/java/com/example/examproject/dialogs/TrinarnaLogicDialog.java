package com.example.examproject.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class TrinarnaLogicDialog extends AppCompatDialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String info = "Розділ тринарна логіка надає можлівість обчислювати: \n"+
                "Слабка Кон'юнкція - '&'\n" +
                "Слабка Диз'юнкція - 'v'\n" +
                "Сильна Кон'юнкція - '*'\n" +
                "Сильна Диз'юнкція - '+'\n" +
                "Заперечення - '!'\n" +
                "Штрих Шеффера - '|'\n" +
                "Стрілка Пірса - '?'\n" +
                "Текстові вікна «A» та «B» заповнюються числами: -1,0,1.\n" +
                "Кнопка «RESULT» повертає результат логічного виразу записаного у текстовому " +
                "вікні «Boolean Expression» а також повертає повертає таблицю істинності " +
                "заданого логічного виразу.\n" +
                "Кнопка «SAVE TO FILE» зберігає до файлу обидва оператори та логічний вираз.\n" +
                "Кнопка «LOAD FROM FILE» виводить вміст файлу у який попереднє " +
                "зберігалися данні при натисненні на кнопку «SAVE TO FILE».\n";
        builder.setTitle("Інформація про сторінку `тринарна логіка`")
                .setMessage(info)
                .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
