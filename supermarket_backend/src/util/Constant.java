package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Constant {
      public static final int SERVER_PORT=8080;
      public static final String RESOURCE_PATH = "/assets/upload";
      public static final String HOST = "localhost";
      public static final String WAR_FILE_NAME = "supermarket_war_exploded";

      public static Date convertParamToDate(Object orderDateObject) throws Exception {
            if (orderDateObject != null && !orderDateObject.toString().isEmpty()) {
                  String orderDateString = orderDateObject.toString();

                  try {

                        LocalDate localDate = LocalDate.parse(orderDateString, DateTimeFormatter.ISO_DATE);
                        Date orderDate = java.sql.Date.valueOf(localDate);
                        return orderDate;
                  } catch (Exception e) {
                        throw new Exception(e.getMessage());
                  }
            } else {
                  throw new Exception("date null or empty");
            }
      }
}
