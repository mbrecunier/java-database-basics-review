import org.junit.*;
import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Awesome Hair Salon");
  }

  @Test
  public void newStylistDisplaysOnPage() {
    Stylist newStylist = new Stylist("Barbara");
    newStylist.save();
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Barbara");
  }

  @Test
  public void newClientDisplaysOnStylistPage() {
    Stylist newStylist = new Stylist("Barbara");
    newStylist.save();
    Client newClient = new Client("Mary", newStylist.getId());
    newClient.save();
    String stylistPath = String.format("http://localhost:4567/stylist/%d", newStylist.getId());
    goTo(stylistPath);
    assertThat(pageSource()).contains("Mary");
  }

  @Test
  public void stylistPageLeadsBackHome() {
    Stylist newStylist = new Stylist("Barbara");
    newStylist.save();
    String stylistPath = String.format("http://localhost:4567/stylist/%d", newStylist.getId());
    goTo(stylistPath);
    click("a", withText("Back to Main Page"));
    assertThat(pageSource()).contains("All Stylists");
  }

  @Test
  public void clickingClientNameLoadsUpdatePage() {
    Stylist newStylist = new Stylist("Barbara");
    newStylist.save();
    Client newClient = new Client("Mary", newStylist.getId());
    newClient.save();
    String stylistPath = String.format("http://localhost:4567/stylist/%d", newStylist.getId());
    goTo(stylistPath);
    click("a", withText("Mary"));
    assertThat(pageSource()).contains("Update Information For Mary");
  }
}
