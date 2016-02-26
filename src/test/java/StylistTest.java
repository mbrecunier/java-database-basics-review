import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void stylist_instantiatesCorrectly_true() {
    Stylist newStylist = new Stylist("Barbara");
    assertTrue(newStylist instanceof Stylist);
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Stylist.all().size(), 0);
  }

  @Test
  public void getName_returnsName_string() {
    Stylist newStylist = new Stylist("Barbara");
    newStylist.getName();
  }

  @Test
  public void save_addsStylistToDatabase_true() {
    Stylist newStylist = new Stylist("Barbara");
    newStylist.save();
    assertTrue(Stylist.all().contains(newStylist));
  }

  @Test
  public void find_returnsStylistFromDatabase() {
    Stylist newStylist = new Stylist ("Barbara");
    newStylist.save();
    assertTrue(newStylist.equals(Stylist.find(newStylist.getId())));
  }

  @Test
  public void getId_returnsId() {
    Stylist newStylist = new Stylist("Barbara");
    newStylist.save();
    Stylist savedStylist = Stylist.find(newStylist.getId());
    assertTrue(newStylist.getId() == savedStylist.getId());
  }

  @Test
  public void update_changesStylistName() {
    Stylist newStylist = new Stylist("Barbara");
    newStylist.save();
    newStylist.update("Mary");
    assertEquals(newStylist.getName(), "Mary");
  }

  @Test
  public void delete_removesStylistFromDatabase() {
    Stylist newStylist = new Stylist("Barbara");
    newStylist.save();
    assertTrue(Stylist.all().contains(newStylist));
    newStylist.delete();
    assertFalse(Stylist.all().contains(newStylist));
  }


}
