import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class StylistTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

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


}
