public class Ball {
    private double X_Position;
    private double Y_Position;
    private double Radius;
    private double Mass;
    private String Name;
    private double Initial_X_Velocity;
    private double Initial_Y_Velocity;
    private double X_Velocity;
    private double Y_Velocity;

    public Ball(String Name,double Mass, double X_Position, double Y_Position, double Radius, double Initial_X_Velocity, double Initial_Y_Velocity) {
        this.Initial_X_Velocity=Initial_X_Velocity;
        this.Initial_Y_Velocity=Initial_Y_Velocity;
        this.X_Position=X_Position;
        this.Y_Position=Y_Position;
        this.Radius=Radius;
        this.Name=Name;
        this.X_Velocity=Initial_X_Velocity;
        this.Y_Velocity=Initial_Y_Velocity;
        this.Mass=Mass;
    }

    public double getX_Position() {
        return X_Position;
    }
    public double getY_Position() {
        return Y_Position;
    }
    public double getMass() {
        return Mass;
    }
    public double getRadius() {
        return Radius;
    }
    public String getName() {
        return Name;
    }

    public double getX_Velocity() {
        return X_Velocity;
    }
    public double getY_Velocity() {
        return Y_Velocity;
    }
    public void setX_Position(double x_Position) {
        X_Position = x_Position;
    }
    public void setY_Position(double y_Position) {
        Y_Position = y_Position;
    }
    public void setX_Velocity(double x_Velocity) {
        X_Velocity = x_Velocity;
    }
    public void setY_Velocity(double y_Velocity) {
        Y_Velocity = y_Velocity;
    }

}