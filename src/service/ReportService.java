package service;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportService {

    public int getDaySpend(Date date, List<Record> monthRecords) {
        int daySpend = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate = sdf.format(date);
        for (Record record : monthRecords) {
            String d = sdf.format(record.date);
            if(d.equals(nowDate))
                daySpend += record.spend;
        }
        return daySpend;
    }

    public List<Record> listThisMonthRecords(){
        RecordDAO recordDAO = new RecordDAO();
        List<Record> monthRawData = recordDAO.listThisMonth();
        List<Record> result = new ArrayList<>();

        Date monthBegin = DateUtil.monthBegin();
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < monthTotalDay; i++) {
            Record r = new Record();
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            Date eachDayOfThisMonth=c.getTime() ;
            int daySpend = getDaySpend(eachDayOfThisMonth,monthRawData);
            r.spend=daySpend;
            result.add(r);
        }
        return result;
    }
}
