public class Degree {

    char degreeUnicode = '\u0039'; // Unicode
    char degreeText = '9';
    char charInt = 65; // HTML-code
    char charHex = 0x0041;
    char charBinary = 0b0100_0001;
    char charOctal = 0101;
    

    void compute() {
        System.out.println("Degree Unicode: " + degreeUnicode);
        System.out.println("Degree Text: " + degreeText);
        System.out.println("Char Int: " + charInt);
        System.out.println("Char Hex: " + charHex);
        System.out.println("Char Binary: " + charBinary);
        System.out.println("Char Octal: " + charOctal);
    }

    public static void main(String[] args) {
        Degree d = new Degree();
        d.compute();
    }
}
