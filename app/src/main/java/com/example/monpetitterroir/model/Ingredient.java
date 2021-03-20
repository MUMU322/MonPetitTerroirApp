package com.example.monpetitterroir.model;




public class Ingredient {


    /**
     * le nom de l'ingredient
     */
    private  String name;

    /**
     * l'url de l'image associe
     */
   private String image;



    //private String amount;


    public Ingredient(String amount,String name, String image ) {
        this.name = name;
        this.image = image;
       //this.amount = "";
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return "https://spoonacular.com/cdn/ingredients_100x100/"+image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Ingredient{" +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }




}
