/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.app.services;

import com.example.app.configuration.DatabaseLoader;
import com.example.app.configuration.IProductoService;
import com.example.app.entities.Producto;
import com.example.app.products.ProductRepository;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Gonzalo
 */
@RunWith(SpringRunner.class)
public class ProductoServiceTests {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DatabaseLoader databaseLoader;

    @Before
    public void init() {
        List<Producto> productos = new ArrayList<>();
        
        Producto p1 = new Producto(1, "hola", "hola", 11);
        Producto p2 = new Producto(2, "aaa", "aaa", 12);
        Producto p3 = new Producto(3, "bbb", "bbb", 13);
        Producto p4 = new Producto(4, "ccc", "ccc", 14);

        productos.add(p1);
        productos.add(p2);
        productos.add(p3);
        productos.add(p4);
        when(productRepository.findAll()).thenReturn(productos);
    }

    @Test
    public void getProductsTest() {
        ArrayList<Producto> listado = databaseLoader.findAll();
        assertEquals(listado.size(), 4);
        verify(productRepository).findAll();
    }
    
    @Test
    public void getOneProduct(){
        Producto product = databaseLoader.getProducto(4);
        assertEquals(product, databaseLoader.getProducto(4));
    }


    @Test
    public void deleteProduct() {
        databaseLoader.delete(2);
        verify(productRepository, times(1)).delete(2);
    }

    @Test
    public void editProduct() {
        Producto p3 = new Producto(3, "ccc", "ccc", 70);
        databaseLoader.edit(p3);
        verify(productRepository).save(p3);
    }
    
    @Test
    public void saveProduct(){
        Producto p5 = new Producto(5, "ddd", "ddd", 15);
        databaseLoader.save(p5);
        verify(productRepository).save(p5);
    }
    
    @Test
    public void findAll() {
        ArrayList<Producto> productos = new ArrayList<>();

        for (Producto p : databaseLoader.findAll()) {
            System.out.println(p.getDescripcion());
        }
       
    }

}
