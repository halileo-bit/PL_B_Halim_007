package Tugas.Modul2;

import java.util.Objects; /**
 * Comprehensive Taxi Ticket Management System
 * Handles passenger details, route information, pricing, and real-time journey management
 */
public class TaxiTicket {
    // Constants
    private static final double MIN_SPEED = 5.0;
    private static final double MAX_SPEED = 100.0;
    private static final double TAX_RATE = 0.10; // 10% tax
    private static final double SLOW_DOWN_DURATION_FACTOR = 0.5;
    private static final double SPEED_UP_DURATION_FACTOR = 0.3;
    private static final double MIN_DURATION = 1.0;

    // Instance variables with proper naming
    private String passengerName;
    private String startLocation;
    private String destination;
    private double basePrice;
    private double estimatedDuration; // in minutes
    private double currentSpeed; // in km/h
    private String ticketId;
    private static int ticketCounter = 1;
    private boolean journeyStarted;
    private double distance; // in kilometers

    /**
     * Constructor for TaxiTicket
     */
    public TaxiTicket(String passengerName, String startLocation, String destination,
                      double basePrice, double estimatedDuration, double initialSpeed) {
        setPassengerName(passengerName);
        setStartLocation(startLocation);
        setDestination(destination);
        setBasePrice(basePrice);
        setEstimatedDuration(estimatedDuration);
        setCurrentSpeed(initialSpeed);
        this.ticketId = generateTicketId();
        this.journeyStarted = false;
        calculateDistance();
    }

    /**
     * Generates a unique ticket ID
     */
    private String generateTicketId() {
        return "TAXI-" + String.format("%04d", ticketCounter++);
    }

    /**
     * Calculates approximate distance based on speed and duration
     */
    private void calculateDistance() {
        this.distance = (currentSpeed * estimatedDuration) / 60.0; // Convert minutes to hours
    }

    // Business methods with descriptive names

    /**
     * Checks and displays the current taxi status
     */
    public void checkStatus() {
        String status = journeyStarted ? "en route" : "waiting for passenger";
        String emoji = journeyStarted ? "🚕" : "⏳";
        System.out.printf("%s Taxi Status: %s | Heading to %s at %.1f km/h%n",
                emoji, status, destination, currentSpeed);
    }

    /**
     * Displays the estimated travel duration
     */
    public void displayEstimatedDuration() {
        System.out.printf("⏱️  Estimated Travel Duration: %.1f minutes%n", estimatedDuration);
    }

    /**
     * Displays the complete route information
     */
    public void displayRoute() {
        System.out.printf("🗺️  Route: %s → %s (%.1f km)%n", startLocation, destination, distance);
    }

    /**
     * Starts the taxi journey
     */
    public void startJourney() {
        if (journeyStarted) {
            System.out.println("⚠️  Journey already started!");
            return;
        }
        this.journeyStarted = true;
        System.out.printf("🚀 Journey started! Taxi is now en route to %s%n", destination);
    }

    /**
     * Completes the taxi journey
     */
    public void completeJourney() {
        if (!journeyStarted) {
            System.out.println("⚠️  Journey hasn't started yet!");
            return;
        }
        this.journeyStarted = false;
        double finalPrice = calculateFinalPrice();
        System.out.printf("🎉 Journey completed! Thank you for riding with us.%n");
        System.out.printf("💰 Final amount to pay: $%.2f%n", finalPrice);
    }

    /**
     * Reduces the taxi speed by the specified amount
     * @param speedReduction amount to reduce speed (km/h)
     */
    public void slowDown(double speedReduction) {
        validateJourneyStarted();
        validatePositiveValue(speedReduction, "Speed reduction");

        double newSpeed = currentSpeed - speedReduction;
        setCurrentSpeed(newSpeed);

        // Adjust duration based on speed reduction
        estimatedDuration += speedReduction * SLOW_DOWN_DURATION_FACTOR;

        System.out.printf("🔻 Taxi slowed down by %.1f km/h! Current speed: %.1f km/h%n",
                speedReduction, currentSpeed);
    }

    /**
     * Increases the taxi speed by the specified amount
     * @param speedIncrease amount to increase speed (km/h)
     */
    public void speedUp(double speedIncrease) {
        validateJourneyStarted();
        validatePositiveValue(speedIncrease, "Speed increase");

        double newSpeed = currentSpeed + speedIncrease;
        setCurrentSpeed(newSpeed);

        // Adjust duration based on speed increase
        estimatedDuration -= speedIncrease * SPEED_UP_DURATION_FACTOR;
        if (estimatedDuration < MIN_DURATION) {
            estimatedDuration = MIN_DURATION;
        }

        System.out.printf("🔺 Taxi sped up by %.1f km/h! Current speed: %.1f km/h%n",
                speedIncrease, currentSpeed);
    }

    /**
     * Displays basic passenger and trip information with tax calculation
     */
    public void displayBasicInfo() {
        double finalPrice = calculateFinalPrice();

        System.out.println("\n📄 === BASIC TRIP INFORMATION ===");
        System.out.println("🎫 Ticket ID: " + ticketId);
        System.out.println("👤 Passenger: " + passengerName);
        System.out.println("📍 Pickup: " + startLocation);
        System.out.println("🎯 Destination: " + destination);
        System.out.printf("📏 Distance: %.1f km%n", distance);
        System.out.printf("💰 Base Price: $%.2f%n", basePrice);
        System.out.printf("💳 Tax (%.0f%%): $%.2f%n", TAX_RATE * 100, basePrice * TAX_RATE);
        System.out.printf("💵 Final Price: $%.2f%n", finalPrice);
        System.out.println("📊 Status: " + (journeyStarted ? "En Route" : "Not Started"));
        System.out.println("===============================\n");
    }

