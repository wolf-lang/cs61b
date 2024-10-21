package com.rll.cs61b;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NBody {


    static double readRadius(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        in.close();
        return radius;
    }

    static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String planetsFile = args[2];
        double r = readRadius(planetsFile);
        double t = 0;
        Planet[] planets = readPlanets(planetsFile);
        int num = planets.length;

        StdDraw.setXscale(-r, r);
        StdDraw.setYscale(-r, r);
        StdDraw.enableDoubleBuffering();

        for (Planet planet : planets) {
            System.out.println(planet);
        }

        while (t <= T) {
            double[] xForces = new double[num];
            double[] yForces = new double[num];

            for (int i = 0; i < num; i++) {
                int index = i;
                xForces[i] = planets[i].calcNetForceExertedByX(Arrays.stream(planets).filter(planet -> !planet.equals(planets[index])).toArray(Planet[]::new));
                yForces[i] = planets[i].calcNetForceExertedByY(Arrays.stream(planets).filter(planet -> !planet.equals(planets[index])).toArray(Planet[]::new));
            }

            System.out.println(Arrays.toString(xForces));

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (int i = 0; i < num; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            for (Planet planet : planets) {
                System.out.println(planet);
            }

            for (Planet planet :planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
    }
}
