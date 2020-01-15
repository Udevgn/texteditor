package com.example.texteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView tvWordCount;
    TextView tvUndo;
    String bufferWord;
    String text="";
    Stack<String> wordStack;
    Deque<String> operation;
    boolean lastWasUndo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.et_text);
        tvUndo = findViewById(R.id.tv_undo);
        tvWordCount = findViewById(R.id.tv_word_count);
        wordStack = new Stack<>();

        if(!Preferences.getSavedText().equals("")){
            editText.setText(Preferences.getSavedText());
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(wordStack.empty())
                    wordStack.add(s.toString());
                else if(!wordStack.peek().equals(s.toString()))
                    wordStack.push(s.toString());
                if(s.toString().length() == 0)
                    tvWordCount.setText("0");
                else
                    tvWordCount.setText(s.toString().split("\\s+").length+"");

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tvUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("word stack",wordStack.toString());
                if(wordStack.empty())
                    return;
                lastWasUndo = true;
                wordStack.pop();
                if(!wordStack.empty()){
                    String text  =      wordStack.peek();
                    editText.setText(text);
                    editText.setSelection(text.length());
                }
                else
                    editText.setText("");
            }
        });





    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        outState.putString("TEXT", editText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        String text = savedInstanceState.getString("TEXT");
        editText.setText(text);
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        Preferences.saveText(editText.getText().toString());
    }
}
