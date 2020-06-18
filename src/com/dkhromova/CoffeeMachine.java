package com.dkhromova;

public class CoffeeMachine {

    private CoffeeMachineState currentState;

    private int availableWater = 400;
    private int availableMilk = 540;
    private int availableCoffeeBeans = 120;
    private int availableDisposableCups = 9;
    private int money = 550;

    public CoffeeMachine() {
        mainMenu();
    }

    public void handleInput(String input) {
        if (currentState == CoffeeMachineState.MAIN_MENU) {
            switch (input) {
                case "buy":
                    buy();
                    break;
                case "fill":
                    fillWater();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    printCoffeeMachineState();
                    break;
                case "exit":
                    System.exit(0);
            }
        } else if (currentState == CoffeeMachineState.BUY) {
            switch (input) {
                case "1":
                    espresso();
                    break;
                case "2":
                    latte();
                    break;
                case "3":
                    cappuccino();
                    break;
                case "back":
                    mainMenu();
                    break;
            }
        } else if (currentState == CoffeeMachineState.FILL_WATER) {
            int addWater = Integer.parseInt(input);
            availableWater += addWater;
            fillMilk();
        } else if (currentState == CoffeeMachineState.FILL_MILK) {
            int addMilk = Integer.parseInt(input);
            availableMilk += addMilk;
            fillCoffeeBeans();
        } else if (currentState == CoffeeMachineState.FILL_COFFEE_BEANS) {
            int addCoffeeBeans = Integer.parseInt(input);
            availableCoffeeBeans += addCoffeeBeans;
            fillCups();
        } else if (currentState == CoffeeMachineState.FILL_CUPS) {
            int addDisposableCups = Integer.parseInt(input);
            availableDisposableCups += addDisposableCups;
            mainMenu();
        } else if (currentState == CoffeeMachineState.TAKE) {
            take();
        } else if (currentState == CoffeeMachineState.PRINT_COFFEE_MACHINE_STATE) {
            printCoffeeMachineState();
        }
    }

    private void fillWater() {
        currentState = CoffeeMachineState.FILL_WATER;
        System.out.println("Write how many ml of water do you want to add:");
    }

    private void fillMilk() {
        currentState = CoffeeMachineState.FILL_MILK;
        System.out.println("Write how many ml of milk do you want to add:");
    }

    private void fillCoffeeBeans() {
        currentState = CoffeeMachineState.FILL_COFFEE_BEANS;
        System.out.println("Write how many grams of coffee beans do you want to add:");
    }

    private void fillCups() {
        currentState = CoffeeMachineState.FILL_CUPS;
        System.out.println("Write how many disposable cups of coffee do you want to add:");
    }

    private void take() {
        currentState = CoffeeMachineState.TAKE;
        System.out.println("I give you $" + money);
        money = 0;
        mainMenu();
    }

    private void printCoffeeMachineState() {
        currentState = CoffeeMachineState.PRINT_COFFEE_MACHINE_STATE;
        System.out.println("The coffee machine has:");
        System.out.println(availableWater + " of water");
        System.out.println(availableMilk + " of milk");
        System.out.println(availableCoffeeBeans + " of coffee beans");
        System.out.println(availableDisposableCups + " of disposable cups");
        System.out.println(money + " of money");
        mainMenu();
    }


    private void buy() {
        currentState = CoffeeMachineState.BUY;
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
    }

    private void espresso() {
        if (canMakeCoffee(250, 0, 16)) {
            availableWater -= 250;
            availableCoffeeBeans -= 16;
            availableDisposableCups--;
            money += 4;
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            printNoCoffeeReason(250, 0, 16);
        }
        mainMenu();
    }

    private void latte() {
        if (canMakeCoffee(350, 75, 20)) {
            availableWater -= 350;
            availableMilk -= 75;
            availableCoffeeBeans -= 20;
            availableDisposableCups--;
            money += 7;
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            printNoCoffeeReason(350, 75, 20);
        }
        mainMenu();
    }

    private void cappuccino() {
        if (canMakeCoffee(200, 100, 12)) {
            availableWater -= 200;
            availableMilk -= 100;
            availableCoffeeBeans -= 12;
            availableDisposableCups--;
            money += 6;
            System.out.println("I have enough resources, making you a coffee!");
        } else {
            printNoCoffeeReason(200, 100, 12);
        }
        mainMenu();
    }

    private boolean canMakeCoffee(int requiredWater, int requiredMilk, int requiredCoffeeBeans) {
        return availableWater >= requiredWater
                && availableMilk >= requiredMilk
                && availableCoffeeBeans >= requiredCoffeeBeans
                && availableDisposableCups >= 1;
    }

    private void printNoCoffeeReason(int requiredWater, int requiredMilk, int requiredCoffeeBeans) {
        if (availableWater < requiredWater) {
            System.out.println("Sorry, not enough water!");
        } else if (availableMilk < requiredMilk) {
            System.out.println("Sorry, not enough milk!");
        } else if (availableCoffeeBeans < requiredCoffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (availableDisposableCups >= 1) {
            System.out.println("Sorry, not enough disposable cups!");
        }
    }

    private void mainMenu() {
        currentState = CoffeeMachineState.MAIN_MENU;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }
}
