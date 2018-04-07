package com.perceptron;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by nikita on 05.04.2018.
 */
public class PerceptronService {
    private static final int INPUTS_COUNT = 15;
    int numbers[][] = {
            {1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1},
            {0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}
    };

    int testFiveNumber[][] = {
            {1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1},
            {1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1, 1}
    };

    int weights[] = new int[INPUTS_COUNT];

    int bias = 7;

    private boolean proceed(int[] number) {
        int net = 0;
        for (int i = 0; i < INPUTS_COUNT; i++) {
            net += number[i] * weights[i];
        }
        return net > bias;
    }

    private void decrease(int[] number) {
        for (int i = 0; i < INPUTS_COUNT; i++) {
            if (number[i] == 1) {
                weights[i] -= 1;
            }
        }
    }

    private void increase(int[] number) {
        for (int i = 0; i < INPUTS_COUNT; i++) {
            if (number[i] == 1) {
                weights[i] += 1;
            }
        }
    }

    private void training() {
        for (int i = 0; i < 10000; i++) {
            int random = ThreadLocalRandom.current().nextInt(0, 9);

            if (random != 5) {
                if (proceed(numbers[random])) {
                    decrease(numbers[random]);
                }
            } else {
                if (!proceed(numbers[5])) {
                    increase(numbers[5]);
                }
            }
        }
    }

    public void run() {
        training();
        System.out.println("Weights");
        for (int weight : weights) {
            System.out.print(weight + " ");
        }

        System.out.println("Numbers");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(i + " - " + proceed(numbers[i]));
        }

        System.out.println("Tests");
        for (int i = 0; i < testFiveNumber.length; i++) {
            System.out.println(i + " - " + proceed(testFiveNumber[i]));
        }
    }
}
