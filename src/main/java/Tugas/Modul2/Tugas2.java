package Tugas.Modul2;

public class _TaxTicket_ {
    public String passengerName;
    public String startLocation;
    public String destination;
    public double price;
    private double duration;
    private double speed;

    private static final double MIN_SPEED = 5;
    private static final double MAX_SPEED = 100;

    public _TaxTicket_(String passengerName, String startLocation, String destination, double price, double duration, double speed) {
        this.passengerName = passengerName;
        this.startLocation = startLocation;
        this.destination = destination;
        this.price = price;
        this.duration = duration;
        this.speed = speed;
    }

    // Method to check taxi status
    public void cS() {
        System.out.println("Your taxi is heading to " + destination);
    }

    // Method to display estimated travel duration
    public void dED() {
        System.out.println("Estimated travel duration: " + duration + " minutes");
    }

    // Method to display the route
    public void dR() {
        System.out.println("Route: " + startLocation + " -> " + destination);
    }

    // Method to slow down the taxi
    public void slowDown(double speedReduction) {
        speed -= speedReduction;
        if (speed < MIN_SPEED)
            speed = MIN_SPEED;
        duration += speedReduction * 0.5;
        System.out.println("Taxi slowed down! Current speed: " + speed + " km/h");
    }

    // Method to speed up the taxi
    public void speedUp(double speedIncrease) {
        speed += speedIncrease;
        if (speed > MAX_SPEED)
            speed = MAX_SPEED;
        duration -= speedIncrease * 0.3;
        if (duration < 1)
            duration = 1;
        System.out.println("Taxi sped up! Current speed: " + speed + " km/h");
    }

    // Method to display ticket details
    public void displayTicket() {
        System.out.println("Passenger: " + passengerName);
        System.out.println("From: " + startLocation);
        System.out.println("To: " + destination);
        System.out.println("Price: $" + price);
        System.out.println("Estimated Duration: " + duration + " minutes");
        System.out.println("Current Speed: " + speed + " km/h");
    }

    public static void main(String[] args) {
        _TaxTicket_ ticket = new _TaxTicket_("John Doe", "Central Station", "Airport", 25.50, 30, 60);
        ticket.displayTicket();
        ticket.dR();
        ticket.dED();
        ticket.cS();
        ticket.slowDown(20);
        ticket.speedUp(15);
    }
}

public void speedUp(double speedIncrease) {
    speed += speedIncrease;
    if (speed > MAX_SPEED)
        speed = MAX_SPEED;
    System.out.println("Taxi sped up! Current speed: " + speed + " km/h");
}

// Method to display basic info passenger and trip
public void df() {
    System.out.println("Passenger Name : " + passengerName);
    System.out.println("Start Location : " + startLocation);
    System.out.println("Destination : " + destination);
    System.out.println("Price : " + price);
    System.out.println("Final Price : " + (price + (price * 0.1))); // Price including 10% tax
}


