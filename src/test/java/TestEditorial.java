import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.uninorte.Articulo;
import com.uninorte.ComiteEditorial;
import com.uninorte.Edicion;
import com.uninorte.Editorial;
import com.uninorte.Persona;
import com.uninorte.Revista;

public class TestEditorial {

    @Test
    public void TestAgregarColeccion(){
        Editorial editorial = new Editorial();
        editorial.addColeccion(1, "Coleccion 1");
        assertEquals(1, editorial.getNumeroColecciones());
        editorial.addColeccion(2, "Coleccion 2");
        assertEquals(2, editorial.getNumeroColecciones());
    }

    @Test
    public void TestAgregarLibro(){
        Editorial editorial = new Editorial();
        editorial.addColeccion(1, "Coleccion 1");

        // Test para verificar que se lanza una excepcion si no se encuentra la coleccion
        // addLibro debería mandar una excepción de este tipo 
        // throw new IllegalArgumentException("No se encontro la coleccion con id " + idColeccion);
        assertThrows(IllegalArgumentException.class, () -> {
            editorial.addLibro(1, "Libro 1", 0, null);
        });        
   

        editorial.addLibro(1, "Libro 1", 1, null);
        assertEquals(1, editorial.getNumeroLibros());
        editorial.addLibro(2, "Libro 2", 1, null);
        assertEquals(2, editorial.getNumeroLibros());
    }

    @Test
    public void TestAgregarLibroEnColeccion(){
        Editorial editorial = new Editorial();
        editorial.addColeccion(0, "Coleccion 1");
        editorial.addColeccion(1, "Coleccion 2");
        editorial.addLibro(1, "Libro 1", 0, null);
        editorial.addLibro(2, "Libro 2", 0, null);
        editorial.addLibro(3, "Libro 3", 1, null);
        editorial.addLibro(4, "Libro 4", 1, null);
        editorial.addLibro(5, "Libro 5", 1, null);
        assertEquals(2, editorial.getNumeroLibrosEnColeccion(0));
        assertEquals(3, editorial.getNumeroLibrosEnColeccion(1));

        assertThrows(IllegalArgumentException.class, () -> {
            editorial.getNumeroLibrosEnColeccion(2);
        });   
    }

    @Test
    public void TestAgregarRevista(){
        Editorial editorial = new Editorial();
        editorial.addRevista(1, "Revista 1");
        editorial.addRevista(2, "Revista 2");
        assertEquals(2, editorial.getNumeroRevistas());
    }

    @Test
    public void TestEdicionRevista(){
        Editorial editorial = new Editorial();
        Revista revista = new Revista(1, "Revista 1");
        editorial.addRevista(revista);
        assertEquals(1, editorial.getNumeroRevistas());

        ComiteEditorial comite = new ComiteEditorial(
            1, 
            new Persona(1, "Presidente"));
        
        comite.addMiembro(new Persona(2, "Miembro 1"));

        Articulo articulo = new Articulo(1,
        new Persona(1, "Revisor 1"), 
        new Persona(2, "Revisor 2"));
        articulo.addAutor(new Persona(1, "Autor 1"));

        // la edición se crea con el articulo principal
        Edicion edicion = new Edicion(1, revista, comite, articulo);
        revista.addEdicion(edicion);

        assertEquals(1, revista.getNumeroEdiciones());

        assertEquals(articulo, edicion.getArticuloPrincipal());
        assertEquals(revista, edicion.getRevista());
        assertEquals(comite, edicion.getComiteEditorial());
        
    }
    
}
