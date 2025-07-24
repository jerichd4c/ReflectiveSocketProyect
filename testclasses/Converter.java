package testclasses;

public class Converter { // test class no. 3
    public double celsiusToFahrenheit(double celsius) { // class no. 3 method no. 1
        return (celsius * 9/5) + 32;
    }
    public double fahrenheitToCelsius(double fahrenheit) { // class no. 3 method no. 2
        return (fahrenheit - 32) * 5/9;
    }
    public double celsiusToKelvin(double celsius) { // class no. 3 method no. 3
        return celsius + 273.15;
    }
}
