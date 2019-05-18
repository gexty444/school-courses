package w11w12;

import java.util.Calendar;
import java.util.Date;

public class Exercise4Fixed {

    public static void main(String[] args) throws InterruptedException {
        CalendarImplementation cal1 = new CalendarImplementation();
        cal1.setTime(new Date());
        Thread.sleep(1000);
        CalendarImplementation cal2 = new CalendarImplementation();
        cal2.setTime(new Date());

        CompositeCalendar c2 = new CompositeCalendar(cal2);
        System.out.println(c2.after(cal1));          // should be true

        CompositeCalendar c1 = new CompositeCalendar(cal1);
        System.out.println(c1.after(cal2));         // should be false
        System.out.println(c1.after(cal1));         // should be true
        System.out.println(c2.after(cal2));         // should be true

    }

}

// Forwarder class contains private member field of CalendarImplementation and redirects to methods of
// CalendarImplementation class.
class ForwardingCalendar{
    private final CalendarImplementation cal;

    // not public because only want to use between classes of this package
    ForwardingCalendar(CalendarImplementation cal) {
        this.cal = cal;
    }

    public CalendarImplementation getCalendarImplementation() {
        return cal;
    }

    public boolean after(Object when) {
        return cal.after(when);
    }

    public int compareTo(Calendar anotherCalendar) {
        // CalendarImplementation.compareTo() will be called
        return cal.compareTo(anotherCalendar);
    }
}


// wrapper class that provides same overridden methods from CalendarSubclass

// When we call the overriden after() method  in CompositeCalendar, we use the CalendarImplementation class's compareTo() method.
// Using super.after(when) forwards to Forwarding Calendar, which invokes CalendarImplementation's after().
// Hence, java.util.Calendar.after() invokes calendarImplementation.compareTo() method.
class CompositeCalendar extends ForwardingCalendar {
    public CompositeCalendar(CalendarImplementation cal) {
        super(cal);
    }

    @Override
    public boolean after(Object when) {
        if (when instanceof Calendar && super.compareTo((Calendar) when) == 0) {
            return true;
        }
        return super.after(when);
    }

    // edited this method to use CalendarImplementation's first day
    @Override
    public int compareTo(Calendar anotherCalendar) {
        return compareDays(super.getCalendarImplementation().getFirstDayOfWeek(), anotherCalendar.getFirstDayOfWeek());
    }

    private int compareDays(int currentFirstDayOfWeek,
                            int anotherFirstDayOfWeek) {
        return (currentFirstDayOfWeek > anotherFirstDayOfWeek) ? 1
                : (currentFirstDayOfWeek == anotherFirstDayOfWeek) ? 0 : -1;
    }

}

// Implementation of other Calendar abstract methods skipped
class CalendarImplementation extends Calendar{
    @Override
    protected void computeTime() { }

    @Override
    protected void computeFields() { }

    @Override
    public void add(int i, int i1) { }

    @Override
    public void roll(int i, boolean b) { }

    @Override
    public int getMinimum(int i) {
        return 0;
    }

    @Override
    public int getMaximum(int i) {
        return 0;
    }

    @Override
    public int getGreatestMinimum(int i) {
        return 0;
    }

    @Override
    public int getLeastMaximum(int i) {
        return 0;
    }
}