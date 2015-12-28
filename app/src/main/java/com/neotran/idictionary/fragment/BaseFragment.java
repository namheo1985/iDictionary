package com.neotran.idictionary.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.neotran.idictionary.R;
import com.neotran.idictionary.helper.SystemHelper;

public abstract class BaseFragment extends Fragment {
    private View mMainView = null;
    protected boolean mFirstLoaded = true;
    private Toolbar mToolBar = null;
    private FragmentCallBacker mFragmentCallBacker = null;
    public BaseFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(getLayoutId(), container, false);
        initiateMainView(mMainView);
        mToolBar = (Toolbar) mMainView.findViewById(R.id.toolbar);
        return mMainView;
    }

    public abstract int getLayoutId();

    public abstract void initiateMainView(View pMainView);

    public abstract void onLazyLoadingUI(View pMainView);

    @Override
    public void onResume() {
        super.onResume();
        if(mFirstLoaded) {
            if(mFragmentCallBacker != null) {
                mFragmentCallBacker.onFirstLoaded(this);
            }
            SystemHelper.postDelayed(new Runnable() {
                @Override
                public void run() {
                    SystemHelper.recycleDelayed(this);
                    onLazyLoadingUI(mMainView);
                }
            }, 50);
        }
        mFirstLoaded = false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public View getMainView() {
        return mMainView;
    }

    public Toolbar getToolBar() {
        return mToolBar;
    }

    public static interface FragmentCallBacker {
        void onFirstLoaded(BaseFragment fragment);
    }
    public void setFragmentCallBacker(FragmentCallBacker callBacker) {
        mFragmentCallBacker = callBacker;
    }
}
