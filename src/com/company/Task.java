package com.company;

import java.util.*;

public class Task {
    public void run(){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(input);
        List<Character> sym = prepareInput(input);
        displayStatus(sym);
        countPoints(sym);
    }

    private void countPoints(List<Character> sym) {
        Map<Character, Integer> balance = new HashMap<>();
        balance.put('S', 0);
        balance.put('H', 0);
        balance.put('D', 0);
        balance.put('C', 0);
        Character previousSymbol = 'S';
        int sum = 0;
        for (int i = 0; i < sym.size(); i++) {
            Character current = previousSymbol;
            int points = 0;
            switch(sym.get(i)){
                case 'A' -> points += 4;
                case 'K' -> points += 3;
                case 'Q' -> points += 2;
                case 'J' -> points += 1;
                case 'S' -> current = 'S';
                case 'H' -> current = 'H';
                case 'D' -> current = 'D';
                case 'C' -> current = 'C';
            }
            if (sym.get(i) != 'S' && sym.get(i) != 'H' && sym.get(i) != 'D' && sym.get(i) != 'C'){
                 sum = sum + 1;
            }
            if(current != previousSymbol || i == sym.size()-1){
                if (sum == 0) points += 3;
                else if(sum == 1) points += 2;
                else if(sum == 2) points += 1;
                sum = 0;
                previousSymbol = current;

            }
            int value = balance.get(current);
            balance.put(current,value + points);
        }
        int score = balance.values().stream().reduce(0, Integer::sum);
        System.out.println(balance);
        System.out.println("Wartosc reki: " + score);
    }

    private List<Character> prepareInput(String input) {
        List<Character> sym = new LinkedList<>();
        for (int i = 0; i < input.length(); i++){
            sym.add(input.charAt(i));
        }
        for (int i = 0; i < sym.size()-1; i++) {
            if (sym.get(i) == '1' && sym.get(i+1) == '0'){
                sym.set(i, 'X');
                sym.remove(i+1);
            }
        }
        return sym;
    }

    private void displayStatus(List<Character> sym) {
        for (int i = 0; i < sym.size(); i++) {
            if (sym.get(i) == 'S'){
                System.out.println("\nSekwencja dla pik:");
                continue;
            }
            if (sym.get(i) == 'H'){
                System.out.println("\nSekwencja dla kier:");
                continue;
            }
            if (sym.get(i) == 'D') {
                System.out.println("\nSekwencja dla karo:");
                continue;
            }
            if (sym.get(i) == 'C'){
                System.out.println("\nSekwencja dla trefl:");
                continue;
            }
            System.out.print(sym.get(i) == 'X' ? "10" : sym.get(i) + " ");
        }
        System.out.println("\n");
    }

}
