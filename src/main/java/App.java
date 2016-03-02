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

      model.put("stylists", Stylist.all());
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

      String stringStylistId = request.queryParams("stylistId");
      int stylistId = Integer.parseInt(stringStylistId);
      String name = request.queryParams("client-name");
      Client newClient = new Client(name, stylistId);
      newClient.save();
      Stylist stylist = Stylist.find(stylistId);

      model.put("stylist", stylist);
      model.put("template", "templates/stylist.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("update/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String stringClientId = request.params(":id");
      int clientId = Integer.parseInt(stringClientId);
      Client client = Client.find(clientId);

      model.put("updateStatus", "");
      model.put("client", client);
      model.put("stylistClass", Stylist.class);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("update/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      int clientId = Integer.parseInt(request.params(":id"));
      String clientName = request.queryParams("client-name");
      int stylistId = Integer.parseInt(request.queryParams("stylistId"));
      Client client = Client.find(clientId);
      client.update(clientName, stylistId);

      model.put("updateStatus", "Updated");
      model.put("client", client);
      model.put("stylistClass", Stylist.class);
      model.put("stylists", Stylist.all());
      model.put("template", "templates/update.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/remove/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      int stylistId = Integer.parseInt(request.params(":id"));
      Stylist oldStylist = Stylist.find(stylistId);
      oldStylist.deleteClients();
      oldStylist.delete();

      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }

}
