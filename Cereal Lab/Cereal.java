public class Cereal {
    
    // Instance Variables
    private String brand;
    private int carbohydrates;
    private int protein;
    private int fats;
    private int vitamins;
    
    // Default constructor
    public Cereal(){
        carbohydrates = 0;
        protein = 0;
        fats = 0;
        vitamins = 0;
    }

    public Cereal(String name, int carbs, int protein, int fats, int vitamins){
        this.brand = name;
        this.carbohydrates = carbs;
        this.protein = protein;
        this.fats = fats;
        this.vitamins = vitamins;
    }

    /* Accessors */

    public String getBrand(){
        return brand;
    }

    public int GetCarbs(){
        return carbohydrates;
    }

    public int GetProtein(){
        return protein;
    }

    public int GetFats(){
        return fats;
    }

    public int GetVitamins(){
        return vitamins;
    }

    /* Methods */
    public String toString(){
        return (brand + " nutrients:\n Carbs - " + carbohydrates + "\nProtein - " + protein + "\nFats - " + fats + "\nVitamins - " + vitamins);
    }
}   
