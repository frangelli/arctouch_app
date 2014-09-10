package com.leonardofrangelli.floriparoutes.activities;

import java.util.List;

/**
 * Created by frangelli on 9/8/14.
 */
public interface SearchableActivity<T> {
    String getSearchValue();
    void onReceiveListOfResults(List<T> result);
}
