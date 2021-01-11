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
/*
    public Amount getAmount() {
        return amount;
    }

    private Amount amount;
*/
/*
    public Ingredient(String name, String image, Amount amount) {
        this.name = name;
        this.image = image;
        this.amount = amount;
    }
*/

    public Ingredient(String name, String image) {
        this.name = name;
        this.image = image;

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
/*
    public String getQuantite(){
        return " "+this.amount.metric.getValue()+this.amount.metric.getUnit();
    }
*/

/*
    public class Amount{

        public Metric getMetric() {
            return metric;
        }

        Metric metric;

        public Amount(Metric metric) {
            this.metric = metric;
        }

      /*  public class Metric{
            private String unit;

            private double value;

            public Metric(String unit, double value) {
                this.unit = unit;
                this.value = value;
            }

            public String getUnit() {
                return unit;
            }

            public double getValue() {
                return value;
            }
        }
*/

  //  }
}
