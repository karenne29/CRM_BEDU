package com.example.modulo1_sesion1_postwork.runners;

import com.example.modulo1_sesion1_postwork.persistence.*;
import com.example.modulo1_sesion1_postwork.persistence.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ClientesRunner implements CommandLineRunner {

    private final ClientesRepository clientesRepository;
    private final VisitaRepository visitaRepository;
    private final ClientesRepository cR;
    private final ProductoRepository productoRepository;
    private final EtapaRepository etapaRepository;
    private final VentaRepository ventaRepository;


    @Override
    public void run(String... args) throws Exception {

            Cliente cliente = crearCliente("Juan", "juan@metroflog.com", "10", "Cesar Madero");
            Cliente cliente1 = crearCliente("Anna", "juan@metroflog.com", "10", "Cesar Madero");
            Cliente cliente2 = crearCliente("Dennis", "juan@metroflog.com", "10", "Cesar Madero");
            Cliente cliente3 = crearCliente("Citlali", "juan@metroflog.com", "10", "Cesar Madero");
            Cliente cliente4 = crearCliente("Juana", "juan@metroflog.com", "10", "Cesar Madero");
            Cliente cliente5 = crearCliente("Katy", "juan@metroflog.com", "10", "Cesar Madero");

            List<Cliente> list = Arrays.asList(cliente,cliente1,cliente2,cliente3,cliente4,cliente5);
            clientesRepository.saveAll(list);

            Etapa etapaModel = crearEtapa("Etapa 1", 1);
            Etapa etapaModel2 = crearEtapa("Etapa 2", 2);
            Etapa etapaModel3 = crearEtapa("Etapa 3", 3);
            Etapa etapaModel4 = crearEtapa("Etapa 4", 4);
            Etapa etapaModel5 = crearEtapa("Etapa 5", 5);

            List<Etapa> etapaList = Arrays.asList(etapaModel,etapaModel2,etapaModel3,etapaModel4,etapaModel5);
            etapaRepository.saveAll(etapaList);

            Producto producto = crearProducto("Coca Cola", "Bebidas", (float) 17.50, "00001", LocalDate.now());
            Producto producto1 = crearProducto("Pepsi", "Bebidas", (float) 17.50, "00001", LocalDate.now());
            Producto producto2 = crearProducto("Galletas Maria", "Bebidas", (float) 17.50, "00001", LocalDate.now());
            Producto producto3 = crearProducto("Chetos Flaming", "Bebidas", (float) 17.50, "00001", LocalDate.now());
            Producto producto4 = crearProducto("Caguama", "Bebidas", (float) 17.50, "00001", LocalDate.now());

            List<Producto> productoList = Arrays.asList(producto,producto1,producto2,producto3,producto4);
            productoRepository.saveAll(productoList);

            Visita visita = crearVisita(clientesRepository.findById(1L).get(), LocalDateTime.now(),"Direccion 1","Acordar precios de mayoreo", "Juanito");
            Visita visita1 = crearVisita(clientesRepository.findById(2L).get(),LocalDateTime.now(),"Direccion 2","Acordar precios de menudeo", "Juanito");
            Visita visita2 = crearVisita(clientesRepository.findById(3L).get(),LocalDateTime.now(),"Direccion 3","Acordar precios de compra", "Juanito");
            Visita visita3 = crearVisita(clientesRepository.findById(4L).get(),LocalDateTime.now(),"Direccion 4","Acordar precios de prica fresas", "Juanito");
            Visita visita4 = crearVisita(clientesRepository.findById(5L).get(),LocalDateTime.now(),"Direccion 5","Carnita Asada", "Juanito");
            List<Visita> visitaList = Arrays.asList(visita,visita1,visita2,visita3,visita4);
            visitaRepository.saveAll(visitaList);

            Venta venta = crearVenta(0, productoList, clientesRepository.findById(1L).get(),LocalDateTime.now());
            Venta venta1 = crearVenta(0, productoList, clientesRepository.findById(2L).get(),LocalDateTime.now());
            Venta venta2 = crearVenta(0, productoList, clientesRepository.findById(3L).get(),LocalDateTime.now());
            Venta venta3 = crearVenta(0, productoList, clientesRepository.findById(4L).get(),LocalDateTime.now());
            Venta venta4 = crearVenta(0, productoList, clientesRepository.findById(5L).get(),LocalDateTime.now());
            List<Venta> ventaList = Arrays.asList(venta, venta1, venta2, venta3, venta4);
            ventaRepository.saveAll(ventaList);
//        }catch (Exception e){
//            System.out.println("------ERRORRRRR---------");
//            System.out.println(e.toString());
//


    }

    private Cliente crearCliente(String nombre, String correo, String numEmpleados, String direccion){
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setCorreoContacto(correo);
        cliente.setNumeroEmpleados(numEmpleados);
        cliente.setDireccion(direccion);

        return cliente;
    }

    private Etapa crearEtapa(String nombre, Integer orden){
        Etapa etapa = new Etapa();
        etapa.setNombre(nombre);
        etapa.setOrden(orden);
        return etapa;
    }

    private Producto crearProducto(String nombre, String categoria, float precio, String numeroRegistro, LocalDate fechaCreacion){
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setNumeroRegistro(numeroRegistro);
        producto.setFechaCreacion(fechaCreacion);
        return producto;
    }

    private Venta crearVenta(float monto, List<Producto> productoList, Cliente cliente, LocalDateTime localDateTime){
        Venta venta = new Venta();
        float total = 0;
        for(Producto value: productoList){
            total += value.getPrecio();
        }
        venta.setMonto(total);
        venta.setProductos(productoList);
        venta.setCliente(cliente);
        venta.setFechaCreacion(localDateTime);
        return venta;
    }

    private Visita crearVisita(Cliente cliente, LocalDateTime fechaProgramada, String direccion, String proposito, String vendedor){
        Visita visita = new Visita();
        visita.setCliente(cliente);
        visita.setFechaProgramada(fechaProgramada);
        visita.setDireccion(direccion);
        visita.setProposito(proposito);
        visita.setVendedor(vendedor);
        return visita;
    }
}
