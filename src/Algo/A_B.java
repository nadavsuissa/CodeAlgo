package Algo;

public class A_B {
    public static int CoinFlips(){
        long result = 0;
        result = Math.round( Math.random());
        return (int)result;
    }
    public static int AliceGame(){
        return CoinFlips();
    }
    public static int BobGame(){
        return CoinFlips();
    }
    public static boolean GameStrategy4(){
        boolean result = false;
        int AliceResult = AliceGame();
        int BobResult = BobGame();
        if ((AliceResult == BobResult) ||
                (BobResult == 1 - AliceResult))
            result = true;
        return result;
    }


    public static void main(String[] args) {
        System.out.println(GameStrategy4());
    }
}
