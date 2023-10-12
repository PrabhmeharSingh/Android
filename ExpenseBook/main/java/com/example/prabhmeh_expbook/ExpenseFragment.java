package com.example.prabhmeh_expbook;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Generic Abstract Base Class for both Add and ViewExpenseFragment Classes
Contains Basic Functionality for both
extends DialogFragment
 */
public abstract class ExpenseFragment<T> extends DialogFragment {
    private EditText expenseName;
    private EditText expenseDate;
    private EditText expenseCharge;
    private EditText expenseComment;
    private T listener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = listenerSetter(context);
    }
    public abstract T listenerSetter(@NonNull Context context);

    public EditText getExpenseName() {
        return expenseName;
    }

    public EditText getExpenseDate() {
        return expenseDate;
    }

    public EditText getExpenseCharge() {
        return expenseCharge;
    }

    public EditText getExpenseComment() {
        return expenseComment;
    }

    public T getListener() {
        return listener;
    }

    public void setEditTexts(@NonNull View view)
    {
        expenseName = view.findViewById(R.id.expenseNameEditText);
        expenseDate=view.findViewById(R.id.expenseDateEditText);
        expenseCharge = view.findViewById(R.id.expenseChargeEditText);
        expenseComment = view.findViewById(R.id.expenseCommentEditText);
    }
    public String checkInputs()
    {
        if(invalidName())
            return "Name Empty List Not Updated";
        if(invalidDate())
            return "Invalid Date List Not Updated";
        if(invalidCharge())
            return "Invalid Charge List Not Updated";
        return null;

    }
    private boolean invalidName()
    {
        return expenseName.getText()==null || expenseName.getText().toString().trim().isEmpty();
    }
    private boolean invalidDate()
    {
        if(expenseDate.getText()==null)
            return true;
        String s = expenseDate.getText().toString().trim();
        if(s.isEmpty())
            return true;
        Pattern ptrn = Pattern.compile("(19[0-9][0-9]|20([01][0-9]|2[0-3]))(-)(0[1-9]|1[012])");
        Matcher matcher = ptrn.matcher(s);
        return !matcher.matches();


    }
    private boolean invalidCharge()
    {
        if(expenseCharge.getText()==null)
            return true;
        String s = expenseCharge.getText().toString().trim();
        if(s.isEmpty())
            return true;
        Pattern ptrn = Pattern.compile("[0-9]+(\\.)[0-9]{2}");
        Matcher matcher = ptrn.matcher(s);
        return !matcher.matches();
    }



}
