package com.example.java_task.Model;

public class Data
{
    private String constructionYear;

    private String imageUrl;

    private String id;

    private String brand;

    private String isUsed;

    public String getConstructionYear ()
    {
        return constructionYear;
    }

    public void setConstructionYear (String constructionYear)
    {
        this.constructionYear = constructionYear;
    }

    public String getImageUrl ()
    {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getBrand ()
    {
        return brand;
    }

    public void setBrand (String brand)
    {
        this.brand = brand;
    }

    public String getIsUsed ()
    {
        return isUsed;
    }

    public void setIsUsed (String isUsed)
    {
        this.isUsed = isUsed;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [constructionYear = "+constructionYear+", imageUrl = "+imageUrl+", id = "+id+", brand = "+brand+", isUsed = "+isUsed+"]";
    }
}