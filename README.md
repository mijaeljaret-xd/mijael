# Jesús te guía a la UPeU — Venta de pasajes (SysPasajes v3)

## Novedades de la versión 3.1

| Pedido | Qué se hizo |
|--------|-------------|
| DNI → RENIEC | Al escribir un DNI nuevo (cliente o ventanilla) los nombres se llenan solos desde RENIEC (API Decolecta; plan B eldni.com en escritorio). Web/app: `consultarDni()`; escritorio: `utils/ConsultaDNI.java`. |
| RUC opcional → SUNAT | El RUC es **opcional**: vacío = boleta, con RUC = factura. Al completar los 11 dígitos se consulta SUNAT y aparece la **razón social**, el **estado** (ACTIVO), la **condición** (HABIDO) y la dirección fiscal tal como están registrados. Web/app: `consultarRuc()` y `cajaRuc()`; escritorio: `utils/ConsultaRUC.java`. |
| Aviso automático al dueño | Al pagar, se manda al celular del taxista/dueño del vehículo la **reserva de asiento + datos del pago** (forma de pago, N° de operación, total): en la **app** por **SMS automático** (pide permiso la primera vez; si no hay permiso, abre WhatsApp); en la **web** y el **escritorio** se abre **WhatsApp** con el mensaje listo. Desactivar: `ENVIO_AUTOMATICO = false`. |
| Token de consultas | Web/app: `TOKEN_CONSULTAS` en `web/syspasajes.html`; escritorio: `api.ruc.token` en `application.properties`. |

## Novedades de la versión 3

| Pedido | Qué se hizo |
|--------|-------------|
| 4 vehículos | 2 **autos de 5 asientos** (4 pasajeros + conductor) y 2 **vans de 11 asientos** (10 pasajeros + conductor). Para vender 5 y 11 cambia un número: `TIPOS` en `web/syspasajes.html` y `enums/TipoVehiculo.java`. |
| Nuevos taxistas | TAX-101 **Mijael Jaret Quispe Vargas** (917651371) · TAX-202 **Álvaro Layme Urviola** (940997634) · VAN-303 **Heans Gary Livisi Huaynasi** (992754434) · VAN-404 **Ronaldo Pablo Sucasaca Mamani** (940375781), que además es el **administrador**. |
| Aviso al taxista | Cada compra crea un **mensaje de reserva de asiento** para el taxista del vehículo: le aparece en su pestaña *Avisos* (con sonido y notificación del celular en la app) y se le puede reenviar por **WhatsApp o SMS** con un toque. En escritorio se guarda en la tabla `notificacion` y hay botón WhatsApp. |
| Colores | Todo en **azul, blanco y dorado** (web, app, tema de escritorio por defecto `css/tema-azul-dorado.css`, croquis de asientos, logo). |
| Imprimir boleta y factura | Botón **Imprimir** en formato **ticket** (ticketera 58/80 mm) u **hoja A4**. En la app usa la impresión de Android (o PDF); en escritorio, el diálogo de impresión de Java. |
| App Android | Carpeta `android/` (ver `android/LEEME.md`). El APK se genera con GitHub Actions o Android Studio. |
| Sitio web | `web/sitio/` listo para subir a cualquier hosting (GitHub Pages, Netlify, Firebase Hosting); se instala en el celular con «Agregar a pantalla de inicio». |

### Usuarios (las contraseñas se entregan aparte)
| Perfil | Usuario | Vehículo |
|---|---|---|
| Administrador + taxista | `Ronaldo` | VAN-404 |
| Taxista | `mijael` | TAX-101 |
| Taxista | `alvaro` | TAX-202 |
| Taxista | `heans` | VAN-303 |

> Escritorio: la base ahora es `./data/syspasajes_v3` (se crea sola con la nueva flota).
> Web/app en modo de práctica: los datos se guardan con la clave `jtg_upeu_v3`.

---

## Versión anterior (v2.1)

Sistema de escritorio (JavaFX 21 + H2) para vender pasajes en la ruta
**Universidad UPeU ↔ C.C. Plaza de Armas** (ida y vuelta).
Arquitectura igual al ejemplo SysVentas del profesor:
`Model → Repository (JDBC + SqlHelper) → Service → Controller → View (FXML)`,
con inyección de dependencias manual en `AppContext`.

Incluye además una **versión web** (`web/syspasajes.html`) que se abre en Google Chrome.

## Novedades de la versión 2

| Pedido | Qué se hizo |
|--------|-------------|
| Compra de pasajes con DNI | El cliente se busca/registra con su DNI (obligatorio) antes de elegir asientos. |
| Modos de pago | Efectivo (monto recibido y vuelto), Yape, Plin, Tarjeta y Transferencia (N° de operación). |
| Boleta o factura | Boleta `B001-…` con DNI o Factura `F001-…` con RUC (11 dígitos) y razón social. Correlativo propio por serie. |
| Flota mixta | 5 vehículos: 3 taxis de 4 asientos y 2 vans de 10 pasajeros, cada uno con su taxista. |
| RUC automático | Al elegir factura se propone el RUC 10 del cliente (DNI + dígito verificador) y su nombre; un RUC 20 ya usado trae su razón social. Se valida el dígito verificador. |
| Logo y nombre | Logo de Jesús y nombre «Jesús te guía a la UPeU» en el ingreso, la ventana principal y los comprobantes. |
| Comentarios | Cada archivo empieza con un recuadro que explica para qué sirve. |
| Todo se puede modificar | CRUD de Marca, Producto, Vehículos y conductores, Salidas, Cliente y Venta carrito. Al cambiar el precio de un Producto se puede aplicar a las salidas futuras. |
| Módulos Cliente, Marca, Producto, Venta carrito | Cada uno con Model, Repository, Service, ServiceImp, Controller y FXML (ver carpeta `modulos_separados`). |

