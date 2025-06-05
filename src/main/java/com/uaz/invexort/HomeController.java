package com.uaz.invexort;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class HomeController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;
    @Autowired
    private VentaService ventaService;
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/invexort")
    public String indexInvexort(HttpSession session) {
        String usuario = (String) session.getAttribute("usuario");
        if (usuario != null) {
            return "invexort";
        }else {
            return "redirect:/login";
        }

    }

    @GetMapping("/login")
    public String loginformulario() {
        return "login";
    }

    @GetMapping("/productos")
    public ResponseEntity<Map<String,Object>> productos(HttpSession session) {
        List<Producto> productos=productoRepository.getProductos();
        return ResponseEntity.ok(Map.of(
                "mensaje", "Listado correctamente",
                "estado", "ok",
                "productos", productos

        ));
    }

    @GetMapping("/stock")
    public ResponseEntity<Map<String,Object>> stock(HttpSession session) {
        List<Producto> productos=productoRepository.getProductosStock();
        return ResponseEntity.ok(Map.of(
                "mensaje", "Listado correctamente",
                "estado", "ok",
                "productos", productos

        ));
    }

    @PostMapping("/producto")
    public ResponseEntity<Map<String,Object>> producto(@RequestBody Map<String, Object> producto) {
        Producto producto1 = new Producto(
                producto.get("nombre").toString(),
                producto.get("categoria").toString(),
                Integer.parseInt(producto.get("stock").toString()),
                producto.get("fecha").toString(),
                Float.parseFloat(producto.get("precio").toString())
        );
        if (productoRepository.agregarProducto(producto1)>0){
            List<Producto> productos=productoRepository.getProductos();
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Producto agregado correctamente",
                    "estado", "ok",
                    "productos", productos

            ));
        }else {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "No se pudo agregar producto",
                    "estado", "nok"
            ));
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody Map<String, Object> credenciales, HttpSession session) {

        Usuario usuario=usuarioRepository.findByCredenciales(credenciales.get("correo").toString(), credenciales.get("contrasena").toString());

        if(!Objects.isNull(usuario)){
            session.setAttribute("usuario", usuario.toString());
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Acceso correcto",
                    "estado", "ok"
            ));
        }else {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Credenciales invalidos",
                    "estado", "nok"
            ));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String,Object>> logout(HttpSession session)    {
        session.removeAttribute("usuario");
        return ResponseEntity.ok(Map.of(
                "mensaje", "Sesion cerrada correctamente",
                "estado", "ok"
        ));
    }

    @PostMapping("/venta")
    public ResponseEntity<Map<String,Object>> venta(HttpSession session, @RequestBody Map<String, Object> venta) {
        Producto producto=productoRepository.getProducto(Integer.parseInt(venta.get("id").toString()));
        int idProducto=Integer.parseInt(venta.get("id").toString());
        int cantidad=Integer.parseInt(venta.get("cantidad").toString());
        System.out.println(venta.get("cantidad"));
        if (cantidad>producto.getStock()){
            return ResponseEntity.ok(Map.of(
                    "mensaje", "No se puede realizar la venta",
                    "estado", "nok"
            ));
        }else {
            int newStock=producto.getStock()-cantidad;
            productoRepository.actualizarStock(idProducto,newStock);
            float subTotal=producto.getPrecio()*cantidad;
            DetalleVenta detalleVenta=new DetalleVenta(subTotal,producto.getPrecio(),cantidad,producto.getNombre());
            detalleVentaRepository.agregarDetalleVenta(detalleVenta);
            List<DetalleVenta> ListadoDetalleVenta=detalleVentaRepository.obtenerDetalleVentas();
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Venta realizada correctamente",
                    "estado", "ok",
                    "ventas", ListadoDetalleVenta

            ));

        }
    }
    @GetMapping("/listadoventas")
    public ResponseEntity<Map<String,Object>> listadoVentas(HttpSession session) {
        List<DetalleVenta> ListadoDetalleVenta=detalleVentaRepository.obtenerDetalleVentas();
        for(DetalleVenta detalleVenta:ListadoDetalleVenta){
            System.out.println(detalleVenta.getPrecio());
        }
        return ResponseEntity.ok(Map.of(
                "mensaje", "Listado de Ventas",
                "estado", "ok",
                "ventas", ListadoDetalleVenta

        ));
    }
    @GetMapping("/ventasmes")
    public ResponseEntity<Map<String,Object>> ventasmes(HttpSession session) {
        List<VentasMes> ventasMes=ventaService.obtenerVentasMensuales();
        return ResponseEntity.ok(Map.of(
                "mensaje", "Listado de Ventas",
                "estado", "ok",
                "ventas_mes", ventasMes

        ));

    }
}
