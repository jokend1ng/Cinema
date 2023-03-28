import java.util.Scanner;
public class Cinema {
    final static Scanner scanner = new Scanner(System.in);
    static String[][] Cinema;
    static int row;
    static int seat;
    static int current=0;
    static int total;
    static double percentage=0.00;
    static int purchased=0;



    public static void main(String[] args) {
        // Write your code here
        // First Step
        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seat = scanner.nextInt();
        Cinema = new String[row + 1][seat + 1];
        setCinema(row, seat);
        total(row,seat);
        while (true) {
            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");
            int cases = scanner.nextInt();
            if (cases == 0) {
                break;
            }
            switchMode(cases);
        }
    }

    public static void SmallCinema() {
        purchased+=1;
        current+=10;
        percentage+= 100.0/(row*seat);
        System.out.println("$" + 10);
    }

    public static void LargeCinema(int row, int seatRow) {
        int mid = row / 2;
        percentage+=100.0/(row*seat);
        purchased+=1;
        current+=(seatRow <= mid) ? 10 : 8;
        System.out.println("$" + ((seatRow <= mid) ? 10 : 8));
    }

    public static void setCinema(int row, int column) {
        for (int i = 0; i <= row; ++i) {
            if (i != 0) {
                Cinema[i][0] = Integer.toString(i);
            }
            for (int j = 0; j <= column; ++j) {
                if (i == 0 && j == 0) {
                    Cinema[i][j] = " ";
                } else if (i == 0 && j > 0) {
                    Cinema[i][j] = Integer.toString(j);
                } else if (j > 0) {
                    Cinema[i][j] = "S";
                }
            }
        }
    }

    public static void displayCinema() {
        System.out.println("Cinema:");
        for (int i = 0; i <= row; ++i) {
            for (int j = 0; j <= seat; ++j) {
                System.out.print(Cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void switchMode(int cases) {
        switch (cases) {
            case 1 -> displayCinema();
            case 2 -> buyTickets();
            case 3 -> Statistics();
            default -> {
                if(cases >row){
                    System.out.println("Wrong input!");
                    System.out.println("Enter a row number:");
                    int seatRow = scanner.nextInt();
                }else{
                    System.out.println("Enter a seat number in that row:");
                    int seatRow=cases;
                    int seatNumber = scanner.nextInt();
                    if  (seatNumber>seat ){
                        System.out.println("Wrong input!");
                        System.out.println("Enter a seat number in that row:");
                        seatNumber = scanner.nextInt();
                    }else{
                        if (Cinema[seatRow][seatNumber].contains("B")) {
                            System.out.println("That ticket has already been purchased!");

                        }else if(cases >row||seatNumber>seat ){
                            System.out.println("Wrong input!");

                        } else {
                            System.out.print("Ticket price: ");
                            if (row * seat >= 60) {
                                LargeCinema(row, seatRow);
                            } else {
                                SmallCinema();
                            }
                            Cinema[seatRow][seatNumber] = "B";
                        }
                    }
                }
            }}}

    public static void buyTickets() {
        System.out.println("Enter a row number:");
        int seatRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();
        if (Cinema[seatRow][seatNumber].contains("B")) {
            System.out.println("That ticket has already been purchased!");

        }else if(seatRow >row||seatNumber>seat ){
            System.out.println("Wrong input!");

        } else {
            System.out.print("Ticket price: ");
            if (row * seat >= 60) {
                LargeCinema(row, seatRow);
            } else {
                SmallCinema();
            }
            Cinema[seatRow][seatNumber] = "B";
        }
    }
    public static void total(int row,int seatRow){
        if (row * seat < 60){
            total=row*seat*10;
        }else{
            total = (row/2)*10*seat+((row-row/2)*8*seat);
        }

    }
    public static void Statistics(){
        System.out.println();
        System.out.printf("Number of purchased tickets: %d%n",purchased);

        System.out.printf("Percentage: %.2f",percentage);
        System.out.println("%");

        System.out.printf("Current income: $%d%n",current);
        System.out.printf("Total income: $%d%n",total);
        System.out.println();
    }

}