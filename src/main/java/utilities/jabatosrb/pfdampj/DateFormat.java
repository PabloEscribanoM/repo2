package jabatosrb.pfdampj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateFormat {

    private static final SimpleDateFormat sdfFecha = new SimpleDateFormat("dd-MM-yyyy");
    private static final SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");
    private static final SimpleDateFormat sdfFechaYHora = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private static final SimpleDateFormat sdfMonth = new SimpleDateFormat("MMMM");

    public static Date fechaToDate(String fecha) throws ParseException {
        if(!fecha.equals("null"))
            return sdfFecha.parse(fecha);
        return null;
    }

    public static String fechaToString(Date fecha){
        if(fecha!=null)
            return sdfFecha.format(fecha);
        return "null";
    }

    public static Date fechaYHoraToDate(String fecha, String hora) throws ParseException {
        return sdfFechaYHora.parse(fecha + " " + hora);
    }

    public static String horaToString(Date fecha){
        return sdfHora.format(fecha);
    }

    public static String getMonth(Date fecha){
        return sdfMonth.format(fecha).toUpperCase();
    }

    public static LocalDate toLocalDate(Date fecha){
        Instant instant = null;
        instant = fecha.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        return zdt.toLocalDate();
    }

    public static Date toDate(LocalDate fecha){
        ZonedDateTime zdt = fecha.atStartOfDay(ZoneId.systemDefault());
        Instant instant = zdt.toInstant();
        return Date.from(instant);
    }

    public static int anyos(Date fecha){
        Date fechaActual = new Date();
        long diff =  fechaActual.getTime() - fecha.getTime() ;
        TimeUnit time = TimeUnit.DAYS;
        long diffDays = time.convert(diff, TimeUnit.MILLISECONDS);
        return (int)(diffDays/365);
    }

}
