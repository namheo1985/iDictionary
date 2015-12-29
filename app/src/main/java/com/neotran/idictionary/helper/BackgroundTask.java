/**
 * Author: Neo Tran
 * Email: nam.tran.flash@gmail.com
 * Editor: ...(Please leave your name here if you want to edit this file)
 * Created Date: Jan 12, 2014
 */
package com.neotran.idictionary.helper;
import android.os.AsyncTask;

/**
 * A customized class is from AsyncTask<Object, Object, Object> to handle a
 * background process. AsyncTask<Object, Object, Object> : The three types used
 * by an asynchronous task are the following: Params, the type of the parameters
 * sent to the task upon execution. Progress, the type of the progress units
 * published during the background computation. Result, the type of the result
 * of the background computation.
 */
public class BackgroundTask extends AsyncTask<Object, Object, Object> {

    /**
     * mOnWorkListener: A listener to listen when the task begins to do.
     */
    private OnWorkListener mOnWorkListener;
    /**
     * mOnCompleteListener: A listener to listen when the task is completed.
     */
    private OnCompleteListener mOnCompleteListener;
    /**
     * mOnProgressListener: A listener to listen when the task is processing.
     */
    private OnProgressListener mOnProgressListener;
    /**
     * mOnTaskWorkListner: A listener to listen when the task begins to do, is
     * processing and completed.
     */
    private OnTaskWorkListner mOnTaskWorkListner;

    protected Object doInBackground(Object... params) {
        if(mOnTaskWorkListner != null)
            return mOnTaskWorkListner.onWork(params);
        if(mOnWorkListener != null) {
            return mOnWorkListener.onWork(params);
        }
        return null;
    }

    protected void onPostExecute(Object result) {
        if(mOnCompleteListener != null)
            mOnCompleteListener.onComplete(result);
        if(mOnTaskWorkListner != null) {
            mOnTaskWorkListner.onComplete(result);
        }
    }

    protected void onProgressUpdate(Object... params) {
        if(mOnProgressListener != null)
            mOnProgressListener.onProgress(params);
        if(mOnTaskWorkListner != null) {
            mOnTaskWorkListner.onProgress(params);
        }
    }

    /**
     * Sets a listener to do some code when the task is processing.
     *
     * @param listener a listener has onProgress(Object... params) function which is
     * implemented in purpose by the programmer.
     */
    public void setOnProgressListener(OnProgressListener listener) {
        mOnProgressListener = listener;
    }

    /**
     * Sets a listener to do some code when the task is completed. Put your code
     * in onComplete(Object param) of the listener.
     *
     * @param listener a listener has onComplete(Object param) function which is
     * implemented in purpose by the programmer.
     */
    public void setOnCompleteListener(OnCompleteListener listener) {
        mOnCompleteListener = listener;
    }

    /**
     * Sets a listener to do some code when the task begins to do.
     *
     * @param listener a listener has onWork(Object... params) function which is
     * implemented in purpose by the programmer.
     */
    public void setOnWorkListener(OnWorkListener listener) {
        mOnWorkListener = listener;
    }

    /**
     * Sets a listener to do some code when the task begins to do.
     *
     * @param listener a listener has onWork(Object... params), onComplete(Object
     * param) and onProgress(Object... params) functions which are
     * implemented in purpose by the programmer.
     */
    public void setOnTaskWorkListner(OnTaskWorkListner listener) {
        mOnTaskWorkListner = listener;
    }

    public interface OnTaskWorkListner {
        Object onWork(Object... params);

        Object onComplete(Object param);

        Object onProgress(Object... params);
    }

    public interface OnWorkListener {
        Object onWork(Object... params);
    }

    public interface OnCompleteListener {
        Object onComplete(Object param);
    }

    public interface OnProgressListener {
        Object onProgress(Object... params);
    }
}