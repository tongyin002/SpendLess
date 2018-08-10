package entity;

public class Spend {
    public String monthSpend;
    public String todaySpend;
    public String dailySpend;
    public String monthlyLeft;
    public String dailyLeft;
    public String daysLeft;
    public int usagePercent;
    public boolean runOut = false;

    public Spend(int monthSpend, int todaySpend, int dailySpend, int monthlyLeft,
                 int dailyLeft, int daysLeft, int usagePercent){
        this.monthSpend = "$ " + monthSpend;
        this.todaySpend = "$ " + todaySpend;
        this.dailySpend = "$ " + dailySpend;
        if (monthlyLeft < 0) {
            runOut = true;
        }

        if (!runOut) {
            this.monthlyLeft = "$ " + monthlyLeft;
            this.dailyLeft = "$ " + dailyLeft;
        }else{
            this.monthlyLeft = "Over Drafted $" + (0 - monthlyLeft);
            this.dailyLeft = "$ 0";
        }
        this.daysLeft = daysLeft + " Days";
        this.usagePercent = usagePercent;
    }
}
