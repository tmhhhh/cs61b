public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        double r = in.readDouble();

        return r;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        double r = in.readDouble();
        Planet[] planets = new Planet[n];

        for (int i = 0; i < n; i++){
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double m = in.readDouble();
            String imgFile = in.readString();
            Planet tmp = new Planet(xPos, yPos, xVel, yVel, m, imgFile);
            planets[i] = tmp;
        }

        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        String imageToDraw = "images/starfield.jpg";

        StdDraw.setScale(-r, r);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        StdDraw.show();

        StdDraw.enableDoubleBuffering();

        for (Planet p : planets) {
            p.draw();
        }


        for (double t = 0; t < T; t += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0; i < planets.length; i += 1) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i += 1) {
                planets[i].update(dt, xForces[i], yForces[i]);

            }
            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            StdDraw.show();

            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.pause(1);

        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
