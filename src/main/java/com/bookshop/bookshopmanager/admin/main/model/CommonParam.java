package com.bookshop.bookshopmanager.admin.main.model;

public interface CommonParam {


  long getPageStart();
  long getPageEnd();

  void init();

  String getQueryString();
}
