import org.sql2o.*;
import java.util.List;

public class Client {
  private String name;
  private int stylistId;
  private int id;

  public Client (String name, int stylistId) {
    this.name = name;
    this.stylistId = stylistId;
  }

  public static List<Client> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM clients;";
      return con.createQuery(sql).executeAndFetch(Client.class);
    }
  }
  // 
  // public String getName() {
  //   return name;
  // }
  //
  // public int getId() {
  //   return id;
  // }
  //
  // public int getStylistId() {
  //   return stylistId;
  // }
  //
  // @Override
  // public boolean equals(Object otherClient){
  //   if (!(otherClient instanceof Client)) {
  //     return false;
  //   } else {
  //     Client newClient = (Client) otherClient;
  //     return this.getName().equals(newClient.getName()) &&
  //       this.getId() == newClient.getId();
  //   }
  // }


}
