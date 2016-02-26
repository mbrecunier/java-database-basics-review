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

  @Test
  public void getId_returnsInt_int() {
    Client newClient = new Client("Darlene", 1);
    assertEquals(newClient.getId(), 0);
  }

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

  @Test
  public void save_savesClientToDatabase_true() {
    Client newClient = new Client("Darlene", 1);
    newClient.save();
    assertTrue(Client.all().contains(newClient));
  }

  @Test
  public void find_returnsClientFromDatabase_true() {
    Client newClient = new Client("Darlene", 1);
    newClient.save();
    assertEquals(newClient, Client.find(newClient.getId()));
  }

  @Test
  public void update_updatesClientProperties() {
    Client newClient = new Client("Darlene", 1);
    newClient.save();
    newClient.update("Darcy", 2);
    assertEquals("Darcy", newClient.getName());
    assertEquals(2, newClient.getStylistId());
  }

}
