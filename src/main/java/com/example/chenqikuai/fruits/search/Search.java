package com.example.chenqikuai.fruits.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.chenqikuai.fruits.R;
import com.example.chenqikuai.fruits.search.fruitShow.FruitShow;

public class Search extends Fragment implements View.OnClickListener {
    private View mView;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        mView = view;
        Button btn_search = (Button) view.findViewById(R.id.btn_search);
        editText = (EditText) mView.findViewById(R.id.edit_search);
        editText.setText("");
        btn_search.setOnClickListener(this);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    searchInBaidu();
                }
                return false;
            }
        });
        return view;
    }

    private void searchInBaidu() {
        Intent intent = new Intent(getActivity(), FruitShow.class);
        intent.putExtra("name", editText.getText().toString());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        editText.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search:
                searchInBaidu();
                break;
        }
    }
}
