class Converter { // test class No. 3
    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9/5) + 32;
    }
    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5/9;
    }
    public double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }
}
