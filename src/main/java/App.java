import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String name = request.queryParams("stylist-name");
      Stylist newStylist = new Stylist(name);
      newStylist.save();

      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String id = request.params(":id");
      Stylist stylist = Stylist.find(Integer.parseInt(id));
      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/stylist/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String stylistId = request.queryParams("stylistId");
      String name = request.queryParams("client-name");
      Client newClient = new Client(name, Integer.parseInt(stylistId));
      Stylist stylist = Stylist.find(Integer.parseInt(stylistId));
      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }

}
