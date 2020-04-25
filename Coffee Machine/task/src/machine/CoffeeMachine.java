package machine;

import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        CoffeeMachineOperations user = new CoffeeMachineOperations();
        runMachine(user);
    }
    public static void runMachine(CoffeeMachineOperations user){
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String make = scanner.nextLine();
        Operations action = Arrays.stream(Operations.values())
                .filter(name -> name.getOperation().equals(make))
                .findFirst().orElse(Operations.EXIT);
        switch (action){
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, " +
                        "3 - cappuccino, back - to main menu:");
                user.buy(scanner.nextLine());
                runMachine(user);
                break;
            case FILL:
                System.out.println("Write how many ml of water do you want to add:");
                int water = scanner.nextInt();
                System.out.println("Write how many ml of milk do you want to add:");
                int milk = scanner.nextInt();
                System.out.println("Write how many grams of coffee beans do you want to add:");
                int beans = scanner.nextInt();
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                int disCups = scanner.nextInt();
                scanner.nextLine();
                user.fill(water, milk, beans, disCups);
                runMachine(user);
                break;
            case TAKE:
                user.take();
                runMachine(user);
                break;
            case REMAINING:
                user.remaining();
                runMachine(user);
                break;
            case EXIT:
                break;
            default:
                break;
        }
    }
}

enum Operations {
    BUY("buy"),
    FILL("fill"),
    TAKE("take"),
    REMAINING("remaining"),
    EXIT("exit");

    private final String operation;

    Operations(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
class CoffeeMachineOperations {
    int water;
    int milk;
    int beans;
    int disCups;
    int money;

    public CoffeeMachineOperations(){
        water = 400;
        milk = 540;
        beans = 120;
        disCups = 9;
        money = 550;
    }
    public void buy(String coffee) {
        switch (coffee){
            case "1":
                if (water >= 250 && beans >= 16) {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= 250;
                    beans -= 16;
                    money += 4;
                    disCups -= 1;
                } else {
                    if (water<250) {
                        System.out.println("Sorry, not enough water!");
                    } else {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                }
                break;
            case "2":
                if (water >= 350 && milk >= 75 && beans >= 20) {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    money += 7;
                    disCups -= 1;
                } else {
                    if (water < 350) {
                        System.out.println("Sorry, not enough water!");
                    } else if (milk < 75) {
                        System.out.println("Sorry, not enough milk!");
                    } else {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                }
                break;
            case "3":
                if (water >= 200 && milk >= 100 && beans >= 12) {
                    System.out.println("I have enough resources, making you a coffee!");
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    money += 6;
                    disCups -= 1;
                } else {
                    if (water < 200) {
                        System.out.println("Sorry, not enough water!");
                    } else if (milk < 100) {
                        System.out.println("Sorry, not enough milk!");
                    } else {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                }
                break;
            case "back":
                break;
        }
    }
    public void fill (int water, int milk, int beans, int disCups){
        this.water += water;
        this.milk += milk;
        this.beans += beans;
        this.disCups += disCups;
    }
    public void take() {
        System.out.println("I gave you $"+ this.money);
        this.money = 0;
    }
    public void remaining() {
        System.out.println("The coffee machine has:\n" +
                +this.water+" of water\n" +
                +this.milk+" of milk\n" +
                +this.beans+" of coffee beans\n" +
                +this.disCups+" of disposable cups\n" +
                "$"+this.money+" of money");
    }
}