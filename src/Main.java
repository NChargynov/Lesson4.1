import java.util.Random;

public class Main {


    public static int[] health = {700, 250, 250, 250, 250};
    public static int[] hits = {50, 20, 20, 20, 15};
    public static String[] hitTypes = {"Physical", "Physical", "Magical", "Mental", "Doctor"};

    public static void main(String[] args) {
        System.out.println("The Game");

        printStatistics();

        while (!isFinished()) {
            changeBossDefence();
            round();
            printStatistics();
        }

    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomNumber = r.nextInt(3) + 1;
        hitTypes[0] = hitTypes[randomNumber];
    }

    public static void round() {
        for (int i = 1; i <= 4; i++) {
            health[i] = bossHit(i);
        }
        for (int i = 1; i <= 3; i++) {
            int healthRemain = playerHit(i);
            if (healthRemain < 0) {
                health[0] = 0;
            } else {
                health[0] = healthRemain;
            }
        }
        for (int i = 1; i <= 3; i++){
            if (health[4] > 0) {
                doctorTreat(i);
            }
        }
    }

    public static int playerHit(int playerIndex) {
        if (hitTypes[0].equals(hitTypes[playerIndex])) {
            Random r = new Random();
            int randomNumber = r.nextInt(7) + 2;
            System.out.println(hitTypes[playerIndex] + " critically hits Boss " + hits[playerIndex] * randomNumber);
            return health[0] - hits[playerIndex] * randomNumber;
        } else {
            return health[0] - hits[playerIndex];
        }
    }

    public static int bossHit(int playerIndex) {
        return health[playerIndex] - hits[0];

    }

    public static int doctorTreat(int playerIndex){
        System.out.println("Doctor treats " + hitTypes[playerIndex] + ' ' + hits[4]);
        return health[playerIndex] + hits[4];

    }

    public static boolean isFinished() {
        if (health[0] <= 0) {
            System.out.println("Heroes won the game!!!");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0) {
            System.out.println("Boss won the game!!!");
            return true;

        }
        return false;
    }

    public static void printStatistics() {
        System.out.println("_________________________________");
        System.out.println("Boss health = " + health[0]);
        System.out.println("Boss defence = " + hitTypes[0]);
        System.out.println("Warrior health = " + health[1]);
        System.out.println("Magic health = " + health[2]);
        System.out.println("Kinetic health = " + health[3]);
        System.out.println("Doctor health = " + health[4]);
        System.out.println("_________________________________");
    }

}
