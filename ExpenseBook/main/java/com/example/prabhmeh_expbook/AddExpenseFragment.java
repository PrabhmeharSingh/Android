package com.example.prabhmeh_expbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/*
Class for implementing AddExpenseFragment Functionality
extends ExpenseFragment<AddExpenseFragment.OnAddFragmentInteractionListener>
 */
public class AddExpenseFragment extends ExpenseFragment<AddExpenseFragment.OnAddFragmentInteractionListener> {
    /*
    Inner interface for OnAddFragmentnteractionListener
    Must be implemented by the context calling AddExpenseFragment
     */
    public static interface OnAddFragmentInteractionListener{
        public abstract void onAddOkPressed(Expense e);
    }
    @Override
    public AddExpenseFragment.OnAddFragmentInteractionListener listenerSetter(@NonNull Context context)
    {
        if(context instanceof AddExpenseFragment.OnAddFragmentInteractionListener)
            return (AddExpenseFragment.OnAddFragmentInteractionListener) context;
        else
            throw new RuntimeException(context.toString()+"OnFIListener not implemented");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.fragment_layout,null);
        setEditTexts(view);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder.setView(view).setTitle("Add Expense").setNegativeButton("Cancel",null).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String s = checkInputs();
                if(s!=null)
                {
                    Toast myToast = Toast.makeText(getActivity(), s, Toast.LENGTH_LONG);
                    myToast.show();
                    return;
                }
                String expense_name = getExpenseName().getText().toString();
                String expense_date = getExpenseDate().getText().toString();
                float expense_charge = Float.parseFloat(getExpenseCharge().getText().toString());
                String expense_comment = getExpenseComment().getText().toString();
                getListener().onAddOkPressed(new Expense(expense_name,expense_date,expense_charge,expense_comment));
            }
        }).create();
    }
}
