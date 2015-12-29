package com.neotran.idictionary.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.neotran.idictionary.R;
import com.neotran.idictionary.helper.BackgroundTask;
import com.neotran.idictionary.helper.SystemHelper;
import com.neotran.idictionary.model.Word;

import io.realm.Realm;
import io.realm.RealmResults;

public class SearchFragment extends BaseFragment {
    private String mSearchText = "";
    private AutoCompleteTextView mSearchEditText;
    private Button mSearchButton;
    private Button mClearButton;
    private View mRecentSearchView;
    private ArrayAdapter<String> mWordsAdapter;

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
            mSearchEditText = (AutoCompleteTextView) pMainView.findViewById(R.id.search_edit_text);
            mRecentSearchView = (View) pMainView.findViewById(R.id.recent_search_view);
            mSearchButton = (Button) pMainView.findViewById(R.id.search_button);
        }
    }

    @Override
    public void onLazyLoadingUI(View pMainView) {
        SystemHelper.toggleSoftKeyboardFromView(getActivity(), true);
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRecentSearchView.setVisibility(mSearchEditText.getText().length() > 0 ? View.GONE : View.VISIBLE);
                if(s.length() >= 3) {
                    updateAdapter(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void updateAdapter(final String key) {
        BackgroundTask task = new BackgroundTask();
        task.setOnTaskWorkListner(new BackgroundTask.OnTaskWorkListner() {
            @Override
            public Object onWork(Object... params) {
                Realm realm = Realm.getInstance(SearchFragment.this.getActivity());
                RealmResults<Word> results = realm.where(Word.class).beginsWith("value", key).findAll();
                int max = results.size() > 10 ? 10 : results.size();
                String[] stringResults = new String[max];
                for(int i = 0; i < max; i++) {
                    stringResults[i] = results.get(i).getValue();
                }
                mWordsAdapter = new ArrayAdapter<String>(SearchFragment.this.getActivity(), R.layout.autocomplete_item, stringResults);
                realm.close();
                return null;
            }

            @Override
            public Object onComplete(Object param) {
                mSearchEditText.setAdapter(mWordsAdapter);
                mSearchEditText.showDropDown();
                return null;
            }

            @Override
            public Object onProgress(Object... params) {
                return null;
            }
        });
        task.execute();
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

    @Override
    public void onResume() {
        super.onResume();
        if(mSearchEditText != null && mWordsAdapter != null && mWordsAdapter.getCount() > 0) {
            mSearchEditText.showDropDown();
        }
    }
}