## Novedades (panel del cliente y RUC)

| Pedido | Qué se hizo |
|--------|-------------|
| Panel del cliente | En el ingreso hay un botón **«Soy cliente: comprar mi pasaje»**. El cliente entra solo con su DNI (si es nuevo se registra; los nombres salen de RENIEC), elige salida y asiento, revisa su carrito y paga con Yape, Plin, tarjeta o transferencia. Recibe su boleta o factura. Archivo: `controller/PanelClienteController.java`. Las compras quedan a nombre del usuario interno `autoservicio` (inactivo, no puede iniciar sesión). |
| Búsqueda por RUC | Al escribir los 11 dígitos del RUC en la factura se llena sola la razón social desde SUNAT (API Decolecta). Funciona en *Vender pasaje* y en el panel del cliente. Archivo: `utils/ConsultaRUC.java`; el token está en `application.properties` (`api.ruc.token`). |

## Acceso

Hay tres formas de ingresar (v3: Ronaldo entra como administrador y también ve su van en «Mi vehículo»):

| Perfil | Cómo ingresa | Qué puede hacer |
|--------|--------------|-----------------|
| Cliente | Solo su DNI (versión web) | Ve sus datos, elige auto (5 asientos) o van (11 asientos), paga y genera boleta o factura |
| Taxista (usuario) | Usuario y contraseña | Clientes, cobrar pasaje, carrito, historial; en la web además ve sus salidas y pasajeros |
| Administrador | Usuario y contraseña | Todo: marcas, productos, vehículos y taxistas, salidas, clientes, ventas |

Las contraseñas se entregan aparte: no aparecen en la pantalla de ingreso.

> La base de datos se crea en `./data/syspasajes_v2.mv.db`. Si ya la tenías de la versión anterior,
> bórrala para que se carguen los 5 vehículos mixtos y los nuevos usuarios.

## Cómo ejecutarlo

1. Abrir la carpeta `SysPasajesUPeU` en IntelliJ IDEA (proyecto Maven) con **JDK 21**.
2. Esperar a que Maven descargue las dependencias.
3. Ejecutar `pe.edu.upeu.syspasajes.App` (o `mvn clean javafx:run`).

Al arrancar se cargan solos: 4 marcas (Toyota, Hyundai, Kia, Nissan), 4 productos (tarifas taxi/van
ida y vuelta), 5 vehículos mixtos con sus taxistas y las salidas de hoy y mañana.

## Menús

- **Principal**: Inicio · Cerrar sesión
- **Catálogo**: Marcas · Productos (tarifas)
- **Flota**: Vehículos y conductores · Programar salidas
- **Pasajes**: Clientes · Vender pasaje · Venta carrito (reservas) · Historial de ventas
- **Cambiar Estilo** e **Idioma** (igual que antes)

## Flujo de venta

1. **Cliente**: escribir el DNI → *Buscar* (si no existe se consulta RENIEC o se escriben los nombres) → *Registrar*.
2. **Salida**: sentido (ida/vuelta), tipo y fecha; se elige el taxi de la lista.
3. **Asientos**: clic en los asientos verdes del croquis → *Agregar asiento(s) al carrito* (reserva de 15 min).
4. **Carrito**: revisar asientos y descuento.
5. **Comprobante y pago**: Boleta o Factura (RUC + razón social), forma de pago, N° de operación o monto recibido →
   *Cobrar y emitir comprobante*. Se muestra la boleta/factura con IGV 18 %, forma de pago y vuelto.

La venta se guarda en **una sola transacción** (cabecera + boletos + limpieza del carrito).

## Versión web (enlace en Chrome)

`web/syspasajes.html` es la misma lógica en una página web: el **cliente entra con su DNI** (ve sus datos,
elige el taxi y su asiento, paga y genera su boleta o factura), y **usuario / administrador** entran con usuario y
contraseña. El enlace publicado comparte los datos entre todos los que lo abren; abierto como archivo suelto en
Chrome funciona en "modo local" (los datos quedan en ese navegador).

## Estructura

```
src/main/java/pe/edu/upeu/syspasajes
├── config        AppContext (DI), DatabaseConfig (H2 + Hikari), DatosIniciales
├── components    MapaAsientos (croquis), Toast, TableViewHelper, AutoCompleteTextField...
├── controller    Login, Maingui, Marca, Producto, Vehiculo, Viaje, Cliente, VentaCarrito, VentaPasaje, Historial
├── dto           DatosPago, VentaResumen, SessionManager...
├── enums         MetodoPago, TipoComprobante, Sentido, TipoVehiculo, EstadoViaje...
├── model         Marca, Producto, Cliente, VentaCarrito, Vehiculo, Viaje, VentaPasaje, Boleto...
├── repository    JDBC con SqlHelper / AbstractJpaRepository
├── service       interfaces + impl
└── utils         ConsultaDNI (RENIEC), UtilsX (idiomas)
src/main/resources
├── view          login, maingui, main_marca, main_producto, main_vehiculo, main_viaje,
│                 main_cliente, main_carrito, main_venta, main_historial
└── schema_pasajes.sql
web/syspasajes.html   versión web (ÚNICA fuente de la web y de la app)
web/sitio/            sitio web instalable (generado con herramientas/sincronizar_web.py)
android/              app Android (WebView + notificaciones + impresión + WhatsApp)
herramientas/         sincronizar_web.py
.github/workflows/    construir-apk.yml (APK automático en GitHub)
```
