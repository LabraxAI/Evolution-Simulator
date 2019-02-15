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
    int numOfLayers;
    public NeuralNetwork(int minNumOfLayers, int maxNumOfLayers, int meanNumOfLayers, int stdDevNumOfLayers, int minNumOfNeurons, int maxNumOfNeurons, int meanNumOfNeurons, int stdDevNumOfNeurons, int numOfInputs, int numOfOutputs) {
        numOfLayers = (int)App.constrain((float)(ThreadLocalRandom.current().nextGaussian() * stdDevNumOfLayers + minNumOfLayers * 0.5 + maxNumOfLayers * 0.5), minNumOfLayers, maxNumOfLayers);
        for (int i = 0; i < numOfLayers; i++) {

        }
    }
}