    /**
     * Displays comprehensive journey information
     */
    public void displayDetailedInfo() {
        double finalPrice = calculateFinalPrice();

        System.out.println("\n📊 === COMPREHENSIVE JOURNEY DETAILS ===");
        System.out.println("📋 Ticket ID: " + ticketId);
        System.out.println("👤 Passenger: " + passengerName);
        System.out.println("📍 Route: " + startLocation + " → " + destination);
        System.out.printf("📏 Distance: %.1f km%n", distance);
        System.out.println("💰 Pricing Breakdown:");
        System.out.printf("   - Base Fare: $%.2f%n", basePrice);
        System.out.printf("   - Tax (%.0f%%): $%.2f%n", TAX_RATE * 100, basePrice * TAX_RATE);
        System.out.printf("   - Total Amount: $%.2f%n", finalPrice);
        System.out.println("⏱️  Journey Metrics:");
        System.out.printf("   - Estimated Duration: %.1f minutes%n", estimatedDuration);
        System.out.printf("   - Current Speed: %.1f km/h%n", currentSpeed);
        System.out.printf("   - Status: %s%n", journeyStarted ? "En Route" : "Not Started");
        System.out.println("========================================\n");
    }

    /**
     * Calculates the final price including tax
     * @return final price with tax
     */
    public double calculateFinalPrice() {
        return basePrice + (basePrice * TAX_RATE);
    }

    /**
     * Calculates estimated arrival time based on current time
     */
    public void displayEstimatedArrival() {
        validateJourneyStarted();
        System.out.printf("🕒 Estimated arrival in %.1f minutes%n", estimatedDuration);
    }

    /**
     * Updates the destination and recalculates route
     */
    public void updateDestination(String newDestination, double additionalPrice, double additionalDuration) {
        this.destination = Objects.requireNonNull(newDestination, "Destination cannot be null");
        this.basePrice += additionalPrice;
        this.estimatedDuration += additionalDuration;
        calculateDistance();
        System.out.printf("🔄 Destination updated to: %s (Additional: $%.2f, +%.1f min)%n",
                newDestination, additionalPrice, additionalDuration);
    }

    /**
     * Validates that the journey has started before performing journey operations
     */
    private void validateJourneyStarted() {
        if (!journeyStarted) {
            throw new IllegalStateException("Journey has not started yet. Call startJourney() first.");
        }
    }

    /**
     * Validates that a value is positive
     */
    private void validatePositiveValue(double value, String valueName) {
        if (value <= 0) {
            throw new IllegalArgumentException(valueName + " must be positive");
        }
    }

    // Getter and Setter methods with validation

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = Objects.requireNonNull(passengerName, "Passenger name cannot be null");
        if (passengerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger name cannot be empty");
        }
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = Objects.requireNonNull(startLocation, "Start location cannot be null");
        if (startLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Start location cannot be empty");
        }
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = Objects.requireNonNull(destination, "Destination cannot be null");
        if (destination.trim().isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be empty");
        }
        calculateDistance();
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice < 0) {
            throw new IllegalArgumentException("Base price cannot be negative");
        }
        this.basePrice = basePrice;
    }

    public double getEstimatedDuration() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(double estimatedDuration) {
        if (estimatedDuration <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        this.estimatedDuration = estimatedDuration;
        calculateDistance();
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        if (currentSpeed < MIN_SPEED) {
            this.currentSpeed = MIN_SPEED;
        } else if (currentSpeed > MAX_SPEED) {
            this.currentSpeed = MAX_SPEED;
        } else {
            this.currentSpeed = currentSpeed;
        }
        calculateDistance();
    }

    public String getTicketId() {
        return ticketId;
    }

    public boolean isJourneyStarted() {
        return journeyStarted;
    }

    public double getDistance() {
        return distance;
    }

    /**
     * Main method to demonstrate the TaxiTicket functionality
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== 🚕 TAXI MANAGEMENT SYSTEM ===\n");

            // Create a taxi ticket
            TaxiTicket ticket = new TaxiTicket("Alice Johnson", "Downtown",
                    "International Airport", 58.0, 30.0, 60.0);

            // Display comprehensive journey details
            System.out.println("=== TAXI BOOKING CONFIRMATION ===");
            ticket.displayDetailedInfo();

            // Check initial status
            ticket.checkStatus();

            // Start the journey
            ticket.startJourney();

            // Display route and estimated duration
            System.out.println("=== JOURNEY COMMENCED ===");
            ticket.displayRoute();
            ticket.displayEstimatedDuration();
            ticket.checkStatus();

            // Simulate journey operations
            System.out.println("=== DURING JOURNEY ===");
            ticket.slowDown(20.0);
            ticket.displayEstimatedArrival();

            ticket.speedUp(15.0);
            ticket.displayEstimatedArrival();

            // Display basic info with pricing
            System.out.println("=== PRICING SUMMARY ===");
            ticket.displayBasicInfo();

            // Complete the journey
            ticket.completeJourney();

            // Final details
            System.out.println("=== JOURNEY COMPLETE ===");
            ticket.displayDetailedInfo();

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
