public class Employee extends User {
    private double payroll;
    private String shift;
    private double rating;
    private boolean leaveRequest;
    private boolean leaveResult;
    private Manager manager;

    public Employee(String username, String password, PersonalInfo info) {
        super(username, password, "employee", info);

    }

    @Override
    public void showGeneralInfo() {
        System.out.print("Employee Info: " + getInfo().getName() + " " + getInfo().getSurname() + " " + getUsername());
        System.out.println(" Payroll: " + payroll + ", Shift: " + shift + ", Rating: " + rating);
    }

    public void showPayroll(){
        System.out.println("Payroll: " + payroll);
    }

    public void showShift(){
        System.out.println("Shift: " + shift);
    }

    public String getShift() {
        return shift;
    }

    public double getPayroll() {
        return payroll;
    }

    public double getRating() {
        return rating;
    }

    public boolean hasLeaveRequest() {
        return leaveRequest;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager m){
        manager= m;
    }

    public void showRating(){
        System.out.println("Rating: " + rating);
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void displayLeaveInfo() {
        System.out.println("Leave request: " + (leaveResult ? "yes" : "no"));
    }

    public void sendLeaveRequest() {
        leaveRequest = true;
        leaveResult = false;

        System.out.println("Leave requested");
    }

    public void setEmployeeData(String payroll,String shift,String rating,String leaveRequest,String leaveResult){
        this.payroll = Double.parseDouble(payroll);
        this.shift = shift;
        this.rating = Double.parseDouble(rating);
        this.leaveRequest = Boolean.parseBoolean(leaveRequest);
        this.leaveResult = Boolean.parseBoolean(leaveResult);
    }



    public void setLeaveRequested(boolean leaveRequest) {
        this.leaveRequest = leaveRequest;
    }

    public boolean getLeaveResult() {
        return leaveResult;
    }

    public void setLeaveResult(boolean leaveResult) {
        this.leaveResult = leaveResult;
    }
}