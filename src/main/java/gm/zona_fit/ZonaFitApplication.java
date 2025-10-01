package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


/*@SpringBootApplication*/
public class ZonaFitApplication implements CommandLineRunner {
    @Autowired
    private IClienteServicio clienteServicio;
    private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);
    String nl = System.lineSeparator();


	public static void main(String[] args) {
        logger.info("iniciando la aplicacion");
        SpringApplication.run(ZonaFitApplication.class, args);
        logger.info("Aplicacion finalizada");
	}

    @Override
    public void run(String... args) throws Exception {
        logger.info("**** Application zona fit (gym) ****");
        ZonaFitApp();
    }

    private void ZonaFitApp(){
        Scanner consola = new Scanner(System.in);
        var salida = false;
        while (!salida){

                var opcion = mostrarMenu(consola);
                salida = ejecutarOpciones(opcion,consola);
                logger.info("");

        }
    }

    private int mostrarMenu(Scanner consola){
       logger.info(nl+"""
                1-listar clientes
                2-agregar
                3-modificar cliente
                4-eliminar
                5-buscar cliente
                6-salir
                elige una opcion:
               """);
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }

    private boolean ejecutarOpciones(int opcion,Scanner consola){
        var salida = false;

        switch (opcion){
            case 1-> {
                logger.info(nl+"1- listar cliente"+nl);
                clienteServicio.listarCliente().forEach(cliente -> logger.info(cliente.toString()+nl));
            }
            case  2->{
                logger.info(nl+"2- agregar cliente"+nl);
                logger.info(nl+"enter the name"+nl);
                var name = consola.nextLine();
                logger.info(nl+"enter the last name"+nl);
                var lastName = consola.nextLine();
                logger.info(nl+"enter the membership"+nl);
                var membership = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente();
                cliente.setNombre(name);
                cliente.setApellido(lastName);
                cliente.setMembresia(membership);
                clienteServicio.guardarCliente(cliente);
            }
            case 3->{
                logger.info(nl+"3- modificar cliente"+nl);
                logger.info(nl+"enter the id"+nl);
                var id = Integer.parseInt(consola.nextLine());
                if (clienteServicio.buscarClientePorId(id) != null) {
                    logger.info(nl+"enter the name"+nl);
                    var name = consola.nextLine();
                    logger.info(nl+"enter the last name"+nl);
                    var lastName = consola.nextLine();
                    logger.info(nl+"enter the membership"+nl);
                    var membership = Integer.parseInt(consola.nextLine());
                    var cliente = new Cliente(id,name,lastName,membership);
                    clienteServicio.guardarCliente(cliente);
                    logger.info("cliente actualizado: "+cliente+nl);
                }else {
                    logger.info("cliente no encontrado");
                }

            }
            case 4->{
                logger.info(nl+"4- eliminar cliente"+nl);
                logger.info(nl+"enter the id"+nl);
                var id = Integer.parseInt(consola.nextLine());
                if (clienteServicio.buscarClientePorId(id) != null) {
                    clienteServicio.eliminarCliente(id);
                    logger.info("cliente eliminado con exito");
                }else {
                    logger.info("cliente no encontrado");
                }
            }
            case 5->{
                logger.info(nl+"5- buscar cliente"+nl);
                logger.info(nl+"ingrse id"+nl);
                var id = Integer.parseInt(consola.nextLine());
                var cliente = clienteServicio.buscarClientePorId(id);

               if (cliente != null){
                   logger.info("cliente encontrado: "+cliente+nl);
               }else {
                   logger.info("cliente no encontrado: "+cliente+nl);
               }
            }
            case 6->{
                logger.info(nl+"salio con exito"+nl);
                salida = true;
            }
            default -> {
                logger.info("opcion no encontrada");
            }

        }


        return salida;
    }

}
