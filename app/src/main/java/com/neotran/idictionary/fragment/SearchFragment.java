package com.neotran.idictionary.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.neotran.idictionary.R;

public class SearchFragment extends BaseFragment {
    private String mSearchText = "";
    private EditText mSearchEditText;
    private Button mSearchButton;
    private Button mClearButton;
    private View mRecentSearchView;

    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void initiateMainView(View pMainView) {
        if(pMainView != null) {
            mSearchEditText = (EditText) pMainView.findViewById(R.id.search_edit_text);
            mRecentSearchView = (View) pMainView.findViewById(R.id.recent_search_view);
            mSearchButton = (Button) pMainView.findViewById(R.id.search_button);
        }
    }

    @Override
    public void onLazyLoadingUI(View pMainView) {
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRecentSearchView.setVisibility(mSearchEditText.getText().length() > 0 ? View.GONE : View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
