/*
    Copyright 2018 Gaurav Kumar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package com.github.mvpbasearchitecture.base;

import com.github.mvpbasearchitecture.R;
import com.github.mvpbasearchitecture.data.source.repository.AppDataSource;
import com.github.mvpbasearchitecture.data.source.repository.AppRepository;
import com.github.mvpbasearchitecture.data.source.network.NetworkError;
import com.github.mvpbasearchitecture.utils.GeneralUtils;
import com.github.mvpbasearchitecture.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Base Presenter class that abstracts some of the repetitive work of a Presenter
 * Implements the basic functions as described in {@link BaseContract.Presenter}
 *
 * Created by gk
 */

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V>{

    private final AppRepository mAppDataRepository;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mView;

    public BasePresenter(AppRepository appRepository,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.mAppDataRepository = appRepository;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V view) {
        this.mView = view;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.clear();
        mView = null;
    }

    @Override
    public void handleApiError(Throwable throwable) {

        if (throwable == null) {
            getView().onError(R.string.default_error_message);
            return;
        }

        NetworkError networkError = new NetworkError(throwable);
        String errorMsg = networkError.getAppErrorMessage();
        if (GeneralUtils.checkStringNotEmpty(errorMsg))
            getView().onError(errorMsg);
        else
            getView().onError(R.string.default_error_message);

    }

    protected boolean isViewAttached() {
        return mView != null;
    }

    protected V getView(){
        return mView;
    }

    protected AppRepository getDataSource(){
        return mAppDataRepository;
    }

    protected SchedulerProvider getSchedulerProvider(){
        return mSchedulerProvider;
    }

    protected CompositeDisposable getCompositeDisposable() {return mCompositeDisposable; }

}
