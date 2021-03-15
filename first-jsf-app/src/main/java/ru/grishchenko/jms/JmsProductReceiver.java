package ru.grishchenko.jms;

import dto.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.grishchenko.services.ProductService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/productQueue")
        }
)
public class JmsProductReceiver implements MessageListener {

    @EJB
    private ProductService productService;

    private final Logger logger = LoggerFactory.getLogger(JmsProductReceiver.class);

    @Override
    public void onMessage(Message message) {
        logger.info("New JMS message Recieved");

        if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;

            try {
                productService.saveOrUpdate((ProductDto) om.getObject());
            } catch (JMSException e) {
                logger.error("ERROR message Cast", e);
            }
        }

    }
}
