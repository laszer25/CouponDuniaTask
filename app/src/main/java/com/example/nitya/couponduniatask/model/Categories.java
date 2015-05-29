package com.example.nitya.couponduniatask.model;

/**
 * Created by Nitya on 23-May-15.
 */
public class Categories {

    public String getOfflineCategoryID() {
        return OfflineCategoryID;
    }

    public void setOfflineCategoryID(String offlineCategoryID) {
        OfflineCategoryID = offlineCategoryID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getParentCategoryID() {
        return ParentCategoryID;
    }

    public void setParentCategoryID(String parentCategoryID) {
        ParentCategoryID = parentCategoryID;
    }

    public String getCategoryType() {
        return CategoryType;
    }

    public void setCategoryType(String categoryType) {
        CategoryType = categoryType;
    }

    private String OfflineCategoryID;
    private String Name;
    private String ParentCategoryID;
    private String CategoryType;

}
