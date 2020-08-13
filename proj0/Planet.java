public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double sqrDist = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
        return Math.sqrt(sqrDist);
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double dist = calcDistance(p);
        return G * this.mass * p.mass / (dist * dist);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double r = calcDistance(p);
        double f = calcForceExertedBy(p);
        return f * dx / r;
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        double r = calcDistance(p);
        double f = calcForceExertedBy(p);
        return f * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double result = 0;
        for (Planet P : allPlanets) {
            if(!P.equals(this)) {
                result += calcForceExertedByX(P);
            }
        }

        return result;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double result = 0;
        for (Planet P : allPlanets) {
            if(!P.equals(this)) {
                result += calcForceExertedByY(P);
            }
        }

        return result;
    }

    public void update(double t, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;

        xxVel += t * ax;
        yyVel += t * ay;

        xxPos += t * xxVel;
        yyPos += t * yyVel;
    }

    public void draw() {
        String imageToDraw = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imageToDraw);
        StdDraw.show();
    }
}
