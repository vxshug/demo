package site.shug.spring.javaweb;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        // 手动创建Connector, 如果要tomcat创建默认Connector, 需要调用tomcat.getConnector()才会创建
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        Context context = tomcat.addWebapp("", new File("javaweb/build/libs/javaweb-1.0-SNAPSHOT.war").getAbsolutePath());
//        Tomcat.addServlet(context, "MVC", new HttpServlet() {
//            @Override
//            protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//                PrintWriter writer = resp.getWriter();
//                writer.write("<html><body>");
//                writer.write("<h1>Hello World!</h1>");
//                writer.write("</body></html>");
//                writer.flush();
//                writer.close();
//            }
//        });
//        context.addServletMappingDecoded("/*", "MVC");
        tomcat.start();
        tomcat.getServer().await();
    }
}