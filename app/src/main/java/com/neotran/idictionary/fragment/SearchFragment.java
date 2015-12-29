package com.neotran.idictionary.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.neotran.idictionary.R;
import com.neotran.idictionary.helper.BackgroundTask;
import com.neotran.idictionary.helper.Noticer;
import com.neotran.idictionary.helper.SystemHelper;
import com.neotran.idictionary.model.Meaning;
import com.neotran.idictionary.model.Word;

import io.realm.Realm;
import io.realm.RealmResults;

public class SearchFragment extends BaseFragment {
    private String mSearchText = "";
    private EditText mSearchEditText;
    private Button mSearchButton;
    private ImageButton mClearButton;
    private View mRecentSearchView;
    private ListView mSuggestedListView;
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
            mClearButton = (ImageButton) pMainView.findViewById(R.id.clear_button);
            mSearchEditText = (EditText) pMainView.findViewById(R.id.search_edit_text);
            mRecentSearchView = pMainView.findViewById(R.id.recent_search_view);
            mSearchButton = (Button) pMainView.findViewById(R.id.search_button);
            mSuggestedListView = (ListView) pMainView.findViewById(R.id.suggested_words_list_view);
        }
    }

    @Override
    public void onLazyLoadingUI(View pMainView) {
        SystemHelper.toggleSoftKeyboardFromView(getActivity(), true);
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchEditText.setText("");
            }
        });
        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mClearButton.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
                mSearchText = s.toString();
                updateAdapter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSuggestedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(mWordsAdapter != null && mWordsAdapter.getCount() > position) {
                    String word = mWordsAdapter.getItem(position);
                    lookup(word);
                }
            }
        });

    }

    private String lookedUpMeaning;

    private void lookup(final String word) {
        BackgroundTask task = new BackgroundTask();
        task.setOnTaskWorkListner(new BackgroundTask.OnTaskWorkListner() {
            @Override
            public Object onWork(Object... params) {
                Realm realm = Realm.getInstance(getActivity());
                Meaning mean = realm.where(Meaning.class).equalTo("value", word).findFirst();
                lookedUpMeaning = (mean != null) ? mean.getMeaning() : null;
                realm.close();
                return null;
            }

            @Override
            public Object onComplete(Object param) {
                if(lookedUpMeaning != null)
                    Noticer.makeToast(getActivity(), lookedUpMeaning);
                return null;
            }

            @Override
            public Object onProgress(Object... params) {
                return null;
            }
        });
        task.execute();
    }

    private void updateAdapter(final String key) {
        if(key == null || key.length() <= 0) {
            mSuggestedListView.setVisibility(View.GONE);
            return;
        }
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
                mSuggestedListView.setAdapter(mWordsAdapter);
                mSuggestedListView.setVisibility((mWordsAdapter != null && mWordsAdapter.getCount() > 0) ? View.VISIBLE : View.GONE);
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
    }
}
