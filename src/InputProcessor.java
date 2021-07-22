import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class InputProcessor extends Thread {
    Manager manager;
    JFrame frame;

    public static double Yard_Width,Yard_Height;
    public static String wind_x,wind_y;

    public InputProcessor(Manager manager){
        this.manager=manager;
    }

    public void Run() {
        String Input;
        Scanner scanner=new Scanner(System.in);

        System.out.print("enter Width: ");
        Yard_Width = Double.parseDouble(scanner.nextLine());
        System.out.print("enter Height: ");
        Yard_Height = Double.parseDouble(scanner.nextLine());
        CreateFrame(Yard_Width,Yard_Height);
        System.out.print("Wind_X_speed : ");
        wind_x = scanner.nextLine();
        System.out.print("Wind_Y_speed : ");
        wind_y=scanner.nextLine();
        while(true) {
            System.out.print("enter your need : ");
            Input=scanner.nextLine();
            String[]split=Input.split("\\s");
            if(split[0].equalsIgnoreCase("Add")&&split.length==9) {
                manager.AddBall(split[2],Double.parseDouble(split[3]),Double.parseDouble(split[4]),Double.parseDouble(split[5]),Double.parseDouble(split[6]),Double.parseDouble(split[7]),Double.parseDouble(split[8]),Yard_Width,Yard_Height);
            }
            else if(split[0].equalsIgnoreCase("Add")&&split.length==7){
                manager.AddBall(split[2],Double.parseDouble(split[3]),Double.parseDouble(split[4]),Double.parseDouble(split[5]),Double.parseDouble(split[6]),0,0,Yard_Width,Yard_Height);
            }
            else if(split[0].equalsIgnoreCase("Remove")){
                manager.RemoveBall(split[1]);
            }
            else if(Input.toLowerCase().startsWith("locate ball")){
                manager.LocateBall(split[2]);
            }
            else if(Input.toLowerCase().startsWith("locate all")){
                manager.LocateAllBalls();
            }
            else if(split[0].equalsIgnoreCase("turn")){
                CreateFrame((int)Yard_Width,(int)Yard_Height);
                Turn(Integer.parseInt(split[1]),Double.parseDouble(split[2]));
            }
            else if(Input.equalsIgnoreCase("Print All Collisions")){
                manager.ShowAllCollisions();
            }
            else if(split[0].equalsIgnoreCase("Print")&&split.length==4){
                manager.ShowSpecialBallCollision(split[3]);
            }
            else if(Input.equalsIgnoreCase("Exit")){
                System.out.println("Finish");
                System.exit(1);
            }
            else{System.out.println("Wrong input...Try Again!!!");}
        }
    }

    public void CreateFrame(double x,double y) {
        frame=new JFrame();
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize((int)x,(int)y);
    }

    public void Turn(int n,double dt) {
        frame.setVisible(true);
        while (manager.tt<10*n){
            manager.interpolate(1,dt);
            frame.setVisible(true);
            frame.add(new Surface(manager));
            manager.tt+=dt;
        }
        frame.dispose();
        System.out.println("succussfully simulated");
        manager.tt=0;
    }
}