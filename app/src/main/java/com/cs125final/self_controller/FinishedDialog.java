package com.cs125final.self_controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;

public class FinishedDialog extends AppCompatDialogFragment {
    private FinishedDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Congratulation")
                .setMessage("You finish the task!")
                .setPositiveButton("back to main page",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onBackClicked();
                    }
                });
        return builder.create();
    }
    public interface FinishedDialogListener {
        void onBackClicked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (FinishedDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + "must implement FinishedDialogListener");
        }

    }
}
