import org.sql2o.*;
import java.util.List;

public class Stylist {
  private String name;
  private int id;

  public Stylist (String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }


  public static List<Stylist> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM stylists;";
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }


}
