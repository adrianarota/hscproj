package com.example.adriana.pocketcloset;

public class Item {
   // public int ID;
    public int pictureID;
    public String Itemtype;
    public String Itemlocation;
    public String ItemDescription;

    public Item(int pictureID, String Itemtype, String Itemlocation, String ItemDescription)
    {
  //      this.ID=  ID;
        this.pictureID= pictureID;
        this.Itemtype = Itemtype;
        this.Itemlocation= Itemlocation;
        this.ItemDescription= ItemDescription;
    }
}
