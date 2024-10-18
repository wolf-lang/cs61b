package com.rll.cs61b;

public class Planet {

    public static final double G = 6.67e-11;
    /**
     * x position
     */
    double xxPos;
    /**
     * y position
     */
    double yyPos;

    /**
     * x velocity
     */
    double xxVel;

    /**
     * y velocity
     */
    double yyVel;

    /**
     * mass
     */
    double mass;

    /**
     * image file name
     */
    String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    double calcDistance(Planet planet) {
        double dx = this.xxPos - planet.xxPos;
        double dy = this.yyPos - planet.yyPos;

        double r2 = dx * dx + dy * dy;
        return Math.sqrt(r2);
    }

    double clcDistanceR2 (Planet planet) {
        double dx = this.xxPos - planet.xxPos;
        double dy = this.yyPos - planet.yyPos;
        return dx * dx + dy * dy;
    }

    double calcForceExertedBy(Planet planet) {
        double r2 = clcDistanceR2(planet);
        return (G * planet.mass * this.mass) / r2;
    }

    double calcForceExertedByX(Planet planet) {
        double dx = planet.xxPos - this.xxPos;
        double f = calcForceExertedBy(planet);
        return (f * dx) / calcDistance(planet);
    }

    double calcForceExertedByY(Planet planet) {
        double dy =  planet.yyPos - this.yyPos;
        double f = calcForceExertedBy(planet);
        return (f * dy) / calcDistance(planet);
    }

    double calcNetForceExertedByX(Planet[] planets) {
        double fx = 0f;
        for (Planet planet : planets) {
            fx += calcForceExertedByX(planet);
        }
        return fx;
    }

    double calcNetForceExertedByY(Planet[] planets) {
        double fy = 0f;
        for (Planet planet : planets) {
            fy += calcForceExertedByX(planet);
        }
        return fy;
    }

    void update(double dt, double fx, double fy ) {
        double aNetX = fx / this.mass;
        double aNetY = fy / this.mass;

        this.xxVel = this.xxVel + dt * aNetX;
        this.yyVel = this.yyVel + dt * aNetY;

        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }
}
