public class PasswordChecker {
    public static String strength(String pw) {
        int count = 0;

        boolean length = pw.length() >= 8;
        boolean upper = pw.matches(".*[A-Z].*");
        boolean digit = pw.matches(".*[0-9].*");
        boolean special = pw.matches(".*[^a-zA-Z0-9].*");

        if (length) count++;
        if (upper) count++;
        if (digit) count++;
        if (special) count++;

        if (count <= 1) return "Weak";
        if (count <= 3) return "Medium";
        return "Strong";
    }

    public static void main(String[] args) {
        String[] passwords = {"abc", "Abcd1234!"};

        for (String p : passwords) {
            System.out.println(p + " -> " + strength(p));
        }
    }
}
