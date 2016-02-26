import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ClientTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void client_instantiatesCorrectly_true() {
    Client newClient = new Client("Darlene", 1);
    assertTrue(newClient instanceof Client);
  }

  @Test
  public void getName_returnsName_string() {
    Client newClient = new Client("Darlene", 1);
    assertEquals("Darlene", newClient.getName());
  }

  @Test
  public void getStylistId_returnsStylistId_int() {
    Client newClient = new Client("Darlene", 1);
    assertEquals(1, newClient.getStylistId());
  }

  // @Test
  // public void getId_returnsInt_true() {
  //   Client newClient = new Client("Darlene", 1);
  //   assertEquals(newClient.getId(), 1);
  // }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Client.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfClientsAreTheSame() {
    Client newClient = new Client("Darlene", 1);
    Client newClient2 = new Client("Darlene", 1);
    assertTrue(newClient.equals(newClient2));
  }

}
