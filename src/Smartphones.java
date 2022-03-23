import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class Smartphones {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a;
        while(true){
            System.out.println("Select a choice");
            System.out.println("1.Add smartphone data");
            System.out.println("2.View all smartphones");
            System.out.println("3.Search smartphoones using brand");
            System.out.println("4.Edit smartphone using IMEI number");
            System.out.println("5.Delete smartphone using IMEI number");
            System.out.println("6.Exit");
            a = input.nextInt();
            switch (a){
                case 1:
                    System.out.println("Add smartphone");
                    try{
                        Connection c = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphone?autoReconnect=true&useSSL=false","root","");
                        String imei,brand,model,price;
                        System.out.println("Enter IMEI number:");
                        imei = input.next();
                        System.out.println("Enter Brand name:");
                        brand = input.next();
                        System.out.println("Enter model name");
                        model = input.next();
                        System.out.println("Enter price:");
                        price = input.next();
                        Statement stmt = (Statement) c.createStatement();
                        stmt.executeUpdate("INSERT INTO `phones`(`imei`,`brand`,`model`,`price`) " +
                                "VALUES ("+imei+",'"+brand+"','"+model+"',"+price+")");
                        System.out.println("Inserted Successfully");

                    }catch (Exception e){
                        System.out.println(e);
                    }break;
                case 2:
                    try{
                        Connection c = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphone?autoReconnect=true&useSSL=false","root","");
                        Statement stmt = (Statement) c.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM `phones` WHERE 1");
                        while(rs.next()){
                            System.out.println("IMEI number :"+rs.getInt("imei"));
                            System.out.println("Smartphone Brand:"+rs.getString("brand"));
                            System.out.println("Model :"+rs.getString("model"));
                            System.out.println("Price :"+rs.getInt("price"));
                        }

                    }catch (Exception e){
                        System.out.println(e);
                    }break;
                case 3:
                    try{
                        String getBrand;
                        System.out.println("Enter the brand name to be searched");
                        getBrand = input.next();
                        Connection c = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphone?autoReconnect=true&useSSL=false","root","");
                        Statement stmt = (Statement) c.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM `phones` WHERE `brand`= '"+getBrand+"'");
                        while(rs.next()){
                            System.out.println("IMEI number :"+rs.getInt("imei"));
                            System.out.println("Smartphone Brand:"+rs.getString("brand"));
                            System.out.println("Model :"+rs.getString("model"));
                            System.out.println("Price :"+rs.getInt("price"));
                        }
                    }catch (Exception e){
                        System.out.println(e);
                    }break;
                case 4:
                    try{String getImei,getImei1,getBrand,getModel,getPrice;
                        System.out.println("Enter the imei number to be updated");
                        getImei = input.next();
                        System.out.println("Enter new Imei number");
                        getImei1 = input.next();
                        System.out.println("Enter the New Brand name :");
                        getBrand = input.next();
                        System.out.println("Enter the new Model :");
                        getModel = input.next();
                        System.out.println("Enter the new Price :");
                        getPrice = input.next();
                        Connection c = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphone?autoReconnect=true&useSSL=false","root","");
                        Statement stmt = (Statement) c.createStatement();
                        stmt.executeUpdate("UPDATE `phones` SET `imei`="+getImei1+",`brand`='"+getBrand+"',`model`='"+getModel+"',`price`="+getPrice+" WHERE `imei` =" +getImei);
                        System.out.println("Updated Successfully");
                    }catch (Exception e){
                        System.out.println(e);
                    }break;
                case 5:
                    try{
                        String getImei;
                        System.out.println("Enter IMEI number to be deleted :");
                        getImei = input.next();
                        Connection c = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/smartphone?autoReconnect=true&useSSL=false","root","");
                        Statement stmt = (Statement) c.createStatement();
                        stmt.executeUpdate("DELETE FROM `phones` WHERE `imei` = " +getImei);
                        System.out.println("Deleted Successfully");
                    }catch (Exception e){
                        System.out.println(e);
                    }break;
                case 6:
                    System.out.println("Exited");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice selection");
                    break;
            }
        }
    }
}