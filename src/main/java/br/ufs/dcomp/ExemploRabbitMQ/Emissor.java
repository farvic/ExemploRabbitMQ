package br.ufs.dcomp.ExemploRabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Emissor {

  private final static String QUEUE_NAME = "minha-fila";

  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    //factory.setUri("amqp://anvmucwf:AOFmi6z64Jv7USJidYyuNhFvzbI3o6WV@mosquito.rmq.cloudamqp.com/anvmucwf"); //Ariel
    factory.setUri("amqp://psbewhvz:s9vIx8znYBvSvy3L0yewQZWlPSkoU_ss@fly.rmq.cloudamqp.com/psbewhvz"); //Neyara
    //factory.setUri("amqp://kusswacx:J372cualNgTdoneGQmlab4hbc-J3c-Ty@fly.rmq.cloudamqp.com/kusswacx""); //Eu
    
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    String message = "Vms tc?";
    
    /*Publicando em uma fila
    * "": exchange não especificado
    * QUEUE_NAME: fila
    * message.getBytes(): mensagem como vetor de bytes
    */
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
    System.out.println(" [x] Mensagem enviada: '" + message + "'");

    channel.close();
    connection.close();
  }
}