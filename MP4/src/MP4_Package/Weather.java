package MP4_Package;

import java.util.Random;

public class Weather {
    private int weatherConditionCounter;
    private String[] weatherConditions = {"Sunny", "Rainy", "Snowy"};
    public String currentWeather; 
    
    public Weather() {
        this.weatherConditionCounter = 0;
        this.currentWeather = pickRandomWeather(); 
    }

    // Method to pick a random weather condition
    public String pickRandomWeather() {
        Random rand = new Random();
        int randomValue = rand.nextInt(3); 
        currentWeather = weatherConditions[randomValue];

        // Update Counter based on the selected weather condition
        switch (randomValue) {
            case 0: // Sunny
                weatherConditionCounter--;
                break;
            case 1: // Rainy
            case 2: // Snowy
                weatherConditionCounter += 2;
                break;
        }
        return currentWeather;
    }

    // Getter
    public int getWeatherConditionCounter() {
        return weatherConditionCounter;
    }

    // Setter
    public void setWeatherConditionCounter(int counter) {
        this.weatherConditionCounter = counter;
    }
}