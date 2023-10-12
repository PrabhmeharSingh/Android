package com.example.prabhmeh_expbook;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
/*
Main activity Class
implements Two OnFragmentInteractionListener Interfaces, one for AddExpenseFragment and one for ViewExpenseFragment
 */
public class MainActivity extends AppCompatActivity implements AddExpenseFragment.OnAddFragmentInteractionListener, ViewExpenseFragment.OnViewFragmentInteractionListener{
    private ArrayList<Expense> dataList;
    private ArrayAdapter<Expense> expenseAdapter;
    private ListView expenseList;
    private TextView totalExpense;
    private int selected;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataList = new ArrayList<Expense>();
        expenseList = findViewById(R.id.expense_list);
        expenseAdapter = new CustomList(this,dataList);
        expenseList.setAdapter(expenseAdapter);
        selected = -1;
        final View addButton = findViewById(R.id.addButton);
        final View viewButton = findViewById(R.id.viewButton);
        final View delButton = findViewById(R.id.delButton);
        totalExpense = findViewById(R.id.totalExpense);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddExpenseFragment().show(getSupportFragmentManager(),"ADD_EXPENSE");
            }
        });
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected<0)
                    return;
                new ViewExpenseFragment().show(getSupportFragmentManager(),"VIEW/EDIT expense");
            }
        });
        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selected<0)
                    return;
                expenseList.getChildAt(selected).setBackgroundColor(Color.WHITE);
                float p = dataList.get(selected).getCharge();
                float f = Float.parseFloat(totalExpense.getText().toString());
                f-=p;
                totalExpense.setText(df.format(f));
                dataList.remove(selected);
                expenseAdapter.notifyDataSetChanged();
                selected = -1;

            }
        });
        expenseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(selected == i)
                {
                    selected =-1;
                    view.setBackgroundColor(Color.WHITE);
                    return;
                }
                if(selected!=-1)
                {
                    adapterView.getChildAt(selected).setBackgroundColor(Color.WHITE);
                }
                selected =i;
                view.setBackgroundColor(getResources().getColor(R.color.sel));

            }
        });

    }



    @Override
    public void onAddOkPressed(Expense e) {
        dataList.add(e);
        expenseAdapter.notifyDataSetChanged();
        float f = Float.parseFloat(totalExpense.getText().toString());
        f+=e.getCharge();
        totalExpense.setText(df.format(f));
        // TODO INPUT PARSING FROM FRAGMENT IDEA TO ADD SUPERCLASS FOR ALL FRAGMENT CLASSES
    }
    @Override
    public void onViewEditPressed(Expense e) {
        float p = dataList.get(selected).getCharge();
        dataList.set(selected,e);
        expenseAdapter.notifyDataSetChanged();
        float f = Float.parseFloat(totalExpense.getText().toString());
        f+=e.getCharge()-p;
        totalExpense.setText(df.format(f));
        // TODO INPUT PARSING FROM FRAGMENT IDEA TO ADD SUPERCLASS FOR ALL FRAGMENT CLASSES
    }

    @Override
    public Expense getSelectedExpense() {
        if(selected<0)
            return null;
        return dataList.get(selected);
    }
}