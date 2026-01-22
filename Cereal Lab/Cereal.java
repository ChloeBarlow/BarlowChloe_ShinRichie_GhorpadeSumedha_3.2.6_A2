import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cereal {
    
    // Instance Variables
    private String brand;
    private double carbohydrates;
    private double protein;
    private double fats;
    private double vitamins;
    
    // Default constructor
    public Cereal(){
        carbohydrates = 0;
        protein = 0;
        fats = 0;
        vitamins = 0;
    }

    public Cereal(String name, double carbs, double protein, double fats, double vitamins){
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

    public double getCarbs(){
        return carbohydrates;
    }

    public double getProtein(){
        return protein;
    }

    public double getFats(){
        return fats;
    }

    public double getVitamins(){
        return vitamins;
    }

    /* Methods */

    /**
     * method to return string representation of cereal object
     */
    public String toString(){
        return (brand + " nutrients:\nCarbs - " + carbohydrates + "\nProtein - " + protein + "\nFats - " + fats + "\nVitamins - " + vitamins);
    }


    public static void main(String[] args) {
        Cereal[] cerealList = new Cereal[77];

        // reading data from CSV file and putting it into cereal object list
        String csvFile = "Cereal.csv";
        String line = "";
        int index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                // parse necessary nutrient values
                double carbs = Double.parseDouble(values[7]);
                double protein = Double.parseDouble(values[3]);
                double fats = Double.parseDouble(values[4]);
                double vitamins = Double.parseDouble(values[10]);

                Cereal cereal = new Cereal(name, carbs, protein, fats, vitamins);
                cerealList[index] = cereal; // add cereal object to the list
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // calculate totals of each nutrient (for calculating average later)
        double totalCarbs = 0;
        double totalProtein = 0;
        double totalFats = 0;
        double totalVitamins = 0;
        for (Cereal cereal :cerealList){
            totalCarbs += cereal.getCarbs();
            totalProtein += cereal.getProtein();
            totalFats += cereal.getFats();
            totalVitamins += cereal.getVitamins();
        }
        
        // calculate nutrition average
        double avgNutrition = (totalCarbs + totalProtein + totalFats + totalVitamins) / cerealList.length;
        System.out.println("Average Nutrition Value across all cereals: " + avgNutrition);

        // calculate standard deviation of all cereal
        double totalNutrients = 0;
        double nutritionDiff = 0;
        double sumNutritionDiff = 0;
        for (Cereal cereal : cerealList){
            totalNutrients = cereal.getCarbs() + cereal.getProtein() + cereal.getFats() + cereal.getVitamins();
            nutritionDiff = totalNutrients - avgNutrition;
            nutritionDiff = nutritionDiff * nutritionDiff; // squaring the difference
            sumNutritionDiff += nutritionDiff;
        }
        sumNutritionDiff = sumNutritionDiff / cerealList.length;
        double nutritionStdDev = Math.sqrt(sumNutritionDiff);
        System.out.print("Nutrition Standard Deviation across all cereals: " + nutritionStdDev);

        // calculate the nutrition value with the highest value
        double highestNutritionValue = 0;
        String highestNutritionCereal = "";
        for (Cereal cereal : cerealList){
            totalNutrients = cereal.getCarbs() + cereal.getProtein() + cereal.getFats() + cereal.getVitamins();
            if (totalNutrients > highestNutritionValue){
                highestNutritionCereal = cereal.getBrand();
                highestNutritionValue = totalNutrients;
            }
        }
        System.out.print("\nThe cereal with the highest nutrition value is [" + highestNutritionCereal + "] with a nutrition value of: " + highestNutritionValue);

        // calculate distance between highest nutrition value and average nutrition value
        double distanceFromAvg = highestNutritionValue - avgNutrition;

        // find number of standard deviations the highest nutrition value is from the average nutrition value
        double numStdDevs = distanceFromAvg / nutritionStdDev;
        System.out.print("\nThe highest nutrition value is [" + numStdDevs + "] standard deviations above the average nutrition value.");
    }
}   
