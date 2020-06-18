package com.dkhromova;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.next();
            coffeeMachine.handleInput(input);
        }
    }
}
