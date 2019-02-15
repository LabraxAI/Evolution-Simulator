package main;

import sas.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class App {
    View view;
    private Energy[] energies;
    private Child[]  children;
    public App() {
        view = new View(1000, 1000, "Evolution");
        view.setBackgroundColor(new Color(7, 6, 30));
    }
    public App(int numberOfEnergies, int numberOfChildren) {
        view = new View(1000, 1000, "Evolution");
        view.setBackgroundColor(new Color(7, 6, 30));
        energies = new Energy[numberOfEnergies];
        for (int i = 0; i < numberOfEnergies; i++) {
            energies[i] = new Energy(view);
        }
        children = new Child[numberOfChildren];
        for (int i = 0; i < numberOfChildren; i++) {
            children[i] = new Child(view);
        }
    }
    public static void main(String[] args) {
        new App(10, 200);
    }
    public static float constrain(float x, float min, float max) {
        if (x > max) {
            return max;
        }
        if (x < min) { 
            return min;
        }
        return x;
    }
    public static float randomGauss(float mean, float stddev) {
        return (float)(ThreadLocalRandom.current().nextGaussian() * stddev + mean);
    }
    public static float randomGauss(float min, float max, float mean, float stddev) {
        return App.constrain(App.randomGauss(mean, stddev), min, max);
    }
    public static float randomGauss(float min, float max, float stddev) {
        return App.randomGauss(min, max, min * 0.5f + max * 0.5f, stddev);
    }
}

class Energy {
    private float strength;
    Rectangle rect;
    public Energy(View view) {
        strength = App.constrain(((float)ThreadLocalRandom.current().nextGaussian() * 5) + 10, 3, 40);
        rect = new Rectangle(ThreadLocalRandom.current().nextInt(0, view.getWidth() - (int)strength),
                             ThreadLocalRandom.current().nextInt(0, view.getHeight() - (int)strength),
                             strength, strength, new Color(48, 142, 120));
    }
}

class Child {
    Circle circle;
    float  speed;
    public Child(View view) {
        circle = new Circle(ThreadLocalRandom.current().nextInt(0, view.getWidth() - 10),
                            ThreadLocalRandom.current().nextInt(0, view.getHeight() - 10),
                            5, new Color(104, 58, 60));
        speed  = ThreadLocalRandom.current().nextFloat() * 20;
        new NeuralNetwork(2, 5, 3, 1, 4, 4, 4, 4, 4, 4);
    }

}

class NeuralNetwork {
    ArrayList<float[][]> weights;
    public static int maxLayers = 10;

    public NeuralNetwork(int minNumOfLayers, int maxNumOfLayers, int meanNumOfLayers, float stdDevNumOfLayers, int minNumOfNeurons, int maxNumOfNeurons, int meanNumOfNeurons, int stdDevNumOfNeurons, int numOfInputs, int numOfOutputs) {
        int numOfLayers = (int)App.randomGauss((float)minNumOfLayers, (float)maxNumOfLayers, stdDevNumOfLayers);
        int x = numOfInputs;
        for (int i = 0; i < numOfLayers; i++) {
            int y;
            if (i == (numOfLayers - 1)) {
                y = numOfOutputs;
            } else {
                y = (int)App.randomGauss(minNumOfNeurons, maxNumOfNeurons, stdDevNumOfNeurons);
            }
            float[][] layer = new float[y][x];
            for (int j = 0; j < y; j++) {
                for (int k = 0; k < x; k++) {
                    layer[j][k] = App.randomGauss(0, 1);
                }
            }
            weights.add(layer);
        }
    }
    public ArrayList<float[][]> getWeights() {
        return weights;
    }
    public NeuralNetwork(NeuralNetwork a, NeuralNetwork b) {
        ArrayList<float[][]> preferredWeights;
        ArrayList<float[][]> secondaryWeights;
        if (ThreadLocalRandom.current().nextBoolean()) {
            preferredWeights = a.getWeights();
            secondaryWeights = b.getWeights();
        } else {
            preferredWeights = b.getWeights();
            secondaryWeights = a.getWeights();
        }
        int min, max;
        if (preferredWeights < 4) {
            min = 3;
        } else
        int numOfLayers = App.randomGauss(min, max, mean, stddev)
    }
    public float[] step(float[] inputs) {
        float[] x = inputs;
        float[] y;
        for (int i = 0; i < weights.size(); i++) {
            float[][] layer = weights.get(i);
            y = new float[layer.length];
            for (int j = 0; j < layer.length; j++) {
                for (int k = 0; k < layer[j].length; k++) {
                    y[j] += x[k] * layer[j][k];
                }
                if (i == (weights.size() - 1)) {
                    y[j] = 1 / ( 1 + (float)Math.exp(-y[j]));
                } else if (y[j] < 0) {
                    y[j] = 0;
                }
            }
            x = y;
        }
        return x;
    }
}