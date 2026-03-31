public class Main {
    public static void main(String[] args) {
        InsuranceOffice office = new InsuranceOffice("Secure Future Warsaw");

        Policy p1 = new Policy("CAR-101", "Anna Nowak", 900.0, 3, 72000.0, true, true);
        Policy p2 = new Policy("CAR-102", "Piotr Lis", 840.0, 4, 54000.0, false, false);
        Policy p3 = new Policy("CAR-103", "Karolina Maj", 780.0, 2, 46000.0, true, false);

        office.addPolicy(p1);
        office.addPolicy(p2);
        office.addPolicy(p3);

        office.printReport();
        System.out.println("Total Premium Portfolio: " + office.calculateTotalPremium());
        System.out.println("Renewal Forecast Total: " + office.calculateTotalRenewalForecast());
        System.out.println("High-Risk Count: " + office.countHighRiskPolicies());
        System.out.println("Global Created Policies (Static): " + Policy.getCreatedPolicyCount());
        System.out.println("\n--- Individual Analysis ---");
        System.out.println(p1.getRiskSummary());
        System.out.println("Renewal Proposal for p1: " + p1.calculateRenewalPremium());
        Policy duplicateChecker = new Policy("CAR-101", "Different Name", 0, 0, 0, false, false);
        System.out.println("Is p1 same as duplicateChecker? " + p1.equals(duplicateChecker));
        Policy found = office.findByNumber("CAR-102");
        System.out.println("Search Result (CAR-102): " + found);
    }
}