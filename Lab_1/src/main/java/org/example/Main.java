package org.example;

import org.example.Part1.MyCos;
import org.example.Part2.BinomialHeap;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    }

    public static void runPart1() {
        double num;
        Scanner sc = new Scanner(System.in);
        num = sc.nextDouble();

        System.out.println(MyCos.cos(num));
        System.out.println(Math.cos(num));
    }

    public static void runPart2() {
        BinomialHeap binHeap = new BinomialHeap();

        binHeap.insert(12);
        binHeap.insert(8);
        binHeap.insert(5);
        binHeap.insert(15);
        binHeap.insert(7);
        binHeap.insert(2);
        binHeap.insert(9);

        System.out.println(binHeap.findMinimum());
        binHeap.decreaseKeyValue(12, 10);
        System.out.println(binHeap.extractMin());
    }

    public static void runPart3() {

    }
}