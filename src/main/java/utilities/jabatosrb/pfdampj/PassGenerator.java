package jabatosrb.pfdampj;

import java.util.Random;

public class PassGenerator {

    public static final int NUMBERS = 1;
    public static final int NUMBERS_LOWER = 2;
    public static final int NUMBERS_LOWER_UPPER = 3;
    public static final int NUMBRES_LOWER_UPPER_SYMBOL = 4;

    private static Random r = new Random();
    private static final char[] SYMBOLS = {'!','#','$','%','&','*','+','-','?','<','>','=','_'};

    public static String generatePass(int len, int format){
        if (len < 8)
            return null;
        switch (format){
            case NUMBERS:
                return numberPass(len);
            case NUMBERS_LOWER:
                return numberLowerPass(len);
            case NUMBERS_LOWER_UPPER:
                return numberLowerUpperPass(len);
            case NUMBRES_LOWER_UPPER_SYMBOL:
                return numberLowerUpperSymbolPass(len);
            default:
                return null;
        }
    }

    private static String numberPass(int len){
        String pass = "";

        for (int i = 0; i < len; i++) {
            pass += r.nextInt(10);
        }

        return pass;
    }

    private static String numberLowerPass(int len){
        String pass = "";

        for (int i = 0; i < len; i++) {
            if(i < len-2){
                int sel = r.nextInt(2);
                switch (sel){
                    case 0:
                        pass += r.nextInt(10);
                        break;
                    case 1:
                        pass += (char)r.nextInt(97, 123);
                        break;
                }
            } else if (i < len-1)
                pass += r.nextInt(10);
            else
                pass += (char)r.nextInt(97, 123);
        }

        return pass;
    }

    private static String numberLowerUpperPass(int len){
        String pass = "";

        for (int i = 0; i < len; i++) {
            if(i < len-3){
                int sel = r.nextInt(3);
                switch (sel){
                    case 0:
                        pass += r.nextInt(10);
                        break;
                    case 1:
                        pass += (char)r.nextInt(97, 123);
                        break;
                    case 2:
                        pass += (char)r.nextInt(65, 91);
                        break;
                }
            } else if (i < len-2)
                pass += r.nextInt(10);
            else if (i < len-1)
                pass += (char)r.nextInt(97, 123);
            else
                pass += (char)r.nextInt(65, 91);

        }

        return pass;
    }

    private static String numberLowerUpperSymbolPass(int len){
        String pass = "";

        for (int i = 0; i < len; i++) {
            if(i < len-4){
                int sel = r.nextInt(4);
                switch (sel){
                    case 0:
                        pass += r.nextInt(10);
                        break;
                    case 1:
                        pass += (char)r.nextInt(97, 123);
                        break;
                    case 2:
                        pass += (char)r.nextInt(65, 91);
                        break;
                    case 3:
                        pass += SYMBOLS[r.nextInt(SYMBOLS.length)];
                        break;
                }
            } else if (i < len-3)
                pass += r.nextInt(10);
            else if (i < len-2)
                pass += (char)r.nextInt(97, 123);
            else if (i < len-1)
                pass += (char)r.nextInt(65, 91);
            else
                pass += SYMBOLS[r.nextInt(SYMBOLS.length)];
        }

        return pass;
    }

}
