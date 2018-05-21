package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Receptor {

  private final static String QUEUE_NAME = "minha-fila";

  public static void main(String[] argv) throws Exception {
    //Estabelecendo conex'ao TCP com o servidor AMQP
    ConnectionFactory factory = new ConnectionFactory();
    factory.setUri("amqp://kusswacx:J372cualNgTdoneGQmlab4hbc-J3c-Ty@fly.rmq.cloudamqp.com/kusswacx");
    Connection connection = factory.newConnection();
    //Canal de comunicação independente
    Channel channel = connection.createChannel();
    
    //Criar uma fila no servidor
    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    System.out.println(" [*] Esperando recebimento de mensagens...");

    Consumer consumer = new DefaultConsumer(channel) {
      //Indica o que serã feito quando a mensagem chegar
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
          throws IOException {
            //Mapeando a mensagem para uma string
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Mensagem recebida: '" + message + "'");
      }
    };
    channel.basicConsume(QUEUE_NAME, true, consumer);
  }
}