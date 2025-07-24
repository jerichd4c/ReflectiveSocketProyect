package testclasses;

public class TextModifier { // test class No. 2
    public String invertText(String text) {return new StringBuilder(text).reverse().toString();} // class no. 2 method no. 1
    public String editUpper(String text) {return text.toUpperCase();} // class no. 2 method no. 2
    public String editLower(String text) {return text.toLowerCase();} // class no. 2 method no. 3
}