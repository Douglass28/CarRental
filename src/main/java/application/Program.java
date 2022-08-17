package application;

import entities.models.CarRental;
import entities.models.Vehicle;
import entities.services.BrazilTaxService;
import entities.services.RentalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");

        System.out.println("Enter rental data : ");
        System.out.print("Car model : ");
        String carModel = sc.nextLine();
        System.out.print("Pickup (dd/MM/yyyy hh:mm) : " );
        Date start = sdf.parse(sc.nextLine());
        System.out.print("Return (dd/MM/yyyy hh:mm) : ");
        Date finish = sdf.parse(sc.nextLine());

        CarRental car = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Enter price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Enter price per day: ");
        double pricePerDay = sc.nextDouble();

        RentalService rental = new RentalService(pricePerDay,pricePerHour, new BrazilTaxService());
        rental.processInvoice(car);

        System.out.println("INVOICE");
        System.out.println("BASIC PAYMENT : " + String.format("%.2f", car.getInvoice().getBasicPayment()));
        System.out.println("tax : " + String.format("%.2f", car.getInvoice().getTax()));
        System.out.println("TOTAL PAYMENT : " + String.format("%.2f", car.getInvoice().getTotalPayment()));


        sc.close();

    }
}
