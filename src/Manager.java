import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Manager {
    public static double B = 3.44 * Math.pow(10, -4);public double fxyt,xx,yy,tt=0;
    ArrayList<Ball>ballArrayList=new ArrayList<Ball>();ArrayList<String>Collisions=new ArrayList<String>();
    public boolean BallCheckName(String BallName) {
        for(int i=0;i<ballArrayList.size();i++) { if(BallName.equalsIgnoreCase(ballArrayList.get(i).getName())){return false;} } return true;}
    public boolean BallCheckName2(String Name) {
        for(int i=0;i<ballArrayList.size();i++) { if(ballArrayList.get(i).getName().equalsIgnoreCase(Name)){return true;} }return false; }
    public void AddBall(String Name,double Mass,double Radius, double X_Position, double Y_Position,  double Initial_X_Velocity, double Initial_Y_Velocity,double Yard_Width,double Yard_Height) {
        if(!BallCheckName(Name)){System.out.println("wrong ball name");}
        else if(CreateBallQualify(X_Position,Y_Position,Radius,Yard_Width,Yard_Height)) { Ball ball=new Ball(Name,Mass,X_Position,Y_Position,Radius,Initial_X_Velocity,Initial_Y_Velocity);ballArrayList.add(ball);System.out.println("Ball Added "); }
        else { System.out.println("ball coud not be created"); }
    }
    public boolean CreateBallQualify(double X_Position, double Y_Position, double Radius,double Yard_Width,double Yard_Height) {
        for(int i=0;i<ballArrayList.size();i++) {
            double LineOfCenters=Math.sqrt(Math.pow(ballArrayList.get(i).getX_Position()-X_Position,2)+Math.pow(ballArrayList.get(i).getY_Position()-Y_Position,2));
            double RadiusSum=ballArrayList.get(i).getRadius()+Radius;if(RadiusSum>LineOfCenters){return false;}
        }if(X_Position<Radius|| Yard_Width-X_Position<Radius||Yard_Height-Y_Position<Radius||Y_Position<Radius){return false;}return true;
    }
    public void RemoveBall(String Name) {
        if (!BallCheckName2(Name)) { System.out.println("wrong ball name"); }
        else { for(int i=0;i<ballArrayList.size();i++) { if(ballArrayList.get(i).getName().equalsIgnoreCase(Name)){ballArrayList.remove(i);} } }
    }
    public void LocateBall(String Name) {
        if (!BallCheckName2(Name)) { System.out.println("wrong ball name"); }
        else { for(int i=0;i<ballArrayList.size();i++) { if(ballArrayList.get(i).getName().equalsIgnoreCase(Name)){System.out.println("Locate Ball "+ballArrayList.get(i).getName()+":\n"+"(x , y) = ("+ballArrayList.get(i).getX_Position()+" , "+ballArrayList.get(i).getY_Position()+" )");} } }
    }
    public void LocateAllBalls() {
        for(int i=0;i<ballArrayList.size();i++) { System.out.println("Locate Ball "+ballArrayList.get(i).getName()+":\n"+"(x , y) = ("+ballArrayList.get(i).getX_Position()+" , "+ballArrayList.get(i).getY_Position()+" )"); } }
    public void ShowAllCollisions() {for(int i=0;i<Collisions.size();i++){System.out.println(Collisions.get(i));}}
    public void ShowSpecialBallCollision(String Name) {
        if(BallCheckName2(Name)){System.out.println("wrong ball name");}
        else{ boolean check=false;for(int i=0;i<Collisions.size();i++) {
            String[] split=Collisions.get(i).split(" ");
            if(split[4].equalsIgnoreCase(Name)||split[12].equalsIgnoreCase(Name)) { System.out.println(Collisions.get(i));check=true; }
        }if(check==false){System.out.println("there is no collision for this ball till now");}}
    }
    public void interpolate(double v,double dt) {
        for (int i = 0; i < ballArrayList.size(); i++) {
            double vx1 = ballArrayList.get(i).getX_Velocity();double vy1 = ballArrayList.get(i).getY_Velocity();double x = ballArrayList.get(i).getX_Position();double y = ballArrayList.get(i).getY_Position();double t = v * this.tt;
            if (x + 2*ballArrayList.get(i).getRadius() >= InputProcessor.Yard_Width || x - 2*ballArrayList.get(i).getRadius() <= 0)
                ballArrayList.get(i).setX_Velocity(-ballArrayList.get(i).getX_Velocity());
            if (y + 2*ballArrayList.get(i).getRadius() >= InputProcessor.Yard_Height || y - 2*ballArrayList.get(i).getRadius() <= 0)
                ballArrayList.get(i).setY_Velocity(-ballArrayList.get(i).getY_Velocity());
            vx1 = (B * ballArrayList.get(i).getRadius() * (getVx(x, y, t) - vx1)) * dt / ballArrayList.get(i).getMass();
            ballArrayList.get(i).setX_Velocity(ballArrayList.get(i).getX_Velocity() + vx1);
            vy1 = (B * ballArrayList.get(i).getRadius() * (getVy(x, y, t) - vy1)) * dt / ballArrayList.get(i).getMass();
            ballArrayList.get(i).setY_Velocity(ballArrayList.get(i).getY_Velocity() + vy1);
            for (int j = i + 1; j < ballArrayList.size(); j++) {
                double q=ballArrayList.get(i).getRadius()+ballArrayList.get(j).getRadius();
                double qprime=Math.sqrt(Math.pow(ballArrayList.get(i).getX_Position()-ballArrayList.get(j).getX_Position(),2)+Math.pow(ballArrayList.get(i).getY_Position()-ballArrayList.get(j).getY_Position(),2));
                if (q>=qprime) {
                    Collisions.add("collision at t = "+t+" : "+ballArrayList.get(i).getName()+" and "+ballArrayList.get(i).getName()+" ( x , y ) = ( "+ballArrayList.get(i).getX_Position()+" , "+ballArrayList.get(i).getY_Position() );
                    Vector v_n = new Vector(ballArrayList.get(i).getX_Position() - ballArrayList.get(j).getX_Position(), ballArrayList.get(i).getY_Position() - ballArrayList.get(j).getY_Position());
                    Vector v_un = new Vector(v_n.getX() / Math.sqrt(v_n.getX() * v_n.getX() + v_n.getY() * v_n.getY()), v_n.getY() / Math.sqrt(v_n.getX() * v_n.getX() + v_n.getY() * v_n.getY()));
                    Vector v_ut = new Vector(-v_un.getY(), v_un.getX());
                    double v1n = v_un.getX() * ballArrayList.get(i).getX_Velocity() + v_un.getY() * ballArrayList.get(i).getY_Velocity();
                    double v1t = v_ut.getX() * ballArrayList.get(i).getX_Velocity() + v_ut.getY() * ballArrayList.get(i).getY_Velocity();
                    double v2n = v_un.getX() * ballArrayList.get(j).getX_Velocity() + v_un.getY() * ballArrayList.get(j).getY_Velocity();
                    double v2t = v_ut.getX() * ballArrayList.get(j).getX_Velocity() + v_ut.getY() * ballArrayList.get(j).getY_Velocity();
                    double v1nPrime = (v1n * (ballArrayList.get(i).getMass() - ballArrayList.get(j).getMass()) + 2 * ballArrayList.get(j).getMass() * v2n) / (ballArrayList.get(i).getMass() + ballArrayList.get(j).getMass());
                    double v2nPrime = (v2n * (ballArrayList.get(j).getMass() - ballArrayList.get(i).getMass()) + 2 * ballArrayList.get(i).getMass() * v1n) / (ballArrayList.get(i).getMass() + ballArrayList.get(j).getMass());
                    Vector v_v1nPrime = new Vector(v_un.getX() * v1nPrime, v_un.getY() * v1nPrime);
                    Vector v_v1tPrime = new Vector(v_ut.getX() * v1t, v_ut.getY() * v1t);
                    Vector v_v2nPrime = new Vector(v_un.getX() * v2nPrime, v_un.getY() * v2nPrime);
                    Vector v_v2tPrime = new Vector(v_ut.getX() * v2t, v_ut.getY() * v2t);
                    ballArrayList.get(i).setX_Velocity(v_v1nPrime.getX() + v_v1tPrime.getX());
                    ballArrayList.get(i).setY_Velocity(v_v1nPrime.getY() + v_v1tPrime.getY());
                    ballArrayList.get(j).setX_Velocity(v_v2nPrime.getX() + v_v2tPrime.getX());
                    ballArrayList.get(j).setY_Velocity(v_v2nPrime.getY() + v_v2tPrime.getY());
                }
            }
            ballArrayList.get(i).setX_Position(ballArrayList.get(i).getX_Position() + ballArrayList.get(i).getX_Velocity() * dt);
            ballArrayList.get(i).setY_Position(ballArrayList.get(i).getY_Position() + ballArrayList.get(i).getY_Velocity() * dt);
        }
    }
    public double getVx(double x, double y, double t) { this.xx = x;this.yy = y;this.tt = t;covertFormula(InputProcessor.wind_x);return fxyt; }
    public double getVy(double x, double y, double t) { this.xx = x;this.yy = y;this.tt = t;covertFormula(InputProcessor.wind_y);return fxyt; }
    private void covertFormula(String s) {
        fxyt = 0;ArrayList<String> each = new ArrayList<>();
        for (String s1 : s.split("\\+")) { String[] s1Split;s1Split = s1.split("-");each.addAll(Arrays.asList(s1Split)); }
        for (String f : each) {
            double hxyt = 1;
            if (Pattern.compile("sin").matcher(f).find()) {
                String[] h = f.split("sin");String[] a;double p = 1, A = 1;
                if (!h[0].equals("")) { h[0] = h[0].substring(0, h[0].length() - 1);A = Double.parseDouble(h[0]); }
                if (h[1].charAt(h[1].length() - 2) == '^') { p = Double.parseDouble("" + h[1].charAt(h[1].length() - 1) + "");h[1] = h[1].substring(1, h[1].length() - 3); }
                else {h[1] = h[1].substring(1, h[1].length() - 1);}
                a = h[1].split("\\*");
                for (String parameter : a) {
                    if (parameter.contains("x")) { if (parameter.contains("^")) {hxyt *= Math.pow(xx, Double.parseDouble(parameter.split("\\^")[1]));} else {hxyt = xx;} }
                    else if (parameter.contains("y")) { if (parameter.contains("^")) {hxyt *= Math.pow(yy, Double.parseDouble(parameter.split("\\^")[1]));} else {hxyt *= yy;} }
                    else if (parameter.contains("t")) { if (parameter.contains("^")) {hxyt *= Math.pow(tt, Double.parseDouble(parameter.split("\\^")[1]));} else {hxyt *= tt;} }
                    else {hxyt *= Double.parseDouble(parameter);}
                }fxyt += A * Math.pow(Math.sin(hxyt), p);
            }
            else if (Pattern.compile("cos").matcher(f).find()) {
                String[] h = f.split("cos");String[] a;double p = 1, A = 1;
                if (!h[0].equals("")) { h[0] = h[0].substring(0, h[0].length() - 1);A = Double.parseDouble(h[0]); }
                if (h[1].charAt(h[1].length() - 2) == '^') { p = Double.parseDouble("" + h[1].charAt(h[1].length() - 1) + "");h[1] = h[1].substring(1, h[1].length() - 3); }
                else {h[1] = h[1].substring(1, h[1].length() - 1);}
                a = h[1].split("\\*");
                for (String parameter : a) {
                    if (parameter.contains("x")) { if (parameter.contains("^")) {hxyt *= Math.pow(xx, Double.parseDouble(parameter.split("\\^")[1]));} else {hxyt = xx;} }
                    else if (parameter.contains("y")) { if (parameter.contains("^")) {hxyt *= Math.pow(yy, Double.parseDouble(parameter.split("\\^")[1]));} else {hxyt *= yy;} }
                    else if (parameter.contains("t")) { if (parameter.contains("^")) {hxyt *= Math.pow(tt, Double.parseDouble(parameter.split("\\^")[1]));}else {hxyt *= tt;} }
                    else {hxyt *= Double.parseDouble(parameter);}
                }fxyt += A * Math.pow(Math.cos(hxyt), p);
            }
            else {
                String[] a = f.split("\\*");if (a.length == 0) hxyt *= 0;
                for (String parameter : a) {
                    if (parameter.contains("x")) { if (parameter.contains("^")) {hxyt *= Math.pow(xx, Double.parseDouble(parameter.split("\\^")[1]));} else {hxyt *= xx;} }
                    else if (parameter.contains("y")) { if (parameter.contains("^")) {hxyt *= Math.pow(yy, Double.parseDouble(parameter.split("\\^")[1]));} else {hxyt *= yy;} }
                    else if (parameter.contains("t")) { if (parameter.contains("^")) {hxyt *= Math.pow(tt, Double.parseDouble(parameter.split("\\^")[1]));} else {hxyt *= tt;} }
                    else {hxyt *= Double.parseDouble(parameter);}
                }fxyt += hxyt;
            }
        }
    }
}