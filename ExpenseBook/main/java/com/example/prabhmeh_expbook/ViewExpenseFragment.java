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
Class for implementing ViewExpenseFragment Functionality
extends ExpenseFragment<ViewExpenseFragment.OnViewFragmentInteractionListener>
 */
public class ViewExpenseFragment extends ExpenseFragment<ViewExpenseFragment.OnViewFragmentInteractionListener> {


    @Override
    public OnViewFragmentInteractionListener listenerSetter(@NonNull Context context) {
        if(context instanceof ViewExpenseFragment.OnViewFragmentInteractionListener)
            return (ViewExpenseFragment.OnViewFragmentInteractionListener) context;
        else
            throw new RuntimeException(context.toString()+"OnFIListener not implemented");
    }

    /*
    Inner interface for OnViewFragmentnteractionListener
    Must be implemented by the context calling ViewExpenseFragment
     */
    public static interface OnViewFragmentInteractionListener{
        public abstract void onViewEditPressed(Expense e);
        public abstract Expense getSelectedExpense();
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Expense e = getListener().getSelectedExpense();
        if(e == null)
        {
            throw new RuntimeException("No Expense Selected");
        }

        View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.fragment_layout,null);
        setEditTexts(view);
        getExpenseName().setText(e.getName());
        getExpenseDate().setText(e.getDate());
        getExpenseCharge().setText(String.valueOf(e.getCharge()));
        getExpenseComment().setText(e.getComment());


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder.setView(view).setTitle("View/Edit Expense").setNegativeButton("Cancel",null).setPositiveButton("Edit", new DialogInterface.OnClickListener() {
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
                getListener().onViewEditPressed(new Expense(expense_name,expense_date,expense_charge,expense_comment));
            }
        }).create();
    }
}
