package com.shankha.newsnow;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse>{
    void OnfetchData(List<NewsHeadlines> list, String message);
    void onError(String message);

}
