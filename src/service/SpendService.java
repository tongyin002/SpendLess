package service;

import dao.RecordDAO;
import entity.Record;
import entity.Spend;
import util.DateUtil;

import java.util.List;

public class SpendService {
    public Spend getSpend(){
        RecordDAO recordDAO = new RecordDAO();

        List<Record> thisMonthRecords = recordDAO.listThisMonth();
        List<Record> todayRecords = recordDAO.listToday();
        int numOfDays = DateUtil.thisMonthTotalDay();

        int monthSpend = 0;
        int todaySpend = 0;
        int dailySpend = 0;
        int monthlyLeft = 0;
        int dailyLeft = 0;
        int daysLeft = 0;
        int usagePercent = 0;

        int monthBudget = new ConfigService().getIntBudget();

        for (Record c : thisMonthRecords) {
            monthSpend += c.getSpend();
        }
        for (Record c : todayRecords) {
            todaySpend += c.getSpend();
        }

        dailySpend = monthSpend / numOfDays;
        monthlyLeft = monthBudget - monthSpend;
        daysLeft = DateUtil.thisMonthLeftDay();
        dailyLeft = monthlyLeft / daysLeft;
        usagePercent = monthSpend * 100 / monthBudget;

        return new Spend(monthSpend, todaySpend, dailySpend, monthlyLeft, dailyLeft, daysLeft, usagePercent);
    }
}
