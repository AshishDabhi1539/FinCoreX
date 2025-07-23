package com.tss.test;

import java.util.Scanner;
import com.tss.facade.FoodAppFacade;

public class FoodAppTest {
    public static void main(String[] args) {
        System.out.println("========= Welcome to Food Ordering Console App =========");
        Scanner scanner = new Scanner(System.in);
        FoodAppFacade facade = new FoodAppFacade(scanner);
        facade.showMainMenu();
    }
}
