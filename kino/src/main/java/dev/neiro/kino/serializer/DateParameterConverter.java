package dev.neiro.kino.serializer;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ParamConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Wayne Stark
 * @since 18.10.2022
 */
public class DateParameterConverter implements ParamConverter<Date> {

    public static final String format = "yyyy-MM-dd";

    @Override
    public Date fromString(String string) {
        if ((string == null) || string.isEmpty() || "null".equalsIgnoreCase(string))
            return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(string);
        } catch (ParseException ex) {
            throw new WebApplicationException(ex);
        }
    }

    @Override
    public String toString(Date t) {
        return new SimpleDateFormat(format).format(t);
    }
}
