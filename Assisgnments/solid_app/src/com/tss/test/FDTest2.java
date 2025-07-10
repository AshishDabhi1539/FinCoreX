//package com.tss.test;
//
//
//import java.util.Scanner;
//
//import com.tss.model.DiwaliInterest;
//import com.tss.model.FestivalInterest;
//import com.tss.model.FixedDeposit2;
//import com.tss.model.HoliInterest;
//import com.tss.model.NewYearInterest;
//import com.tss.model.NormalInterest;
//
//public class FDTest2 {
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        System.out.print("Enter Account Number: ");
//        int accNo = sc.nextInt();
//        sc.nextLine();
//
//        System.out.print("Enter Name: ");
//        String name = sc.nextLine();
//
//        System.out.print("Enter Principal Amount: ");
//        double principal = sc.nextDouble();
//
//        System.out.print("Enter Duration (in years): ");
//        int duration = sc.nextInt();
//        sc.nextLine();
//
//        System.out.print("Enter Festival (NEWYEAR / DIWALI / HOLI / OTHERS): ");
//        String festInput = sc.nextLine().toUpperCase();
//
//        FestivalInterest interestStrategy;
//
//        switch (festInput) {
//            case "NEWYEAR":
//                interestStrategy = new NewYearInterest();
//                break;
//            case "DIWALI":
//                interestStrategy = new DiwaliInterest();
//                break;
//            case "HOLI":
//                interestStrategy = new HoliInterest();
//                break;
//            default:
//                interestStrategy = new NormalInterest();
//        }
//
//        FixedDeposit2 fd = new FixedDeposit2(accNo, principal, interestStrategy);
//        double interest = fd.calculateSimpleInterest();
//
//        System.out.println("\nSimple Interest = " + interest);
//        sc.close();
//    }
//}
