import dto.CategoryDto;
import dto.ProductDto;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class JmsClient {

    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        JMSContext jmsContext = factory.createContext("jmsUser", "123");

        Destination dst = (Destination) context.lookup("jms/productQueue");

        JMSProducer producer = jmsContext.createProducer();

        ObjectMessage om = jmsContext.createObjectMessage(new ProductDto(
                null,
                "Jms Product",
                "New Product from JMS",
                new BigDecimal(123.0),
                new CategoryDto(4L, "Cat JMS", "from JMS")));

        producer.send(dst, om);
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}
