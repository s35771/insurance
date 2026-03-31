import java.util.Objects;

public class Policy {
    private String policyNumber;
    private String clientName;
    private double basePremium;
    private int riskLevel;
    private double vehicleValue;
    private boolean hasAlarm;
    private boolean claimFreeClient;
    private static int createdPolicyCount = 0;
    private static final double ADMINISTRATIVE_FEE = 50.0;

    public Policy(String policyNumber, String clientName, double basePremium,
                  int riskLevel, double vehicleValue, boolean hasAlarm, boolean claimFreeClient) {
        this.policyNumber = policyNumber;
        this.clientName = clientName;
        this.basePremium = basePremium;
        this.riskLevel = riskLevel;
        this.vehicleValue = vehicleValue;
        this.hasAlarm = hasAlarm;
        this.claimFreeClient = claimFreeClient;

        createdPolicyCount++;
    }

    public double calculateFinalPremium() {
        double premium = basePremium + ADMINISTRATIVE_FEE;

        premium += (riskLevel * 120.0);

        if (vehicleValue > 60000) {
            premium += 200.0;
        }

        if (hasAlarm) premium -= 50.0;
        if (claimFreeClient) premium *= 0.90;

        return Math.max(premium, basePremium);
    }


    public double calculateRenewalPremium() {
        double currentFinal = calculateFinalPremium();
        double renewal = currentFinal;

        if (riskLevel == 4) renewal *= 1.10;
        else if (riskLevel >= 5) renewal *= 1.20;


        if (vehicleValue > 60000) renewal += 150.0;

        if (claimFreeClient) renewal *= 0.92;
        if (hasAlarm) renewal *= 0.95;

        double minAllowed = currentFinal * 0.90;
        double maxAllowed = currentFinal * 1.25;

        if (renewal < minAllowed) renewal = minAllowed;
        if (renewal > maxAllowed) renewal = maxAllowed;

        return Math.round(renewal * 100.0) / 100.0;
    }

    public String getRiskSummary() {
        return "Policy " + policyNumber + " Risk Level: " + riskLevel +
                (riskLevel > 3 ? " (HIGH RISK)" : " (Standard)");
    }

    public static int getCreatedPolicyCount() {
        return createdPolicyCount;
    }

    public String getPolicyNumber() { return policyNumber; }
    public int getRiskLevel() { return riskLevel; }

    @Override
    public String toString() {
        return String.format("Policy[No: %s, Client: %s, Premium: %.2f, Risk: %d]",
                policyNumber, clientName, calculateFinalPremium(), riskLevel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(policyNumber, policy.policyNumber);
    }
}