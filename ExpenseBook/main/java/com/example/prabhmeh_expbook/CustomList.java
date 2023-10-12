package com.example.prabhmeh_expbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
/*
Class Representing a custom ArrayAdapter which can create views other than just TextViews from a DataSource
extends ArrayAdapter
 */
public class CustomList extends ArrayAdapter<Expense> {
    private ArrayList<Expense> expenses;
    private Context context;

    public CustomList(Context ctx, ArrayList<Expense> exs)
    {
        super(ctx,0,exs);
        expenses = exs;
        context = ctx;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.content,parent,false);
        }
        Expense exp = expenses.get(position);
        TextView expenseName = view.findViewById(R.id.expenseName);
        TextView expenseDate = view.findViewById(R.id.expenseDate);
        TextView expenseAmt = view.findViewById(R.id.expenseAmount);
        expenseName.setText(exp.getName());
        expenseDate.setText(exp.getDate());
        expenseAmt.setText(String.valueOf(exp.getCharge()));
        return view;
    }
}
